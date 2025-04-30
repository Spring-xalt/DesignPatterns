package Structure.Proxy.JDKdynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/*
 *@auther:Jimi
 *@version:1.0
 *@description:
 */
// jdk动态的代理
// 动态代理 Proxy 不是原来意义上的类 而是提供了一个创建代理对象的newProxyInstance方法来获取代理对象

interface SellTickets{
    void sell();
}
public class Transtation implements SellTickets{
    @Override
    public void sell() {
        System.out.println("火车站卖票");
    }
}

//该类并非 代理类
class ProxyFactory {
    private Transtation ts=new Transtation();
    public SellTickets getProxyInstance(){
        // 直接返回
        //                 (ClassLoader loader,   类加载器，加载代理类 ，通过目标对象(具体主题的)来获取类加载器
//                         Class<?>[] interfaces, 代理类实现接口的字节码对象
//                         InvocationHandler h)   代理对象的调用处理程序
        //这个方法返回的对象才为代理类
        return (SellTickets) Proxy.newProxyInstance
                (ts.getClass().getClassLoader(),
                ts.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        //分别传入 代理对象，代理对象的原始方法，该方法的参数
                        return null;
                    }
                }
        );
    }
}

class C{
    public static void main(String[] args) {
        //获取工厂对象
        ProxyFactory pf=new ProxyFactory();
        // 由工厂获取代理类的实例来 实现代理业务
        pf.getProxyInstance().sell();
    }
}