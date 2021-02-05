package math.loss;


import math.Tensor;
import math.op.Operator;

/**
 * 损失函数
 *
 * @author zhangluoyang
 */
public class L2Loss extends Operator {

    public L2Loss(String name, Tensor lChild) {
        super(name, lChild);
    }

    public static L2Loss create(String name, Tensor lChild) {
        return new L2Loss(name, lChild);
    }

    @Override
    public float forward() {
        return (float) Math.pow(lChild.forward(), 2.0f) * 0.5f;
    }

    @Override
    public void backward(float childGradient) {
        lChild.backward(childGradient * lChild.forward());
    }
}
