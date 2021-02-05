package math.variable.impl;


import math.variable.ValueTensor;

public class Constant extends ValueTensor {

    public Constant(String name, float value) {
        super(name, value);
    }

    @Override
    public void cleanGradient() {
    }

    @Override
    public float gradient() {
        return 0;
    }

    public static Constant create(String name, float value) {
        return new Constant(name, value);
    }


    @Override
    public float forward() {
        return value;
    }

    @Override
    public void backward(float childGradient) {
    }

    /**
     * 设置新值 (仅能内部使用)
     */
    public Constant withValue(float value) {
        this.value = value;
        return this;
    }

}
