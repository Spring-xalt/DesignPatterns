package Structure.Adaptor;

// 适配器模式 将某个类的接口转换为 客户端希望的接口表示 主要目的是兼容性 Wrapper
    // 分为三种 类适配器 对象适配器 接口适配器

public class AdaptorPattern {

}
// 1.类适配器
    // 适配器继承 被适配部分并且实现适配目标接口
    //  eg: 给手机充电的电压问题 充电器本身相当于 Adapter,220V交流电是src, 目标是 5V(dst)
    // 缺点: 单继承机制 需要继承 src,要求 dst必须是接口
class Client{
    public static void main(String[] args) {
        System.out.println("----类适配器----");
        Phone p=new Phone();
        p.charging(new VolitageAdapter());

        System.out.println("-----对象适配器----");
        Phone p1=new Phone();
        // 将需要 被适配的传入 到适配器中去 变为5v的 在传入手机充电方法的参数内
        p1.charging(new VolitageAdapter2(new Volitage220V()));

        System.out.println("----接口适配器----");
        AbstractVoltageAdapter ad=new VoltageAdapterImpl(new Volitage220V());
        ad.output5V();
    }
}

interface Volitage5V{
    // 适配目标 dst
    int output5V();
}

class Volitage220V{
    //被适配类 src
    public int output220V(){
        int src=220;
        System.out.println("源电压:"+src);
        return src;
    }
}
class VolitageAdapter extends Volitage220V implements Volitage5V{
    // 电源适配器 (充电器)
    @Override
    public int output5V() {
        int srcV=output220V();
        int dstV=srcV/44;
        return dstV;
    }
}

class Phone{
    public void charging(Volitage5V v5){
        if(v5.output5V()<=5){
            System.out.println("yes");
        }
        else{
            System.out.println("no");
        }
    }
}

//对象适配器
        // 修改继承关系 不要继承 削弱适配器与src的耦合度
class VolitageAdapter2 implements Volitage5V{
    private Volitage220V v220;   //直接持有这个对象 而不继承他 换为聚合关系
    public VolitageAdapter2(Volitage220V v220){
        this.v220=v220;
    }

    @Override
    public int output5V() {
        int dstV=0;
        if(v220!=null){
            int srcV= v220.output220V();
            dstV=srcV/44;
            System.out.println("使用对象适配器进行适配");
            return dstV;
        }
        else{return dstV;}
    }
}

// 接口适配器
    // 用于 要实现一个接口但是 不想实现他的全部方法的情形 当不需要全部实现接口提供的方法时，可先设计一个抽象类实现接口，
    // 并为该接口中每个方法提供一个默认实现（空方法），那么该抽象类的子类可有选择地覆盖父类的某些方法来实现需求，
    // 它适用于一个接口不想使用其所有的方法的情况。
        // eg:对于A接口 a1(),a2(),a3()方法  C类想要实现a1()而不想实现a2(),a3();
        // 可以先做一个抽象类先实现 A接口 接着 C类可以全部空实现A的所有方法 继承B类
interface Vadaptor{
    int output5V();
    int output12V();
    int output24V();
}

abstract class AbstractVoltageAdapter implements Vadaptor {
    @Override
    public int output5V() {
        // 空实现（子类按需覆盖）
        return 0;
    }
    @Override
    public int output12V() {
        // 空实现（子类按需覆盖）
        return 0;
    }
    @Override
    public int output24V() {
        // 空实现（子类按需覆盖）
        return 0;
    }
}

class VoltageAdapterImpl extends AbstractVoltageAdapter {
    private Volitage220V v220;

    public VoltageAdapterImpl(Volitage220V v220) {
        this.v220=v220;
    }
    @Override
    public int output5V() {
        int src = v220.output220V();
        System.out.println("利用接口适配器输出"+src/44);
        return src/44;
    }
    // 其他方法（如 output12V()、output24V()）无需覆盖，默认返回 0
}
