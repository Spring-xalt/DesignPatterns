package HomeWork.Homework1;
//原型模式实现
//
public class CharacterClone implements Cloneable {
    private String name;
    private char sex;
    private String race;
    private String profession;
    private D d;   // 测试是深克隆还是浅克隆
    private E e;   // 深克隆

    public CharacterClone() {
        System.out.println("原型模式加载中");
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public char getSex() {
        return sex;
    }
    public void setSex(char sex) {
        this.sex = sex;
    }
    public String getRace() {
        return race;
    }
    public void setRace(String race) {
        this.race = race;
    }
    public String getProfession() {
        return profession;
    }
    public void setProfession(String profession) {
        this.profession = profession;
    }

    /*
        // 浅度克隆不修改 clone的内部逻辑(默认浅度克隆 无法区分元对象与新对象 或者说二者其一修改会相互影响)
        @Override
        public CharacterClone clone() throws CloneNotSupportedException {
            System.out.println("正在复制元对象");
            return (CharacterClone) super.clone();
        }
    */

    @Override
    public CharacterClone clone() throws CloneNotSupportedException {
        // 手动先复制 具体原型类
        CharacterClone clone=(CharacterClone)super.clone();
        // 此处如果为空则为 null正常的 不为空就是native的浅克隆把该引用也指向了原对象的地址 显然不符合深克隆的要求
        if(this.e!=null) {
            clone.e=this.e.clone();
        }
        return clone;
    }
    public D getD() {
        return d;
    }
    public void setD(D d) {
        this.d = d;
    }

    public E getE() {
        return e;
    }

    public void setE(E e) {
        this.e = e;
    }
}
class Client{
    public static void main(String[] args) throws CloneNotSupportedException {
        /*
            // 原对象
            CharacterClone character = new CharacterClone();
            // 此处可以设置属性 修改属性 等等....
            // 在克隆之前设置原对象的属性 测试一下是深克隆还是浅克隆

            character.setD(new D());
            // 克隆对象
            CharacterClone char1=character.clone();

            System.out.println(char1.getD()==character.getD());// true 代表两引用 指向同一块内存地址 是浅克隆
         */
        // 创建原型对象 并设置属性(引用类型的 )
        CharacterClone chars=new CharacterClone();
        chars.setE(new E());
        CharacterClone chars1=chars.clone();
        System.out.println(chars.getE()==chars1.getE()); //false 代表两个对象分别指向不同的 内存地址
    }
}
class D{
    public D() {
    }
}
// 若采用深度克隆？ 需要继续将引用对象 cloneable 然后在具体原型类中重写 clone的内部逻辑
class E implements Cloneable{
    public E(){
    }
    @Override
    public E clone() throws CloneNotSupportedException {
        return (E)super.clone();
    }
}