package com.xzh;

import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件工具
 *
 * @author 向振华
 * @date 2020/08/19 14:08
 */
public class MetadataUtils {

    /**
     * 获取拍摄日期
     *
     * @param file    文件
     * @param pattern 输出日期格式
     * @return
     */
    public static String getDateTime(File file, String pattern) {
        List<Tag> tagList = getTagList(file);
        if (tagList.isEmpty()) {
            return null;
        }
        for (Tag tag : tagList) {
            int tagType = tag.getTagType();
            String directoryName = tag.getDirectoryName();
            String tagName = tag.getTagName();
            String description = tag.getDescription();
            // jpeg
            if ("Exif IFD0".equals(directoryName) && "Date/Time".equals(tagName)) {
                return stringToString(description, "yyyy:MM:dd HH:mm:ss", pattern);
            }
            // mp4
            else if ("MP4 Video".equals(directoryName) && "Creation Time".equals(tagName)) {
                return stringToString(description, "E MMM dd HH:mm:ss +08:00 yyyy", pattern);
            }
            // mov
            else if ("QuickTime Video".equals(directoryName) && "Creation Time".equals(tagName)) {
                return stringToString(description, "E MMM dd HH:mm:ss +08:00 yyyy", pattern);
            }
            // file
            else if ("File".equals(directoryName) && "File Modified Date".equals(tagName)) {
                return stringToString(description, "E MMM dd HH:mm:ss +08:00 yyyy", pattern);
            }
        }
        return null;
    }

    /**
     * 获取所有标签
     *
     * @param file
     * @return
     */
    public static List<Tag> getTagList(File file) {
        List<Tag> tagList = new ArrayList<>();
        Metadata metadata = null;
        try {
            metadata = ImageMetadataReader.readMetadata(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (metadata != null) {
            for (Directory directory : metadata.getDirectories()) {
                for (Tag tag : directory.getTags()) {
                    //System.out.println(tag);
                    tagList.add(tag);
                }
            }
        }
        return tagList;
    }

    public static String stringToString(String dateString, String oldPattern, String newPattern) {
        try {
            return new SimpleDateFormat(newPattern).format(new SimpleDateFormat(oldPattern).parse(dateString));
        } catch (ParseException e) {
            throw null;
        }
    }
}