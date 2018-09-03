package liwei.com.designmodel.proxy.staticproxy;

import android.util.Log;

import liwei.com.designmodel.proxy.Poker;

import static liwei.com.designmodel.proxy.ProxyActivity.Tag;

/**
 * 具体的打牌者（李四）——也就是静态代理老板打牌的人
 */
public class LiSi implements Poker {

    private Poker poker;

    public LiSi(Poker poker) {
        this.poker = poker;
    }

    @Override
    public void getOne() {
        //调用真实对象方法之前所做的操作
        poker.getOne();
        //调用真实对象方法之后所做的操作
        Log.e(Tag, "李四要摸A");
    }

    @Override
    public void getTwo() {
        //调用真实对象方法之前所做的操作
        poker.getTwo();
        //调用真实对象方法之后所做的操作
        Log.e(Tag, "李四要摸2");
    }

    @Override
    public void getThree() {
        poker.getThree();
        Log.e(Tag, "李四要摸3");
    }

    @Override
    public void playOne() {
        poker.playOne();
        Log.e(Tag, "李四要打A");
    }

    @Override
    public void playTwo() {
        poker.playTwo();
        Log.e(Tag, "李四要打2");
    }

    @Override
    public void playThree() {
        poker.playThree();
        Log.e(Tag, "李四要打3");
    }
}