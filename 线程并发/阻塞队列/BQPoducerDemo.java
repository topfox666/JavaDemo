import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class Cookie{
    private volatile boolean FLAG=true;
    private AtomicInteger atomicInteger=new AtomicInteger(0);
    BlockingQueue<String> blockingQueue=null;
    public Cookie(BlockingQueue<String> blockingQueue){
        this.blockingQueue=blockingQueue;
        System.out.println(blockingQueue.getClass().getName());
    }

    public void producer() throws Exception{
        String data;
        boolean shallP;
        while(FLAG){
            data=atomicInteger.incrementAndGet()+"";
            shallP=blockingQueue.offer(data,2L, TimeUnit.SECONDS);
            if(shallP){
                System.out.println(Thread.currentThread().getName()+"生产成功"+data);
            }
            else{
                System.out.println(Thread.currentThread().getName()+"生产失败");
                TimeUnit.SECONDS.sleep(2);
            }
        }
        System.out.println("结束生产");
    }
    public void consumer() throws Exception {
        String result = null;
        while (FLAG) {
            result = blockingQueue.poll(2L, TimeUnit.SECONDS);
            if (result == null || result.equalsIgnoreCase("")) {
                FLAG = false;
                System.out.println(Thread.currentThread().getName() + "消费失败");
                return;
            }
            System.out.println("成功购买");
        }
    }
        public void stop(){
            System.out.println("关门大吉");
            FLAG=false;
        }
    }
public class BQPoducerDemo {
    public static void main(String[] args) {
        Cookie cookie=new Cookie(new ArrayBlockingQueue<String>(10));
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"顾客来消费");
            try {
           //     cookie.consumer();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"消费者1").start();
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"师傅来制作");
            try {
                cookie.producer();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"师傅1").start();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        cookie.stop();
    }
}
