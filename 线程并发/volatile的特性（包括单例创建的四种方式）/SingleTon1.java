/**
 * A:懒汉式下的单例模式
 * B:如果不加synchronized会创建多个单例
 * C:把i设到10000才看出来。
 */
public class SingleTon1 {
    private static SingleTon1 instance;
    private SingleTon1(){
        System.out.println(Thread.currentThread().getName()+"  创建单例模式  ");
    }
    public synchronized static SingleTon1 getInstance(){
        if(instance==null)
        instance=new SingleTon1();
        return instance;
    }

    public static void main(String[] args) {
        for(int i=0;i<10000000;i++){
            new Thread(()->{
                SingleTon1.getInstance();
            },String.valueOf(i)).start();
        }
    }
}
