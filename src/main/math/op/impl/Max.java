package math.op.impl;


import math.Tensor;
import math.op.PairOperator;

public class Max extends PairOperator {

    public Max(String name, Tensor lChild, Tensor rChild) {
        super(name, lChild, rChild);
    }

    public static Max create(String name, Tensor lChild, Tensor rChild) {
        return new Max(name, lChild, rChild);
    }

    @Override
    public float forward() {
        return Math.max(lChild.forward(), rChild.forward());
    }

    @Override
    public void backward(float childGradient) {
        if (lChild.forward() > rChild.forward()) {
            lChild.backward(childGradient * 1);
        } else {
            rChild.backward(childGradient * 1);
        }
    }
}
