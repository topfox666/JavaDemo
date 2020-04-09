import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * 通过反射去获取Car对象
 */
public class DemoReflection {
    //要抛异常，可能会找不到类
    public static Car initByDefaultConst() throws Throwable {
        //创建类加载器
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        //通过类加载器创建Car类的对象，classname为源文件路径下的。
        Class clazz = loader.loadClass("Car");
        //类的默认构造器
        Constructor cons = clazz.getDeclaredConstructor((Class[]) null);
        //通过构造器实例化Car
        Car car = (Car) cons.newInstance();
        //通过反射方法设置属性
        Method setBrand = clazz.getMethod("setBrand", String.class);
        setBrand.invoke(car, "奔驰260");
        Method setColor = clazz.getMethod("setColor", String.class);
        setColor.invoke(car, "藏蓝色");
        Method setMaxSpeed = clazz.getMethod("setMaxSpeed", int.class);
        setMaxSpeed.invoke(car, 200);
        return car;
    }
    //方法抛异常到主方法，主方法也要抛个异常
    public static void main(String[] args) throws Throwable {
          Car car=initByDefaultConst();
          //因为通过反射实例化了，验证一下是否可以调用这个方法
          car.introduce();
    }
}

