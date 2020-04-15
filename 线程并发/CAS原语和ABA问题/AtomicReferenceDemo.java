/**
 * A:原子引用是什么?
 * B:可见对象也可以用原子引用来做CAS操作;
 *   CAS的弊端是产生ABA问题，也就是当你成功操作了并不意味着你的操作是正确的，
 *   可能中间被其他线程改变了结果又改回来了。
 */

import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceDemo {
    public static void main(String[] args) {
        User z3=new User(22,"z3");
        User ls=new User(25,"ls");
        AtomicReference<User> atomicReference=new AtomicReference<>();
        atomicReference.set(z3);
        System.out.println(atomicReference.compareAndSet(z3,ls)+"    now:"+atomicReference.get().toString());
    }
}
class User{
    @Override
    public String toString() {
        return "User{" +
                "age=" + age +
                ", username='" + username + '\'' +
                '}';
    }

    int age;

    String username;

    public User(int age, String username) {
        this.age = age;
        this.username = username;
    }

        }
