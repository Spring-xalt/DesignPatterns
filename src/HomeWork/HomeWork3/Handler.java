package HomeWork.HomeWork3;

import java.util.ArrayList;
import java.util.List;

/*
   @auther:Jimi
   @description: 责任链模式
 */

public abstract class Handler {
    private Handler nextHandler;

    private static final List<Handler> Handlers = new ArrayList<>();
    public Handler() {
        // 每次实例化对象时候把所有的与会人员(责任链上的人)记录
        Handlers.add(this);
    }
    protected void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public void handleRequest(double money) {

        if (canHandle(money)) {
            handle(money);
        } else if (nextHandler != null) {
            nextHandler.handleRequest(money);

        } else {
            // 金额过大，需要所有处理者参与会议
            System.out.println("金额过大 需要开会处理！");
            for (Handler handler : Handlers) {
                System.out.println(handler.toString() + "准备入会讨论..\n");
            }
        }
    }

    public abstract boolean canHandle(double money);
    public abstract void handle(double money);
}

class Boss3 extends Handler {
    private String name = "主任";
    @Override
    public boolean canHandle(double money) {
        return money <= 10000.0;
    }
    @Override
    public void handle(double money) {
        System.out.println(name + "同意审批" + money + "元的采购单");
    }

    @Override
    public String toString() {
        return "Boss3{" +
                "name='" + name + '\'' +
                '}';
    }
}

class Boss2 extends Handler {
    private String name = "部门经理";
    @Override
    public boolean canHandle(double money) {
        return money <= 50000.0;
    }
    @Override
    public void handle(double money) {
        System.out.println(name + "同意审批" + money + "元的采购单");
    }

    @Override
    public String toString() {
        return "Boss2{" +
                "name='" + name + '\'' +
                '}';
    }
}

class Boss1 extends Handler {
    private String name = "副总经理";
    @Override
    public boolean canHandle(double money) {
        return money <= 100000.0;
    }
    @Override
    public void handle(double money) {
        System.out.println(name + "同意审批" + money + "元的采购单");
    }

    @Override
    public String toString() {
        return "Boss1{" +
                "name='" + name + '\'' +
                '}';
    }
}

class Boss0 extends Handler {
    private String name = "总经理";
    @Override
    public boolean canHandle(double money) {
        return money <= 200000.0;
    }
    @Override
    public void handle(double money) {
        System.out.println(name + "同意审批" + money + "元的采购单");
    }

    @Override
    public String toString() {
        return "Boss0{" +
                "name='" + name + '\'' +
                '}';
    }
}

class T {
    public static void main(String[] args) {
        Handler handler3 = new Boss3();
        Handler handler2 = new Boss2();
        Handler handler1 = new Boss1();
        Handler handler0 = new Boss0();

        handler3.setNextHandler(handler2);
        handler2.setNextHandler(handler1);
        handler1.setNextHandler(handler0);

        handler3.handleRequest(9999.7);
        handler3.handleRequest(49999.7);
        handler3.handleRequest(99999.7);
        handler3.handleRequest(199999.7);
        handler3.handleRequest(10000000);
    }
}