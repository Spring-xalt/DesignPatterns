package HomeWork.Homework2;

import java.util.ArrayList;
import java.util.List;

// 组合模式实现 部分整体形成树状结构 是的用户对单个对象和组合对象具有一致的访问性
//
public interface AntiVirusFile {
    void addFile(AntiVirusFile a);
    void removeFile(AntiVirusFile a);
    AntiVirusFile getFile(int i);
    void testVirus();
}
// 树叶文件(末端最小的文件格式)
class LeafFile implements AntiVirusFile {
    private String fileName;
    public LeafFile(String fileName) {
        this.fileName = fileName;
    }
    @Override
    public void addFile(AntiVirusFile a) {
        throw new UnsupportedOperationException("叶子文件不支持此操作");
    }
    @Override
    public void removeFile(AntiVirusFile a) {
        throw new UnsupportedOperationException("叶子文件不支持此操作");
    }
    @Override
    public AntiVirusFile getFile(int i) {
        throw new UnsupportedOperationException("叶子文件不支持此操作");
    }
    @Override
    public void testVirus() {
        System.out.println("正在对"+this.fileName+"文件杀毒处理...");
    }

}
// 树枝文件(文件夹)
class FolderFile implements AntiVirusFile {
    private List<AntiVirusFile> f=new ArrayList<>();
    @Override
    public void addFile(AntiVirusFile a) {
        f.add(a);
    }
    @Override
    public void removeFile(AntiVirusFile a) {
        f.remove(a);
    }
    @Override
    public AntiVirusFile getFile(int i) {
        return f.get(i);
    }
    @Override
    public void testVirus() {
        System.out.println("开始扫描文件夹");
        for (AntiVirusFile antiVirusFile : f) {
            antiVirusFile.testVirus();
        }
        System.out.println("对该文件夹杀毒完毕");
    }
}
class T{
    public static void main(String[] args) {
        // 文件夹
        FolderFile folderFile = new FolderFile();
        //独立文件
        LeafFile lf1 = new LeafFile("TextFile");
        LeafFile lf2 = new LeafFile("ImageFile");
        LeafFile lf3 = new LeafFile("VideoFile");
        LeafFile lf4 = new LeafFile("OtherFile");

        // 可以对文件夹内元素增删查 test
        folderFile.addFile(lf1);
        folderFile.addFile(lf2);
        folderFile.addFile(lf3);
        folderFile.addFile(lf4);
        folderFile.testVirus();
        folderFile.removeFile(lf4);
        folderFile.testVirus();
        // 也可以对单独文件test 单独文件不支持其他操作
        lf1.testVirus();
        // 抛异常
        lf2.getFile(0);
        lf3.removeFile(lf2);
    }
}