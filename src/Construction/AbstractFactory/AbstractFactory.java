package Construction.AbstractFactory;

// 若业务拓展 多个产品族 容易产生 类爆炸情形
// 抽象工厂模式


// 抽象工厂
public abstract class AbstractFactory {
    // 提供双接口 对于单个 shape color 实行单实现 另一个 冷处理
    public abstract Shape1 getShape(String shape);
    public abstract Color1 getColor(String color);
}

// 具体工厂(多个产品组)
class ShapeFactory extends AbstractFactory{
    @Override
    public Shape1 getShape(String shape) {
        if(shape ==null)return null;
        else if(shape.equalsIgnoreCase("rectangle"))return new Rectangle1();
        else if(shape.equalsIgnoreCase("circle"))return new Circle1();
        else{
            return null;
        }
    }
    @Override
    public Color1 getColor(String color) {
        return null;
    }
}
class ColorFactory extends AbstractFactory{
    @Override
    public Shape1 getShape(String shapeType) {
        return null;
    }
    @Override
    public Color1 getColor(String color) {
        if(color==null)return null;
        else if(color.equalsIgnoreCase("red"))return new Red1();
        else if(color.equalsIgnoreCase("blue"))return new Blue1();
        else{
            return null;
        }
    }
}

// 抽象产品接口 (fill the color)(draw the shape)
interface Shape1{
    void draw();
}
interface Color1{
    void fill();
}

// 具体产品
class Rectangle1 implements Shape1{
    public void draw(){
        System.out.println("Rectangle");
    }
}
class Circle1 implements Shape1{
    public void draw(){
        System.out.println("Circle");
    }
}
class Red1 implements Color1{
    @Override
    public void fill(){
        System.out.println("Red");
    }
}
class Blue1 implements Color1{
    @Override
    public void fill(){
        System.out.println("Blue");
    }
}

// 创建一个工厂选择类
class ChoosingFactory{
    public static AbstractFactory getFactory(String choice){
        if(choice.equalsIgnoreCase("shape"))return new ShapeFactory();
        else if(choice.equalsIgnoreCase("color"))return new ColorFactory();
        else{
            return null;
        }
    }
}

// 测试类
class DEMO{
    public static void main(String[] args) {
        // 根据工厂选择类 得到各个产品族的 抽象工厂

        AbstractFactory abshape=ChoosingFactory.getFactory("shape");
        assert abshape != null;
        //泛型向上转型
        Shape1 shape1=abshape.getShape("rectangle");
        shape1.draw();
        Shape1 shape2=abshape.getShape("circle");
        shape2.draw();

        AbstractFactory abcolor=ChoosingFactory.getFactory("color");
        assert abcolor != null;
        Color1 c1=abcolor.getColor("red");
        c1.fill();
        Color1 c2=abcolor.getColor("blue");
        c2.fill();

    }
}