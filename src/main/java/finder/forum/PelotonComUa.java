package finder.forum;

import finder.Item;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dmitrijtitenko on /87/16.
 */

public class PelotonComUa implements Forum {

    private String url;
    private Elements lists;
    List<String> urlList = new ArrayList<>();

    public PelotonComUa() {
        load();
        url = urlList.get(1);
    }

    private void load() {
        urlList.add("http://peloton.com.ua/finder.forum/viewforum.php?f=223");
    }

    public void get() {
        try {
            Document doc = Jsoup.connect(url).get();
            lists = doc.select("ul.topiclist.topics>li>dl>dt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void print() {
        for(Element thread: lists){
            Element a = thread.select("a[href]").first();
            System.out.println(thread.text());
        }
    }

    @Override
    public List<Item> parse() {
        return null;
    }
}
