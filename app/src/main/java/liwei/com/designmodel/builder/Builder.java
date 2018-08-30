package liwei.com.designmodel.builder;

/**
 * builder抽象类
 */
public abstract class Builder {

    public abstract Builder buildBoard(String board);

    public abstract Builder buildCpu(String cpu);

    public abstract Builder buildMemory(String memory);

    public abstract Builder buildVideocard(String videocard);

    public abstract Builder buildOs(String os);

    public abstract ComputerProduct createComputer();
}