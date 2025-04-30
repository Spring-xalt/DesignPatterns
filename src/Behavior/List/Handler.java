package Behavior.List;

/*
 *@auther:Jimi
 *@version:1.0
 *@description: 责任链模式:  多个对象有机会处理请求，责任链可使请求的发送者和接受者解耦，
                               请求沿着责任链传递，直到有一个对象处理了它为止。
                    主要解决：职责链上的处理者负责处理请求，客户只需要将请求发送到职责链上即可，
                             无须关心请求的处理细节和请求的传递，所以职责链将请求的发送者和请求的处理者解耦了。
 */

// eg：请假流程 teacher,classBoss,principal都有能力做 请假
public abstract class Handler {
    // 链表式结构0
    private Handler nextHandler;

    public void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }
    public void handleRequest(int leaveDays){
        // 建立责任链 中间递归流程
        if(canHandle(leaveDays)){
            approveRequest(leaveDays);
            System.out.println("请假"+leaveDays+"天成功");
        }
        else if(nextHandler != null){
            //非常精彩的 责任链递归前进
            nextHandler.handleRequest(leaveDays);
        }
        else{
            System.out.println("请假失败...");
        }
    }
    protected abstract boolean canHandle(int leaveDays);
    protected abstract void approveRequest(int leaveDays);
}

class teacher extends Handler{
    @Override
    protected boolean canHandle(int leaveDays) {
        return leaveDays <=2;
    }
    @Override
    protected void approveRequest(int leaveDays) {
        System.out.println("老师同意批假"+leaveDays+"天");
    }
}
class classBoss extends Handler{
    @Override
    protected boolean canHandle(int leaveDays) {
        return leaveDays <=5;
    }
    @Override
    protected void approveRequest(int leaveDays) {
        System.out.println("班主任同意批假"+leaveDays+"天");
    }
}
class principal extends Handler{
    @Override
    protected boolean canHandle(int leaveDays) {
        return leaveDays <=15;
    }
    @Override
    protected void approveRequest(int leaveDays) {
        System.out.println("校长同意批假"+leaveDays+"天");
    }
}

class Dt{
    public static void main(String[] args) {
        Handler handler1 = new teacher();
        Handler handler2 = new classBoss();
        Handler handler3 = new principal();

        // 建立责任链
        handler1.setNextHandler(handler2);
        handler2.setNextHandler(handler3);

        handler1.handleRequest(2);
        handler2.handleRequest(6);
        handler3.handleRequest(14);

    }
}