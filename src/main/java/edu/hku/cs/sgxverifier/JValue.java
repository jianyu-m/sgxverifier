package edu.hku.cs.sgxverifier;

/**
 * Created by max on 22/7/2018.
 */
public class JValue {
    enum Taint {
        Secret,
        PlainText
    }
    enum Memory {
        Enclave,
        Untrusted
    }
    Taint taint;
    Memory mem;
    JValue(Taint t, Memory m) {
        taint = t;
        mem = m;
    }
}
