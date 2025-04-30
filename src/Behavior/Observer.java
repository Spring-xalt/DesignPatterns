package Behavior;

/*
 观察者模式
     定义对象间的一种一对多的依赖关系，当一个对象的状态发生改变时，所有依赖于它的对象都得到通知并被自动更新。

    适用对象: 一个对象状态改变给其他对象通知的问题，而且要考虑到易用和低耦合，保证高度的协作。

        抽象被观察者角色：也就是一个抽象主题，它把所有对观察者对象的引用保存在一个集合中，每个主题都可以有任意数量的观察者。
                        抽象主题提供一个接口，可以增加和删除观察者角色。一般用一个抽象类和接口来实现。
        抽象观察者角色：  为所有的具体观察者定义一个接口，在得到主题通知时更新自己。
        具体被观察者角色： 也就是一个具体的主题，在集体主题的内部状态改变时，所有登记过的观察者发出通知。
        具体观察者角色：  实现抽象观察者角色所需要的更新接口，一边使本身的状态与制图的状态相协调。
 */
import java.util.ArrayList;
import java.util.List;

// 抽象观察者
public interface Observer {
    public void update(String message);
}
// 抽象被观察者
interface Subject{
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}

// 具体被观察者
class WechatSubject implements Subject{
    // 在被观察者中放一个list用于存放观察者
    private List<Observer> list;
    private String message;

    public WechatSubject() {
        list = new ArrayList<>();
    }

    @Override
    public void registerObserver(Observer o) {
        // 添加观察者
        list.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        if (!list.isEmpty()) {
            list.remove(o);
        }
    }

    @Override
    public void notifyObservers() {
        // 更新信息
        for (Observer o : list) {
            o.update(message);
        }
    }

    public void setInfomation(String message) {

        // 通报信息
        this.message = message;
        System.out.println("微信服务更新消息： " + message);
        // 消息更新，通知所有观察者(此为触发机制 )
        notifyObservers();
    }
}

class User implements Observer {
    private String name;
    private String message;
    public User(String name) {
        this.name = name;
    }
    @Override
    public void update(String message) {
        this.message = message;
        read();
    }
    public void read() {
        System.out.println(name + " 收到推送消息： " + message);
    }
}
class T{
    public static void main(String[] args) {
        WechatSubject server = new WechatSubject();

        Observer userZhang = new User("ZhangSan");
        Observer userLi = new User("LiSi");
        Observer userWang = new User("WangWu");

        server.registerObserver(userZhang);
        server.registerObserver(userLi);
        server.registerObserver(userWang);
        // 被观察者更新信息 并通知所有观察者
        server.setInfomation("C++是世界上最好用的语言！");

        System.out.println("----------------------------------------------");
        server.removeObserver(userZhang);
        server.setInfomation("JAVA是世界上最好用的语言！");}
}
