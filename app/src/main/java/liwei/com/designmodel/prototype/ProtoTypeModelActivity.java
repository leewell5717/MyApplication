package liwei.com.designmodel.prototype;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import liwei.com.R;

/**
 * 原型模式Activity
 * 学习参考：https://blog.csdn.net/qq_25806863/article/details/66972873
 */
public class ProtoTypeModelActivity extends Activity {

    @BindView(R.id.shallow_clone)
    public Button shallowClone;
    @BindView(R.id.deep_clone)
    public Button deepClone;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design_model_prototype);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.shallow_clone,R.id.deep_clone})
    public void OnClick(View v){
        switch (v.getId()){
            case R.id.shallow_clone:
                ClassOne classOne = new ClassOne();
                classOne.name = "Lee";
                classOne.age = 22;
                classOne.eatFruit.add("苹果");
                classOne.eatFruit.add("香蕉");
                classOne.eatFruit.add("梨子");

                ClassOne one = classOne.clone();
                one.name = "Well";
                one.age = 24;
                one.eatFruit.add("西瓜");

                log(classOne.toString());
                log(one.toString());
                break;
            case R.id.deep_clone:
                ClassTwo classTwo = new ClassTwo();
                classTwo.name = "li";
                classTwo.age = 23;
                classTwo.eatFruit.add("橘子");
                classTwo.eatFruit.add("草莓");
                classTwo.eatFruit.add("菠萝");

                ClassTwo two = classTwo.clone();
                two.name = "wei";
                two.age = 25;
                two.eatFruit.add("芒果");

                log(classTwo.toString());
                log(two.toString());
                break;
        }
    }

    /**
     * 克隆根原型
     */
    private class RootClass implements Cloneable{

    }

    /**
     * 浅拷贝——Cloneable的方法clone默认就是浅拷贝，浅拷贝并不是把所有字段都重新构造了一份，而是引用了原型中的字段。
     * 对于值类型，也就是基本数据类型来说，还有String类型，clone方法会进行一个拷贝，可以让拷贝的对象和原型互不干扰。
     * 但是对于引用类型（对象，集合，数组等）来说，clone方法只是让他们指向了同一个内存地址，所以修改其中一个的内容，两个都会变化。
     */
    private class ClassOne extends RootClass{
        public String name;
        public int age;
        public ArrayList<String> eatFruit = new ArrayList<>();

        public ClassOne(){
            log("执行了ClassOne的构造函数");
        }

        @Override
        protected ClassOne clone(){
            ClassOne one = null;
            try{
                one = (ClassOne) super.clone();
            }catch (CloneNotSupportedException e){
                e.printStackTrace();
            }
            return one;
        }

        @Override
        public String toString() {
            return "name:" + name+",age:"+age+",eatFruit:" + eatFruit;
        }
    }

    /**
     * 深拷贝——对于不是基本类型的属性，在clone的时候要手动调用引用对象的clone方法进行拷贝，也就是深拷贝.
     */
    private class ClassTwo extends RootClass{
        public String name;
        public int age;
        public ArrayList<String> eatFruit = new ArrayList<>();

        public ClassTwo(){
            log("执行了ClassTwo的构造函数");
        }

        @Override
        protected ClassTwo clone(){
            ClassTwo two = null;
            try{
                two = (ClassTwo) super.clone();
                //TODO 重点是加了这句话
                two.eatFruit = (ArrayList<String>)this.eatFruit.clone();
            }catch (CloneNotSupportedException e){
                e.printStackTrace();
            }
            return two;
        }

        @Override
        public String toString() {
            return "name:" + name+",age:"+age+",eatFruit:" + eatFruit;
        }
    }

    private void log(String log){
        Log.e("XXX",log);
    }
}