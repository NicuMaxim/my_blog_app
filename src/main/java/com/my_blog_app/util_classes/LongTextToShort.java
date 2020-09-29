package com.my_blog_app.util_classes;

import org.springframework.stereotype.Component;

@Component
public class LongTextToShort {

    public LongTextToShort() {}

    public String  CreateShortText (String long_text) {

        int max_length = 250;
        String short_text;

        if (long_text.length() >= max_length)
            short_text = long_text.substring(0, max_length) + "...";
        else short_text = long_text;

        return short_text;
    }
}
