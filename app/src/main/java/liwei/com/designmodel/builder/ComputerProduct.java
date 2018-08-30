package liwei.com.designmodel.builder;

/**
 * 产品抽象类——使用电脑产品进行产品实现
 */
public abstract class ComputerProduct {

    public abstract void setOs(String os);

    public abstract void setBoard(String board);

    public abstract void setMemory(String memory);

    public abstract void setCpu(String cpu);

    public abstract void setVideocard(String videocard);
}