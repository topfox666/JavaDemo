/**
 * A:countdownlatch的用法
 * B:倒计时，要等全部的线程执行完 才能执行 await()方法用来阻塞
 */

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch c=new CountDownLatch(5);
        for(int i=0;i<5;i++){
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+" 同学离开教室");
                c.countDown();
            }).start();
        }

        c.await();
        System.out.println(Thread.currentThread().getName()+"班长把门锁上");
    }
}
