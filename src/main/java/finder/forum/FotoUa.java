package finder.forum;

import finder.Item;
import org.jsoup.Connection;
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
public class FotoUa implements Forum {

    private String currUrl;
    Elements htmlList;
    List<String> urlList = new ArrayList<>();

    public FotoUa() {
        load();
        currUrl = urlList.get(1);
    }

    private void load() {
        urlList.add("http://foto.ua/forum/forums/54-plenochnyie-kameryi"); //film cameras
        urlList.add("http://foto.ua/forum/forums/64-prochee"); //lens->other
    }

    public void get() {
        try {
            Connection connection = Jsoup.connect(currUrl);
            connection.execute();
            Connection.Response response = connection.response();

            if(response.statusCode()==200) {
                Document document = connection.get();
                htmlList = document.select("li.threadbit");
            } else {
                System.out.println("Page not found!");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Item> parse() {
        List<Item> itemsList = new ArrayList<>();

        for(Element element: htmlList){
            if(!"gaw".equals(element.attr("id"))) {
                Item item = new Item();

                Element a = element.select("a[href]").first();
                Element info = element.select("div.threadinfo").first();

                item.setText(a.text());
                item.setTitle(info.attr("title"));

                itemsList.add(item);
            }
        }

        return itemsList;
    }
}
