/**
 * A:解决集合并发问题
 * B:Collections的工具类
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CollectionsUtilDemo {
    public static void main(String[] args) {
        List<String> list= Collections.synchronizedList(new ArrayList<>());
        for(int i=0;i<1000;i++){
            new Thread(()->{
                list.add("??");
                System.out.println(list);
            },String.valueOf(i)).start();
        }

    }
}
