package com.my_blog_app.util_classes;

import org.springframework.stereotype.Component;

@Component
public class LongTextToShort {

    public LongTextToShort() {}

    public String  CreateShortText (String longText) {

        int maxLength = 250;
        String shortText;

        if (longText.length() >= maxLength)
            shortText = String.format("%s...", longText.substring(0, maxLength));
        else shortText = longText;

        return shortText;
    }
}
