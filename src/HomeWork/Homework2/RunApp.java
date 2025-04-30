package HomeWork.Homework2;

// 采用静态代理实现
// 抽象主题

public interface RunApp {
    void run();
}
// 真实(具体)主题
class Application implements RunApp {
    @Override
    public void run() {
        System.out.println("Application is running");
    }
}
// 代理类
class ProxyPicture implements RunApp {
    private Application app;
    @Override
    public void run() {
        System.out.println("正在通过桌面图片快捷来运行app...");
        app = new Application();
        app.run();
    }
}

class D{
    public static void main(String[] args) {
        ProxyPicture proxyPicture = new ProxyPicture();
        proxyPicture.run();
    }
}