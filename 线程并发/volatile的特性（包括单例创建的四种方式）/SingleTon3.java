/**
 * A:DCL但不加volatile 和 DCL加volatile
 * B:如果不加synchronized也不会创建多个，居然检查不出指令重排的缺陷！！
 * C:其实挺安全的了 但是最好还是加上volatile。
 */
public class SingleTon3 {
    private static volatile SingleTon3 instance=null;
    private SingleTon3(){
        System.out.println(Thread.currentThread().getName()+"  创建单例模式  ");
    }
    public static SingleTon3 getInstance(){
        if(instance==null){
            synchronized (SingleTon3.class){
                if(instance==null)
                    instance=new SingleTon3();
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        for(int i=0;i<10000000;i++){
            new Thread(()->{
                SingleTon3.getInstance();
            },String.valueOf(i)).start();
        }
    }
}
