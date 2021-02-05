import math.loss.L2Loss;
import math.op.impl.Sub;
import math.utils.TuneMath;
import math.variable.impl.Constant;
import math.variable.impl.Variable;

public class Demo {

    public static void main(String[] args) {

        TuneMath tuneMath = new TuneMath();

        Constant a1 = tuneMath.constant("a1", 1.0f);
        Variable a2 = tuneMath.variable("a2", 2.0f);
        Sub sub = tuneMath.sub(a1, a2);
        System.out.println("loss = 0.5 * (a1 - a2) ^2  其中 a1=1是常数 a2=2是变量");
        L2Loss l2_loss = tuneMath.l2Loss("l2_loss", sub);
        System.out.println("java 自动求导的测试类");
        System.out.println("l2_loss： " + l2_loss.forward());
        System.out.println("计算导数");
        l2_loss.backward(1.0f);
        System.out.println("常数项的导数为零");
        System.out.println("常数项导数" + a1.gradient());
        System.out.println("变量项导数" + a2.gradient());
    }

}
