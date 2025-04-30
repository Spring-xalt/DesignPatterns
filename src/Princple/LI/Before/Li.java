package Princple.LI.Before;

// 里氏代换规则  子类尽量不要改变父类的功能，尽量扩展功能(多态后极其复杂)
                //子类要保证能够完全代替父类出现在父类可以出现的任何地方(基类应当有更宽松的实现条件)
public class Li {
    // 正方形不是长方形?---->正方形不可以继承长方形
}
class Rcetangle{
    private int l;
    private int w;
    public int getL() {
        return l;
    }
    public int getW() {
        return w;
    }
    public void setL(int l) {
        this.l = l;
    }
    public void setW(int w) {
        this.w = w;
    }
}
class Square extends Rcetangle{
    @Override
    public void setL(int l) {
        super.setW(l);
        super.setL(l);
    }
    @Override
    public void setW(int w) {
        super.setW(w);
        super.setW(w);
    }
}
class Test{
    public static void main(String[] args) {
        Rcetangle r1= new Rcetangle();
        r1.setL(10);
        r1.setW(2);
        bootW(r1);
        printRc(r1);
        System.out.println("--------------");

        Square s1= new Square();
        s1.setL(10);
        bootW(s1);
        // 此处由于每次只要宽不小于长就 一直增长宽，然而 正方形的setL方法一直设置长宽相等，导致死循环
        printRc(s1);
    }
    public static void bootW(Rcetangle rc){
        while(rc.getW()<=rc.getL()){
            rc.setW(rc.getW()+1);
        }
        // 变为正方形
    }
    public static void printRc(Rcetangle rc){
        System.out.println("长方形长为"+rc.getL()+"，宽为"+rc.getW());
    }
}