package HomeWork.HomeWork3;

/*
  @auther:Jimi
  @description: 命令模式
 */

// 遥控器角色 获得指令对象
public class HomePod {
    private Mode mode;
    public void setMode(Mode mode) {
        this.mode = mode;
    }
    public void excute() {
        mode.changeMode();
    }
}

//改变模式对象 实际执行状态转移
class ModeChanger{
    public void TvOn(){System.out.println("   电视已经打开");}
    public void TvOff() {System.out.println("   电视已经关闭");}

    public void ComputerOn(){System.out.println("   电脑已经打开");}
    public void ComputerOff() {System.out.println("   电脑已经关闭");}

    public void LampOn(){System.out.println("   台灯已经打开");}
    public void LampOff() {System.out.println("   台灯已经关闭");}
}

interface Mode{
    void changeMode();
}
class OfficeMode implements Mode{
    ModeChanger c;
    public OfficeMode(){
        c = new ModeChanger();
    }
    @Override
    public void changeMode(){
        System.out.println("切换到办公模式..");
        c.TvOff();
        c.ComputerOn();
        c.LampOn();
    }
}
class MovieMode implements Mode{
    ModeChanger c;
    public MovieMode(){
        c = new ModeChanger();
    }
    @Override
    public void changeMode(){
        System.out.println("切换到电影模式...");
        c.TvOn();
        c.ComputerOff();
        c.LampOff();
    }
}
class SleepMode implements Mode{
    ModeChanger c;
    public SleepMode(){
        c = new ModeChanger();
    }
    @Override
    public void changeMode(){
        System.out.println("切换到睡眠模式...");
        c.TvOff();
        c.ComputerOff();
        c.LampOff();
    }
}

class Te{
    public static void main(String[] args) {
        HomePod homePod = new HomePod();

        homePod.setMode(new OfficeMode());
        homePod.excute();

        homePod.setMode(new MovieMode());
        homePod.excute();

        homePod.setMode(new SleepMode());
        homePod.excute();
    }
}