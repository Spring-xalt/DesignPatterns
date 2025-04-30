package Structure.Proxy;
//代理模式一般来讲 三个角色
//抽象主题类:声明对真实主题和业务的方法
//具体主题类:实现具体业务 是代理对象的真实对象 最终需要引用的对象
//代理类: 提供 与真实主题相同的接口 引用真实主题 也可对 对象方法进行扩展增强

// 静态代理: eg:火车站卖票 火车站位置固定需要代售点代为出售

//抽象主题类
interface sellTickets{
    void sell();
}

public class BuyHouseImcla {

}

//具体主题类 实现真正的卖票业务
class transtation implements sellTickets{
    @Override
    public void sell() {
        System.out.println("火车站售票");
    }
}

//代理类
class ProxyPoint implements sellTickets{
    transtation ts;
    @Override
    public void sell() {
        System.out.println("代售点收取一定费用");
        ts = new transtation();
        ts.sell();
    }
}
class Client{
    public static void main(String[] args) {
 // 测试类 直接访问的是代理类，作为访问和提供对象的中介方法 同时对sell进行了增强
        ProxyPoint p = new ProxyPoint();
        p.sell();
    }
}