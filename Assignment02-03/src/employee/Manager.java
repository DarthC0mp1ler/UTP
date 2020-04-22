package employee;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public final class Manager extends Worker {

	// attributes
	// * subordinates (a list of immediate subordinates)
	// * all subordinates (a list of subordinates in all hierarchy)

	private List<Employee> _immediateSubordinates;
	private List<Employee> _allSubordinates;

	public Manager(String firstName, String surname, Date dateOfBirth, BigDecimal salary, Manager manager,
				   LocalDate employmentDate, BigDecimal bonus){
		super(firstName,surname,dateOfBirth,salary,manager,employmentDate,bonus);
		_allSubordinates = new ArrayList<>();
		_immediateSubordinates = new ArrayList<>();
	}

	public Manager(String firstName) {
		super(firstName);
	}

	public void addSubordinate(Employee worker) {
		_allSubordinates.add(worker);
	}

	public void addSubordinate(List<Employee> e){
		_allSubordinates.addAll(e);
	}

	public void addImmediateSubordinate(Trainee trainee){
		_immediateSubordinates.add(trainee);
		_allSubordinates.add(trainee);
	}

	public void addImmediateSubordinate(List<Trainee> e){
		_immediateSubordinates.addAll(e);
		_allSubordinates.addAll(e);
	}


	public List<Employee> getImmediateSubordinates() {
		return _immediateSubordinates.stream().filter(e -> e instanceof Trainee && e.getManager() == this).collect(Collectors.toList());
	}

	public List<Employee> getAllSubordinates() {
		return _allSubordinates.stream().filter(e -> e.getManager() == this).collect(Collectors.toList());
	}

	@Override
	public String toString(){
		return super.toString();
	}
}