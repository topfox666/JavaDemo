/**(volatile可见性验证)
 * A:加volatile与不加volatile的情况跑两个线程
 * B:加了volatile后会立即写入主内存，不加不可见。
 * C:fantastic baby!
 */

import java.util.concurrent.TimeUnit;

public class VolatileDemo1 {
    public static void main(String[] args) {
        MyData myData=new MyData();
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"\t come in");
            //暂停一会儿
            try{
                TimeUnit.SECONDS.sleep(3);
            }catch (InterruptedException e){
                  e.printStackTrace();
            }
            myData.addTo60();
            System.out.println(Thread.currentThread().getName()+"\t updated number value:   "+myData.number);
        },"AAA").start();

        //第二个线程是main线程
        while(myData.number==0){
            //main线程一直循环等待直到不等于0
        }
        System.out.println(Thread.currentThread().getName()+" can see you"+myData.number);
    }

}
class MyData{
    volatile int number=0;
    void addTo60(){
        this.number=60;
    }
}
