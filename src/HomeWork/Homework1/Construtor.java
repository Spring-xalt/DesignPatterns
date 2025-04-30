package HomeWork.Homework1;

public class Construtor {
}
    // 组件接口定义
interface Menu {
    void display();
}
interface Playlist {
    void display();
}
interface MainWindow {
    void display();
}
interface ControlBar {
    void display();
}
interface FavoritesList {
    void display();
}

// 具体组件实现
class DefaultMenu implements Menu {
    @Override
    public void display() {
        System.out.println("[菜单]");
    }
}
class DefaultPlaylist implements Playlist {
    @Override
    public void display() {
        System.out.println("[播放列表]");
    }
}
class DefaultMainWindow implements MainWindow {
    @Override
    public void display() {
        System.out.println("[主窗口]");
    }
}
class DefaultControlBar implements ControlBar {
    @Override
    public void display() {
        System.out.println("[控制条]");
    }
}
class DefaultFavoritesList implements FavoritesList {
    @Override
    public void display() {
        System.out.println("[收藏列表]");
    }
}
// 产品类
class PlayerUI {
    private Menu m;
    private Playlist p;
    private MainWindow mw;
    private ControlBar c;
    private FavoritesList f;

    public void setMenu(Menu menu) { this.m = menu; }
    public void setPlaylist(Playlist playlist) { this.p = playlist; }
    public void setMainWindow(MainWindow mainWindow) { this.mw = mainWindow; }
    public void setControlBar(ControlBar controlBar) { this.c = controlBar; }
    public void setFavoritesList(FavoritesList favoritesList) { this.f = favoritesList; }

    public void show() {
        System.out.println("\n当前界面组成：");
        if (m!=null)m.display();
        if (p!=null)p.display();
        if (mw!=null)mw.display();
        if (c!=null)c.display();
        if (f!=null)f.display();
    }
}

// 抽象建造者
interface PlayerBuilder {
    void buildMenu();
    void buildPlaylist();
    void buildMainWindow();
    void buildControlBar();
    void buildFavoritesList();
    PlayerUI getUI();
}

// 具体建造者实现
class FullModeBuilder implements PlayerBuilder {
    private PlayerUI ui = new PlayerUI();

    @Override
    public void buildMenu() {
        ui.setMenu(new DefaultMenu());
    }
    @Override
    public void buildPlaylist() {
        ui.setPlaylist(new DefaultPlaylist());
    }

    @Override
    public void buildMainWindow() {
        ui.setMainWindow(new DefaultMainWindow());
    }

    @Override
    public void buildControlBar() {
        ui.setControlBar(new DefaultControlBar());
    }

    @Override
    public void buildFavoritesList() {
        // 完整模式不需要收藏列表
    }

    @Override
    public PlayerUI getUI() {
        return ui;
    }
}

class LiteModeBuilder implements PlayerBuilder {
    private PlayerUI ui = new PlayerUI();

    @Override
    public void buildMenu() {
        // 精简模式不需要菜单
    }

    @Override
    public void buildPlaylist() {
        // 精简模式不需要播放列表
    }

    @Override
    public void buildMainWindow() {
        ui.setMainWindow(new DefaultMainWindow());
    }

    @Override
    public void buildControlBar() {
        ui.setControlBar(new DefaultControlBar());
    }

    @Override
    public void buildFavoritesList() {
        // 精简模式不需要收藏列表
    }

    @Override
    public PlayerUI getUI() {
        return ui;
    }
}

class MemoryModeBuilder implements PlayerBuilder {
    private PlayerUI ui = new PlayerUI();

    @Override
    public void buildMenu() {
        // 记忆模式不需要菜单
    }

    @Override
    public void buildPlaylist() {
        // 记忆模式不需要播放列表
    }

    @Override
    public void buildMainWindow() {
        ui.setMainWindow(new DefaultMainWindow());
    }

    @Override
    public void buildControlBar() {
        ui.setControlBar(new DefaultControlBar());
    }

    @Override
    public void buildFavoritesList() {
        ui.setFavoritesList(new DefaultFavoritesList());
    }

    @Override
    public PlayerUI getUI() {
        return ui;
    }
}

// 指导者
class UIDirector {
    public void construct(PlayerBuilder builder) {
        builder.buildMenu();
        builder.buildPlaylist();
        builder.buildMainWindow();
        builder.buildControlBar();
        builder.buildFavoritesList();
    }
}

// 演示
class BuilderDemo {
    public static void main(String[] args) {
        UIDirector dir = new UIDirector();

        System.out.println("----- 完整模式 -----");
        PlayerBuilder f = new FullModeBuilder();
        dir.construct(f);
        f.getUI().show();

        System.out.println("\n----- 精简模式 -----");
        PlayerBuilder l = new LiteModeBuilder();
        dir.construct(l);
        l.getUI().show();

        System.out.println("\n----- 记忆模式 -----");
        PlayerBuilder m = new MemoryModeBuilder();
        dir.construct(m);
        m.getUI().show();
    }
}