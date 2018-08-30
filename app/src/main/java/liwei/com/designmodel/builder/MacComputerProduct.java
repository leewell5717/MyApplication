package liwei.com.designmodel.builder;

/**
 * Mac电脑产品类
 */
public class MacComputerProduct extends ComputerProduct {

    /**
     * 主板
     */
    private String board;
    /**
     * 内存
     */
    private String memory;
    /**
     * cpu
     */
    private String cpu;
    /**
     * 显卡
     */
    private String videocard;
    /**
     * 操作系统
     */
    private String os;

    @Override
    public void setOs(String os) {
        this.os = os;
    }

    @Override
    public void setBoard(String board) {
        this.board = board;
    }

    @Override
    public void setMemory(String memory) {
        this.memory = memory;
    }

    @Override
    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    @Override
    public void setVideocard(String videocard) {
        this.videocard = videocard;
    }

    @Override
    public String toString() {
        return "cpu：" + cpu + "，主板：" + board + "，内存：" + memory + "，显卡：" + videocard + "，操作系统：" + os;
    }
}