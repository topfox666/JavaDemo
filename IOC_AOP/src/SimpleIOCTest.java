public class SimpleIOCTest {
    public static void main(String[] args) throws Exception{
        SimpleIOCTest t=new SimpleIOCTest();
        t.getBean();
    }
    public void getBean() throws Exception {
        //这边的路径是相对路径。
        String location = ".\\src\\ioc.xml";
        SimpleIOC bf = new SimpleIOC(location);
        Wheel wheel = (Wheel) bf.getBean("wheel");
        System.out.println(wheel);
        Car car = (Car) bf.getBean("car");
        System.out.println(car);
    }
}
