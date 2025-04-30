package HomeWork.Homework2;
public class DataChange {
}
// 适配器模式
//假定B数据我们无法处理 需要转化为A数据类型来处理的情形

// 适配目标
interface Data_A{
    String output();
    void getData();
}
// 被适配部分
class Data_B{
    String output(){
        System.out.println("当前为B类型");
        return "B";
    }
    void fetch_data(Data_A data){
        if(data.output().equals("A")){
            System.out.println("B数据已经转换成功，开始执行fetch_data...");
        }
        else{
            System.out.println("数据还未转换，系统无法识别B数据");
        }
    }

}
// 适配器(数据转化器)
class DataExchange implements Data_A{
    // 采用对象适配器 直接获取对象而不继承 将适配器的构造方法重载 传入被适配的数据
    Data_B data;
    public DataExchange(Data_B data){
        this.data = data;
    }
    @Override
    public String output() {
        return "A";
    }
    @Override
    public void getData() {
        // A 数据可以直接读取 故可以直接在处理方法时 执行get_Data
        System.out.println("A数据执行get_Data中...");
    }
}
// 工具类
class Data_process{
    public void process(Data_A data){
        if(data.output().equals("A")){
            System.out.println("由B数据转化为A数据,数据格式转换成功..开始处理");
        }
        else{
            System.out.println("转换失败");
        }
    }
}
//测试类
class Tests{
    public static void main(String[] args) {
        Data_process dp= new Data_process();
        Data_B b = new Data_B();
        Data_A a=new DataExchange(b);

        dp.process(a);
        System.out.println("开始处理数据.......");

        b.fetch_data(a);
        a.getData();


    }
}
