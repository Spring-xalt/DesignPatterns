package Princple.On_off;

// 开闭原则  对扩展开放，对修改关闭(利用抽象类或者接口)
public class On_Off {
    // 搜狗输入法更换皮肤
}

abstract class ChangeSkin{
    public abstract void display();
}
class DefaultSkin extends ChangeSkin{
    public void display(){
        System.out.println("Default skin");
    }
}
class A_Skin extends ChangeSkin{
    public void display(){
        System.out.println("A Skin");
    }
}
class SougouInput{
    private ChangeSkin skin;
    public  SougouInput(){}
    public SougouInput(ChangeSkin skin){
        this.skin = skin;
    }
    public void setSkin(ChangeSkin skin) {
        this.skin = skin;
    }
    public void display(){
        skin.display();
    }
}
class User{
    public static void main(String[] args) {
        SougouInput s = new SougouInput();
        //DefaultSkin ds = new DefaultSkin();s.setSkin(ds);
        A_Skin as=new A_Skin(); s.setSkin(as);
        s.display();
    }
}