package finder;

public class Item {

    private String title;
    private String text;

    public void setText(String text) {
        this.text = text;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void print() {
        System.out.println("++++++");
        System.out.println(title);
        System.out.println(text);
    }
}
