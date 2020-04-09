/**
 * 生成代理类（用来对具体对象进行通知的注册）
 */

import java.lang.reflect.Proxy;

public class SimpleAOP {
    //需要告诉代理类，代理哪个对象（bean），代理发送什么通知

    public static Object getProxy(Object bean, Advice advice) {
        /**
         * 三个参数分别是:
         * loader - 类加载器来定义代理类(getProxy方法所在的类)
         * interfaces - 代理类实现的接口列表(就是bean实现的接口)
         * h - 调度方法调用的调用处理函数(一个通知/handler)
         */
        return Proxy.newProxyInstance(SimpleAOP.class.getClassLoader(),
                bean.getClass().getInterfaces(), advice);
    }
}
