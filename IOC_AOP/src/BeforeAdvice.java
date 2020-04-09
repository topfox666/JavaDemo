import java.lang.reflect.Method;

/**
 * 前置通知，实现handler，
 * handler是用来实现invoke（决定怎么通知、什么时候通知）！！！
 */
public class BeforeAdvice implements Advice{
    private Object bean;   //原对象
    private MethodInvocation methodInvocation;     //通知类
    //传入原对象和通知
    public BeforeAdvice(Object bean, MethodInvocation methodInvocation) {
        this.bean = bean;
        this.methodInvocation = methodInvocation;
    }

    /**
     *
     * @param proxy 调用该方法的代理实例
     * @param method 所述方法对应于调用代理实例上的接口方法的实例
     *               方法对象的声明类将是该方法声明的接口，
     *               它可以是代理类继承该方法的代理接口的超级接口。
     * @param args 包含的方法调用传递代理实例的参数值的对象的阵列
     * @return 从代理实例上的方法调用返回的值
     * @throws Throwable 返回值为空
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 在目标方法执行前调用通知
        methodInvocation.invoke();
        return method.invoke(bean, args);
    }
}
