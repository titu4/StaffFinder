package finder.forum;

import finder.Thread;
import org.apache.log4j.Logger;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class TouristKharkovUa extends HTMLpage {

    private static String baseURL;

    public TouristKharkovUa() {
        logger = Logger.getLogger("XT");
        section = "cycle";
        title = "tourist.kharkov.ua";

        load();
    }

    public void get() {
        for(String currURL:urlList) {
            baseURL = currURL;
            for(int i=0 ; i<=1360 ; i = i+20) {
                String url = currURL + "viewforum.php?f=44&sk=m&sd=d&start=" + i;
                logger.debug("[" + logger.getName() + "]" + "get: " + url);
                try {
                    Connection connection = Jsoup.connect(url);
                    connection.timeout(0);
                    connection.execute();
                    Connection.Response response = connection.response();

                    if (response.statusCode() == 200) {
                        Document document = connection.get();
                        htmlList.add(document.select("#pagecontent > table.tablebg > tbody > tr"));
                    } else {
                        logger.error(logger.getName() +
                                "\nPage not found");
                    }

                } catch (IOException e) {
                    logger.error(e.getStackTrace());
                }
            }
        }

        parse();
    }

    protected void parse() {
        for(Elements elements:htmlList) {
            int i = 0;
            for (Element element : elements) {
                i++;
                if((i>6 && i<45) && element.attr("align").isEmpty()) {

                    Thread thread = new Thread();

                    Element r = element.select("td").get(2);
                    Element a = r.select("a[href]").first();
                    Element price = element.select("td").get(4);

                    thread.setText(price.text());
                    thread.setLink(a.attr("href"));
                    thread.setTitle(r.text());

                    threads.add(thread);
                }
            }
        }
    }

    public static String getBasseURL() {
        return baseURL;
    }

}
