package Behavior.Interceptor;

/*
 *@auther:Jimi
 *@version:1.0
 *@description: 解释器模式 :
                 解释器模式（Interpreter Pattern）是一种行为设计模式，它允许你定义一种语言的语法表示，并创建一个解释器来处理这种语法

 */
import java.util.HashMap;
import java.util.Map;

// 抽象表达式接口
interface Expression {
    boolean interpret(Map<String, Boolean> context);
}

// 变量表达式实现
class VariableExpression implements Expression {
    private String name;

    public VariableExpression(String name) {
        this.name = name;
    }

    @Override
    public boolean interpret(Map<String, Boolean> context) {
        if (!context.containsKey(name)) {
            throw new IllegalArgumentException("变量" + name + "未定义");
        }
        return context.get(name);
    }
}

// 非表达式实现
class NotExpression implements Expression {
    private Expression operand;

    public NotExpression(Expression operand) {
        this.operand = operand;
    }

    @Override
    public boolean interpret(Map<String, Boolean> context) {
        return !operand.interpret(context);
    }
}

// 与表达式实现
class AndExpression implements Expression {
    private Expression left;
    private Expression right;

    public AndExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public boolean interpret(Map<String, Boolean> context) {
        return left.interpret(context) && right.interpret(context);
    }
}

// 或表达式实现
class OrExpression implements Expression {
    private Expression left;
    private Expression right;

    public OrExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public boolean interpret(Map<String, Boolean> context) {
        return left.interpret(context) || right.interpret(context);
    }
}

// 客户端类
public class InterpreterPatternDemo {
    public static void main(String[] args) {
        // 创建表达式: (A AND B) OR (NOT C)
        Expression expression = new OrExpression(
                new AndExpression(
                        new VariableExpression("A"),
                        new VariableExpression("B")
                ),
                new NotExpression(
                        new VariableExpression("C")
                )
        );

        // 准备上下文数据
        Map<String, Boolean> context = new HashMap<>();
        context.put("A", true);
        context.put("B", false);
        context.put("C", false);

        // 解释并计算表达式
        boolean result = expression.interpret(context);
        System.out.println("表达式 (A AND B) OR (NOT C) 的计算结果是: " + result);
    }
}