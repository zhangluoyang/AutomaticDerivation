package math.op.impl;


import math.Tensor;
import math.op.PairOperator;

public class Mul extends PairOperator {

    private Mul(String name, Tensor lChild, Tensor rChild) {
        super(name, lChild, rChild);
    }

    public static Mul create(String name, Tensor lChild, Tensor rChild) {
        return new Mul(name, lChild, rChild);
    }

    @Override
    public float forward() {
        return lChild.forward() * rChild.forward();
    }

    @Override
    public void backward(float childGradient) {
        lChild.backward(childGradient * rChild.forward());
        rChild.backward(childGradient * lChild.forward());
    }
}
