import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ABC三个线程分别轮流打印5，10,15次。
 * 用condition控制
 */
class PrintNum{
    int num=1;
    Lock lock=new ReentrantLock();
    Condition c1=lock.newCondition();
    Condition c2=lock.newCondition();
    Condition c3=lock.newCondition();
    public void printA(){
        lock.lock();
        try{
            while(num!=1){
                c1.await();
            }
            for(int i=1;i<=5;i++){
                System.out.println(Thread.currentThread().getName()+"prints "+i);
            }
            num=2;
            c2.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public void printB(){
        lock.lock();
        try{
            while(num!=2){
                c2.await();
            }
            for(int i=1;i<=10;i++){
                System.out.println(Thread.currentThread().getName()+"prints "+i);
            }
            num=3;
            c3.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public void printC(){
        lock.lock();
        try{
            while(num!=3){
                c3.await();
            }
            for(int i=1;i<=15;i++){
                System.out.println(Thread.currentThread().getName()+"prints "+i);
            }
            num=1;
            c1.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
        }
public class ConditionDemo {
    public static void main(String[] args) {
        PrintNum p=new PrintNum();
        new Thread(()->{
            for(int i=0;i<5;i++){
                p.printA();
            }
        },"A").start();
        new Thread(()->{
            for(int i=0;i<5;i++){
                p.printB();
            }
        },"B").start();
        new Thread(()->{
            for(int i=0;i<5;i++){
                p.printC();
            }
        },"C").start();
    }
}
