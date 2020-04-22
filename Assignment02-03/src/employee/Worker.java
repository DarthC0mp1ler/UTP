package employee;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

public class Worker extends Employee {

	// attributes
	// * employment date
	// * bonus

	// (assignment 03)
	// attributes:
	// * has bonus
	//
	// methods:
	// * seniority is longer than given number of years (seniority - sta�)
	// * seniority is longer than given number of months
	// * has bonus greater than given amount of money

	private LocalDate _employmentDate;
	private BigDecimal _bonus;

	//assignment 03
	private boolean hasBonus;

	public Worker(String firstName, String surname, Date dateOfBirth, BigDecimal salary, Manager manager,
				  LocalDate employmentDate, BigDecimal bonus){
		super(firstName,surname,dateOfBirth,salary,manager);
		_employmentDate = employmentDate;
		_bonus = bonus;
		hasBonus = _bonus != null;
	}

	public Worker(String firstName) {
		super(firstName);
	}

	//assognment 03
	public boolean isHasBonus() {
		return hasBonus;
	}

	public void setHasBonus(boolean hasBonus) {
		this.hasBonus = hasBonus;
	}

	public boolean isLongerThanYear(int years){
		return Period.between(getEmploymentDate(),LocalDate.now()).getYears() > years;
	}

	public boolean isLongerThanMonths(int months){
		return Period.between(getEmploymentDate(),LocalDate.now()).getYears()*12 +
				Period.between(getEmploymentDate(),LocalDate.now()).getMonths() > months;
	}

	public boolean bonusGreaterThan(BigDecimal b){
		return getBonus().intValue() > b.intValue();
	}

	// setter
	public void setEmploymentDate(LocalDate empDate){
		if(empDate == null){
			System.out.println("--Unable to set this date of employment for" + toString() + " : date = null");
			return;
		}
		_employmentDate = empDate;
		System.out.println("--Date of employment of "  + toString() + " is set to " + empDate);
	}

	public void setBonus(BigDecimal bonus) {
		if(bonus == null){
			System.out.println("--Unable to set this bonus for " + toString() + " : bonus = null");
			return;
		}
		_bonus = bonus;
		System.out.println("--Bonus for" + toString() + " is set to " + bonus);
	}

	// getter
	public LocalDate getEmploymentDate() {
		return _employmentDate;
	}

	public BigDecimal getBonus() {
		return _bonus;
	}

	@Override
	public String toString(){
		return super.toString();
	}
}