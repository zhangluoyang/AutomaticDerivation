package math.op.impl;


import math.Tensor;
import math.op.PairOperator;

public class Min extends PairOperator {

    public Min(String name, Tensor lChild, Tensor rChild) {
        super(name, lChild, rChild);
    }

    public static Min create(String name, Tensor lChild, Tensor rChild) {
        return new Min(name, lChild, rChild);
    }

    @Override
    public float forward() {
        return Math.min(lChild.forward(), rChild.forward());
    }

    @Override
    public void backward(float childGradient) {
        if (lChild.forward() < rChild.forward()) {
            lChild.backward(childGradient * 1);
        } else {
            rChild.backward(childGradient * 1);
        }
    }
}
