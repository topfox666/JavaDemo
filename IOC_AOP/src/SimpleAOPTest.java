public class SimpleAOPTest {
    public static void main(String[] args) throws Exception{
        SimpleAOPTest t=new SimpleAOPTest();
        t.getProxy();
    }
    //生成代理类（被代理对象、通知）
    public void getProxy() throws Exception{
        //通知类的实现类,应该是用lambda表达式结合函数式接口，实际上就是invoke()方法的实现
        MethodInvocation logTask = () -> System.out.println("log task start");
        HelloServiceImpl helloServiceImpl = new HelloServiceImpl();
        //创建通知处理器
        Advice beforeAdvice = new BeforeAdvice(helloServiceImpl, logTask);
        //为目标对象生成代理，并声明通知处理器；返回代理类型为目标对象的接口类型
        HelloService helloServiceImplProxy = (HelloService) SimpleAOP.getProxy(helloServiceImpl,beforeAdvice);
        //相当于通知+目标对象对方法的实现
        helloServiceImplProxy.sayHelloWorld();
    }
}
