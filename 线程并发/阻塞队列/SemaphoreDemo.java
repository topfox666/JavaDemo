/**
 * A:Semaphore的使用
 * B:在实例化的时候声明互斥量，没有acquire就没办法执行 离开后要release。
 */

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore=new Semaphore(3);    //共享资源数
        for(int i=0;i<10;i++){
            new Thread(()->{
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"抢车位");
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    System.out.println(Thread.currentThread().getName()+"离开车位");
                    semaphore.release();
                }
            }).start();
        }
    }
}
