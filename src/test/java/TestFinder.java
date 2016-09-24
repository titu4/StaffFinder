import finder.Thread;
import finder.forum.Forum;
import finder.forum.FotoUa;
import finder.forum.XbikersComUa;
import org.junit.Test;

import java.util.List;

public class TestFinder {

    @Test
    public void testFotoua() {
        Forum f = new FotoUa();
        f.get();
        List<Thread> items = f.search();

        for (Thread t:items) {
            System.out.println("-->" + t.getKeyword());
            System.out.println(t.getDesc());
            System.out.println(t.getLink());
            System.out.println();
        }
    }

    @Test
    public void test_x_bikers() {
        Forum f = new XbikersComUa();
        f.get();
        List<Thread> items = f.search();

        for (Thread t:items) {
            System.out.println("-->" + t.getKeyword() + "!");
            System.out.println(t.getDesc());
            System.out.println(t.getLink());
            System.out.println();
        }
    }

}
