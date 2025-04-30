package Behavior.Iterator;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/*
    迭代器模式 :提供一种方法顺序访问一个聚合对象中各个元素, 而又无须暴露该对象的内部表示。
                聚合类应该将遍历的责任交给一个迭代器对象
 */
// 抽象迭代器类
public interface Iterator {
    public boolean hasNext();
    public Object next();

}
// 需要迭代的子元素
class Menu{
    private String name;
    private String desc;
    private double price;
    public Menu(String name, String desc, double price) {
        this.name = name;
        this.desc = desc;
        this.price = price;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public String getDesc() {
        return desc;
    }
    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", price=" + price +
                '}';
    }
}

// 具体聚合类
class CMenu{
    private List<Menu> cmenu;
    // list中要遍历的对象是menu
    public CMenu() {
        cmenu=new ArrayList<>();
    }
    public void addMenu(Menu m){
        this.cmenu.add(m);
    }

    // 在聚合类中获取迭代器对象
    public Iterator getIterator(){
        return new CMenuIterator(cmenu);
    }
    //利用传入的迭代器进行遍历
    public void go(Iterator i){
        while(i.hasNext()){
            System.out.println(i.next());
        }
    }

}

// 具体迭代器类
class CMenuIterator implements Iterator{
    private int cnt;
    private List<Menu> cmenu;
    public CMenuIterator(List<Menu> cmenu) {
        cnt=0;
        this.cmenu=cmenu;
    }
    @Override
    public boolean hasNext() {
        return cnt<cmenu.size();
    }
    @Override
    public Object next() {
        if(!hasNext()) {
            throw new NoSuchElementException();
        }
        return cmenu.get(cnt++);// 先返回当前元素后递增
    }

}
// 测试类
class D{
    public static void main(String[] args) {
        CMenu cm=new CMenu();
        cm.addMenu(new Menu("fjm","good",18.0));
        cm.addMenu(new Menu("fjm","goo",148.0));
        cm.addMenu(new Menu("zt","god",8.0));
        cm.addMenu(new Menu("ljj","ood",20.0));

        cm.go(cm.getIterator());
    }
}