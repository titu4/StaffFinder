package finder.forum;

import finder.Thread;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Kharkovforum extends HTMLpage {

    public Kharkovforum() {
        section = "photo";
        title = "kharkovforum.com";

        load();
    }

    public void get() {
        for(String currURL:urlList) {
            for(int i=1;i<=5;i++) {
                String url = currURL + "/i" + i;
                try {
                    Connection connection = Jsoup.connect(url);
                    connection.timeout(0);
                    connection.execute();
                    Connection.Response response = connection.response();

                    if (response.statusCode() == 200) {
                        Document document = connection.get();
                        htmlList.add(document.select("#threadbits_forum_41 > tr"));
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

                Element td = element.select("td").get(1);
                Element a = td.select("a").get(0);
                if(a.attr("href").equals("#"))
                    a = td.select("a").get(1);

                thread.setText(a.text());
                thread.setLink(a.attr("href"));
                thread.setTitle(td.attr("title"));

                threads.add(thread);
            }
        }
    }
}
