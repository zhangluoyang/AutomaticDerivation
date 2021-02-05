package math.op.impl;


import math.Tensor;
import math.op.PairOperator;

public class Add extends PairOperator {

    public Add(String name, Tensor lChild, Tensor rChild) {
        super(name, lChild, rChild);
    }

    public static Add create(String name, Tensor lChild, Tensor rChild) {
        return new Add(name, lChild, rChild);
    }

    @Override
    public float forward() {
        return lChild.forward() + rChild.forward();
    }

    @Override
    public void backward(float childGradient) {
        lChild.backward(1 * childGradient);
        rChild.backward(1 * childGradient);
    }
}
