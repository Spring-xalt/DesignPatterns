package Structure.Proxy.cglib;

/*
 *@auther:Jimi
 *@version:1.0
 *@description:
 */

//cglib 代理模式是对jdk代理的补充， 用于没有定义抽象主题类(eg:selltickets)时
public class Trainstation {
    public void sell(){
        System.out.println("火车站卖票");
    }
}
