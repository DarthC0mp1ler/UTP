package employee;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.Date;

public abstract class Person {

	// To implement an attribute means that you provide a backing field and
	// getter or optionally setter for read-write properties/attributes
	// 
	// NO BACKING FIELDS SHOULD BE PROVIDED FOR DERIVED ATTRIBUTES
	// THOSE SHOULD BE COMPUTED ON-LINE
	//
	// attributes:
	// * first name (read-only)
	// * surname (read-only)
	// * birth date (read-only) --- date MUST BE represented by an instance of
	// type designed for date representation (either Date or LocalDate)
	//
	// * age (derived --- computed based on birth date) --- implemented as a
	// getter calculating the difference between the current date and birth date


	// (assignment 03)
	// methods:
	// * is older than other person
	// * is younger than other person
	// * compare age with other person's age

	private final String _firstName; // backing field
	private final String _surname;
	private final Date _dateOfBirth;

	// constructors:
	protected Person(String firstName,String surname, Date dateOfBirth){
		_firstName = firstName;
		_surname = surname;
		_dateOfBirth = dateOfBirth;
	}

	protected Person(String firstName) {
		_firstName = firstName;
		_surname = null;
		_dateOfBirth = null;
	}

	//assignment 03 ===============================

	public boolean isOlder(Person p){
//		System.out.println(getAge()+ ">"  + p.getAge());
		return getAge() > p.getAge() ;
	}

	public boolean isYounger(Person p){
//		System.out.println(p.getAge() + ">" + getAge());
		return p.getAge() > getAge();
	}

	public int compareAgeWith(Person p){
		if(isOlder(p)){return 1;}
		else if(isYounger(p)){ return -1;}
		else return 0;
	}
	//=============================================

	// getters:

	public String getFirstName() {
		return _firstName;
	}

	public String getSurname() {
		return _surname;
	}

	public Date getDateOfBirth() {
		return _dateOfBirth;
	}

	public int getAge(){
		return LocalDate.now().getYear()-_dateOfBirth.getYear();
	}

	@Override
	public String toString(){
		return getFirstName() + " " + getSurname();
	}
}