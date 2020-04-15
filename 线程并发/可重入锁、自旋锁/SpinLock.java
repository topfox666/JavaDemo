/**
 * A:设计一个自旋锁
 * B:new两个线程，其中一个等待25s后再退出，另一个就会一直wait
 */

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class SpinLock {
    AtomicReference<Thread> atomicReference=new AtomicReference<>();
    void mylock(){
        Thread thread=Thread.currentThread();
        System.out.println(thread.getName()+" coming");
        while(!atomicReference.compareAndSet(null,thread)){
            System.out.println(thread.getName()+" waiting now");
        }
    }
    void myunlock(){
        Thread thread=Thread.currentThread();

        atomicReference.compareAndSet(thread,null);
        System.out.println(thread.getName()+"\t exit");
    }

    public static void main(String[] args) {
        SpinLock sl=new SpinLock();
        new Thread(()->{
            sl.mylock();
            try {
                TimeUnit.SECONDS.sleep(25);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sl.myunlock();
        },"t1").start();

        new Thread(()->{
            sl.mylock();
            try {
                TimeUnit.SECONDS.sleep(25);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sl.myunlock();
        },"t2").start();

    }
}
