package ru.otus.logger;

import org.objectweb.asm.*;
import org.objectweb.asm.commons.AdviceAdapter;

import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.Arrays;
import java.util.List;

import static org.objectweb.asm.Opcodes.H_INVOKESTATIC;

public class MyClassVisitor extends ClassVisitor {
    public MyClassVisitor(ClassVisitor cv) {
        super(Opcodes.ASM5, cv);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {

        List<Type> methodArgumentTypes =List.of(Type.getArgumentTypes(descriptor));
        MethodVisitor methodVisitor = cv.visitMethod(access, name, descriptor, signature, exceptions);

        return new AdviceAdapter(Opcodes.ASM5, methodVisitor, access, name, descriptor) {
            boolean isAnnotation = false;
            String newDescriptor;

            @Override
            public AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {

                if (descriptor.contains("newAnnotation/Log")) {
                    isAnnotation = true;
                }
                return super.visitAnnotation(descriptor, visible);
            }

            @Override
            protected void onMethodEnter() {

                if (isAnnotation) {
                    Type.getMethodDescriptor(Type.VOID_TYPE,methodArgumentTypes.toArray(new Type[0]));
                    StringBuilder stringBuilder = new StringBuilder("(Ljava/lang/String;");
                    visitVarInsn(Opcodes.ALOAD, 0);
                    visitLdcInsn(name);
                    for (int i = 0; i < methodArgumentTypes.size(); i++) {
                        visitVarInsn(MyClassVisitor.getOpcodesForVarInsn(methodArgumentTypes.get(i)), i + 1);
                        //stringBuilder.append(methodArgumentTypes[i]);
                    }
                    newDescriptor = stringBuilder.append(")V").toString();
                    visitMethodInsn(Opcodes.INVOKEVIRTUAL, "ru/otus/logger/TestClass", "LOG_" + name, newDescriptor, false);
                }
            }

            @Override
            public void visitEnd() {
                if (isAnnotation) {
                    createNewLogMethod(access, "LOG_" + name, newDescriptor, signature, exceptions, cv);
                }
                super.visitEnd();
            }
        };
    }

    private static void createNewLogMethod(int access, String name, String descriptor, String signature, String[] exceptions, ClassVisitor cv) {
        Type[] methodArgumentTypes = Type.getArgumentTypes(descriptor);
        StringBuilder stringBuilderForConcat = new StringBuilder("(");
        String formatParam = " param: " + "(\u0001)".repeat(methodArgumentTypes.length - 1);

        MethodVisitor methodVisitor = cv.visitMethod(access, name, descriptor, null, null);

        Handle handle = new Handle(
                H_INVOKESTATIC,
                Type.getInternalName(java.lang.invoke.StringConcatFactory.class),
                "makeConcatWithConstants",
                MethodType.methodType(CallSite.class, MethodHandles.Lookup.class, String.class, MethodType.class, String.class, Object[].class).toMethodDescriptorString(),
                false);

        methodVisitor.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");

        for (int i = 0; i < methodArgumentTypes.length; i++) {
            methodVisitor.visitVarInsn(MyClassVisitor.getOpcodesForVarInsn(methodArgumentTypes[i]), i + 1);
            stringBuilderForConcat.append(methodArgumentTypes[i]);
        }
        String newDescriptorForConcat = stringBuilderForConcat.append(")Ljava/lang/String;").toString();

        methodVisitor.visitInvokeDynamicInsn("makeConcatWithConstants", newDescriptorForConcat, handle, "Method name:\u0001" + formatParam);

        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);

        methodVisitor.visitInsn(Opcodes.RETURN);
        methodVisitor.visitMaxs(0, 0);
        methodVisitor.visitEnd();

    }

    private static int getOpcodesForVarInsn(Type typeDescriptor) {
        switch (typeDescriptor.toString()) {
            case "Z":
            case "C":
            case "B":
            case "S":
            case "I":
                return Opcodes.ILOAD;

            case "F":
                return Opcodes.FLOAD;
            case "J":
                return Opcodes.LLOAD;
            case "D":
                return Opcodes.DLOAD;
            default:
                return Opcodes.ALOAD;
        }
    }
}