/**
 * A:用线程池创建线程
 * B:Executors.newFixedThreadPool(固定线程池的个数)；  适合执行长期任务的情景
 *   Executors.newSingleThreadExecutor(); 单个线程的线程池  适合任务序列化执行的场景
 *   Executors.newCashedThreadExecutor(); 不限数量的线程池  适合短期异步的程序或者负载均衡较轻的服务器
 */

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExcutorDemo {
    public static void main(String[] args) {
    //    ExecutorService executorService= Executors.newSingleThreadExecutor();
        //ExecutorService executorService= Executors.newFixedThreadPool(5);
        ExecutorService executorService= Executors.newCachedThreadPool();

        try{
            for(int i=0;i<10;i++) {
                new Thread(()->{
                    System.out.println(Thread.currentThread().getName()+"被创建");
                    try {
                        TimeUnit.SECONDS.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }).start();
        }
        }finally {
                executorService.shutdown();
            }


        }

}
