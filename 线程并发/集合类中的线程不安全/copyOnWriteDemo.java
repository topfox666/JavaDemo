/**
 * A:解决集合并发问题
 * B:CopyOnWrite  : private transient volatile Object[] array;
 * C:其实线程不安全的集合类有很多 还有 map set ....
 *
 */

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class copyOnWriteDemo {
    public static void main(String[] args) {
        List<String> list= new CopyOnWriteArrayList<>();
        for(int i=0;i<1000;i++){
            new Thread(()->{
                list.add("??");
                System.out.println(list);
            },String.valueOf(i)).start();
        }

    }
}
