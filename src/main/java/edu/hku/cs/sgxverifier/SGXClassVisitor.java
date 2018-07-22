package edu.hku.cs.sgxverifier;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

import java.io.IOException;
import java.util.Hashtable;
import java.util.LinkedList;

import static org.objectweb.asm.Opcodes.ASM6;

/**
 * Created by max on 21/7/2018.
 */
public class SGXClassVisitor extends ClassVisitor {

    VirtualMachine vm = VirtualMachine.getVM();
    String class_name = "";
    ClassReader reader;
    String method_name = "";
    String method_desc = "";
    LinkedList<JObject> call_parameters = new LinkedList<JObject>();
    public SGXClassVisitor(String cname) {
        super(ASM6);
        class_name = cname;
        try {
            reader = new ClassReader(class_name);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void execute_method(String name, String desc, boolean is_obj) {
        method_name = name;
        method_desc = desc;
        call_parameters = new LinkedList<JObject>();
        int c = SGXMethodVisitor.parameter_count(method_desc);
        for (int i = 0; i < c; i++) {
            call_parameters.addLast(vm.pop());
        }
        if (is_obj)
            call_parameters.addFirst(vm.pop());
        vm.new_frame(class_name, name);
        reader.accept(this, 0);
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        super.visit(version, access, name, signature, superName, interfaces);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        if (method_name.equals(name) && method_desc.equals(descriptor)) {
            SGXMethodVisitor sgxMethodVisitor = new SGXMethodVisitor(null);
            int n = call_parameters.size();
            for (int i = 0; i < n; i++) {
                sgxMethodVisitor.locals.put(i, call_parameters.removeFirst());
            }
            method_name = method_desc = "";
            return sgxMethodVisitor;
        }
        return null;
    }
}
