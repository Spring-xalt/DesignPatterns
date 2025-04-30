package Construction.Singleton;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

//单例模式 ：该类只能创建一个对象 因而需要对构造器private
public class Singleton implements Serializable{
    // 懒汉式分为 线程安全是（否？）
    private static boolean flag=false;
    private static Singleton instance;
                // 解决 反射导致 单例被破坏
                private Singleton() {
                    if(flag){
                        throw new RuntimeException("多线程非第一次调用了");
                    }
                    else{
                        flag=true;
                    }
                }
                // 解决 反射导致 单例被破坏
    public static synchronized Singleton getInstance() {
        // 不加synchronized 就是线程不安全的
        if (instance == null) {
            // 在多次获取对象时仍然保持 是同一个实例化对象
            instance = new Singleton();
        }
        return instance;
    }

            // 序列化导致单例被破坏
            /*  readResolve方法允许类 替换 从序列化流中读取的对象。
                当反序列化时，ObjectInputStream(反序列化)会检查是否有readResolve方法，
                如果有的话，就用它返回的对象替代反序列化的新对象，从而保持单例
            */
            public static Object readResolve() {
                return instance;
            }
            // 序列化导致单例被破坏
}
class Singleton2{
    // 饿汉式 1
    // 在类加载时就完成实例对象的
    private static Singleton2 instance=new Singleton2();
    private Singleton2() {}
    public static Singleton2 getInstance() {
        return instance;
    }

    /*
    // 饿汉式 2
    private static Singleton2 instance;
    private Singleton2() {}
    // 静态代码块
    static {
        instance = new Singleton2();
    }
    public static Singleton2 getInstance() {
        return instance;
    }
    */
}
class Singleton3{
    // DCL 模式
    // 采用双锁机制 安全且高性能
    private static Singleton3 instance;
    private Singleton3() {}
    public static Singleton3 getInstance() {
        // 第一次判断 如果对象不为空直接返回 不进入抢锁阶段
        if(instance==null) {
            // 对象不为空 多个线程需要抢夺对空对象的实例过程 各个线程抢锁
            synchronized (Singleton3.class) {
                // 类锁确保唯一
                if(instance==null) {
                    instance=new Singleton3();
                }
            }
        }
        return instance;
    }
}
class Singleton4{
    // 内部类模式 延迟类加载 也实例化对象
    public static class Singleton4Holder{
        private static Singleton4 instance=new Singleton4();
    }
    private Singleton4() {}
    public static Singleton4 getInstance() {
        return Singleton4Holder.instance;
    }
}

enum Singleton5{
    // 属于饿汉式方式 枚举过程就完成了对象实例化
    // 枚举方式实现 极其推荐 不会有线程问题也不会有其他问题
    // 但不太普及
    INSTANCE;
}

// 接下来演示序列化和反序列化如何破坏单例模式 Singleton implements Serializable
class Demo {
    public static void main(String[] args) throws Exception {
        //writeObjectToFile();
        readObjectFromFile();
        readObjectFromFile();
        // 两次对象不一致
    }
    public static void writeObjectToFile() throws Exception {
        ObjectOutputStream oos=new ObjectOutputStream(
                new FileOutputStream("D:\\Use\\fjm.txt"));
        oos.writeObject(Singleton.getInstance());
        oos.close();
    }
    public static void readObjectFromFile() throws Exception {
        ObjectInputStream ois=new ObjectInputStream(
                new FileInputStream("D:\\Use\\fjm.txt")
        );
        Singleton s=(Singleton) ois.readObject();
        System.out.println(s);
        ois.close();

    }
    /*
    解决序列化问题导致单例破坏？
        需要在单例方法中加一个readResolve()方法 在反序列化时被反射调用
    */
}

// 接下来演示反射如何破坏单例模式
class Client{
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

    // 先获取类的Class对象
    Class c=Singleton.class;
    // 取得构造方法对象(构造器是private的)
    Constructor cons=c.getDeclaredConstructor();
    // 取消访问检查
    cons.setAccessible(true);

    Singleton s1=(Singleton) cons.newInstance();
    Singleton s2=(Singleton) cons.newInstance();

    System.out.println(s1==s2);
    // 不是同一个对象
    }

    /*
    如何解决?
        加一个标志位 在多线程进入Singleton类时 加入一个静态属性标志是否第一次创建对象实例了
    * */
}

/*
        jdk中使用的 单例模式类?
        Runtime 类
*/
