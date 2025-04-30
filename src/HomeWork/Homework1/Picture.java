package HomeWork.Homework1;
public class Picture {
}

    //工厂方法模式
// 抽象产品
interface pictureReader {
    void readPicture();
}

// 具体产品
class Gif implements pictureReader{
    public void readPicture() {
        System.out.println("读取gif模式图片读取器被用工厂方法模式制作出来");
    }
}
class JPEG implements pictureReader{
    public void readPicture() {
        System.out.println("读取jpeg模式图片读取器被用工厂方法模式制作出来");
    }
}
class JPG implements pictureReader{
    public void readPicture() {
        System.out.println("读取jpg模式图片读取器被用工厂方法模式制作出来");
    }
}

// 抽象工厂
abstract class pictureFactory{
    abstract pictureReader createReader();
}
// 具体工厂
class GifReader extends pictureFactory{
    @Override
    pictureReader createReader() {
        return new Gif();
    }
}
class JPEGReader extends pictureFactory{
    @Override
    pictureReader createReader() {
        return new JPEG();
    }
}
class JpgReader extends pictureFactory{
    @Override
    pictureReader createReader() {
        return new JPG();
    }
}

class Clients{
    public static void main(String[] args) {
        pictureFactory f1=new GifReader();
        pictureFactory f2=new JPEGReader();
        pictureFactory f3=new JpgReader();

        f1.createReader().readPicture();
        f2.createReader().readPicture();
        f3.createReader().readPicture();

    }
}