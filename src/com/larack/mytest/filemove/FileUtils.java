package com.larack.mytest.filemove;

public class FileUtils {

    public static final String[] VIDEO_TYPES = {"avi", "mpg", "wmv", "3gp", "mov", "mp4", "asf", "asx", "flv", "wmv9", "rm", "rmvb", ".mkv"};

    public static boolean isVideoType(String inputPath) {
        return checkContentType(inputPath) > -1;
    }

    private static int checkContentType(String inputPath) {
        String type = inputPath.substring(inputPath.lastIndexOf(".") + 1, inputPath.length()).toLowerCase();
        // ffmpeg能解析的格式：（asx，asf，mpg，wmv，3gp，mp4，mov，avi，flv等）
        if (type.equals("avi")) {
            return 0;
        } else if (type.equals("mpg")) {
            return 0;
        } else if (type.equals("wmv")) {
            return 0;
        } else if (type.equals("3gp")) {
            return 0;
        } else if (type.equals("mov")) {
            return 0;
        } else if (type.equals("mp4")) {
            return 0;
        } else if (type.equals("asf")) {
            return 0;
        } else if (type.equals("asx")) {
            return 0;
        } else if (type.equals("flv")) {
            return 0;
        }
        // 对ffmpeg无法解析的文件格式(wmv9，rm，rmvb等),
        // 可以先用别的工具（mencoder）转换为avi(ffmpeg能解析的)格式.
        else if (type.equals("wmv9")) {
            return 1;
        } else if (type.equals("rm")) {
            return 1;
        } else if (type.equals("rmvb")) {
            return 1;
        } else if (type.equals("mkv")) {
            return 1;
        }
        return -1;
    }

}
