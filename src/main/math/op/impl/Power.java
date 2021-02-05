package math.op.impl;


import math.Tensor;
import math.op.Operator;

public class Power extends Operator {

    private final float eof;

    public Power(String name, Tensor lChild, float eof) {
        super(name, lChild);
        this.eof = eof;
    }

    public static Power create(String name, Tensor lChild, float eof) {
        return new Power(name, lChild, eof);
    }

    @Override
    public float forward() {
        return (float) Math.pow(lChild.forward(), eof);
    }

    @Override
    public void backward(float childGradient) {
        lChild.backward(childGradient * (eof - 1) * (float) Math.pow(lChild.forward(), eof - 1));
    }
}
