package math.variable.impl;


import math.variable.ValueTensor;

public class Variable extends ValueTensor {

    protected float gradient;

    private Variable(String name, float value) {
        super(name, value);
    }

    private Variable(String name, float value, float weight, float min, float max) {
        super(name, value, weight, min, max);
    }

    @Override
    public void cleanGradient() {
        this.gradient = 0;
    }

    @Override
    public float gradient() {
        return gradient;
    }

    public static Variable create(String name, float value) {
        return new Variable(name, value);
    }

    public static Variable create(String name, float value, float weight, float min, float max) {
        return new Variable(name, value, weight, min, max);
    }

    @Override
    public float forward() {
        return value;
    }

    @Override
    public void backward(float childGradient) {
        gradient += childGradient;
    }

}
