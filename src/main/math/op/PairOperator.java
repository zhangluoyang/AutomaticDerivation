package math.op;


import math.Tensor;

/**
 * 操作符 (传入的是两个数值)
 *
 * @author zhangluoyang
 */
public abstract class PairOperator extends Operator {

    public final Tensor rChild;

    public PairOperator(String name, Tensor lChild, Tensor rChild) {
        super(name, lChild);
        this.rChild = rChild;
    }

}
