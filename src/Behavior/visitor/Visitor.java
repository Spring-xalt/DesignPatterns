package Behavior.visitor;

/*
  访问者模式:
    将作用于某种数据结构中的各元素的操作分离出来封装成独立的类，使其在不改变数据结构的前提下可以添加作用于这些元素的新的操作，
    为数据结构中的每个元素提供多种访问方式。它将对数据的操作与数据结构进行分离

    抽象访问者（Visitor）角色：定义一个访问具体元素的接口，为每个具体元素类对应一个访问操作 visit() ，该操作中的参数类型标识了被访问的具体元素。
    具体访问者（ConcreteVisitor）角色：实现抽象访问者角色中声明的各个访问操作，确定访问者访问一个元素时该做什么。
    抽象元素（Element）角色：声明一个包含接受操作 accept() 的接口，被接受的访问者对象作为 accept() 方法的参数。
    具体元素（ConcreteElement）角色：实现抽象元素角色提供的 accept() 操作，其方法体通常都是 visitor.visit(this) ，
                                另外具体元素中可能还包含本身业务逻辑的相关操作。
    对象结构（Object Structure）角色：是一个包含元素角色的容器，提供让访问者对象遍历容器中的所有元素的方法，
                                    通常由 List、Set、Map 等聚合类实现。
 */


import java.util.ArrayList;
import java.util.List;

// 抽象元素（商品）
interface Item {
    // 核心方法：接受访问者
    void accept(Visitor visitor);
}

// 具体元素：水果
class Fruit implements Item {
    private String name;
    private double price;

    public Fruit(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public double getPrice() { return price; }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

// 具体元素：书籍
class Book implements Item {
    private String title;
    private double price;

    public Book(String title, double price) {
        this.title = title;
        this.price = price;
    }

    public double getPrice() { return price; }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

// 4. 抽象访问者
public interface Visitor {
    void visit(Fruit fruit);
    void visit(Book book);
}

// 5. 具体访问者：普通计价
class NormalPriceVisitor implements Visitor {
    private double total = 0;

    @Override
    public void visit(Fruit fruit) {
        // 水果按原价计算
        total += fruit.getPrice();
    }

    @Override
    public void visit(Book book) {
        // 书籍按原价计算
        total += book.getPrice();
    }

    public double getTotal() {
        return total;
    }
}

// 6. 具体访问者：折扣计价
class DiscountPriceVisitor implements Visitor {
    private double total = 0;

    @Override
    public void visit(Fruit fruit) {
        // 水果打8折
        total += fruit.getPrice() * 0.8;
    }

    @Override
    public void visit(Book book) {
        // 书籍打9折
        total += book.getPrice() * 0.9;
    }

    public double getTotal() { return total; }
}

// 对象结构(购物车)
class ShoppingCart {
    private List<Item> items = new ArrayList<>();

    public void addItem(Item item) {
        items.add(item);
    }

    // 关键方法：让访问者处理所有商品
    public void accept(Visitor visitor) {
        for (Item item : items) {

            item.accept(visitor);
        }
    }
}


class VisitorDemo {
    public static void main(String[] args) {
        // 1. 创建购物车并添加商品
        ShoppingCart cart = new ShoppingCart();

        cart.addItem(new Fruit("苹果", 10.0));
        cart.addItem(new Book("设计模式", 100.0));

        // 2. 使用普通计价访问者
        NormalPriceVisitor nv = new NormalPriceVisitor();
        cart.accept(nv);
        System.out.println("普通计价: " + nv.getTotal());

        // 3. 使用折扣计价访问者
        DiscountPriceVisitor dv = new DiscountPriceVisitor();
        cart.accept(dv);
        System.out.println("折扣计价: " + dv.getTotal());
    }
}