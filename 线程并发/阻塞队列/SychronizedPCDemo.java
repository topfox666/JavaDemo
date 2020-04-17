class Cake{
    int num=0;
    public synchronized void producer(){
        System.out.println(Thread.currentThread().getName()+"准备生产蛋糕，当前数量"+num);
        while(num>=3){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        num++;
        System.out.println(Thread.currentThread().getName()+"生产完蛋糕,当前数量"+num);
        notify();
    }
    public synchronized void consumer(){
        System.out.println(Thread.currentThread().getName()+"排队，当前数量"+num);
        while(num<=2){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        num-=2;
        System.out.println(Thread.currentThread().getName()+"买完蛋糕,当前数量"+num);
        notify();
    }
}
public class SychronizedPCDemo {
    public static void main(String[] args) {
        Cake cake=new Cake();

        new Thread(()->{
            for(int i=0;i<5;i++){
                cake.consumer();
            }
        },"顾客1").start();
        new Thread(()->{
            for(int i=0;i<5;i++){
                cake.producer();
            }
        },"蛋糕师傅1").start();
    }
}
