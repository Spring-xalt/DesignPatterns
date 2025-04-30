package Structure.Proxy.cglib;

import Structure.Proxy.JDKdynamicProxy.Transtation;
import net.sf.cglib.proxy.Enhancer;

/*
 *@auther:Jimi
 *@version:1.0
 *@description: 代理工厂 用于获取代理对象
 */
// cglib 通过动态生成 目标类的子类 
public class ProxyFactory {
    public Transtation getProxyObject() {
        //创建enhancer对象 类似于jdk代理中的proxy类
        Enhancer eh=new Enhancer();
        //设置父类的字节码对象
        eh.setSuperclass(Transtation.class);
        //设置回调函数

        return (Transtation) eh.create();
    }
}
