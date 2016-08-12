package finder;

import finder.forum.Forum;
import finder.forum.FotoUa;

import java.util.List;

public class Finder {

    public static void main(String[] args) {
        Forum f = new FotoUa();
        f.get();

        List<Item> list = f.parse();
        for (Item i:list)
            i.print();
    }
}
