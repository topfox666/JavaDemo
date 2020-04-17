/**
 * 阻塞队列集合：接口BlockingQueue<>
 * 有 ArrayBlockingQueue<>(填写队列容量)  LinkedBlockingQueue<>()
 * add() 和 take() 方法会抛异常 如果队列空还取满还放   element()返回第一个元素
 * offer()和poll() 方法返回true false offer(元素，时间，时间单位)声明被阻塞时间
 * SynchronousQueue() 不存取元素，就是一个占位的，占了就不能再占一直到取出来为止才能再占。
 */

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class BlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
       /* BlockingQueue<String>  blockingQueue=new ArrayBlockingQueue<>(3);
        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.offer("d"));*/
        BlockingQueue<Integer> blockingQueue=new SynchronousQueue<>();
                new Thread(()->{
                try {

                    System.out.println(Thread.currentThread().getName()+"放进去");
                    blockingQueue.put(2);


                    System.out.println(Thread.currentThread().getName()+"放进去");
                    blockingQueue.offer(1,3,TimeUnit.SECONDS);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();

        TimeUnit.SECONDS.sleep(10);
            new Thread(()->{
                try {
                    TimeUnit.SECONDS.sleep(10);

                    System.out.println(Thread.currentThread().getName()+"取出来"+blockingQueue.take());

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }


}
