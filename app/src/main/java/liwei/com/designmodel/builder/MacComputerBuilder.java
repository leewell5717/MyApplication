package liwei.com.designmodel.builder;

/**
 * Mac电脑buider构建类
 */
public class MacComputerBuilder extends Builder {

    private ComputerProduct product = new MacComputerProduct();

    @Override
    public Builder buildBoard(String board) {
        product.setBoard(board);
        return this;
    }

    @Override
    public Builder buildCpu(String cpu) {
        product.setCpu(cpu);
        return this;
    }

    @Override
    public Builder buildMemory(String memory) {
        product.setMemory(memory);
        return this;
    }

    @Override
    public Builder buildVideocard(String videocard) {
        product.setVideocard(videocard);
        return this;
    }

    @Override
    public Builder buildOs(String os) {
        product.setOs(os);
        return this;
    }


    @Override
    public ComputerProduct createComputer() {
        return product;
    }

    @Override
    public String toString() {
        return product.toString();
    }
}