/**
 * A:用实现runnable接口的方式实现run创建线程；
 * B:结果还是和刚才DEMo1的类似哈哈
 * C:这次比较集中不会交叉着来，可见这个线程很可爱，让人捉摸不透！
 */
public class RunnableThreadTest implements Runnable{
    public void run(){
        for(int i=0;i<100;i++){
            System.out.println(Thread.currentThread().getName()+"   "+i);
        }
    }

    public static void main(String[] args) {
        for(int i=0;i<100;i++){
            System.out.println(Thread.currentThread().getName()+"   "+i);
            if(i==60){
                RunnableThreadTest rtt=new RunnableThreadTest();
                new Thread(rtt,"new thread 1").start();
                new Thread(rtt,"new thread 2").start();
            }
        }
    }
}
