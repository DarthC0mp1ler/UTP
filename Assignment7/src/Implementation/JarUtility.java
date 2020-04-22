package Implementation;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.function.Predicate;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.stream.Collectors;

public class JarUtility {

    public static List<String> seatchByName(File file, String name){
        if(!file.isFile()){
            return null;
        }
        try{
            JarFile jar = new JarFile(file);
            List<String> fileNames = search(jar,name,entry -> searchByNamePredicate(entry, name));
            return fileNames;
        }catch (Exception e) {
            System.err.println("Oooooops...18");
        }
        return null;
    }

    public static List<String> searchByContent(File file, String content){
        if(!file.isDirectory()){
            return null;
        }
        try{
            JarFile jar = new JarFile(file);
            List<String> fileNames = search(jar,content,entry -> searchByContentPredicate(entry,jar, content));
            return fileNames;
        }catch (Exception e){
            System.err.println("Ooooops...32");
        }
        return null;
    }

    private static List<String> search(JarFile jar, String name, Predicate<? super JarEntry> predicate){
        try{
            List<String> filenames = jar.stream().filter(predicate).map(JarEntry::getName).collect(Collectors.toList());
            jar.close();
            return filenames;
        }catch (Exception e){
            System.err.println("Oooooops...50");
        }
        return null;
    }

    private static boolean searchByNamePredicate(JarEntry entry, String name){
        return entry.getName()
                .contains(name);
    }

    private static boolean searchByContentPredicate(JarEntry entry, JarFile jar, String content){
        try{
            InputStream inputStream = jar.getInputStream(entry);
            return FileContentUtility.contains(inputStream,content,(int)entry.getSize());
        }catch (Exception e){
            System.err.println("Ooooops...66");
        }
        return false;
    }
}
