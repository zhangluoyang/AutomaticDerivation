package math.utils;




import math.Tensor;
import math.loss.L1Loss;
import math.loss.L2Loss;
import math.op.define.OpEm;
import math.op.impl.*;
import math.variable.impl.Constant;
import math.variable.impl.Variable;
import utils.L;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 计算图 自动求导 工具类
 *
 * @author zhangluoyang
 */
public class TuneMath {

    public static class AutoIncrement {
        private long id;

        public AutoIncrement() {
        }

        public long generate() {
            return id++;
        }
    }

    private final Map<OpEm, AutoIncrement> map;

    public TuneMath() {
        Map<OpEm, AutoIncrement> map = new HashMap<>();
        for (OpEm opem : OpEm.values()) {
            map.put(opem, new AutoIncrement());
        }
        this.map = map;
    }

    private long generate(OpEm opEm) {
        return map.get(opEm).generate();
    }

    public Constant constant(float value) {
        return Constant.create("constant_" + generate(OpEm.Constant), value);
    }

    public Constant constant(String name, float value) {
        return Constant.create(name, value);
    }

    public Variable variable(String name, float value) {
        return Variable.create(name, value);
    }

    public Variable variable(String name, float value, float weight, float min, float max) {
        return Variable.create(name, value, weight, min, max);
    }

    public L2Loss l2Loss(String name, Tensor lChild) {
        return L2Loss.create(name, lChild);
    }

    public L1Loss L1Loss(String name, Tensor lChild) {
        return L1Loss.create(name, lChild);
    }

    public Abs abs(Tensor tensor) {
        return Abs.create("abs_" + generate(OpEm.Abs), tensor);
    }

    public Abs abs(String name, Tensor tensor) {
        return Abs.create(name, tensor);
    }

    public Add add(Tensor lChild, Tensor rChild) {
        return Add.create("add_" + generate(OpEm.Add), lChild, rChild);
    }

    public Tensor add(Tensor... tensors) {
        Tensor result = constant(0.0f);
        if (tensors != null) {
            for (int i = 0; i < tensors.length; i++) {
                result = add(result, tensors[i]);
            }
        }
        return result;
    }

    public Tensor add(List<Tensor> tensors) {
        Tensor result = constant(0.0f);
        if (!L.isNullOrEmpty(tensors)) {
            for (int i = 0; i < tensors.size(); i++) {
                result = add(result, tensors.get(i));
            }
        }
        return result;
    }

    public Add add(String name, Tensor lChild, Tensor rChild) {
        return Add.create(name, lChild, rChild);
    }

    public Mul mul(String name, Tensor lChild, Tensor rChild) {
        return Mul.create(name, lChild, rChild);
    }

    public Mul mul(Tensor lChild, Tensor rChild) {
        return Mul.create("mul_" + generate(OpEm.Mul), lChild, rChild);
    }

    public Max max(Tensor lChild, Tensor rChild) {
        return Max.create("max_" + generate(OpEm.Max), lChild, rChild);
    }

    public Max max(String name, Tensor lChild, Tensor rChild) {
        return Max.create(name, lChild, rChild);
    }

    public Min min(Tensor lChild, Tensor rChild) {
        return Min.create("min_" + generate(OpEm.Min), lChild, rChild);
    }

    public Min min(String name, Tensor lChild, Tensor rChild) {
        return Min.create("name", lChild, rChild);
    }

    public Power power(Tensor lChild, float eof) {
        return Power.create("power_" + generate(OpEm.Power), lChild, eof);
    }

    public Power power(String name, Tensor lChild, float eof) {
        return Power.create(name, lChild, eof);
    }

    public Sign sign(Tensor lChild) {
        return Sign.create("sign_" + generate(OpEm.Sign), lChild);
    }

    public Sign sign(String name, Tensor lChild) {
        return Sign.create(name, lChild);
    }

    public StopGradient stopGradient(Tensor lChild) {
        return StopGradient.create("stopGradient_" + generate(OpEm.StopGradient), lChild);
    }

    public StopGradient stopGradient(String name, Tensor lChild) {
        return StopGradient.create(name, lChild);
    }

    public Sub sub(Tensor lChild, Tensor rChild) {
        return Sub.create("sub_" + generate(OpEm.Sub), lChild, rChild);
    }

    public Sub sub(String name, Tensor lChild, Tensor rChild) {
        return Sub.create(name, lChild, rChild);
    }

    public void gradient(Tensor loss) {
        loss.backward(1.0f);
    }

    public void cleanGradient(List<Variable> variables) {
        variables.forEach(v -> v.cleanGradient());
    }

    public void gradientOptimizer(int epoch, float maxLr, float minLr, Tensor loss, List<Variable> variables, List<Tensor> losses) {
        float delta = (maxLr - minLr) / epoch;
        for (int e = 0; e <= epoch; e++) {
            System.out.println("all loss: " + loss.forward());
            for (Tensor t : losses) {
                System.out.println("name: " + t.name + " loss: " + t.forward());
            }
            float learnRate = maxLr - delta * e;
            // 梯度清空
            cleanGradient(variables);
            // 后向传播
            loss.backward(1.0f);
            for (Variable variable : variables) {
                System.out.println("name: " + variable.name + " value: " + variable.findValue() + " gradient: " + variable.gradient());
            }
            // 梯度优化
            variables.forEach(variable -> variable.updateValue(learnRate));
        }
    }

}
