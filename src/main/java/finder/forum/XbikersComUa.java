package finder.forum;

import org.apache.log4j.Logger;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import finder.Thread;

import java.io.IOException;

public class XbikersComUa extends HTMLpage {

    public XbikersComUa(){
        logger = Logger.getLogger("x-bikers");

        section = "cycle";
        title = "x-bikers.com.ua";

        load();
    }

    public void get() {
        for(String currURL:urlList) {
            for(int i=0;i<=50;i++) {
                String url = currURL + "/index.php?&page=" + i;
                logger.debug("[" + logger.getName() + "]" + "get: " + url);
                try {
                    Connection connection = Jsoup.connect(url);
                    connection.timeout(0);
                    connection.execute();
                    Connection.Response response = connection.response();

                    if (response.statusCode() == 200) {
                        Document document = connection.get();
                        htmlList.add(document.select("body > table > tbody > tr:nth-child(2) > td.content > div:nth-child(8) > table > tbody > tr"));
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
            for (Element element : elements) {

                if ( !"".equals(element.attr("valign")) &&
                        element.select("td").size() > 1 ) {

                    Element type = element.select("td").get(1);
                    if("Продам".equals(type.text())) {
                        Thread thread = new Thread();

                        Element a = element.select("a[href]").first();
                        Element r = element.select("td").get(1);
                        Element title = element.select("td").get(5);

                        thread.setText(title.text());
                        thread.setLink(a.attr("href"));
                        thread.setTitle("");

                        threads.add(thread);
                    }

                }
            }
        }
    }
}
