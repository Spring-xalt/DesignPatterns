package Structure.Decorator;

/*
 *@auther:Jimi
 *@version:1.0
 *@description: 装饰者模式 (动态地为对象添加新功能 比继承更具弹性)
 */
/*1.Component（被装饰对象的基类）
定义一个对象接口，可以给这些对象动态地添加职责。

2.ConcreteComponent（具体被装饰对象）
定义一个对象，可以给这个对象添加一些职责。

3.Decorator（装饰者抽象类）
维持一个指向Component实例的引用，并定义一个与Component接口一致的接口。

4.ConcreteDecorator（具体装饰者）
具体的装饰对象，给内部持有的具体被装饰对象，增加具体的职责。
被装饰对象和修饰者继承自同一个超类
*/


// 被装饰的 基类
abstract class Drink{
    // 描述性信息包内公开
    String des="";
    // 给被装饰对象的基础属性
    private float price=5.0f;
    public void setDescription(String description)
    {
        this.des=description;
    }

    public String getDescription()
    {
        return des+"价格为->"+this.getPrice();
    }
    public float getPrice()
    {
        return price;
    }
    public void setPrice(float price)
    {
        this.price=price;
    }
    public abstract float cost();
}

// 被装饰的具体对象
class Coffee extends Drink{
    @Override
    public float cost() {
        return super.getPrice();
    }
}
// 具体对象的一些子类
class Decaf extends Coffee {
    public Decaf()
    {
        super.setDescription("Decaf");
        super.setPrice(3.0f);
    }
}
// 装饰者模式的核心思想:整体的耗材应该是基础耗材加上装饰耗材 而装饰不必继承基础而存在
// 装饰者抽象类
public class Decorator extends Drink {
    // 装饰着不仅要考虑自身 还要考虑自己所修饰的对象
    private Drink Obj;
    public Decorator(Drink Obj) {
        this.Obj = Obj;
        // 不用继承而用组合的方式来取消 二者之间的耦合状态
    };
    @Override
    public float cost() {
        // 总价格
        return super.getPrice() + Obj.cost();
    }
    @Override
    public String getDescription() {
        return super.des + "总价格为->" + this.cost() ;
    }
}
// 装饰者具体类 (往咖啡里具体加什么)
class Milk extends Decorator {
    public Milk(Drink Obj) {
        super(Obj);
        super.setDescription("Milk");
        super.setPrice(2.0f);
    }
}
class ClientDemo{
    public static void main(String[] args) {

        Drink Obj = new Coffee();
        Decorator decorator= new Milk(Obj);
        System.out.println(decorator.getDescription());
    }
}