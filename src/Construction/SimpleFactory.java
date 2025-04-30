package Construction;

public class SimpleFactory {
    // 简单工厂模式 : 一个工厂类根据传入的参数 决定 创建哪种产品类的实例
}


// 实体类的实现接口 抽象产品
interface Shape{
    void draw();
    void erase();
}

// 定义一个工厂类 抽象工厂
class shapeFactory {
    public Shape getShape(String shape){
        if(shape==null){
            return null;
        }
        else if(shape.equalsIgnoreCase("Rectangle")){
            return new Rectangle();
        }
        else if(shape.equalsIgnoreCase("Circle")){
            return new Square();
        }
        else if(shape.equalsIgnoreCase("Triangle")){
            return new Circle();
        }
        return null;
    }
}
 // 可以改进的地方在于把这个 shapeFactory 定义为一个 抽象的工厂接口 再由具体的工厂来实现这个工厂接口来完成 实例化 (即为工厂方法模式)

// 工厂类的产品 具体产品
class Rectangle implements Shape{
    @Override
    public void draw() {
        System.out.println("Rectangle被简单工厂模式生产出来");
    }
    @Override
    public void erase() {
        System.out.println("Rectangle被擦除");
    }
}
class Square implements Shape{
    @Override
    public void draw() {
        System.out.println("Square被简单工厂模式生产出来");
    }
    @Override
    public void erase() {
        System.out.println("Square被擦除");
    }
}
class Circle implements Shape{
    @Override
    public void draw(){
        System.out.println("Circle被简单工厂模式生产出来");
    }
    @Override
    public void erase() {
        System.out.println("Circle被擦除");
    }
}

// 测试类
class ClientDemo{
    public static void main(String[] args) {
        shapeFactory sf=new shapeFactory();
        Shape rectangle=sf.getShape("Rectangle");
        Shape circle=sf.getShape("Circle");
        Shape triangle=sf.getShape("Triangle");

        rectangle.draw();
        circle.draw();
        triangle.draw();

        rectangle.erase();
        circle.erase();
        triangle.erase();
    }
}
