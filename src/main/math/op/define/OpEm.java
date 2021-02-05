package math.op.define;

public enum OpEm {

    Abs("abs", 0),
    Add("add", 1),
    Max("max", 2),
    Min("min", 3),
    Power("power", 4),
    Sign("sign", 5),
    StopGradient("stopGradient", 6),
    Sub("sub", 7),
    Mul("mul", 8),
    Constant("constant", 9),
    Variable("variable", 10);
    public final String name;
    public final int id;

    OpEm(String name, int id) {
        this.name = name;
        this.id = id;
    }
}
