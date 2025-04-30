package Construction.ProtoType;

// 原型模式  eg 对于克隆羊 原来的羊就是原型对象 根据该对象来创建和原型对象相同的新对象
        // 实现了一个原型接口，该接口用于创建当前对象的克隆
            //例如，一个对象需要在一个高代价的数据库操作之后被创建。
            //我们可以缓存该对象，在下一个请求时返回它的克隆，在需要的时候更新数据库，以此来减少数据库调用。
        //分为深克隆和浅克隆
            // 浅克隆(拷贝):对于基本数据类型 新对象的属性和 原来对象相互独立 直接复制值(独立的 count++不会互相影响)
                    // 但对于引用数据类型 仍然指向原来的对象的 内存地址(地址相同 多一个引用)(修改其中一个对象的属性会影响 其他相同地址的对象)
            // 深克隆 :  对于基本数据类型也是相互独立的
                    // 对于引用数据类型产生的新对象与原来对象完全独立 新引用的对象是 老对象的副本实现 地址不同！
        // java中Clonenable 是 原型接口 实现了该接口的具体类 为具体原型类
public class RealizeType  implements Cloneable{
    public RealizeType(){
        System.out.println("原型对象创建成功");
    }
    @Override
    public RealizeType clone() throws CloneNotSupportedException {
        System.out.println("原型对象复制成功");
        return (RealizeType) super.clone();
    }
}
class Clients{
    // 原理
    /*
    public static void main(String[] args) throws CloneNotSupportedException {
        RealizeType r1 = new RealizeType();

        // 调用clone方法进行克隆
        RealizeType r2 = r1.clone();
        System.out.println(r1==r2);
        // flase 地址不一致 不是同一个对象
        // 同时我们注意到 新对象的产生并没有构造方法的 提示信息 说明不是通过 new对象的方式产生对象的
                            //而是通过 JVM的native方法(解析元对象 得到数据类型 分配内存 复制内存 引用指针指向新内存)的方法实现
    }





   */

    // 深克隆 浅克隆案例解析 1.浅克隆 2.深

    /*
    public static void main(String[] args) throws CloneNotSupportedException {
        // 原型对象
        Citation stu=new Citation();
        // 克隆对象
        Citation stu1=stu.clone();

        System.out.println(stu.getName()==stu1.getName());// true 浅拷贝 都为null


            stu.setName("1"); // stu.name 指向新地址（内容为 "1"）
                              // stu1.name 仍指向原地址（null）
            System.out.println(stu.getName()==stu1.getName());
            //由于String的不可变性 setname之后会有一个新对象产生 所以让人错认为是 深拷贝 实则是浅拷贝

    }
     */
    public static void main(String[] args) {

    }
}
// 三好学生案例
class Citation implements Cloneable {
    // 奖状的姓名
    private String name;

    public Citation clone() throws CloneNotSupportedException {
        return (Citation) super.clone();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Citation1 implements Cloneable {
    private Student student;

}

// 可变引用类型（需实现Cloneable）
class Student implements Cloneable {
    private String name;

    public Student(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public Student clone() throws CloneNotSupportedException {
        return (Student) super.clone(); // 深克隆需递归调用
    }
}