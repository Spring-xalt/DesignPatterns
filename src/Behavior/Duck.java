package Behavior;

/*
 *@auther:Jimi
 *@version:1.0
 *@description: 策略模式
 */
/*
定义了一系列算法，并将每个算法封装起来，使他们可以相互替换，且算法的变化不会影响到使用算法的客户。
        意图：定义一系列的算法,把它们一个个封装起来, 并且使它们可相互替换。
        主要解决：在有多种算法相似的情况下，使用 if...else 所带来的复杂和难以维护。
        何时使用：一个系统有许多许多类，而区分它们的只是他们直接的行为。
        如何解决：将这些算法封装成一个一个的类，任意地替换。
        关键代码：实现同一个接口
 */

public abstract class Duck {
    //我们思考 如果有一个新功能飞翔需要实现 可以设计一个flyable的接口防止每一个都继承 简化结构
    // 对于会飞的鸭子 实现该接口 不会飞的则不实现 但我们想到 和继承似乎相似只是从空实现变为了 选择实现
    // 故我们可以选择 把fly分两种实现 一种是会飞实现 一种不会飞
    public abstract void display();
    //环境角色 获取抽象策略的引用
    protected flyBehavior fb;

    public void setFly(flyBehavior fb) {
        // 选择策略实现fly行为
        this.fb = fb;
    }
    public void perfromFly(){
        fb.fly();
    }
}
class greenDuck extends Duck {
    @Override
    public void display() {
        System.out.println("Green Duck");
    }
}
class redDuck extends Duck {
    @Override
    public void display() {
        System.out.println("Red Duck");
    }
}


// 抽象策略角色
interface flyBehavior{
    void fly();
}
// 两个具体策略角色
class flyOK implements flyBehavior{
    @Override
    public void fly(){
        System.out.println("会飞");
    }
}
class flyNO implements flyBehavior{
    @Override
    public void fly(){
        System.out.println("不会飞");
    }
}

class Client{
    public static void main(String[] args) {
        Duck d = new redDuck();
        d.display();
        d.setFly(new flyOK());
        d.perfromFly();

        Duck d1 = new greenDuck();
        d1.display();
        d1.setFly(new flyNO());
        d1.perfromFly();
    }
}