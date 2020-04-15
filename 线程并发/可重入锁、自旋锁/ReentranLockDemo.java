import java.util.concurrent.locks.ReentrantLock;

/**
 * A:验证synchronize 和 ReentranLock的可重入性（两个均非公平，其中re可设置成公平，默认非公平）
 * B:由于是可重入的不会产生死锁，分开执行。要注意lock和unlock的个数要匹配，lock>unlock不会解锁程序不会结束，lock<unlock会抛异常
 */
public class ReentranLockDemo {
    public static void main(String[] args) {
        Phone p=new Phone();
      /*  new Thread(()->{
            p.sentMail();
            System.out.println("I'm t1");
        },"t1").start();
        new Thread(()->{
            p.sentQQ();
            System.out.println("I'm t2");
        },"t2").start();
       */
      new Thread(p,"t3").start();
        new Thread(p,"t4").start();
    }
}
class Phone implements Runnable{
   /* synchronized void sentMail(){
        System.out.println(Thread.currentThread().getName()+"\t"+"invoke sentMail()");
        sentQQ();
    }
    synchronized void sentQQ(){
        System.out.println(Thread.currentThread().getName()+"\t"+"invoke sentQQ()");
    }*/
    ReentrantLock lock=new ReentrantLock();
    public void run() {
        get();
    }
    void get() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t  invoke get()");
            set();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
        void set() {
            lock.lock();
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "\t  invoke set()");

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();

            }
        }
}
