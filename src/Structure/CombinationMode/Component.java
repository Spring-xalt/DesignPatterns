package Structure.CombinationMode;


/*
 *@auther:Jimi
 *@version:1.0
 *@description:
 */
/*
        组合模式
            又叫作部分-整体模式，它是一种将对象组合成树状的层次结构的模式，
            用来表示“部分-整体”的关系，使用户对单个对象和组合对象具有一致的访问性。
         组合模式使得用户对单个对象和组合对象的使用具有一致性。

         抽象构件（Component）角色：它的主要作用是为树叶构件和树枝构件声明公共接口，并实现它们的默认行为。
                                在透明式的组合模式中抽象构件还声明访问和管理子类的接口；
                                在安全式的组合模式中不声明访问和管理子类的接口，管理工作由树枝构件完成。
         树叶构件（Leaf）角色：是组合中的叶节点对象，它没有子节点，用于实现抽象构件角色中 声明的公共接口。
        树枝构件（Composite）角色：是组合中的分支节点对象，它有子节点。它实现了抽象构件角色中声明的接口，
                        它的主要作用是存储和管理子部件，通常包含 Add()、Remove()、GetChild() 等方法
*/
import java.util.ArrayList;
import java.util.List;

//抽象构件
public interface Component {
    void add(Component c);
    void remove(Component c);
    Component getChild(int i);
    void operation();
}
//树叶构件
class Leaf implements Component {
    private String name;
    public Leaf(String name) {
        this.name = name;
    }
    @Override
    public void add(Component c) {}
    @Override
    public void remove(Component c) {}
    @Override
    public Component getChild(int i) {
        // 叶子结点空实现即可
        return null;
    }
    @Override
    public void operation() {
        System.out.println("树叶"+name+".operate");
    }
}
//树枝构件
class Composite implements Component {
    private List<Component> c=new ArrayList<>();
    public void add(Component com) {
        c.add(com);
    }
    public void remove(Component c) {
        c.remove(c);
    }
    public Component getChild(int i) {
        if(i>=0&&i<c.size()) {
            return c.get(i); //返回有效索引的子组件
        }
        return null;
    }
    public void operation() {
        for (Component obj : c) {
            obj.operation();
        }
    }
}
