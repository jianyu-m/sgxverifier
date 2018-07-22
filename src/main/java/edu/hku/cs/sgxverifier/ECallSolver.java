package edu.hku.cs.sgxverifier;

import edu.hku.cs.sgxverifier.SGXClassVisitor;
import org.objectweb.asm.ClassReader;

import java.io.IOException;
import java.util.LinkedList;

/**
 * Created by max on 21/7/2018.
 */

/**
 * VM -> stack, heap
 * JObject -> list of JValue
 * JValue -> location & taint
 * JClass -> name to index resolving, constant pool
 */



public class ECallSolver {
    public static void main(String[] args) throws IOException {
        VirtualMachine.init();
        JClass jc = JClass.resolve("SampleClass");
        LinkedList<JObject> inputs = new LinkedList<JObject>();
        VirtualMachine vm = VirtualMachine.getVM();
        vm.push(new JObject("p", new JValue(JValue.PlainText, JValue.Memory.Enclave)));
        vm.push(new JObject("p", new JValue(JValue.PlainText, JValue.Memory.Enclave)));
        vm.push(new JObject("p", new JValue(JValue.Secret, JValue.Memory.Enclave)));
        jc.classVisitor.execute_method("do_add", "(II)I", true);
        System.out.println("here");
    }
}
