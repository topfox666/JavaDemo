/**
 * JDK动态代理
 */

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


public class TestJdkDynamicProxy {
    public static void main(String[] args) {
        IRegisterService registerService=new RegisterServiceImpl();
        InsertDataHandler insertDataHandler=new InsertDataHandler();
        //创建动态代理，传入参数为要代理的类
        IRegisterService proxy=(IRegisterService)insertDataHandler.getProxy(registerService);
        proxy.register("topfox","888");
    }
}

class InsertDataHandler implements InvocationHandler{

    Object obj;
    public Object getProxy(Object obj){
        this.obj=obj;
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(),obj.getClass().getInterfaces(),this);
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        doBefore();
        //被代理对象的实现
        Object result=method.invoke(obj, args);
        doAfter();
        System.out.println(result);
        return result;
    }
    private void doBefore(){
        System.out.println("[Proxy]一些前置处理");
    }
    private void doAfter(){
        System.out.println("[proxy]一些后置处理");
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