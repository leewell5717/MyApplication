package liwei.com.designmodel.builder;

/**
 * Windows电脑产品类
 */
public class WindowsComputerProduct {

    private String os;
    private String cpu;
    private String board;

    private WindowsComputerProduct(Builder builder){
        this.os = builder.os;
        this.cpu = builder.cpu;
        this.board = builder.board;
    }

    public static class Builder{

        private String os;
        private String cpu;
        private String board;

        public Builder setOs(String os){
            this.os = os;
            return this;
        }

        public Builder setCpu(String cpu){
            this.cpu = cpu;
            return this;
        }

        public Builder setBoard(String board){
            this.board = board;
            return this;
        }

        public WindowsComputerProduct build() {
            return new WindowsComputerProduct(this);
        }

        @Override
        public String toString() {
            return "os:"+os+",cpu:"+cpu+",board:"+board;
        }
    }

    public String getOs() {
        return os;
    }

    public String getCpu() {
        return cpu;
    }

    public String getBoard() {
        return board;
    }
}