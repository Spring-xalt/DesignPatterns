package HomeWork.Homework2;

/*
 *@auther:Jimi
 *@version:1.0
 *@description:
 */
// 桥接模式
public class Figure {
    private gender g;
    private race r;
    private Profession p;
    public Figure(gender g, race r, Profession p) {
        this.g = g;
        this.r = r;
        this.p = p;
    }
    public void show(){
        g.showGender();
        r.showRace();
        p.showProfession();
    }
}
// 当一个类存在多个独立变化的维度，且这些维度都需要进行扩展或者联合时。
interface gender{
    void showGender();
}
interface race{
    void showRace();
}
interface Profession{
    void showProfession();
}

class Man implements gender{
    @Override
    public void showGender() {
        System.out.println("This gender is Man");
    }
}
class Woman implements gender{
    @Override
    public void showGender() {
        System.out.println("This gender is Woman");
    }
}

class Human implements race{
    @Override
    public void showRace() {
        System.out.println("This race is Human");
    }
}
class Dwarf implements race{
    @Override
    public void showRace() {
        System.out.println("This race is Dwarf");
    }
}
class Orc implements race{
    @Override
    public void showRace() {
        System.out.println("This race is Orc");
    }
}
class Tauren implements race{
    @Override
    public void showRace() {
        System.out.println("This race is Tauren");
    }
}

class Warrior implements Profession{
    @Override
    public void showProfession() {
        System.out.println("This profession is Warrior");
    }
}
class Mage implements Profession{
    @Override
    public void showProfession() {
        System.out.println("This profession is Mage");
    }
}
class Priest implements Profession{
    @Override
    public void showProfession() {
        System.out.println("This profession is Priest");
    }
}
class Hunter implements Profession{
    @Override
    public void showProfession() {
        System.out.println("This profession is Hunter");
    }
}
class User {
    public static void main(String[] args) {
        gender m = new Man();
        race r = new Human();
        Profession p = new Priest();
        Figure f1=new Figure(m,r,p);
        f1.show();

        System.out.println("--------");

        gender m2=new Woman();
        race r2=new Orc();
        Profession p2=new Hunter();
        Figure f2=new Figure(m2,r2,p2);
        f2.show();
    }
}
















