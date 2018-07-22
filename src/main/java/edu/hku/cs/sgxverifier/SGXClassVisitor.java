package edu.hku.cs.sgxverifier;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.ASM6;

/**
 * Created by max on 21/7/2018.
 */
public class SGXClassVisitor extends ClassVisitor {

    VirtualMachine vm = VirtualMachine.getVM();
    public SGXClassVisitor() {
        super(ASM6);
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        super.visit(version, access, name, signature, superName, interfaces);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        System.out.println("visit method " + name);
        return new SGXMethodVisitor(super.visitMethod(access, name, descriptor, signature, exceptions));
    }
}
