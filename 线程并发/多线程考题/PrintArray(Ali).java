import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Test{
    public static void main(String[] args) {
       Print p=new Print();
       new Thread(()->{
           while(true)
            p.printA();
        }).start();
        new Thread(()->{
            while(true)
            p.printB();
        }).start();
        new Thread(()->{
            while(true)
            p.printC();
        }).start();
    }

}
class Print{
    int[] array=new int[]{1,2,5,6,7};
    int index=0,n=1;
    Lock lock=new ReentrantLock();
    Condition c1=lock.newCondition();
    Condition c2=lock.newCondition();
    Condition c3=lock.newCondition();
    public void printA(){
        lock.lock();
        try{
            while(n!=1){
                c1.await();
            }
            System.out.println(Thread.currentThread().getName()+array[index%5]);
            index++;
            n=2;
            c2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public void printB(){
        lock.lock();
        try{
            while(n!=2){
                c2.await();
            }
            System.out.println(Thread.currentThread().getName()+array[index%5]);
            index++;
            n=3;
            c3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public void printC(){
        lock.lock();
        try{
            while(n!=3){
                c3.await();
            }
            System.out.println(Thread.currentThread().getName()+array[index%5]);
            index++;
            n=1;
            c1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}