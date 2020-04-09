/**
 * 静态代理的例子
 */

public class TestStaticProxy {
    public static void main(String[] args) {
        //创建接口的具体实例
        IRegisterService iRegisterService=new RegisterServiceImpl();
        //创建代理，把需要代理的实例当做参数传入
        IRegisterService proxy=new RegisterServiceProxy(iRegisterService);
        //代理增强了原本的register方法，在方法前后增加了处理
        proxy.register("topfox","666");

    }
}
//注册的接口
interface IRegisterService{
    void register(String name,String pwd);
}
//具体实现
class RegisterServiceImpl implements IRegisterService {

    @Override
    public void register(String name, String pwd) {
        System.out.println(String.format("[向数据库插入数据]name:%s,pwd:%s",name,pwd));
    }
}
//静态代理类，通过传入具体实现的实例来增强具体实现的对象，缺点：要实现一样的接口
class RegisterServiceProxy implements IRegisterService {

    IRegisterService iRegisterService;

    public RegisterServiceProxy(IRegisterService iRegisterService){
        this.iRegisterService=iRegisterService;
    }
    @Override
    public void register(String name, String pwd) {

        System.out.println("[Proxy]一些前置处理");
        System.out.println(String.format("[Proxy]打印注册信息：姓名：%s,密码：%s",name,pwd));
        iRegisterService.register(name,pwd);    //具体实现实例的前后加了打印和处理
        System.out.println("[Proxy]一些后置处理");

    }
}
