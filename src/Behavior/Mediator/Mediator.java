package Behavior.Mediator;

import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.List;

/*
 *@auther:Jimi
 *@version:1.0
 *@description: 中介者模式
                定义一个中介对象来封装一系列对象之间的交互，使原有对象之间的耦合松散，且可以独立地改变它们之间的交互。
                中介者模式又叫调停模式，它是迪米特法则的典型应用。

                抽象中介者（Mediator）角色：它是中介者的接口，提供了同事对象注册与转发同事对象信息的抽象方法。
                具体中介者（ConcreteMediator）角色：实现中介者接口，定义一个 List 来管理同事对象，协调各个同事角色之间的交互关系，
                                                因此它依赖于同事角色。
                抽象同事类（Colleague）角色：定义同事类的接口，保存中介者对象，提供同事对象交互的抽象方法，实现所有相互影响的同事类的公共功能。
                具体同事类（Concrete Colleague）角色：是抽象同事类的实现者，当需要与其他同事对象交互时，由中介者对象负责后续的交互。
 */
public class Mediator {

}
// 抽象中介者
interface MediatorIF {
    void register(Colleague colleague);
    void forward(String from , String to,String content);
}
//具体中介者
class ConcreteMediator implements MediatorIF {
    List<Colleague> colleagues=new ArrayList<Colleague>();
    @Override
    public void register(Colleague colleague) {
        if(!colleagues.contains(colleague)){
            colleagues.add(colleague);

            colleague.setMediator(this);
        }
    }
    @Override
    public void forward(String from, String to, String content) {
        for(Colleague cl: colleagues){
            String name=cl.getName();
            if(name.equals(to)){
                cl.receive(from,content);
                return;
            }
        }
        System.out.println("未找到发送对象");
    }
}
// 抽象同事类
abstract class Colleague {
    protected MediatorIF mediatorif;
    protected String name;

    public Colleague(String name) {
        this.name = name;
    }
    public void setMediator(MediatorIF mediator) {
        //设置当前同事的可调停者
        this.mediatorif = mediator;
    }
    public String getName() {
        return name;
    }
    public abstract void send(String to, String ad);
    public abstract void receive(String from, String ad);
}

//具体同事类
class Buyer extends Colleague {
    public Buyer(String name) {
        super(name);
    }
    @Override
    public void send(String to, String ad) {
        System.out.println("Buyer " + name + " 发送消息: " + ad);
        //通过中介者转发消息
        mediatorif.forward(name, to, ad);
    }

    @Override
    public void receive(String from, String ad) {
        System.out.println(name + "接收到来自" + from + "的消息:" + ad);
    }

}

class T{
    public static void main(String[] args) {
        MediatorIF mediator = new ConcreteMediator();

        // 2. 创建同事对象并注册
        Buyer buyer1 = new Buyer("买家1");
        Buyer buyer2 = new Buyer("买家2");
        mediator.register(buyer1);
        mediator.register(buyer2);

        // 3. 测试消息发送
        buyer1.send("买家2", "123");
        buyer2.send("买家1", "456");

        // 4. 测试不存在的接收者
        buyer1.send("买家3", ".....");
    }
}