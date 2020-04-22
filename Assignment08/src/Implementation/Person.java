package Implementation;

import Implementation.Assignment8Exception.Assignment08Exception;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.Date;

public class Person implements Comparable<Person> {
	
	private final String _firstName;
	private final String _surname;
	private final Date _birthdate;
	
	public Person(String firstName, String surname, Date birthdate) {
		_firstName = firstName;
		_surname = surname;
		_birthdate = birthdate;
	}



	// assignment 8
	public void serialize(DataOutputStream output) throws Assignment08Exception {
		// serialize birth date with getTime() method
		// encapsulate IOException in Assignment08Exception
		try{
			output.writeUTF(_firstName);
			output.writeUTF(_surname);

			DateUtility.serialize(_birthdate, output);
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	public static Person deserialize(DataInputStream input) throws Assignment08Exception {
		try{
			String firstname = input.readUTF(),
					surname = input.readUTF();
			Date birthdate = DateUtility.deserialize(input);
			return new Person(firstname,surname,birthdate);
		}catch (Exception e){
			e.printStackTrace();
		}


		return null;
	}


	@Override
	public int compareTo(Person otherPerson) {
		int result = getSurname().compareTo(otherPerson.getSurname());
		if(result != 0){
			return result;
		}
		result = getName().compareTo(otherPerson.getName());
		if (result != 0){
			return result;
		}
		return getBirthdate().compareTo(otherPerson.getBirthdate());
	}

	public String getName() {
		return _firstName;
	}

	public String getSurname() {
		return _surname;
	}

	public Date getBirthdate() {
		return _birthdate;
	}

	@Override
	public String toString() {
		return _firstName + " " + _surname + "\n";
	}
}