/**
 * A:静态内部类创建单例模式
 * B:多线程安全
 * C:第一个生成单例的线程不是main,说明不是在类加载的时候实例化的，要用的时候才生成。
 */
public class SingleTon4 {
    private SingleTon4(){
        System.out.println(Thread.currentThread().getName()+"  创建单例模式  ");
    }
    private static class T{
        private static SingleTon4 instance =new SingleTon4();
    }
    public static SingleTon4 getInstance(){
       return T.instance;
    }

    public static void main(String[] args) {
        for(int i=0;i<10000000;i++){
            new Thread(()->{
                SingleTon4.getInstance();
            },String.valueOf(i)).start();
        }
    }
}
