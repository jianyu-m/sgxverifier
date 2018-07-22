package edu.hku.cs.sgxverifier;


import org.objectweb.asm.Handle;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.*;

/**
 * Created by max on 22/7/2018.
 */
public class SGXMethodVisitor extends MethodVisitor {

    private VirtualMachine vm = VirtualMachine.getVM();
    private int nums_locals = 0;
    void println(String s) {
        System.out.println(s);
    }

    public SGXMethodVisitor(MethodVisitor methodVisitor) {
        super(ASM6, methodVisitor);
    }

    @Override
    public void visitLocalVariable(String name, String descriptor, String signature, Label start, Label end, int index) {
        println("visit local " + index);
        nums_locals += 1;
    }

    @Override
    public void visitIntInsn(int opcode, int operand) {
        switch (opcode) {
            case BIPUSH:
                println("BIPUSH");
            case SIPUSH:
                println("SIPUSH");
        }
    }

    @Override
    public void visitVarInsn(int opcode, int var) {
        switch (opcode) {
            case ILOAD:
            case LLOAD:
            case FLOAD:
            case DLOAD:
            case ALOAD:
                println("load " + var);
                break;
            case ISTORE:
            case LSTORE:
            case FSTORE:
            case DSTORE:
            case ASTORE:
                println("store " + var);
                break;
            case RET:
                println("ret " + var);
        }
    }

    @Override
    public void visitTypeInsn(int opcode, String type) {
        switch (opcode) {
            case NEW:
            case ANEWARRAY:
            case CHECKCAST:
            case INSTANCEOF:
                println("type ");
                break;
        }
    }

    @Override
    public void visitFieldInsn(int opcode, String owner, String name, String descriptor) {
        switch (opcode) {
            case GETSTATIC:
            case PUTSTATIC:
            case GETFIELD:
            case PUTFIELD:
                println("field ");
                break;
        }
    }

    @Override
    public void visitMethodInsn(int opcode, String owner, String name, String descriptor, boolean isInterface) {
        switch (opcode) {
            case INVOKEVIRTUAL:
            case INVOKESPECIAL:
            case INVOKESTATIC:
            case INVOKEINTERFACE:
                println("method call");
                break;
        }
    }

    @Override
    public void visitInvokeDynamicInsn(String name, String descriptor, Handle bootstrapMethodHandle, Object... bootstrapMethodArguments) {
        println("not supported");
    }

    @Override
    public void visitJumpInsn(int opcode, Label label) {
        println("not supported");
    }
}
