/**
 * A:模拟生产者消费者模式
 * B:控制面包数量3个，多了不生产少于0不能买
 * C:一开始程序停不下来以为是没有unlock,后来发现是面包师不做了但是消费者还在排队。
 */

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Bread{
    int num=0;
    Lock lock=new ReentrantLock();
    Condition condition=lock.newCondition();
    public void producer(){
        lock.lock();
        System.out.println(Thread.currentThread().getName()+" 准备生产面包,当前数量"+num);
                try {
                    while(num>=3){
                condition.await();
            }
                    num++;
                    System.out.println(Thread.currentThread().getName()+" 生产完面包,当前数量"+num);
                    condition.signalAll(); }
                catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                    lock.unlock();
                }

    }
    public void consumer(){
        lock.lock();
        System.out.println(Thread.currentThread().getName()+" 排队买面包,当前还剩"+num);
        try {
        while(num<=0){
                condition.await();
            }
        num--;
        System.out.println(Thread.currentThread().getName()+" 买完面包,当前还剩"+num);
        condition.signalAll();
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }
}
public class LockConditionPCDemo {

    public static void main(String[] args) {
        Bread bread=new Bread();
        new Thread(()->{
            for(int i=0;i<10;i++) {
                bread.producer();
            }
        },"面包师").start();
        new Thread(()->{
            for(int i=0;i<5;i++) {
            bread.consumer();
        }
        },"顾客1").start();
        new Thread(()->{
            for(int i=0;i<5;i++) {
                bread.consumer();
            }
        },"顾客2").start();
    }
}
