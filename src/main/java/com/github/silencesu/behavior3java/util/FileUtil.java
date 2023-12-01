package com.github.silencesu.behavior3java.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * 文件工具
 * @author SilenceSu
 * @Email Silence.Sx@Gmail.com
 * Created by Silence on 2019/3/2.
 */
public class FileUtil {
    /**
     * 读取文件内容
     * @param path
     * @return
     */
    public static String readFile(String path) throws IOException {
        List<String> list =  Files.readAllLines(Paths.get(path));
        return String.join("", list);
    }
}
