/**
 * A:写一个读写锁
 * B:从结果可以看出来 读写锁的时候线程的写是分离的 读是并发的
 */

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockDemo {
    ReentrantReadWriteLock reentrantReadWriteLock=new ReentrantReadWriteLock();
    private  volatile Map<Integer,Object> map=new HashMap<>();
    void put(int i,Thread thread) throws InterruptedException {
       // reentrantReadWriteLock.writeLock().lock();
        System.out.println(Thread.currentThread().getName()+" 正在写入："+"第"+i);
        TimeUnit.MILLISECONDS.sleep(10);
        map.put(i,Thread.currentThread().getName());
        System.out.println(Thread.currentThread().getName()+"写入完成");
    //    reentrantReadWriteLock.writeLock().unlock();
    }
    Object get(int i) throws InterruptedException {
  //      reentrantReadWriteLock.readLock().lock();
        System.out.println(Thread.currentThread().getName()+"正在读取："+"第"+i);
        TimeUnit.MILLISECONDS.sleep(10);
        Object o=map.get(i);
        System.out.println(Thread.currentThread().getName()+"读取完成");
    //    reentrantReadWriteLock.readLock().unlock();
        return o;
    }

    public static void main(String[] args) throws InterruptedException {
        ReadWriteLockDemo r=new ReadWriteLockDemo();
        for(int i=0;i<5;i++){
            int finalI = i;
            new Thread(()->{
                try {
                    r.put(finalI,Thread.currentThread());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },"t"+i).start();
        }
        TimeUnit.SECONDS.sleep(5);
        for(int i=0;i<5;i++){
            int finalI1 = i;
            new Thread(()->{
                try {
                    System.out.println(Thread.currentThread().getName()+"result is"+r.get(finalI1));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },"t"+i).start();
        }
        TimeUnit.SECONDS.sleep(30);
         for(int i=0;i<5;i++){
             System.out.println(r.map.get(i));
         }
    }
}
