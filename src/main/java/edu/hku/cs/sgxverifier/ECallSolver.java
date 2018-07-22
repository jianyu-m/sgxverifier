package edu.hku.cs.sgxverifier;

import edu.hku.cs.sgxverifier.SGXClassVisitor;
import org.objectweb.asm.ClassReader;

import java.io.IOException;

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
        SGXClassVisitor sgxClassVisitor = new SGXClassVisitor();
        ClassReader classReader = new ClassReader("SampleClass");
        classReader.accept(sgxClassVisitor, 0);
    }
}
