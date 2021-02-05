package math.op.impl;


import math.Tensor;
import math.op.PairOperator;

public class Sub extends PairOperator {

    private Sub(String name, Tensor lChild, Tensor rChild) {
        super(name, lChild, rChild);
    }

    public static Sub create(String name, Tensor lChild, Tensor rChild) {
        return new Sub(name, lChild, rChild);
    }

    @Override
    public float forward() {
        return lChild.forward() - rChild.forward();
    }

    @Override
    public void backward(float childGradient) {
        lChild.backward(1 * childGradient);
        rChild.backward(-1 * childGradient);
    }
}
