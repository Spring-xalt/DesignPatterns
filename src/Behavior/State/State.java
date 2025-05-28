package Behavior.State;

/*
 *@auther:Jimi
 *@version:1.0
 *@description:  状态模式
 */
public class State {
}
/*
    一个拥有状态的context对象，在不同的状态下，其行为会发生改变
    对象的行为依赖于它的状态（属性），并且可以根据它的状态改变而改变它的相关行为

     把每个状态设置为一个标准模式 状态转变利用上下文实现
 */
// 抽象状态接口
interface TCPState {
    // 根据上下文报文改变状态
    void open(TCPConnection context);
    void close(TCPConnection context);
    void acknowledge(TCPConnection context);
}

// 状态转移实现
class ClosedState implements TCPState {
    @Override
    public void open(TCPConnection context) {
        System.out.println("发送SYN包，启动连接");
        context.setState(new SynSentState());
    }
    @Override
    public void close(TCPConnection context) {
        System.out.println("连接已关闭，无需操作");
    }
    @Override
    public void acknowledge(TCPConnection context) {
        System.out.println("错误：连接未建立");
    }
}

// 状态对象 唯一的上下文对象 根据状态对象的改变自己的行为
class TCPConnection {
    private TCPState currentState;

    public TCPConnection() {
        currentState = new ClosedState();
    }

    void setState(TCPState state) {
        this.currentState = state;
    }

    public void open() {
        currentState.open(this);
    }

    public void close() {
        currentState.close(this);
    }

    public void acknowledge() {
        currentState.acknowledge(this);
    }

    public String getStateName() {
        return currentState.getClass().getSimpleName();
    }
}
//以下均为获得状态的对象


class SynSentState implements TCPState {
    @Override
    public void open(TCPConnection context) {
        System.out.println("错误：连接已初始化");
    }

    @Override
    public void close(TCPConnection context) {
        System.out.println("中止连接，返回关闭状态");
        context.setState(new ClosedState());
    }

    @Override
    public void acknowledge(TCPConnection context) {
        System.out.println("收到SYN+ACK，发送ACK");
        context.setState(new EstablishedState());
    }
}

class EstablishedState implements TCPState {
    @Override
    public void open(TCPConnection context) {
        System.out.println("错误：连接已建立");
    }

    @Override
    public void close(TCPConnection context) {
        System.out.println("发送FIN包，进入FIN_WAIT_1");
        context.setState(new FinWait1State());
    }

    @Override
    public void acknowledge(TCPConnection context) {
        System.out.println("正常数据传输...");
    }
}

class FinWait1State implements TCPState {
    @Override
    public void open(TCPConnection context) {
        System.out.println("错误：连接正在关闭");
    }

    @Override
    public void close(TCPConnection context) {
        System.out.println("错误：FIN已发送");
    }

    @Override
    public void acknowledge(TCPConnection context) {
        System.out.println("收到ACK，进入FIN_WAIT_2");
        context.setState(new FinWait2State());
    }
}

class FinWait2State implements TCPState {
    @Override
    public void open(TCPConnection context) {
        System.out.println("错误：连接正在关闭");
    }

    @Override
    public void close(TCPConnection context) {
        System.out.println("错误：等待最终ACK");
    }

    @Override
    public void acknowledge(TCPConnection context) {
        System.out.println("收到FIN，发送ACK，进入TIME_WAIT");
        context.setState(new TimeWaitState());
    }
}

class TimeWaitState implements TCPState {
    @Override
    public void open(TCPConnection context) {
        System.out.println("错误：等待超时");
    }

    @Override
    public void close(TCPConnection context) {
        System.out.println("错误：等待超时");
    }

    @Override
    public void acknowledge(TCPConnection context) {
        System.out.println("超时时间到，连接关闭");
        context.setState(new ClosedState());
    }
}


// 测试
class StatePatternDemo {
    public static void main(String[] args) {
        // 建立连接初始状态为 closed  开始通信后

        /* 服务端发送syn包 进入syn准备同步
           客户端收到请求同步的信息syn+ack 进入established 发送ack给客户端
           服务端收到ack 进入finwait1
         */
        TCPConnection connection = new TCPConnection();
        System.out.println("当前状态: " + connection.getStateName());
        connection.open();
        System.out.println("当前状态: " + connection.getStateName());
        connection.acknowledge();
        System.out.println("当前状态: " + connection.getStateName());
        connection.close();
        System.out.println("当前状态: " + connection.getStateName());
        connection.acknowledge();
        System.out.println("当前状态: " + connection.getStateName());
        connection.acknowledge();
        System.out.println("当前状态: " + connection.getStateName());
    }
}
