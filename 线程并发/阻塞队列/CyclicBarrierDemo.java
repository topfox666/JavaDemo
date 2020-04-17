/**
 * A:CyclicBarrier的用法
 * B:正的计数器，全部线程都执行完才会执行 但是await放在线程中，阻塞的确是new方法里的线程
 */

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier=new CyclicBarrier(7,()->{
            System.out.println("集齐7龙珠哈哈哈");
        });    //被阻塞
        for(int i=0;i<7;i++){
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"就绪");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }

            }).start();
        }
    }
}
