package Construction.FactoryWay;

public class FactoryWay {
    // 工厂方法模式 定义一个创建对象的接口，但让子类决定实例化哪一个类
    // 一般有 抽象工厂 抽象产品 具体工厂 具体产品
}

// 抽象产品
interface Shapes{
    void draw();
}
// 抽象工厂
interface shapesFactory{
    Shapes createShape();
}

// 具体工厂
class RectangleFactory implements shapesFactory{
    @Override
    public Shapes createShape() {
        return new Rectangles();
    }
}
class CircleFactory implements shapesFactory{
    @Override
    public Shapes createShape() {
        return new Circles();
    }
}
class TriangleFactory implements shapesFactory{
    @Override
    public Shapes createShape() {
        return new Triangles();
    }
}

// 具体产品
class Rectangles implements Shapes{
    @Override
    public void draw() {
        System.out.println("Rectangles被工厂方法模式创造出来");
    }
}
class Circles implements Shapes{
    @Override
    public void draw() {
        System.out.println("Circle被工厂方法模式创造出来");
    }
}
class Triangles implements Shapes{
    @Override
    public void draw() {
        System.out.println("Triangle被工厂方法模式创造出来");
    }
}

class D{
    public static void main(String[] args) {
        shapesFactory sf1=new RectangleFactory();
        shapesFactory sf2=new CircleFactory();
        shapesFactory sf3=new TriangleFactory();
        sf1.createShape().draw();
        sf2.createShape().draw();
        sf3.createShape().draw();
        //ArrayList
    }
}
/* 单列集合 的 Iterator类就是工厂方法模式
    <interface>Colllection(抽象工厂)             <interface>Interator(抽象产品)

        ArrayList (具体工厂)                        ArraylistIter(具体产品)

 调用ArrayList.iterator()方法时，本质是通过具体工厂（ArrayList）创建具体产品（Itr迭代器）的过程
          public Iterator<E> iterator()
          {return new Itr();}
    将迭代器的实例化延迟到 iterator()方法被调用时实现 符合按需创建对象的思想
*/