package HomeWork.HomeWork3;

/*
   @auther:Jimi
   @desciption: 模板模式
 */
public abstract class Business {
    protected void doBusiness() {
        indentify();
        displayBooks();
        transact();
        overtimePayment();
    }
    abstract void indentify();
    abstract void displayBooks();
    abstract void transact();
    abstract void overtimePayment();
}

class Transaction extends Business {
    static Long startTime=null;
    static Long endTime=null;
    // num的 123分别代表 借书 还书 延长借书
    private int num=0;
    public void setNum(int num) {
        this.num = num;
    }
    @Override
    void indentify() {
        System.out.println("开始识别学生身份..");
    }
    @Override
    void displayBooks() {
        System.out.println("开始展示已借阅书籍");
    }

    @Override
    void transact() {
        switch (num){
            case 1 :
                System.out.println("准备借书..");
                startTime = System.currentTimeMillis();
                endTime = null;
                break;
            case 2:
                System.out.println("准备还书..");
                endTime = System.currentTimeMillis();
                break;
            case 3:
                System.out.println("准备延长借书时间");
                break;
            default:
                break;
        }
    }
    @Override
    void overtimePayment() {
        if(num!=2){
            return;
        }
        if (startTime == null || endTime == null) {
            System.out.println("无法计算超时费用：缺少开始或结束时间");
            return;
        }
        Long lastingTime=(endTime-startTime)/1000;
        double price=0;
        price +=lastingTime*1;
        System.out.println("超时应缴费"+price+"元！");
    }
}
class TestDemo{
    public static void main(String[] args) {
        Business b=new Transaction();
        //借书
        ((Transaction)b).setNum(1);
        b.doBusiness();
        //休眠5s
        try{
            Thread.sleep(5000);
        }
        catch(InterruptedException e){
            System.out.println("休眠被打断！！");
            e.printStackTrace();
        }

        //延长借书
        ((Transaction)b).setNum(3);
        b.doBusiness();
        //休眠5s
        try{
            Thread.sleep(10000);
        }
        catch(InterruptedException e){
            System.out.println("休眠被打断！！");
            e.printStackTrace();
        }

        ((Transaction)b).setNum(2);
        b.doBusiness();
    }
}