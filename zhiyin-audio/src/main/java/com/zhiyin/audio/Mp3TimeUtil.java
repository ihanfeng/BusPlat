package com.zhiyin.audio;

import com.mpatric.mp3agic.Mp3File;
import lombok.extern.slf4j.Slf4j;

import java.io.File;

/**
 * Created by hg on 2016/5/29.
 */
@Slf4j
public class Mp3TimeUtil {

    public static long getTime(File file) {
        try {
            Mp3File mp3file = new Mp3File(file);
            long time = mp3file.getLengthInSeconds();
            log.info("mp3 file {}'s  time length is: {} seconds", file.getName(),time);
            return time;
        } catch (Exception e) {
            log.error("mp3 file {}'s  time length error.",file.getName(), e);
        }
        throw new RuntimeException("get time error.");
    }

    public static long getTime(String path) {

        File f = new File(path);
        return getTime(f);

    }

    public static void main(String[] args) {
        String path = Mp3TimeUtil.class.getResource("/").getPath() + "/data/default.mp3";
        log.info("file path:{}", path);
        Mp3TimeUtil.getTime(path);
    }
}
