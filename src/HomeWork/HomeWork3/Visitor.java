package HomeWork.HomeWork3;

/*
    @auther:Jimi
    @description: 访问者模式
 */

public interface Visitor {
    void visit(ComputerBook computerBook);
    void visit(ToolBook toolBook);
    void visit(NovelBook novelBook);
}
class Per10PriceVisitor implements Visitor {
    private double totalPrice;
    @Override
    public void visit(ComputerBook computerBook) {
        totalPrice += computerBook.getPrice()*0.9;
        System.out.println("打折后的价格"+totalPrice);
    }

    //其余两个空实现
    @Override
    public void visit(ToolBook toolBook){}
    @Override
    public void visit(NovelBook novelBook){}

}
class P2PriceVisitor implements Visitor {
    private double totalPrice;
    @Override
    public void visit(ComputerBook book) {}
    @Override
    public void visit(NovelBook novelBook){}

    @Override
    public void visit(ToolBook toolBook){
        totalPrice += toolBook.getPrice()-2;
        System.out.println("打折后的价格"+totalPrice);
    }

}
class P100_10PriceVisitor implements Visitor {
    private double totalPrice;
    @Override
    public void visit(ComputerBook book) {}
    @Override
    public void visit(ToolBook toolBook){}

    @Override
    public void visit(NovelBook novelBook){
        double price = novelBook.getPrice();
        totalPrice +=(price<100)?price:(price-price/100*10);
        System.out.println("打折后的价格"+totalPrice);
    }

}



interface Book{
    void accept(Visitor visitor);
}
class ComputerBook implements Book{
    private double price;
    public ComputerBook(double price){
        this.price = price;
    }
    @Override
    public void accept(Visitor visitor){
        visitor.visit(this);
    }
    public double getPrice() {
        return price;
    }
}
class ToolBook implements Book{
    private double price;
    public ToolBook(double price){
        this.price = price;
    }
    @Override
    public void accept(Visitor visitor){
        visitor.visit(this);
    }
    public double getPrice() {
        return price;
    }
}
class NovelBook implements Book{
    private double price;
    public NovelBook(double price){
        this.price = price;
    }
    @Override
    public void accept(Visitor visitor){
        visitor.visit(this);
    }
    public double getPrice() {
        return price;
    }
}


class Ts{
    public static void main(String[] args) {
        Visitor visitor = new Per10PriceVisitor();
        ComputerBook computerBook = new ComputerBook(1150.0);
        System.out.println("computerBook原始价格"+computerBook.getPrice());
        computerBook.accept(visitor);

        Visitor visitor2 = new P2PriceVisitor();
        ToolBook toolBook=new ToolBook(1045.2);
        System.out.println("toolBook原始价格"+toolBook.getPrice());
        toolBook.accept(visitor2);

        Visitor visitor3 = new P100_10PriceVisitor();
        NovelBook novelBook=new NovelBook(1200.0);
        System.out.println("novelBook原始价格"+novelBook.getPrice());
        novelBook.accept(visitor3);

    }
}