package Construction.Builder;

//产品类
public class Computer {
    private final String cpu;
    private final String ram;
    private final String ssd;
    private final String gpu;

    // 包级私有构造函数，仅允许同包下的 Builder 访问
    Computer(String cpu, String ram, String ssd, String gpu) {
        if (cpu == null || ram == null || ssd == null || gpu == null) {
            throw new IllegalArgumentException("所有参数必须设置！");
        }
        this.cpu = cpu;
        this.ram = ram;
        this.ssd = ssd;
        this.gpu = gpu;
    }

    @Override
    public String toString() {
        return String.format(
                "Computer [CPU=%s, RAM=%s, SSD=%s, GPU=%s]",
                cpu, ram, ssd, gpu
        );
    }
}
// 建造者类
class ComputerBuilder {
    private String cpu;
    private String ram;
    private String ssd;
    private String gpu;
    // 链式方法设置参数（必填）
    public ComputerBuilder setCPU(String cpu) {
        this.cpu = cpu;
        return this;
    }
    public ComputerBuilder setRAM(String ram) {
        this.ram = ram;
        return this;
    }
    public ComputerBuilder setSSD(String ssd) {
        this.ssd = ssd;
        return this;
    }
    public ComputerBuilder setGPU(String gpu) {
        this.gpu = gpu;
        return this;
    }
    // 构建 Computer 对象
    public Computer build() {
        return new Computer(cpu, ram, ssd, gpu);
    }
}
// 测试类
class Main {
    public static void main(String[] args) {
        ComputerBuilder builder = new ComputerBuilder();
        builder.setCPU("Intel i9")
                .setRAM("32GB DDR5")
                .setSSD("1TB NVMe")
                .setGPU("NVIDIA RTX 4090");

        Computer computer = builder.build();
        System.out.println(computer);
    }
}