package Tests;

import Implementation.FileUtility;
import Implementation.ZipUtility;
import org.junit.Assert;
import org.junit.Test;
import java.io.File;
import java.util.List;

public class UtilityTest {


    @Test
    public void testFile(){
        File folder = new File("src/testFolder");
        File file = new File("src/testFolder/testFile");
        Assert.assertTrue(folder.exists());
        Assert.assertTrue(file.exists());

        List<File> fileList = FileUtility.searchByName(folder,"File");
        Assert.assertEquals(1,fileList.size());
        Assert.assertEquals("testFile",fileList.get(0).getName());


        fileList.remove(0);
        fileList = FileUtility.searchByContent(folder,"Mordor");
        Assert.assertEquals(1,fileList.size());
        Assert.assertEquals("testFile",fileList.get(0).getName());
    }



    @Test
    public void testZip(){
        File zip = new File("src/testZip.zip");
        Assert.assertTrue(zip.exists());

        List<String> fileList = ZipUtility.searchByName(zip,"File");

        Assert.assertEquals(1,fileList.size());
        Assert.assertEquals("testFile",fileList.get(0));
        fileList = ZipUtility.searchByContent(zip,"Mordor");
    }

}
