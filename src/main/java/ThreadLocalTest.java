import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * created by Jay on 2019/6/20
 */
public class ThreadLocalTest
{
    private static ThreadLocal<String> threadLocal = new ThreadLocal()
    {
        @Override
        protected String initialValue()
        {
            String initialValue = Thread.currentThread().getName();
            System.out.println("initialValue:" + initialValue);
            return initialValue;
        }
    };

    public static String get()
    {
        return threadLocal.get();
    }

    public static void set(String value)
    {
        threadLocal.set(value);
    }

    public static void main(String[] args)
    {
        int toal = 50000;
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                test();
            }

            private void test()
            {
                for (int i = 0; i < toal; i++)
                {
                    System.out.println(Thread.currentThread().getName() + " get value:" + threadLocal.get());

                    try
                    {
                        TimeUnit.MILLISECONDS.sleep(500);
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                    String nextValue = threadLocal.get() + String.valueOf(i);
                    threadLocal.set(nextValue);
                    threadLocal.remove();
                }
            }
        }).start();
//
//        new Thread(() ->
//                   {
//                       for (int i = 0; i < toal; i++)
//                       {
//                           System.out.println(Thread.currentThread().getName() + " get value:" + threadLocal.get());
//                           try
//                           {
//                               TimeUnit.MILLISECONDS.sleep(500);
//                           }
//                           catch (InterruptedException e)
//                           {
//                               e.printStackTrace();
//                           }
//                       }
//                   }).start();

        try
        {
            System.in.read();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}


