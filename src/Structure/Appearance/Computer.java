package Structure.Appearance;

/*
 *@auther:Jimi
 *@version:1.0
 *@description: 外观模式:复杂的流程封装成一个接口供给外部用户更简单的使用
 */
/*
    1）门面角色：外观模式的核心。它被客户角色调用，它熟悉子系统的功能。内部根据客户角色的需求预定了几种功能的组合。（客户调用，同时自身调用子系统功能）

　　2）子系统角色:实现了子系统的功能。它对客户角色和Facade时未知的。它内部可以有系统内的相互交互，也可以由供外界调用的接口。（实现具体功能）

　　3）客户角色:通过调用Facede来完成要实现的功能（调用门面角色）。
* */


//门面角色
public class Computer {
    CPU cpu;
    Disk disk;
    Memory memory;

    public Computer() {
        cpu = new CPU();
        memory = new Memory();
        disk = new Disk();
    }
    public void start() {
        System.out.println("Computer start begin");
        cpu.start();
        disk.start();
        memory.start();
        System.out.println("Computer start end");
    }
    public void shutDown() {
        System.out.println("Computer shutDown begin");
        cpu.shutDown();
        disk.shutDown();
        memory.shutDown();
        System.out.println("Computer shutDown end...");
    }
}

// 以下三个子系统角色
class CPU {
    public void start() {
        System.out.println("cpu is start...");
    }
    public void shutDown() {
        System.out.println("CPU is shutDown...");
    }
}
class Disk {
    public void start() {
        System.out.println("Disk is start...");
    }
    public void shutDown() {
        System.out.println("Disk is shutDown...");
    }
}
class Memory {
    public void start() {
        System.out.println("Memory is start...");

    }
    public void shutDown() {
        System.out.println("Memory is shutDown...");
    }
}

class Client{
    public static void main(String[] args) {
        Computer computer = new Computer();
        computer.start();
        System.out.println("using... ");
        computer.shutDown();
    }
}