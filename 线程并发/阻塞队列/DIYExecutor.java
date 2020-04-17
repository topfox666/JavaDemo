/**
 * A:diy一个线程池，参数有（corePoolSize,maximumPoolSize,keepAliveTime,TimeUnit,缓冲队列通过阻塞队列来实现,threadFactory一般用默认,拒绝策略）
 */

import java.util.concurrent.*;

public class DIYExecutor {
    public static void main(String[] args) {
        BlockingQueue<Runnable> blockingQueue=new LinkedBlockingQueue<>(3);
        ExecutorService executorService= new ThreadPoolExecutor(2,3,1L, TimeUnit.SECONDS,blockingQueue, Executors.defaultThreadFactory(),new ThreadPoolExecutor.DiscardOldestPolicy());
        System.out.println(Runtime.getRuntime().availableProcessors());
       /* for(int i=0;i<15;i++){
            executorService.execute(()->{
                System.out.println(Thread.currentThread().getName()+"执行中");
            });
        }
        executorService.shutdown();
        for(int i=0;i<3;i++){
            System.out.println(blockingQueue.poll().toString());
        }*/
    }
}
