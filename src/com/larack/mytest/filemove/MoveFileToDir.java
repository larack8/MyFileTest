package com.larack.mytest.filemove;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MoveFileToDir {

    public static void testDeleteDuplicateFiles() {
        String filePath = "I:\\mm";
        deleteDuplicateFiles(filePath);
    }

    public static void testMove() {
        String from = "F:\\xx";
        String to = from + "-video";
//        String to = "";
        String[] types = FileUtils.VIDEO_TYPES;
        moveFileToRootDir(from, to, types);
    }

    /**
     * @param inFiles  C:\\Windows\\SysWOW64
     * @param outFiles C:\\Users\\Administrator\\Desktop\\64dll
     * @param type     txt
     */
    public static void copyFileToDir(String inFiles, String outFiles, String type) {
        System.out.println(inFiles + " --move ." + type + "--> " + outFiles);

        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            File outFiled = new File(outFiles);
            if (!outFiled.exists()) {
                outFiled.mkdirs();
            }

            File files = new File(inFiles);

            if (files.exists()) {
                File[] listFiles = files.listFiles();
                if (listFiles != null && listFiles.length > 0) {
                    for (File file : listFiles) {
                        String fileName = file.getName();
                        String[] str = fileName.split("\\.");
                        if (str[str.length - 1].equals(type)) {
                            fis = new FileInputStream(file);
                            fos = new FileOutputStream(new File(outFiled, fileName));
                            byte[] buf = new byte[1024];
                            int bytesRead;
                            while ((bytesRead = fis.read(buf)) > 0) {
                                fos.write(buf, 0, bytesRead);

                            }
                        }
                    }
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {//关闭资源
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static int sDeleteFileCount = 0;

    public static void moveFileToRootDir(String from, String to, String[] types) {
        System.out.println(from + " --move ." + getString(types) + " root--> " + to);
        moveFileToDir(from, to, types, to);
        moveFileToDir(from, to, "torrent");
        sDeleteFileCount = 0;
        deleteEmptyDirs(to, sDeleteFileCount);
        System.out.println("delete empty dirs count " + sDeleteFileCount);
        sDeleteFileCount = 0;
    }

    public static void moveFileToTargetDir(String from, String to, String[] types) {
        System.out.println(from + " --move ." + getString(types) + " target--> " + to);
        moveFileToDir(from, to, types, null);
    }

    private static String getString(String[] types) {
        String s = null;
        for (String t : types) {
            if (s == null) {
                s = t;
            } else {
                s += "|" + t;
            }
        }
        return s;
    }

    /**
     * 移动指定格式文件，并删除空文件夹
     *
     * @param from
     * @param to
     * @param type
     */
    private static void moveFileToDir(String from, String to, String type) {
        try {
            File fromDir = new File(from);
            File[] files = fromDir.listFiles();
            if (files == null || files.length == 0) {
                return;
            }
            for (int i = 0; i < files.length; i++) {
                File f = files[i];
                if (f.isDirectory()) {
                    continue;
                }
                String toPath = to + "\\" + f.getName();
                String fStuff = toPath.substring(toPath.lastIndexOf(".") + 1).toLowerCase();
                if (fStuff.equalsIgnoreCase(type)) {
                    File toFile = new File(toPath);
                    if (toFile.exists()) {
                        System.out.println("delete exists toFile " + toFile.getAbsolutePath());
                        toFile.delete();
                    }
                    System.out.println(f.getAbsolutePath() + " renameTo " + toFile.getAbsolutePath());
                    f.renameTo(toFile);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 移动指定格式文件，并删除空文件夹
     *
     * @param from
     * @param to
     * @param types
     */
    private static void moveFileToDir(String from, String to, String[] types, String toRoot) {
        try {
            File fromDir = new File(from);
            File[] files = fromDir.listFiles();
            if (files == null || files.length == 0) {
                return;
            }
            File toDir = new File(to);
            if (!toDir.exists()) {
                toDir.mkdirs();
            }
            for (int i = 0; i < files.length; i++) {
                File f = files[i];
                if (f.isDirectory()) {
                    moveFileToDir(f.getPath(), to + "\\" + f.getName(), types, toRoot);
                }
                String toPath = toDir.getPath() + "\\" + f.getName();
                if (null != toRoot) {
                    if (toRoot.endsWith("\\")) {
                        toPath = toRoot + f.getName();
                    } else {
                        toPath = toRoot + "\\" + f.getName();
                    }
                }
                String fStuff = toPath.substring(toPath.lastIndexOf(".") + 1).toLowerCase();
                for (String type : types) {
                    if (fStuff.equalsIgnoreCase(type)) {
                        File toFile = new File(toPath);
                        if (toFile.exists()) {
                            System.out.println("delete exists toFile " + toFile.getAbsolutePath());
                            toFile.delete();
                        }
                        System.out.println(f.getAbsolutePath() + " renameTo " + toFile.getAbsolutePath());
                        f.renameTo(toFile);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        deleteEmptyDir(from);
    }

    private static void deleteEmptyDir(String dir) {
        try {
            File dirFile = new File(dir);
            File[] files = dirFile.listFiles();
            if (files == null || files.length == 0) {
                System.out.println("delete empty dirFile " + dirFile.getAbsolutePath());
                dirFile.delete();
                return;
            }
            for (int i = 0; i < files.length; i++) {
                File f = files[i];
                if (f.isDirectory()) {
                    deleteEmptyDir(f.getPath());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void deleteEmptyDirs(String dir, int totalCount) {
        System.out.println("deleteEmptyDirs " + dir);
        File dirFile = new File(dir);
        File[] dirs = dirFile.listFiles();
        for (int i = 0; i < dirs.length; i++) {
            if (dirs[i].isDirectory()) {
                deleteEmptyDirs(dirs[i].getAbsolutePath(), totalCount);
            }
        }
        if (dirFile.isDirectory() && dirFile.delete())
            totalCount++;
        System.out.println("delete dir " + dir);
    }

    private static void deleteFormatFiles(String filePath, String type, int totalCount) {
        System.out.println("deleteTargetFile " + filePath + ", type " + type);
        if (null == type) {
            return;
        }
        File dirFile = new File(filePath);
        File[] dirs = dirFile.listFiles();
        for (int i = 0; i < dirs.length; i++) {
            if (dirs[i].isDirectory()) {
                deleteFormatFiles(dirs[i].getAbsolutePath(), type, totalCount);
            }
        }
        if (dirFile.isFile()) {
            String fStuff = filePath.substring(filePath.lastIndexOf(".") + 1).toLowerCase();
            if (fStuff.equalsIgnoreCase(type) && dirFile.delete()) {
                totalCount++;
            }
        }
        System.out.println("delete file " + filePath);
    }

    private static void deleteDuplicateFiles(String filePath) {
        if (null == filePath) {
            return;
        }
        File file = new File(filePath);
        if (file.isFile()) {
            String absName = filePath.substring(0, filePath.lastIndexOf("."));
            String stuff = filePath.substring(filePath.lastIndexOf(".") + 1).toLowerCase();

            String duplicatePath = absName + " - 副本." + stuff;
            File duplicateFile = new File(duplicatePath);
            if (duplicateFile.length() == file.length()) {
                System.out.println("delete duplicate file " + duplicatePath + ", file size " + duplicateFile.length());
                duplicateFile.delete();
            } else {
                int index = 1;
                while (index != 0) {
                    duplicatePath = absName + " (" + index + ")." + stuff;
                    duplicateFile = new File(duplicatePath);
                    if (duplicateFile.length() == file.length()) {
                        System.out.println("delete duplicate file " + duplicatePath + ", file size " + duplicateFile.length());
                        duplicateFile.delete();
                        index++;
                    } else {
                        index = 0;
                    }
                }
            }

        } else {
            File[] dirs = file.listFiles();
            for (int i = 0; i < dirs.length; i++) {
                deleteDuplicateFiles(dirs[i].getAbsolutePath());
            }
        }

    }


}