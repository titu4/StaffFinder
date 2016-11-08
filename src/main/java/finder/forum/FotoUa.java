package finder.forum;

import finder.Thread;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class FotoUa extends HTMLpage {

    public FotoUa() {
        section = "photo";
        title = "foto.ua";

        load();
    }

    public void get() {
        for(String currURL:urlList) {
            for(int i=1;i<=5;i++) {
                String url = currURL + "/page" + i + "?order=desc";
                try {
                    Connection connection = Jsoup.connect(url);
                    connection.timeout(0);
                    connection.execute();
                    Connection.Response response = connection.response();

                    if (response.statusCode() == 200) {
                        Document document = connection.get();
                        htmlList.add(document.select("li.threadbit"));
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
                if (!"gaw".equals(element.attr("id"))) {
                    Thread thread = new Thread();

                    Element a = element.select("a[href]").first();
                    Element info = element.select("div.threadinfo").first();

                    thread.setText(a.text());
                    thread.setLink( "http://" + title + "/forum/" + a.attr("href"));
                    thread.setTitle(info.attr("title"));

                    threads.add(thread);
                }
            }
        }
    }
}
