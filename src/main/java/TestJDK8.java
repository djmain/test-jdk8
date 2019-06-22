//import java.util.Base64;


import inner.Book;
import inner.Person;
import org.junit.Test;
import org.omg.CORBA.PUBLIC_MEMBER;

import java.util.concurrent.TimeUnit;

/**
 * created by Jay on 2019/6/18
 */
public class TestJDK8 extends  Person
{
    public static void main(String[] args)
    {
        for (int i = 0; i < 100; i++)
        {
            byte[] bytes = new byte[2 * 2048];
            System.out.println("prepared to gc");
            System.gc();
            try
            {
                TimeUnit.MILLISECONDS.sleep(500);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testWeakReference()
    {
        Salad salad = new Salad(new Apple("红富士"), "aa");
        //通过WeakReference的get()方法获取Apple
        System.out.println("Apple:" + salad.get());
        System.gc();
        try
        {
            //休眠一下，在运行的时候加上虚拟机参数-XX:+PrintGCDetails，输出gc信息，确定gc发生了。
            Thread.sleep(5000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        //如果为空，代表被回收了
        if (salad.get() == null)
        {
            System.out.println("clear Apple。");
        }
        System.out.println(salad.getValue());
    }


    /**
     * 测试匿名内部类
     */
    @Test
    public void testInnner1()
    {
        Book book = new Book()
        {
            @Override
            public int getPrice()
            {
                return 15;
            }

            @Override
            public String getName()
            {
                return "zhangming";
            }
        };
        System.out.println("price:" + book.getPrice());
        System.out.println("name:" + book.getName());
    }

    /**
     * 测试匿名内部类
     */
    @Test
    public void testInnner2()
    {
        Person person = new Person()
        {
            @Override
            protected int getAge()
            {
                return 15;
            }

            @Override
            public String getName()
            {
                return "hjj:"+getAge();
            }
        };
        System.out.println("age:" + person.getAge());
        System.out.println("name:" + person.getName());
    }

}
