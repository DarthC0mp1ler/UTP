package employee;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

public class Trainee extends Employee {

	// attributes:
	// * practice start date
	// * practice length (in days)

	// (assignment 03)
	// * practice length is shorter than given number of days
	// * practice length is longer than given number of days

	private LocalDate _practiceStart;
	private int _practiceLength;

	public Trainee(String firstName, String surname, Date dateOfBirth, BigDecimal salary, Manager manager,
				   LocalDate practiceStart, int practiceLength){
		super(firstName,surname,dateOfBirth,salary,manager);
		_practiceStart = practiceStart;
		_practiceLength = practiceLength;
	}

	public Trainee(String firstName) {
		super(firstName);
	}

	// assignment 03

	public boolean isShorterPracticeThan(int days){
		return Period.between(getPracticeStart(),LocalDate.now()).getYears()*364 +
				Period.between(getPracticeStart(),LocalDate.now()).getMonths()*30 +
				Period.between(getPracticeStart(),LocalDate.now()).getDays() < days;
	}

	public boolean isLongerPracticeThan(int days){
		return  Period.between(getPracticeStart(),LocalDate.now()).getYears()*364 +
				Period.between(getPracticeStart(),LocalDate.now()).getMonths()*30 +
				Period.between(getPracticeStart(),LocalDate.now()).getDays() > days;
	}

	// setter
	public void setPracticeStart(LocalDate practiceStart) {
		if(practiceStart == null){
			System.out.println("--Practice start date of " + toString() + " cannot be set : date = null");
			return;
		}
		_practiceStart = practiceStart;
		System.out.println("--Practice start date of " + toString() + " is set successfully : " + practiceStart);
	}

	public void setPracticeLength(int practiceLength) {
		_practiceLength = practiceLength;
		System.out.println("--Practice length of " + toString() + " defined successfully : " + practiceLength + " days");
	}

	//getter
	public LocalDate getPracticeStart() {
		return _practiceStart;
	}

	public int getPracticeLength() {
		return _practiceLength;
	}

	@Override
	public String toString(){
		return super.toString();
	}
}