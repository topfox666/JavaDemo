/**
 * A:先实现Callable接口，该类没办法直接用，要用FutureTask封装，
 * B:但是它作为target时只能跑出一个thread,奇怪了
 * C:奇了怪了
 */

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableThreadTest implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        int i=0;
        for(;i<100;i++){
            System.out.println(Thread.currentThread().getName()+"   "+i);
        }
        return i;
    }

    public static void main(String[] args) {
        CallableThreadTest ctt=new CallableThreadTest();
        FutureTask<Integer> ft=new FutureTask<>(ctt);
        for(int i=0;i<1000;i++){
            System.out.println(Thread.currentThread().getName());
            if(i==60){
                new Thread(ft,"hello 1").start();
                new Thread(ft,"goodbye 2").start();
            }
        }
        try{
            System.out.println("The result "+ft.get());
        }catch (InterruptedException e){
            e.printStackTrace();
        }catch (ExecutionException e){
            e.printStackTrace();
        }
    }

}
