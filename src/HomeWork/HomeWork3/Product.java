package HomeWork.HomeWork3;

/*
    @auther:Jimi
    @description: 观察者模式
 */

import java.util.ArrayList;
import java.util.List;

// 抽象被观察者
public interface Product {
    void addBuyer(Buyer buyer);
    void removeBuyer(Buyer buyer);
    void notifyBuyer();
}
//具体被观察者
class Product1 implements Product{
    //一直被监测的产品价格
    private double price;
    List<Buyer> buyerList = new ArrayList<>();
    @Override
    public void addBuyer(Buyer buyer) {
        buyerList.add(buyer);
    }
    @Override
    public void removeBuyer(Buyer buyer) {
        if(buyerList.contains(buyer)){
            buyerList.remove(buyer);
            return;
        }
        System.out.println("观察者列表中不存在当前观察者..");
    }
    public void setPrice(double price) {
        this.price = price;
        //价格变动后主动通知各位观察者
        notifyBuyer();
    }
    @Override
    public void notifyBuyer() {
        for(Buyer buyer : buyerList){
            buyer.changePrice(price);
        }
    }
}

interface Buyer{
    void changePrice(double money);
}

class Buyer1 implements Buyer{
    //消费者此时的产品价格
    private double moneyFromBuyer;
    @Override
    public void changePrice(double money) {
        if(moneyFromBuyer ==0.0 || moneyFromBuyer != money){
            this.moneyFromBuyer = money;
            System.out.println("价格改变了,新价格为"+moneyFromBuyer);
            return ;
        }
        System.out.println("价格未初始化或改变..");
    }
}
class Test{
    public static void main(String[] args) {
        //被观察者
        Product p = new Product1();

        Buyer buyer1 = new Buyer1();
        Buyer buyer2 = new Buyer1();

        p.addBuyer(buyer1);
        p.addBuyer(buyer2);

        //最初都为0 不会有消息
        ((Product1)p).setPrice(15.2);

        ((Product1)p).setPrice(14.65);


    }
}