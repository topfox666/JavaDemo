/**
 * A:通过AtomicStampedReference 来设置版本号
 * B:即使值一样，版本号不同也不会修改。
 */

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

public class ABADemo {
    static AtomicReference<Integer> atomicReference=new AtomicReference<>(100);
    static AtomicStampedReference<Integer> atomicStampedReference=new AtomicStampedReference<>(100,1);
    public static void main(String[] args) {

        new Thread(()->{
            atomicReference.compareAndSet(100,101);
            atomicReference.compareAndSet(101,100);
        },"t1").start();
        new Thread(()->{
            int stamp=atomicStampedReference.getStamp();
            //暂停一秒钟让线程1做完
            try{
                TimeUnit.SECONDS.sleep(1);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            System.out.println(atomicStampedReference.compareAndSet(100,2019,stamp,stamp)+"\t"+atomicStampedReference.getStamp());
        },"t2").start();

        new Thread(()->{
            int stamp=atomicStampedReference.getStamp();
            //暂停一秒钟让线程2做完
            try{
                TimeUnit.SECONDS.sleep(3);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            System.out.println(atomicStampedReference.compareAndSet(2019,2020,stamp,stamp)+"\t"+atomicStampedReference.getStamp());
        },"t3").start();



    }
}
