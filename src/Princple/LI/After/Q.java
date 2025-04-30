package Princple.LI.After;

public interface Q {
    // 正方形不能继承长方形 ，而是实现一个抽象的属性 "Q"
    public int getL();
    public int getW();
}
class Square implements Q {
    private int side;

    public int getSide() {
        return side;
    }
    public void setSide(int side) {
        this.side = side;
    }
    @Override
    public int getL() {
        return side;
    }
    @Override
    public int getW() {
        return side;
    }
}
class Rctangle implements Q {
    private int l;
    private int w;
    public void setL(int l) {
        this.l = l;
    }
    public void setW(int w) {
        this.w = w;
    }
    @Override
    public int getL() {
        return l;
    }
    @Override
    public int getW() {
        return w;
    }
}
class Test{
    public static void main(String[] args) {
        Rctangle r = new Rctangle();
        r.setL(10);
        r.setW(2);
        bootW(r);
        display(r);
        System.out.println("--------------");
        Square s = new Square();
        s.setSide(10);
        display(s);
    }
    public static void bootW(Rctangle r){
        // 这里传参为什么 不是 Q q? 因为需要扩展的只有长方形，正方形并不需要此方法，此为 长方形 扩展 Q 的功能
        while(r.getW()<=r.getL()){
            r.setW(r.getW()+1);
        }
    }
    public static void display(Q q){
        System.out.println("长方形长为"+q.getL()+"，宽为"+q.getW());
    }
}