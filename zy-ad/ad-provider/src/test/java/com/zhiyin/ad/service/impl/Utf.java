package com.zhiyin.ad.service.impl;

import com.vdurmont.emoji.Emoji;
import com.vdurmont.emoji.EmojiManager;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringEscapeUtils;
import org.junit.Test;

/**
 * Created by hg on 2016/7/19.
 */
@Slf4j
public class Utf {
    @Test
    public void t(){
        String s = StringEscapeUtils.unescapeJava("\u20ac\n"); // s contains the euro symbol followed by newline
        log.info(s);

        StringBuffer sb = new StringBuffer();
        sb.append(Character.toChars(127467));
        sb.append(Character.toChars(127479));
        System.out.println(sb);


        Emoji emoji = EmojiManager.getForAlias("fr");
        System.out.println("HEY: " + emoji.getUnicode());
    }
}
