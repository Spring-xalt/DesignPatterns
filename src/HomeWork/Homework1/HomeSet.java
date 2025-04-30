package HomeWork.Homework1;

// 家居装修
public class HomeSet {
}

    // 抽象产品
interface Type{
    void showType();
}
interface Furniture{
    void showFurniture();
}

//具体产品一(家具族群)
class Chair implements Furniture{
    @Override
    public void showFurniture() {
        System.out.println("Chair家具被用抽象工厂模式制造出来");
    }
}
class Sofa implements Furniture{
    @Override
    public void showFurniture() {
        System.out.println("Sofa家具被用抽象工厂模式制造出来");
    }
}
class Table implements Furniture{
    @Override
    public void showFurniture() {
        System.out.println("Table家具被用抽象工厂模式制造出来");
    }
}
//具体产品二(风格族群)
class Modern implements Type {
    @Override
    public void showType() {
        System.out.println("Modern风格被抽象工厂模式选中");
    }
}
class Victorian implements Type{
    @Override
    public void showType() {
        System.out.println("Victorian风格被抽象工厂模式选中");
    }
}
class Chinese implements Type{
    @Override
    public void showType() {
        System.out.println("Chinese风格被抽象工厂模式选中");
    }
}
// 抽象总工厂
// 两种产品族群 需要双工厂 对于抽象的总工厂 需要工厂选择器根据要求调度
abstract class FinalFactory{
    public abstract Type getType(String req);
    public abstract Furniture getFurniture(String req);
    // 在单个工厂中 需要实现两个方法 一个方法处理逻辑 一个冷处理
}
 //工厂选择器(调度器) 选择哪个工厂
class ChooseFactory {
    static FinalFactory getFactory(String req){
        if(req.equalsIgnoreCase("furniture"))return new FurnitureFactory();
        else if(req.equalsIgnoreCase("type"))return new TypeFactory();
        else return null;
    }
}
class TypeFactory extends FinalFactory {
    @Override
    public Type getType(String req) {
        if (req == null) return null;
        else if (req.equalsIgnoreCase("Modern")) return new Modern();
        else if (req.equalsIgnoreCase("Victorian")) return new Victorian();
        else if (req.equalsIgnoreCase("Chinese")) return new Chinese();
        else return null;
    }

    @Override
    public Furniture getFurniture(String req) {
        return null;
    }
}
class FurnitureFactory extends FinalFactory {
    @Override
    public Type getType(String req) {
        return null;
    }
    @Override
    public Furniture getFurniture(String req) {
        if(req == null) return null;
        else if(req.equalsIgnoreCase("Chair"))return new Chair();
        else if(req.equalsIgnoreCase("Sofa"))return new Sofa();
        else if(req.equalsIgnoreCase("Table"))return new Table();
        else return null;
    }
}
class Test{
    public static void main(String[] args) {
        // 第一个产品组
        FinalFactory f= ChooseFactory.getFactory("furniture");
        Furniture chair= f.getFurniture("Chair");
        chair.showFurniture();
        Furniture sofa= f.getFurniture("Sofa");
        sofa.showFurniture();
        Furniture table= f.getFurniture("Table");
        table.showFurniture();

        System.out.println("---------");

        //第二个产品组
        FinalFactory t= ChooseFactory.getFactory("type");
        Type modern= t.getType("Modern");
        modern.showType();
        Type victorian= t.getType("Victorian");
        victorian.showType();
        Type chinese =t.getType("chinese");
        chinese.showType();
    }
}