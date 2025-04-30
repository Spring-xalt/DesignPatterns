public class Main {
    public static void main(String[] args) {

        System.out.println("Hello world!");

    }
    public boolean squareIsWhite(String coordinates) {
        boolean b=false;
        if(coordinates.charAt(0)=='a'||coordinates.charAt(0)=='c'||coordinates.charAt(0)=='e'
                ||coordinates.charAt(0)=='g')b=true;
        int n=Integer.parseInt(coordinates.substring(1));
        if((b&&n%2==0)||(!b&&n%2==1))return true;
        else{
            return false;
        }
    }
}
/*
  设计模式
 0.设计原则
 1.创建者模式(5)——怎样创建对象
       {
       单例
       原型
       工厂方法
       抽象工厂
       建造者模式
       }
 2.结构型模式(7)——如何利用类和对象的思想扩展工程结构
       {
       代理
       适配器
       桥接
       装饰者
       外观
       享元
       组合
       }
 3.行为型模式(11)——类与对象之间如何交互完成单个对象无法完成的任务，以及分配职责
        {
        模板方法
        策略
        命令
        职责链
        状态
        观察者
        中介者
        迭代器
        访问者
        备忘录
        解释器
        }
* */