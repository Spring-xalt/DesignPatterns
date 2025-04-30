package HomeWork.Homework2;

/*
 *@auther:Jimi
 *@version:1.0
 *@description:
 */
// 利用外观模式实现
// 门面角色
public class Mainframe {
    private Memory mem;
    private CPU cpu;
    private HardDIsk hd;
    private OS os;
    public Mainframe(Memory mem, CPU cpu, HardDIsk hd, OS os) {
        this.mem = mem;
        this.cpu = cpu;
        this.hd = hd;
        this.os = os;
    }

    public void run(){
        if(mem.check()&&cpu.run()&&hd.read()&&os.load()){
            System.out.println("全部部件启动成功，电脑启动ing...");
        }
        else{
            System.out.println("启动失败..");
        }
    }
}
// 子系统角色
class Memory{
    private boolean checkOK;
    public Memory(boolean check) {
        this.checkOK = check;
    }
    public boolean check(){
        if(this.checkOK){
            System.out.println("内存加载成功..");
            return true;
        }
        else{
            System.out.println("内存加载失败！");
            return false;
        }
    }
}
class CPU{
    private boolean runOK;
    public CPU(boolean runR) {
        this.runOK = runR;
    }
    public boolean run(){
        if(this.runOK){
            System.out.println("CPU加载成功..");
            return true;
        }
        else{
            System.out.println("CPU加载失败！");
            return false;
        }
    }
}
class HardDIsk{
    private boolean readOK;
    public HardDIsk(boolean readOK) {
        this.readOK = readOK;
    }
    public boolean read(){
        if(this.readOK){
            System.out.println("硬盘读取成功..");
            return true;
        }
        else{
            System.out.println("硬盘读取失败！");
            return false;
        }
    }
}
class OS{
    private boolean loadOK;
    public OS(boolean loadOK) {
        this.loadOK = loadOK;
    }
    public boolean load(){
        if(this.loadOK){
            System.out.println("os启动成功..");
            return true;
        }
        else{
            System.out.println("os启动失败！");
            return false;
        }
    }
}
// 客户角色
class Ct{
    public static void main(String[] args) {
        Mainframe mainframe = new Mainframe(
                new Memory(true),
                new CPU(true),
                new HardDIsk(true),
                new OS(true));
        mainframe.run();

    }
}