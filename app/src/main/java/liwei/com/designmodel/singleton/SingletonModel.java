package liwei.com.designmodel.singleton;

/**
 * 单例模式
 */
public class SingletonModel {

    /**
     * 饿汉式
     */
    public static class MySingletonModelOne {
        private final static MySingletonModelOne singleton = new MySingletonModelOne();

        private MySingletonModelOne() {

        }

        public static MySingletonModelOne getInstance() {
            return singleton;
        }

        public String getValueString() {
            return "我是来自饿汉式的单例模式";
        }
    }

    /**
     * 懒汉式
     */
    public static class MySingletonModelTwo {
        private static MySingletonModelTwo singleton = null;

        private MySingletonModelTwo() {

        }

        public static MySingletonModelTwo getInstance() {
            if (singleton == null) {
                singleton = new MySingletonModelTwo();
            }
            return singleton;
        }

        public String getValueString() {
            return "我是来自懒汉式的单例模式";
        }
    }

    /**
     * 静态内部类
     */
    public static class MySingletonModelThree {
        private static class InnerClass {
            private static final MySingletonModelThree instance = new MySingletonModelThree();
        }

        private MySingletonModelThree() {

        }

        public static MySingletonModelThree getInstance() {
            return InnerClass.instance;
        }

        public String getValueString() {
            return "我是来自静态内部类的单例模式";
        }
    }

    /**
     * 双重验证锁
     */
    public static class MySingletonModelFour {
        private static MySingletonModelFour singleton = null;

        private MySingletonModelFour() {

        }

        public static MySingletonModelFour getInstance() {
            if (singleton == null) {
                synchronized (MySingletonModelFour.class) {
                    if (singleton == null) {
                        singleton = new MySingletonModelFour();
                    }
                }
            }
            return singleton;
        }

        public String getValueString() {
            return "我是来自双重验证锁的单例模式";
        }
    }

    /**
     * 枚举单例
     */
    public enum MySingletonModelFive {
        instance;

        public String getValueString() {
            return "我是来自枚举单例的单例模式";
        }
    }
}