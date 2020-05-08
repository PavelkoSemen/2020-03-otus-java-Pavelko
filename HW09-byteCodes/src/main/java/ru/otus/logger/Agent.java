package ru.otus.logger;

import org.objectweb.asm.*;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

public class Agent {
    public static void premain(String agentArgs, Instrumentation inst) {
        inst.addTransformer(new ClassFileTransformer() {
            @Override
            public byte[] transform(ClassLoader loader, String className,
                                    Class<?> classBeingRedefined,
                                    ProtectionDomain protectionDomain,
                                    byte[] classfileBuffer) throws IllegalClassFormatException {
                if (className.equals("ru/otus/logger/TestClass")) {
                    return addProxyMethod(classfileBuffer);
                }
                return classfileBuffer;

            }
        });
    }

    private static byte[] addProxyMethod(byte[] originalClass) {
        ClassReader cr = new ClassReader(originalClass);
        ClassWriter cw = new ClassWriter(cr, ClassWriter.COMPUTE_MAXS);
        MyClassVisitor cv = new MyClassVisitor(cw);

        cr.accept(cv, Opcodes.ASM5);


        byte[] finalClass = cw.toByteArray();

        try (OutputStream fos = new FileOutputStream("loggerASM.class")) {
            fos.write(finalClass);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return finalClass;
    }

}
