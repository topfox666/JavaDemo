/**
 * A:用继承Thread的方法，重写run(),并创建线程
 * B:线程0,1和main交替运行，而且时间片是谁拿到的并不确定
 * C:有点意思！
 */
public class FirstThreadTest extends Thread{

    //继承thread重写run方法
    public void run(){
        for(int i=0;i<100;i++){
            System.out.println(getName()+"   "+i);
        }
    }

    public static void main(String[] args) {
        for(int i=0;i<100;i++){
            System.out.println(Thread.currentThread().getName()+"   "+i);
            if(i==50){
                new FirstThreadTest().start();
                new FirstThreadTest().start();
            }
        }
    }
}
