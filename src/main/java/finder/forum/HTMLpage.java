package finder.forum;

import finder.Thread;
import org.jsoup.select.Elements;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public abstract class HTMLpage implements Forum {

    public String title;
    protected String section;

    protected List<String> urlList = new ArrayList<>();
    protected List<Elements> htmlList = new ArrayList<>();
    protected List<Thread> threads = new ArrayList<>();

    protected List<String> interests = new ArrayList<>();

    protected void load() {
        loadLocations();
        loadInterests();
    }

    public List<Thread> search() {
        List<Thread> hotItems = new ArrayList<>();

        for (Thread thread: threads){
            for(String i:interests )
                if(thread.search(i)) {
                    hotItems.add(thread);
                }

        }
        return hotItems;
    }

    protected void loadInterests() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new FileInputStream("./src/main/resources/interests.xml"));
            doc.getDocumentElement().normalize();

            NodeList nodes = doc.getElementsByTagName(section);
            for (int i=0;i<nodes.getLength();i++) {
                Element element = (Element) nodes.item(i);
                interests.add(element.getTextContent());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void loadLocations() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new FileInputStream("./src/main/resources/location.xml"));
            doc.getDocumentElement().normalize();

            NodeList nodes = doc.getElementsByTagName(section);
            for (int i=0;i<nodes.getLength();i++) {
                Element element = (Element) nodes.item(i);
                if(element.getAttribute("forum").equals(title))
                    urlList.add(element.getTextContent());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
