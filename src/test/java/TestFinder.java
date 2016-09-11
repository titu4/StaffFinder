import finder.Thread;
import finder.forum.Forum;
import finder.forum.FotoUa;
import org.junit.Test;

import java.util.List;
import java.util.Scanner;
import java.util.regex.MatchResult;

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
    public void testScaner() {
        String i = "pocket wizard";
        Scanner s = new Scanner(" long text to search java. pocket wizard and other stuff. pocket wizard and lalalal");
        while(null != s.findWithinHorizon("(?i)\\b" + i + "\\b", 0)) {
            MatchResult m = s.match();
            System.out.println(m.group());
        }

    }
}
