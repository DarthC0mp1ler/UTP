package Implementation;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FileUtility {

    private static final int PATH_LENGTH = 250;

    private FileUtility(){}

    public static List<File> searchByName(File directory, String name){
         return search(directory,name,FileUtility::searchByNamePredicate);

    }

    public static List<File> searchByContent(File directory, String name){
        return search(directory,name,FileUtility::searchByContentPredicate);
    }

    private static List<File> search(File directory, String searchParameter,BiPredicate<Path,String> predicate){
        if(!directory.exists()){
            return null;
        }
        try{
            Path path = directory.toPath();
            return Files
                    .find(path,PATH_LENGTH,(p,attributes) -> predicate.test(path, searchParameter))
                    .map(Path::toFile).filter(File::isFile).collect(Collectors.toList());
        }catch (Exception e){
            System.err.println("Oooops...39");
        }

        //todo=====================================================
        return null;
    }

    private static boolean searchByNamePredicate(Path path, String name){
        File file = path.toFile();
        if(file.isDirectory()){
            return true;
        }
        String filename = file.getName();
        return filename.contains(name);
    }

    private static boolean searchByContentPredicate(Path path, String content){
        try{
            File file = path.toFile();
            if(file.isDirectory()){
                return true;
            }
            InputStream input = new FileInputStream(path.toFile());
            return FileContentUtility.contains(input,content,(int)file.length());
        }catch (Exception e){
            System.err.println("Oooops...64");
        }

        //todo=====================================
        return false;
    }





}
