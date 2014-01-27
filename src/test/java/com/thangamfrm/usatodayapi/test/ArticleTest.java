package com.thangamfrm.usatodayapi.test;

import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.sun.syndication.feed.rss.Channel;
import com.sun.syndication.feed.rss.Item;
import com.thangamfrm.usatoday.USATodayClient;

public class ArticleTest extends BaseTest {

    protected USATodayClient client;

    @BeforeClass
    protected void setUp() {
        super.setUp();
        client = applicationContext.getBean("usaTodayClient",
                USATodayClient.class);
    }

    @DataProvider(name = "sections")
    public Object[][] sectionDataProvider() {
        return new String[][] { { "home" }, { "news" }, { "travel" },
                { "money" }, { "sports" }, { "life" }, { "tech" },
                { "weather" }, { "nation" }, { "washington" }, { "world" },
                { "opinion" }, { "health" }, { "nfl" }, { "mlb" }, { "nba" },
                { "nhl" }, { "highschool" }, { "motorsports" }, { "golf" },
                { "soccer" }, { "olympics" }, { "horseracing" }, { "people" },
                { "books" }, { "music" }, { "movies" } };
    }

    @Test(dataProvider = "sections", groups = "section")
    public void testArticleApi(String section) {

        sleep(750);

        Channel channel = client.getSection(section);

        @SuppressWarnings("unchecked")
        List<Item> items = channel.getItems();
        for (Item item : items) {
            assertNotNull(item.getTitle(), "Title is Null");
            assertNotNull(item.getDescription(), "Description is Null");
            assertNotNull(item.getLink(), "Link is Null");
            assertNotNull(item.getPubDate(), "Published date is Null");
        }

    }

    protected void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException ie) {
            // ignore
        }
    }

}
