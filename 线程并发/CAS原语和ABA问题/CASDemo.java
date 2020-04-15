/**
 * A:AtomicInteger的compareAndSet,先比较再交换;
 * B:  public final int getAndIncrement() {   //this是当前对象，value是内存偏移地址，unsafe类是native方法
 *         return U.getAndAddInt(this, VALUE, 1);
 *     }
 *     是一条CPU并发原语，中间不能有人打断，他是最底层的方法（unsafe）
 */

import java.util.concurrent.atomic.AtomicInteger;

public class CASDemo {
    public static void main(String[] args) {
        AtomicInteger atomicInteger=new AtomicInteger(5);
        System.out.println(atomicInteger.compareAndSet(5,2020)+"   "+atomicInteger.get());
        System.out.println(atomicInteger.compareAndSet(2020,5)+"   "+atomicInteger.get());

        System.out.println(atomicInteger.compareAndSet(5,2021)+"   "+atomicInteger.get());
       atomicInteger.getAndIncrement();
    }
}
