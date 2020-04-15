/**
 * A:饿汉式下的单例模式
 * B:如果不加synchronized也不会创建多个，因为类加载的时候就已经创建好单例模式，因此结果是main创建单例模式
 * C:与懒汉+synchronized不同的是，第一个创建出单例的线程是main
 */
public class SingleTon2 {
    private static SingleTon2 instance=new SingleTon2();
    private SingleTon2(){
        System.out.println(Thread.currentThread().getName()+"  创建单例模式  ");
    }
    public static SingleTon2 getInstance(){
        return instance;
    }

    public static void main(String[] args) {
        for(int i=0;i<10000000;i++){
            new Thread(()->{
                SingleTon2.getInstance();
            },String.valueOf(i)).start();
        }
    }
}
