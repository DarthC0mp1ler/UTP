package employee;

import java.math.BigDecimal;
import java.util.Date;

public abstract class Employee extends Person {

	//
	// attributes:
	// * salary (use BigDecimal type for representing currency values)
	// * manager (empty if at top of hierarchy)

	// (assignment 03)
	// methods:
	// * salary is greater than given amount of money
	// * salary is less than given amount of money
	// * compare salary with other employee salary

	private BigDecimal _salary = null;
	private Manager _manager = null;

	// constructors
	public Employee(String firstName, String surname, Date dateOfBirth,BigDecimal salary,Manager manager){
		super(firstName,surname,dateOfBirth);
		_manager = manager;
		_salary = salary;
	}

	protected Employee(String firstName) {
		super(firstName);
	}


	//assignment 03 ===============================

	public boolean isSalGreater(BigDecimal b){
		return b.intValue() < getSalary().intValue();
	}

	public boolean isSalLess(BigDecimal b){
		return b.intValue() > getSalary().intValue();
	}

	public int compareSalWith(Employee e){
		if(isSalGreater(e.getSalary())){return 1;}
		else if(isSalLess(e.getSalary())){ return -1;}
		else return 0;
	}
	//=============================================

	// setters
	public void setSalary(BigDecimal salary) {
		if(salary == null) {
			System.out.println("--Unable to set salary to " + getFirstName() + " " + getSurname() + " : salary = null");
			return;
		}
		_salary = salary;
		System.out.println("--Salary is set to " + getFirstName() + " " + getSurname() + " : " + salary);
	}

	public void setManager(Manager manager) {
		if(manager == null){
			System.out.println("--Unable to set manager to " + getFirstName() + " " + getSurname() + " : manager = null");
			return;
		}
		_manager = manager;
		System.out.println("--Manager is set to " + getFirstName() + " " + getSurname() + " : " + manager);
	}

	// getters
	public BigDecimal getSalary() {
		return _salary;
	}

	public Manager getManager() {
		return _manager;
	}

	@Override
	public String toString(){
		return super.toString();
	}
}