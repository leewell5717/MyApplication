package liwei.com.designmodel.facade;

/**
 * 提供给外部访问的方法
 */
public class FacadeCenter {

    private CPU cpu;
    private Board board;
    private VideoCard card;

    public FacadeCenter(){
        cpu = new CPU();
        board = new Board();
        card = new VideoCard();
    }

    /**
     * 开机
     */
    public void turnOn(){
        cpu.on();
        board.on();
        card.on();
    }

    /**
     * 关机
     */
    public void turnOff(){
        cpu.off();
        board.off();
        card.off();
    }
}