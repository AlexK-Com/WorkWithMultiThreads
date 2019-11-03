package com.mainacad.service;

import com.mainacad.model.ConnectionInfo;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class FileService {
    public static final String MAIN_DIR = System.getProperty("user.dir");
    public static final String SEP = System.getProperty("file.separator");
    public static final String FILES_DIR = MAIN_DIR + SEP + "files";

    // work with test
    public static synchronized void writeTextToFile(String text, String fileName, boolean append){
        checkTargetDir();

        try (FileWriter fileWriter = new FileWriter(FILES_DIR + SEP + fileName, append)) {// avtozakritie vseh obiektov
            fileWriter.write(text);
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void checkTargetDir() {
        File file = new File(FILES_DIR);
        if (!file.exists()) {
            file.mkdir();
        }
    }
    private static void checkTargetDir(File file) {
        if (!file.exists()) {
            file.mkdir();
        }
    }
    public static String readTextFromFile(String fileName){
        String out = "";
        try ( FileReader fileReader = new FileReader(FILES_DIR + SEP + fileName);
              BufferedReader bufferedReader = new BufferedReader(fileReader) )
        {
            String line;
            while ( (line = bufferedReader.readLine()) != null ){
                out += line + "\n";
            }
        } catch (IOException  e) {
            e.printStackTrace();
        }
        return  out;
    }

    public static List<ConnectionInfo> readConnectionsFromFile(String fileName){
        List<ConnectionInfo> connectionInfoList = new ArrayList<>();

        try ( FileReader fileReader = new FileReader(FILES_DIR + SEP + fileName);
              BufferedReader bufferedReader = new BufferedReader(fileReader) )
        {
            String line;
            while ( (line = bufferedReader.readLine()) != null ){
                ConnectionInfo connectionInfo = new ConnectionInfo();
                String[] words = line.split(" ");
                connectionInfo.setSessionId(Integer.valueOf(words[0]));
                connectionInfo.setConnectionTime(Long.valueOf(words[1]));
                connectionInfo.setIp(words[2]);
            }

        } catch (IOException  e) {
            e.printStackTrace();
        }
        return connectionInfoList;
    }

    // скопировали со старого проекта, теперь работаем без того, что внизу. Оставлено на всякий случай...
/*
    public static void writeBytesToFile (byte[] bytes, String fileName) {
        checkTargetDir();
        try (FileOutputStream fileOutputStream = new FileOutputStream(FILES_DIR + SEP + fileName);) {
            fileOutputStream.write(bytes);
            fileOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeBytesToFile (byte[] bytes, File file) {
        checkTargetDir();
        try (FileOutputStream fileOutputStream = new FileOutputStream(file.getAbsoluteFile());) {
            fileOutputStream.write(bytes);
            fileOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static byte[] getBytesFromFile(String fileName)
        {
            File file = new File(FILES_DIR + SEP + fileName);
            try {
                return Files.readAllBytes(file.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return new byte[0];
        }

    public static void copyFile(String sourceName, String targetName) {
       byte[] bytes = getBytesFromFile(sourceName);
       writeBytesToFile(bytes, targetName);

    }
    public static void moveFileToFolder(String sourceName, String folderTarget) {
        byte[] bytes = getBytesFromFile(sourceName);

        File fileDir = new File (FILES_DIR + SEP + folderTarget);
        checkTargetDir();

        fileDir = new File(MAIN_DIR + SEP + folderTarget + SEP + sourceName);
        writeBytesToFile(bytes, fileDir.getAbsolutePath() + SEP + sourceName);

        File sourceFile = new File (FILES_DIR + SEP + sourceName);
     //   sourceFile.delete();


}
*/

}



