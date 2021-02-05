package math;

/**
 * tensor 节点
 *
 * @author zhangluoyang
 */
public abstract class Tensor {

    public final String name;

    protected Tensor(String name) {
        this.name = name;
    }

    public abstract float forward();

    public abstract void backward(float childGradient);

}
