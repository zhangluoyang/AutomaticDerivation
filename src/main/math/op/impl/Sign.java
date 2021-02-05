package math.op.impl;


import math.Tensor;
import math.op.Operator;

public class Sign extends Operator {

    public Sign(String name, Tensor lChild) {
        super(name, lChild);
    }

    public static Sign create(String name, Tensor lChild) {
        return new Sign(name, lChild);
    }

    @Override
    public float forward() {
        return Math.signum(lChild.forward());
    }

    @Override
    public void backward(float childGradient) {
        lChild.backward(0 * childGradient);
    }
}
