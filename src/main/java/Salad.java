import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * created by Jay on 2019/6/20
 */
public class Salad extends WeakReference<Apple>
{
    private Object value ;

    public Salad(Apple referent, Object value)
    {
        super(referent);
        this.value = value;
    }



    public Object getValue()
    {
        return value;
    }

    public void setValue(Object value)
    {
        this.value = value;
    }

    public Salad(Apple apple) {
        super(apple);
    }
}
