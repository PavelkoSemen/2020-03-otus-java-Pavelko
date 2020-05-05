package ru.otus.logger;

import org.objectweb.asm.*;
import org.objectweb.asm.commons.AdviceAdapter;

public class MyClassVisitor extends ClassVisitor {
    public MyClassVisitor(ClassVisitor cv) {
        super(Opcodes.ASM5, cv);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {

        MethodVisitor methodVisitor = cv.visitMethod(access, name, descriptor, signature, exceptions);
        return new AdviceAdapter(Opcodes.ASM5, methodVisitor, access, name, descriptor) {
            boolean isAnnotation = false;

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
                    visitVarInsn(Opcodes.ALOAD, 0);
                    visitLdcInsn(name);
                    visitVarInsn(Opcodes.ILOAD, 1);
                    visitMethodInsn(Opcodes.INVOKEVIRTUAL, "ru/otus/logger/TestClass", "log", "(Ljava/lang/String;I)V", false);
                }
            }
        };
    }
}
