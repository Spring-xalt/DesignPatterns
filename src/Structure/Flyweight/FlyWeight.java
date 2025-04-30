package Structure.Flyweight;

import java.util.HashMap;
import java.util.Map;

/*
 *@auther:Jimi
 *@version:1.0
 *@description:
    享元模式 :有大量对象时，有可能会造成内存溢出，我们把其中共同的部分抽象出来，
                如果有相同的业务请求，直接返回在内存中已有的对象，避免重新创建。(类似java中String的字符串缓存池)
         适用场景： 1、系统中有大量对象。 2、这些对象消耗大量内存。 3、这些对象的状态大部分可以外部化。
                    4、这些对象可以按照内蕴状态分为很多组，当把外蕴对象从对象中剔除出来时，每一组对象都可以用一个对象来代替。
                    5、系统不依赖于这些对象身份，这些对象是不可分辨的。


    1、Flyweight (享元抽象类)：一般是接口或者抽象类，定义了享元类的公共方法。
                             这些方法可以分享内部状态的数据，也可以调用这些方法修改外部状态。

    2、ConcreteFlyweight(具体享元类)：具体享元类实现了抽象享元类的方法，
                           为享元对象开辟了内存空间来保存享元对象的内部数据，同时可以通过和单例模式结合只创建一个享元对象。

    3、FlyweightFactory(享元工厂类)：享元工厂类创建并且管理享元类，享元工厂类针对享元类来进行编程，
                                 通过提供一个享元池来进行享元对象的管理。一般享元池设计成键值对(保证唯一性)，或者其他的存储结构来存储。
                        当客户端进行享元对象的请求时，如果享元池中有对应的享元对象则直接返回对应的对象，否则工厂类创建对应的享元对象并保存到享元池。
 */
//具体享元类
public class FlyWeight implements IFlyweight {
    private String id;
    public FlyWeight(String id){
        this.id = id;
    }
    public String getId() {
        return id;
    }
    @Override
    public void print() {
        System.out.println("Flyweight.id = " + getId() + " ...");
    }

}
// 享元抽象类
interface IFlyweight {
    void print();
}

// 享元工厂类 负责创建大量的享元对象工厂并管理享元对象
class FlyweightFactory {
    // 根据每一个固定的id存储唯一的 共享对象
    private Map<String, IFlyweight> flyweightMap = new HashMap();
    // 在get方法中同时实现了 创建共享对象池
    public IFlyweight getFlyweight(String id){
        // 在池中获取IFlyWeight
        IFlyweight flyweight = flyweightMap.get(id);
        // 没有就创建到池中然后返回
        if(flyweight == null){
            flyweight = new FlyWeight(id);
            flyweightMap.put(id, flyweight);
        }
        return  flyweight;
    }
    public int getFlyweightMapSize(){
        return flyweightMap.size();
    }
}
class R{
    public static void main(String[] args) {
        FlyweightFactory fwF = new FlyweightFactory();
        IFlyweight f1 = fwF.getFlyweight("1");
        IFlyweight f2 = fwF.getFlyweight("2");
        IFlyweight f3 = fwF.getFlyweight("1");
        // 1，3是同一个对象 虽然对象引用不同 但都是从享元对象池中获取到的
        System.out.println(f1==f2);
        System.out.println(f2==f3);
        System.out.println(f1==f3);

        System.out.println(fwF.getFlyweightMapSize());// 2只创建了两个对象
    }
}