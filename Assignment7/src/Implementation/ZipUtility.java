package Implementation;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ZipUtility {


    public static List<String> searchByName(File file, String name){
        if(!file.isFile()){
            return null;
        }
        try{
            ZipFile zip = new ZipFile(file);
            List<String> filesNames = zip.stream().filter(entry ->  entry.getName().contains(name)).map(ZipEntry::getName).collect(Collectors.toList());
            zip.close();
            return filesNames;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static List<String> searchByContent(File file, String content){
        if(!file.isDirectory()){
            return null;
        }
        try{
            ZipFile zip = new ZipFile(file);
            List<String> filesNames = zip.stream().filter(entry -> searchByContentPredicate(entry, zip, content)).map(ZipEntry::getName).collect(Collectors.toList());
            zip.close();
            return filesNames;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private static boolean searchByContentPredicate(ZipEntry entry, ZipFile zip, String content){
        try {
            InputStream inputStream = zip.getInputStream(entry);
            return FileContentUtility.contains(inputStream,content,(int)entry.getSize());
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
