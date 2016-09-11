package finder;

import java.util.Scanner;
import java.util.regex.MatchResult;

public class Thread {

    private String keyword;
    private String title;
    private String text;
    private String link;

    public void setText(String text) {
        this.text = text;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setLink(String link) { this.link = link; }

    public void print() {
        System.out.println("++++++");
        System.out.println(link);
        System.out.println(title);
        System.out.println(text);
    }

    public boolean search(String i) {
        Scanner titleScanner = new Scanner(title);
        Scanner textScanner = new Scanner(text);

        while(null != titleScanner.findWithinHorizon("(?i)\\b" + i + "\\b", 0)) {
            MatchResult m = titleScanner.match();
            keyword = m.group();
            return true;
        }
        while(null != textScanner.findWithinHorizon("(?i)\\b" + i + "\\b", 0)) {
            MatchResult m = textScanner.match();
            keyword = m.group();
            return true;
        }

        return false;
    }

    public String getLink() {
        return link;
    }


    public String getKeyword() {
        return keyword;
    }

    public String getDesc() {
        return text;
    }
}
