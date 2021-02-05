package math.variable;


import math.Tensor;

/**
 * 数值节点
 *
 * @author zhangluoyang
 */
public abstract class ValueTensor extends Tensor {

    protected float value;

    /**
     * 变量的权重值 优化的时候会乘以这个数值
     */
    protected float weight;

    /**
     * 该值的最大值和最小值范围 再优化过程中 超出此值的范围 自动修正 (比如: 缩放值)
     */
    protected Float min;

    protected Float max;

    public ValueTensor(String name, float value) {
        super(name);
        this.value = value;
        this.weight = 1.0f;
        this.min = null;
        this.max = null;
    }

    public ValueTensor(String name, float value, float weight, float min, float max) {
        super(name);
        this.value = value;
        this.weight = weight;
        this.min = min;
        this.max = max;
    }

    public ValueTensor withWeight(float weight) {
        this.weight = weight;
        return this;
    }

    public abstract void cleanGradient();

    public abstract float gradient();

    public void updateValue(float learnRate) {
        value -= gradient() * learnRate * weight;
        // 如果设定此变量的可行域 则不允许超出此范围
        if (max != null && min != null) {
            value = (float) Math.min(Math.max(min + 0.001, value), max - 0.001);
        }
    }

    public float findValue() {
        return this.value;
    }

    public ValueTensor forceSet(float value) {
        this.value = value;
        return this;
    }

}
