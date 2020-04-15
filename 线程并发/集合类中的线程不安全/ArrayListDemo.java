/**
 * A:多线程下的ArrayList表现如何？
 * B:会产生concurrentModificationException
 */

import java.util.ArrayList;
import java.util.List;

public class ArrayListDemo {
    public static void main(String[] args) {
        List<String> list=new ArrayList<>();
        for(int i=0;i<300;i++){
            int finalI = i;
            new Thread(()->{
                list.add(String.valueOf(finalI));
                System.out.println(Thread.currentThread().getName()+"   "+list);
            },String.valueOf(i)).start();
        }

    }
}
