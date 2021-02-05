package math.op;


import math.Tensor;

/**
 * 操作符
 *
 * @author zhangluoyang
 */
public abstract class Operator extends Tensor {

    public final Tensor lChild;

    public Operator(String name, Tensor lChild) {
        super(name);
        this.lChild = lChild;
    }
}
