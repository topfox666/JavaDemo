class Prints{
    int[] array;
    int index=0,n=0;
    public Prints(int[] array){
        this.array=array;
        n=array.length;
    }
    synchronized void print(){
        System.out.println(Thread.currentThread().getName()+"正在打印"+array[index%n]);
        index++;
    }
}
public class Test3 {
    public static void main(String[] args) {
        Prints p=new Prints(new int[]{1,2,3,4,5});
        for(int i=0;i<10;i++){
            new Thread(()->{
                p.print();
            }).start();
        }
    }
}
