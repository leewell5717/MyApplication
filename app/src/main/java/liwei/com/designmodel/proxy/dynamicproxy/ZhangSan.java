package liwei.com.designmodel.proxy.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 具体的打牌者（张三）——也就是动态代理老板打牌的人
 */
public class ZhangSan implements InvocationHandler {

    private Object object;

    /**
     * 方法一：使用构造方法
     */
    public ZhangSan(Object object){
        this.object = object;
    }

    public ZhangSan(){

    }

    /**
     * 方法二 ：绑定委托对象，并返回代理类
     */
    public Object bind(Object object){
        this.object = object;
        //绑定该类实现的所有接口，取得代理类
        return Proxy.newProxyInstance(object.getClass().getClassLoader(),object.getClass().getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //当然这里可以对方法名进行判断过滤 if(method.getName().equals("***"))

        //调用真实对象方法之前所做的操作
        Object obj = method.invoke(object,args);
        //调用真实对象方法之后所做的操作
        return obj;

        //如果不需要其他多余的操作，可直接返回；
//        return method.invoke(object,args);
    }
}