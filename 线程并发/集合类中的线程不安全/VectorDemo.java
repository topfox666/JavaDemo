/**
 * A:多线程下的Vector表现如何？
 * B:Vector是加锁的线程安全容器，保证线程安全却不保证并发性。 加了synchronized的add方法。
 */

import java.util.List;
import java.util.Vector;

public class VectorDemo {
    public static void main(String[] args) {
        List<String> list=new Vector<>();
        for(int i=0;i<1000;i++){
            new Thread(()->{
                list.add("??");
                System.out.println(list);
            },String.valueOf(i)).start();
        }

    }
}
