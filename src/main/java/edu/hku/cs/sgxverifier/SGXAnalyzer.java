package edu.hku.cs.sgxverifier;


import jdk.internal.org.objectweb.asm.Type;
import jdk.internal.org.objectweb.asm.tree.AbstractInsnNode;
import jdk.internal.org.objectweb.asm.tree.analysis.AnalyzerException;
import jdk.internal.org.objectweb.asm.tree.analysis.Interpreter;
import jdk.internal.org.objectweb.asm.tree.analysis.Value;

import java.util.List;

/**
 * Created by max on 22/7/2018.
 */
public class SGXAnalyzer extends Interpreter {
    protected SGXAnalyzer(int i) {
        super(i);
    }

    public Value newValue(Type type) {
        return null;
    }

    public Value newOperation(AbstractInsnNode abstractInsnNode) throws AnalyzerException {
        return null;
    }

    public Value copyOperation(AbstractInsnNode abstractInsnNode, Value value) throws AnalyzerException {
        return null;
    }

    public Value unaryOperation(AbstractInsnNode abstractInsnNode, Value value) throws AnalyzerException {
        return null;
    }

    public Value binaryOperation(AbstractInsnNode abstractInsnNode, Value value, Value v1) throws AnalyzerException {
        return null;
    }

    public Value ternaryOperation(AbstractInsnNode abstractInsnNode, Value value, Value v1, Value v2) throws AnalyzerException {
        return null;
    }

    public Value naryOperation(AbstractInsnNode abstractInsnNode, List list) throws AnalyzerException {
        return null;
    }

    public void returnOperation(AbstractInsnNode abstractInsnNode, Value value, Value v1) throws AnalyzerException {

    }

    public Value merge(Value value, Value v1) {
        return null;
    }
}
