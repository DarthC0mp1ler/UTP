package Tests;

import Implementation.InputParser;
import Implementation.Person;
import org.junit.Assert;

import java.io.File;
import java.util.List;

public class InputParserTest {


    @org.junit.jupiter.api.Test
    public void inputParse(){
        File file = new File("src/Implementation/Resources/personFile");
        Assert.assertTrue(file.exists());
        Assert.assertEquals(file.length(),161);
        List<Person> personList  = InputParser.parse(file);
        Assert.assertNotNull(personList);
        Assert.assertEquals(personList.size(),6);
        System.out.println(personList);

    }

}
