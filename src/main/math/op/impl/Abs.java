package math.op.impl;


import math.Tensor;
import math.op.Operator;

public class Abs extends Operator {

    private Abs(String name, Tensor lChild) {
        super(name, lChild);
    }

    public static Abs create(String name, Tensor lChild) {
        return new Abs(name, lChild);
    }

    @Override
    public float forward() {
        return Math.abs(lChild.forward());
    }

    @Override
    public void backward(float childGradient) {
        lChild.backward(Math.signum(lChild.forward()) * childGradient);
    }
}
