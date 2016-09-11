package finder.forum;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class PelotonComUa extends HTMLpage {

    private Elements lists;

    public PelotonComUa() {
        section = "cycle";
        title = "peloton.com.ua";

        load();
    }

    public void get() {
        for(String currURL:urlList) {
            try {
                Connection connection = Jsoup.connect(currURL);
                connection.execute();
                Connection.Response response = connection.response();

                if (response.statusCode() == 200) {
                    Document document = connection.get();
                    htmlList.add(document.select("ul.topiclist.topics>li>dl>dt"));
                } else {
                    System.out.println("Page not found!");
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void parse() {
    }
}
