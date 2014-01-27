package com.thangamfrm.usatoday;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.converter.feed.RssChannelHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.sun.syndication.feed.rss.Channel;

public class USATodayClient {

    @Value("#{usaTodayAPIProperties['usatoday.api.key']}")
    protected String apiKey;

    protected String sectionURL = "http://api.usatoday.com/open/articles?section={section}&api_key={key}";

    public USATodayClient() {
        super();
    }

    public Channel getSection(String section) {
        Map<String, String> vars = new HashMap<String, String>();
        vars.put("section", section);
        vars.put("key", apiKey);

        RestTemplate restTemplate = new RestTemplate();

        RssChannelHttpMessageConverter rssChannelConverter = new RssChannelHttpMessageConverter();
        rssChannelConverter.setSupportedMediaTypes(Collections
                .singletonList(MediaType.APPLICATION_XML));
        restTemplate.getMessageConverters().add(rssChannelConverter);

        return restTemplate.getForObject(sectionURL, Channel.class, vars);
    }

}
