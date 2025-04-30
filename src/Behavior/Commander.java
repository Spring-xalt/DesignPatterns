package Behavior;

/* 命令模式:
    将一个请求封装为一个对象，使发出请求的责任和执行请求的责任分割开。
    这样两者之间通过命令对象进行沟通，这样方便将命令对象进行储存、传递、调用、增加与管理。

  抽象命令类（Command）角色：声明执行命令的接口，拥有执行命令的抽象方法 execute()。
  具体命令（Concrete Command）角色：是抽象命令类的具体实现类，它拥有接收者对象，并通过调用接收者的功能来完成命令要执行的操作。
  接收者（Receiver）角色：执行命令功能的相关操作，是具体命令对象业务的真正实现者。
  请求者（Invoker）角色：是请求的发送者，它通常拥有很多的命令对象，并通过访问命令对象来执行相关请求，它不直接访问接收者。

 */

// 抽象命令
public interface Commander {
    void execute();
    void undo();

}

// 接收者 真正实现
class LightReceiver {
    public void on(){
        System.out.println("Light is on");
    }
    public void off(){
        System.out.println("Light is off");
    }

}

//开关电灯的具体命令
class LightsOnCommand implements Commander {
    private LightReceiver receiver;
    public LightsOnCommand(LightReceiver receiver) {
        this.receiver = receiver;
    }
    @Override
    public void execute() {
        receiver.on();
    }
    @Override
    public void undo() {
        receiver.off();
    }
}
class LightsOffCommand implements Commander {
    private LightReceiver receiver;
    public LightsOffCommand(LightReceiver receiver) {
        this.receiver = receiver;
    }
    @Override
    public void execute() {
        receiver.off();
    }
    @Override
    public void undo() {
        receiver.on();
    }
}

//调用者 电灯遥控器
class Invoker  {
    private Commander commander;
    public void setCommander(Commander commander) {
        this.commander = commander;
    }
    public void pressButton(){
        commander.execute();
    }
}

class Clients{
    public static void main(String[] args) {
        // 定义调用者(发送)和接收者(处理返回)
        Invoker invoker = new Invoker();
        LightReceiver l = new LightReceiver();


        invoker.setCommander(new LightsOnCommand(l));
        invoker.pressButton();

        invoker.setCommander(new LightsOffCommand(l));
        invoker.pressButton();
    }
}