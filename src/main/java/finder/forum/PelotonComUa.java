package finder.forum;

import finder.Thread;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
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
            for(int i=0; i<=25; i+=25) {
                String url = currURL + "&start=" + i;
                try {
                    Connection connection = Jsoup.connect(url);
                    connection.timeout(0);
                    connection.execute();
                    Connection.Response response = connection.response();

                    if (response.statusCode() == 200) {
                        Document document = connection.get();
                        htmlList.add(document.select("#page-body > div.forumbg > div > ul.topiclist.topics > li"));
                    } else {
                        System.out.println("Page not found!");
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        parse();
    }

    protected void parse() {
        for(Elements elements:htmlList) {
            for (Element element : elements) {
                Thread thread = new Thread();

                Element a = element.select("a.topictitle").first();

                thread.setText(a.text());
                thread.setLink( "http://" + title + "/forum/" + a.attr("href"));
                thread.setTitle("");

                threads.add(thread);
            }
        }
    }
}
