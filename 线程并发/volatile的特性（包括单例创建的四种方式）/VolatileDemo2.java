/**(验证volatile不保证原子性)
 * A:创建20个线程每个线程执行1000次的++操作
 * B:最终结果不会是20000，因为++不是原子操作
 *   原因:++的底层操作->先拷贝到工作内存，进行+1操作，再刷回主内存
 *   字节码： aload_0  加载number
 *           dup
 *           getField    拿到原始的number
 *           iadd        执行+1操作
 *           putfield    执行写把累加后的值写回
 *   产生写覆盖!
 * C:其实出现过一次20000,hhhh
 */
public class VolatileDemo2 {
    public static void main(String[] args) {
        MyData2  myData2=new MyData2();
        for(int i=0;i<20;i++){
           new Thread(()->{
               for(int j=1;j<=1000;j++){
                   myData2.addPlusPlus();;
               }
           },String.valueOf(i)).start();
        }
       while(Thread.activeCount()>2){
           Thread.yield();
       }
        System.out.println(Thread.currentThread().getName()+"\t finally number value"+myData2.number);
    }

}
class MyData2 {
    volatile int number = 0;

     void addPlusPlus(){
        number++;
    }
}
