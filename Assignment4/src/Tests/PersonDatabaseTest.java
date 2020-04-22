package Tests;

import Implementation.PersonDatabase;
import org.junit.Assert;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class PersonDatabaseTest {

    @org.junit.jupiter.api.Test
    public void personDatabase(){
        File file = new File("src/Implementation/Resources/personFile");
        Assert.assertTrue(file.exists());
        Assert.assertEquals(file.length(),161);
        PersonDatabase personDatabase = new PersonDatabase(file);
        Assert.assertNotNull(personDatabase);
        Assert.assertEquals(personDatabase.sortedByBirthdate().size(),6);
        Assert.assertEquals(personDatabase.sortedByFirstName().size(),6);
        Assert.assertEquals(personDatabase.sortedBySurnameFirstNameAndBirthdate().size(),6);
        try {
            Assert.assertEquals(personDatabase.bornOnDay(new SimpleDateFormat("yyyy-MM-dd").parse("2001-08-17")).size(),2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}
