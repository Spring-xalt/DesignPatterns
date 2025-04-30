package Structure.BridingMode;

/*
 *@auther:Jimi
 *@version:1.0
 *@description: 桥接模式：将抽象部分（手机）与它的实现部分（手机软件应用类）分离，
 *                      将实现部分 抽象成单独的类，使它们都可以独立地变化。整个类图 看起来像一座桥，所以称为桥接模式
 *
 *              适用场景: 当一个类存在多个独立变化的维度，且这些维度都需要进行扩展或者联合时。（
                        当一个系统不希望使用继承或因为多层次继承导致系统类的个数急剧增加时。
                        当一个系统需要在构件的抽象化角色和具体化角色之间增加更多的灵活性时。

 */
//抽象部分
public abstract class Phone {
    protected softWare sw;
    public void setSw(softWare sw) {
        this.sw = sw;
    }
    public abstract void run();
}
// 三个实现类
class vivo extends Phone {
    @Override
    public void run() {
        sw.run();
    }
}
class oppo extends Phone {
    @Override
    public void run() {
        sw.run();
    }
}
class Iphone extends Phone {
    @Override
    public void run() {
        sw.run();
    }
}

// 实现部分
interface softWare{
    void run();
}
class App implements softWare{
    @Override
    public void run() {
        System.out.println("App is running");
    }
}
class Camera implements softWare{
    @Override
    public void run() {
        System.out.println("Camera is running");
    }
}

class Test{
    public static void main(String[] args) {
        oppo oppo = new oppo();
        oppo.setSw(new App());
        oppo.run();

        Iphone iphone = new Iphone();
        iphone.setSw(new Camera());
        iphone.run();

        vivo vivo = new vivo();
        vivo.setSw(new App());
        vivo.run();

    }
}