package finder.forum;

import finder.Item;

import java.util.List;

/**
 * Created by dmitrijtitenko on /87/16.
 */
public interface Forum {

    String userAgent = "Mozilla/5.0 (iPad; CPU OS 6_1_2 like Mac OS X) AppleWebKit/536.26 (KHTML, like Gecko) Version/6.0 Mobile/10B147 Safari/8536.25";

    void get();

    List<Item> parse();
}
