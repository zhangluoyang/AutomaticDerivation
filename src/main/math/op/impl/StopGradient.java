package math.op.impl;


import math.Tensor;
import math.op.Operator;

public class StopGradient extends Operator {

    public StopGradient(String name, Tensor lChild) {
        super(name, lChild);
    }

    public static StopGradient create(String name, Tensor lChild) {
        return new StopGradient(name, lChild);
    }

    @Override
    public float forward() {
        return lChild.forward();
    }

    @Override
    public void backward(float childGradient) {
        lChild.backward(0 * childGradient);
    }
}
