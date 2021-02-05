package math.loss;


import math.Tensor;
import math.op.Operator;

/**
 * 损失函数
 *
 * @author zhangluoyang
 */
public class L1Loss extends Operator {

    public L1Loss(String name, Tensor lChild) {
        super(name, lChild);
    }

    public static L1Loss create(String name, Tensor lChild) {
        return new L1Loss(name, lChild);
    }

    @Override
    public float forward() {
        return Math.abs(lChild.forward());
    }

    @Override
    public void backward(float childGradient) {
        lChild.backward(Math.signum(lChild.forward()) * childGradient);
    }
}
