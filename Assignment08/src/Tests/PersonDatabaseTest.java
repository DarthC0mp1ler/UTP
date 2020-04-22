package Tests;
import Implementation.Assignment8Exception.Assignment08Exception;
import Implementation.PersonDatabase;
import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class PersonDatabaseTest {

	@Test
	public void serializeAndDeserialize() {

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


		try {
			DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(new File("src/Implementation/Resources/SerializeFile")));
			personDatabase.serialize(dataOutputStream);

			DataInputStream dataInputStream = new DataInputStream( new FileInputStream(new File("src/Implementation/Resources/SerializeFile")));
			PersonDatabase personDatabase1= personDatabase.deserialize(dataInputStream);


			Assert.assertNotNull(personDatabase);

			Assert.assertNotNull(personDatabase1);
			Assert.assertEquals(personDatabase.size(),personDatabase1.size());
			dataInputStream.close();
			dataOutputStream.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}