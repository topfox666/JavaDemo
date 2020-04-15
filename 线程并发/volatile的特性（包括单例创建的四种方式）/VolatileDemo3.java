/**
 * A:解决原子性问题
 * B:用AtomicInteger包，调用getAndIncrement方法
 */

import java.util.concurrent.atomic.AtomicInteger;

public class VolatileDemo3 {
    public static void main(String[] args) {
        MyData3  myData3=new MyData3();
        for(int i=0;i<20;i++){
            new Thread(()->{
                for(int j=1;j<=1000;j++){

                    myData3.addAtomic();
                }
            },String.valueOf(i)).start();
        }
        while(Thread.activeCount()>2){
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName()+"\t finally number value"+myData3.atomicInteger);

    }
}
class MyData3 {
    volatile int number = 0;
    //可以用synchronized但效率低
    /*synchronized public void addPlusPlus(){
        number++;
    }*/

    AtomicInteger atomicInteger=new AtomicInteger();
    public void addAtomic(){
        atomicInteger.getAndIncrement();
    }
}