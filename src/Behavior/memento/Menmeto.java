package Behavior.memento;

import java.util.ArrayList;
import java.util.List;

/*
 *@auther:Jimi
 *@version:1.0
 *@description:  备忘录(快照模式):
                    在不破坏封装性的前提下，捕获一个对象的内部状态，并在该对象之外保存这个状态，
                    以便以后当需要时能将该对象恢复到原先保存的状态。该模式又叫快照模式
                   优点：提供了一种可以恢复状态的机制。当用户需要时能够比较方便地将数据恢复到某个历史的状态。
                        实现了内部状态的封装。除了创建它的发起人之外，其他对象都不能够访问这些状态信息。
                        简化了发起人类。发起人不需要管理和保存其内部状态的各个备份，所有状态信息都保存在备忘录中，
                        并由管理者进行管理，这符合单一职责原则。
 */
/*
    发起人（Originator）角色：记录当前时刻的内部状态信息，提供创建备忘录和恢复备忘录数据的功能，实现其他业务功能，它可以访问备忘录里的所有信息。
    备忘录（Memento）角色：负责存储发起人的内部状态，在需要的时候提供这些内部状态给发起人。
    管理者（Caretaker）角色：对备忘录进行管理，提供保存与获取备忘录的功能，但其不能对备忘录的内容进行访问与修改。
 */

interface MenmetoIF{
    String getState();

}

//发起人
class Originator{
    //发起人的数据是最原始的数据源
    private String state;
    public Originator(String state){
        this.state = state;
    }
    public String getState(){
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }

    //保存到备忘录
    public MenmetoIF saveToMenmeto(){
        return new Menmeto(state);
    }

    //内部类 必要修改功能屏蔽外界
        // 备忘录角色 (数据源于发起人)(需要保存处理的角色)
        private class Menmeto implements MenmetoIF{
            private final String stateCopy;
            private Menmeto(String state){
                this.stateCopy = state;
            }
            @Override
            public String getState() {
                return stateCopy;
            }
            @Override
            public String toString() {
                return "Menmeto{" +
                        "stateCopy='" + stateCopy + '\'' +
                        '}';
            }
        }

    //从备忘录对象中恢复
    public void restore(MenmetoIF menmeto){
        this.state = menmeto.getState();
    }
}

class Manager{
    private List<MenmetoIF> menmetoIFList = new ArrayList<>();
    public void add(MenmetoIF menmetoIF){
        menmetoIFList.add(menmetoIF);
    }
    public MenmetoIF getMenmetoIF(int index){
        return menmetoIFList.get(index);
    }

    //展示保存的备份信息
    public void displayMenmeto(){
        menmetoIFList.stream().forEach(
                (item)-> {
                    System.out.println(item.toString());
                });
    }
}


class Test{
    public static void main(String[] args) {
        //源数据流
        List<Originator> src=new ArrayList<>();
        src.add(new Originator("a"));
        src.add(new Originator("b"));
        src.add(new Originator("c"));

        //保存到快照
        Manager manager=new Manager();
        src.forEach(o->manager.add(o.saveToMenmeto()));
        manager.displayMenmeto();
    }
}
