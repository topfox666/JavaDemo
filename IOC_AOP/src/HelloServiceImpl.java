/**
 * 被代理的类
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public void sayHelloWorld() {
        System.out.println("Hey!Fox said hello world!");
    }
}
