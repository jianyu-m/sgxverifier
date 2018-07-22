package edu.hku.cs.sgxverifier;


import org.objectweb.asm.Handle;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

import java.util.Hashtable;

import static org.objectweb.asm.Opcodes.*;

/**
 * Created by max on 22/7/2018.
 */
public class SGXMethodVisitor extends MethodVisitor {

    private VirtualMachine vm = VirtualMachine.getVM();
    Hashtable<Integer, JObject> locals = new Hashtable<Integer, JObject>();
    void println(String s) {
        System.out.println(s);
    }

    public SGXMethodVisitor(MethodVisitor methodVisitor) {
        super(ASM6, methodVisitor);
    }

    @Override
    public void visitInsn(int opcode) {
        switch (opcode) {
            case NOP:
                break;
            case ACONST_NULL:
            case ICONST_M1:
            case ICONST_0:
            case ICONST_1:
            case ICONST_2:
            case ICONST_3:
            case ICONST_4:
            case ICONST_5:
            case LCONST_0:
            case LCONST_1:
            case FCONST_0:
            case FCONST_1:
            case FCONST_2:
            case DCONST_0:
            case DCONST_1:
                vm.push(new JObject("p", new JValue(JValue.PlainText, JValue.Memory.Enclave)));
                break;
            case IALOAD:
            case LALOAD:
            case FALOAD:
            case DALOAD:
            case AALOAD:
            case BALOAD:
            case CALOAD:
            case SALOAD:
                int index = (Integer)vm.pop().object_value.value;
                JObject arr_obj = vm.pop();
                vm.push(arr_obj.getfield(String.valueOf(index)));
                break;
            case IASTORE:
            case LASTORE:
            case FASTORE:
            case DASTORE:
            case AASTORE:
            case BASTORE:
            case CASTORE:
            case SASTORE:
                JObject tobj = vm.pop();
                int index2 = (Integer)vm.pop().object_value.value;
                JObject arr_obj2 = vm.pop();
                arr_obj2.putfield(String.valueOf(index2), tobj);
            case POP:
                vm.pop();
                break;
            case POP2:
                vm.pop();
                vm.pop();
                break;
            case DUP:
                JObject dobj = vm.pop();
                vm.push(dobj);
                vm.push(dobj);
                break;
            case DUP_X1:
                JObject wobj1 = vm.pop();
                JObject wobj2 = vm.pop();
                vm.push(wobj1);
                vm.push(wobj2);
                vm.push(wobj1);
                break;
            case DUP_X2:
                JObject wwobj1 = vm.pop();
                JObject wwobj2 = vm.pop();
                JObject wwobj3 = vm.pop();
                vm.push(wwobj1);
                vm.push(wwobj3);
                vm.push(wwobj2);
                vm.push(wwobj1);
                break;
            case DUP2:
                JObject dwobj1 = vm.pop();
                JObject dwobj2 = vm.pop();
                vm.push(dwobj2);
                vm.push(dwobj1);
                vm.push(dwobj2);
                vm.push(dwobj1);
                break;
            case DUP2_X1:
                JObject ddwwobj1 = vm.pop();
                JObject ddwwobj2 = vm.pop();
                JObject ddwwobj3 = vm.pop();
                vm.push(ddwwobj2);
                vm.push(ddwwobj1);
                vm.push(ddwwobj3);
                vm.push(ddwwobj2);
                vm.push(ddwwobj1);
                break;
            case DUP2_X2:
                JObject dddwwobj1 = vm.pop();
                JObject dddwwobj2 = vm.pop();
                JObject dddwwobj3 = vm.pop();
                JObject dddwwobj4 = vm.pop();
                vm.push(dddwwobj2);
                vm.push(dddwwobj1);
                vm.push(dddwwobj4);
                vm.push(dddwwobj3);
                vm.push(dddwwobj2);
                vm.push(dddwwobj1);
                break;
            case SWAP:
                JObject swap1 = vm.pop();
                JObject swap2 = vm.pop();
                vm.push(swap1);
                vm.push(swap2);
                break;
            case IADD:
            case LADD:
            case FADD:
            case DADD:
            case ISUB:
            case LSUB:
            case FSUB:
            case DSUB:
            case IMUL:
            case LMUL:
            case FMUL:
            case DMUL:
            case IDIV:
            case LDIV:
            case FDIV:
            case DDIV:
            case IREM:
            case LREM:
            case FREM:
            case DREM:
            case INEG:
            case LNEG:
            case FNEG:
            case DNEG:
            case ISHL:
            case LSHL:
            case ISHR:
            case LSHR:
            case IUSHR:
            case LUSHR:
            case IAND:
            case LAND:
            case IOR:
            case LOR:
            case IXOR:
            case LXOR:
                JObject first = vm.pop();
                JObject second = vm.pop();
                JValue fv = first.object_value;
                JValue sv = second.object_value;
                JValue value = new JValue(fv.taint | sv.taint, JValue.Memory.Enclave);
                JObject obj = new JObject("p", value);
                vm.push(obj);
                break;
            case I2L:
            case I2F:
            case I2D:
            case L2I:
            case L2F:
            case L2D:
            case F2I:
            case F2L:
            case F2D:
            case D2I:
            case D2L:
            case D2F:
            case I2B:
            case I2C:
            case I2S:
                println("cast");
                break;
            case LCMP:
            case FCMPL:
            case FCMPG:
            case DCMPL:
            case DCMPG:
                println("cmp");
                break;
            case IRETURN:
            case LRETURN:
            case FRETURN:
            case DRETURN:
            case ARETURN:
                vm.frame_return(true);
                break;
            case RETURN:
                vm.frame_return(false);
                break;
            case ARRAYLENGTH:
                vm.pop();
                vm.push(new JObject("p", new JValue(JValue.PlainText, JValue.Memory.Enclave)));
                break;
            case ATHROW:
            case MONITORENTER:
            case MONITOREXIT:
                println("other");
                break;
        }
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
                JObject new_top = locals.get(var);
                vm.push(new_top);
                break;
            case ISTORE:
            case LSTORE:
            case FSTORE:
            case DSTORE:
            case ASTORE:
                JObject get_obj = vm.pop();
                locals.put(var, get_obj);
                break;
            case RET:
                println("ret " + var);
        }
    }

    @Override
    public void visitTypeInsn(int opcode, String type) {
        switch (opcode) {
            case NEW:
                JObject new_obj = vm.malloc_enclave(type);
                vm.push(new_obj);
                break;
            case ANEWARRAY:
                JObject new_arr_obj = vm.malloc_enclave(type);
                vm.push(new_arr_obj);
                break;
            case CHECKCAST:
            case INSTANCEOF:
                break;
        }
    }

    @Override
    public void visitFieldInsn(int opcode, String owner, String name, String descriptor) {
        switch (opcode) {
            case GETSTATIC:
                JClass gclass = JClass.resolve(owner);
                JObject ptop = gclass.getfield(name);
                vm.push(ptop);
                break;
            case PUTSTATIC:
                JClass gclass2 = JClass.resolve(owner);
                JObject pfield = vm.pop();
                gclass2.putfield(name, pfield);
                break;
            case GETFIELD:
                JObject obj = vm.pop();
                vm.push(obj.getfield(name));
                break;
            case PUTFIELD:
                JObject tobj = vm.pop();
                JObject iobj = vm.pop();
                tobj.putfield(name, iobj);
                break;
        }
    }

    static int parameter_count(String desc) {
        int i = 0;
        int par_count = 0;
        while (i < desc.length()) {
            switch (desc.charAt(i)) {
                case '(':
                    i += 1;
                    break;
                case ')':
                    i = desc.length();
                    break;
                case 'Z':
                case 'C':
                case 'B':
                case 'S':
                case 'I':
                case 'F':
                case 'J':
                case 'D':
                    i += 1;
                    par_count += 1;
                    break;
                case 'L':
                    for (;i < desc.length() && desc.charAt(i) != ';';i++) ;
                    par_count += 1;
                    break;
            }
        }
        return par_count;
    }

    @Override
    public void visitMethodInsn(int opcode, String owner, String name, String descriptor, boolean isInterface) {
        JClass c = JClass.resolve(owner);
        switch (opcode) {
            case INVOKEVIRTUAL:
            case INVOKEINTERFACE:
            case INVOKESPECIAL:
                c.classVisitor.execute_method(name, descriptor, true);
                break;
            case INVOKESTATIC:
                c.classVisitor.execute_method(name, descriptor, false);
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

    @Override
    public void visitLdcInsn(Object value) {
        println("ldc");
    }

    @Override
    public void visitIincInsn(int var, int increment) {
        // do nothing
    }

    @Override
    public void visitTableSwitchInsn(int min, int max, Label dflt, Label... labels) {
        println("table");
    }

    @Override
    public void visitLookupSwitchInsn(Label dflt, int[] keys, Label[] labels) {
        println("lookup");
    }

    @Override
    public void visitMultiANewArrayInsn(String descriptor, int numDimensions) {
        println("new multi");
    }


}
