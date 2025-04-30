package Behavior;

/*
 *@auther:Jimi
 *@version:1.0
 *@description: 模板模式
 */
/*
定义一个操作中算法的骨架，而将一些步骤延迟到子类中，模板方法使得子类可以不改变算法的结构即可重定义该算法的某些特定步骤。
    通俗点的理解就是 ：完成一件事情，有固定的数个步骤，但是每个步骤根据对象的不同，而实现细节不同；
                    就可以在父类中定义一个完成该事情的总方法，按照完成事件需要的步骤去调用其每个步骤的实现方法。
                    每个步骤的具体实现，由子类完成。
 */
public class template {
}

// 抽象父类 规定所有步骤
abstract class Dish{
    // 整个做菜的步骤
    protected void doDish(){
        this.prepare();
        this.make();
        this.carryDishes();
    }
    // 将具体实现延迟到子类
    abstract void prepare();
    abstract void make();
    abstract void carryDishes();
}
// 两个具体实现类
class TE extends Dish{
    @Override
    void prepare() {
        System.out.println("prepare ET");
    }
    @Override
    void make() {
        System.out.println("make ET");
    }
    @Override
    void carryDishes() {
        System.out.println("carry ET");
    }
}
class Meat extends Dish{
    @Override
    void prepare() {
        System.out.println("prepare Meat");
    }
    @Override
    void make() {
        System.out.println("make Meat");
    }
    @Override
    void carryDishes() {
        System.out.println("carry Meat");
    }
}
class T1{
    public static void main(String[] args) {
        Dish d1=new TE();
        d1.doDish();

        Dish d2=new Meat();
        d2.doDish();
    }
}