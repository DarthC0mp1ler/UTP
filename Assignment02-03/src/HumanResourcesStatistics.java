
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.function.*;
import java.util.stream.Collectors;

import employee.*;
import payroll.PayrollEntry;

public final class HumanResourcesStatistics {

	// The best solution is to impleent the below features as static methods having a list of Employee as the first input argument.
	// In addition the list of arguments may be augmented with parameters required for the given feature.
	// najlepiej zaimplementowa� poni�sze metody jako statyczne, gdzie argumentem lista pracownik�w i odpowiednio dodatkowo to co wymagane w danym punkcie

	// (assignment 03)
	// methods:
	//
	// * search for Employees older than given employee and earning less than him
	//   wyszukaj osoby zatrudnione (Employee), kt�re s� starsze od podanej innej zatrudnionej osoby oraz zarabiaj� mniej od niej
	public static List<Employee> olderThenAndEarnMore(List<Employee> allEmployees, Employee employee) {
		BiPredicate<Employee,Employee> o = Person::isYounger;
		BiPredicate<Employee,Employee> e = (u, t) -> u.isSalGreater(t.getSalary());
		List<Employee> list = new ArrayList<>();
		allEmployees
				.forEach(
						emp -> {
					if(o.test(employee,emp) && e.test(employee,emp)){
						list.add(emp);
					}
				});
		return list;
	}

	//
	// * search for Trainees whose practice length is longer than given number of days and raise their salary by 5%
	//   wyszukaj praktykant�w (Trainee), kt�rych praktyka jest d�u�sza od podanej liczby dni,
	//   a nast�pnie podnie� ich uposa�enie o 5%
	public static List<Trainee> practiceLengthLongerThan(List<Employee> allEmployees, int daysCount) {
		Predicate<Employee> t = te -> te instanceof Trainee;
		BiPredicate<Trainee,Integer> p = Trainee::isLongerPracticeThan;
				Consumer<Employee> u = e -> e.setSalary(e.getSalary().multiply(new BigDecimal(0.05).add(e.getSalary())));

		List<Trainee> trainees = new ArrayList<>();
		allEmployees.stream()
				.filter( e -> t.test(e) && p.test((Trainee) e,daysCount) )
				.forEach(e ->{ u.accept(e);trainees.add((Trainee) e);
				});
		return trainees;
	}

	//
	// * search for Workers whose seniority is longer than given number of months and give them bonus of 300 if their bonus is smaller
	//   wyszukaj pracownik�w o sta�u d�u�szym ni� podana liczba miesi�cy,
	//   a nast�pnie przyznaj im premi� w wysoko�ci 300 je�li ich premia jest ni�sza
	public static List<Worker> seniorityLongerThan(List<Employee> allEmployees, int monthCount) {
		Predicate<Employee> w = we -> we instanceof Worker;
		BiPredicate<Worker,Integer> p = Worker::isLongerThanMonths;
				Predicate<Worker> b = wt -> wt.getBonus().intValue() < 300;
						Consumer<Worker> u = wr-> wr.setBonus(new BigDecimal(300));
		List<Worker> workers = new ArrayList<>();
		allEmployees.stream()
				.filter( e -> w.test(e) && p.test((Worker) e,monthCount) )
				.forEach(e ->{
					if(b.test((Worker) e))
					u.accept((Worker) e);
					workers.add((Worker) e);
				});
		return workers;
	}

	//
	// * search for Workers whose seniority is between 1 and 3 years and give them raise of salary by 10%
	//   wyszukaj pracownik�w o sta�u pomi�dzy 1 a 3 lata i przyznaj im podwy�k� w wysoko�ci 10%
	public static List<Worker> seniorityBetweenOneAndThreeYears(List<Employee> allEmployees) {
		Predicate<Employee> w = we -> we instanceof Worker;
				Predicate<Worker> sb13 = wr -> wr.isLongerThanYear(1) && !wr.isLongerThanYear(3);
						Consumer<Worker> c = wt -> wt.setSalary(wt.getSalary().multiply(new BigDecimal(0.1)).add(wt.getSalary()));
		List<Worker> workers = new ArrayList<>();
		allEmployees.stream()
				.filter(e -> w.test(e) && sb13.test((Worker) e))
				.forEach( e -> {
				c.accept((Worker) e);
				workers.add((Worker) e);
		});
		return workers;
	}

	//
	// * search for Workers whose seniority is longer than the seniority of a given employee and earn less than him and align their salary with the given employee
	//   wyszukaj pracownik�w o sta�u d�u�szym ni� sta� podanego pracownika i kt�rzy zarabiaj� mniej od niego,
	//   nast�pnie zr�wnaj ich wynagrodzenie z wynagrodzeniem danego pracownika
	public static List<Worker> seniorityLongerThanAllignSilary(List<Employee> allEmployees, Employee employee ) {
		Predicate<Employee> t = w -> w instanceof Worker;
		BiPredicate<Worker,Worker> o = (er,w)-> w.isLongerThanMonths(Period.between(
				er.getEmploymentDate(),LocalDate.now()).getYears()*12 + Period.between(
				er.getEmploymentDate(),LocalDate.now()).getMonths());
		BiPredicate<Worker,Worker> e = (er,w) -> er.isSalGreater(w.getSalary());
		BiConsumer<Worker,Worker> c = (ed,w) -> w.setSalary(ed.getSalary());

		if(!(employee instanceof Worker))return null;
		List<Worker> workers = new ArrayList<>();
		allEmployees.stream()
				.filter(emp -> t.test(emp) && o.test((Worker)employee,(Worker)emp) && e.test((Worker) employee,(Worker) emp))
				.forEach(
				 emp -> {
					 c.accept((Worker) employee, (Worker) emp);
					 workers.add((Worker)emp);
				}
		);
		return workers;
	}

	//
	// * search for Workers whose seniority is between 2 and 4 years and whose age is greater than given number of years
	//   wyszukaj pracownik�w o sta�u pomi�dzy 2 i 4 lata i starszych ni� podana liczba lat
	public static List<Worker> seniorityBetweenTwoAndFourYearsAndAgeGreaterThan(List<Employee> allEmployees, int age) {
		Predicate<Employee> w = wr -> wr instanceof Worker;
		BiPredicate<Worker,Integer> a = (wt,y) -> wt.getAge() > y;
		Predicate<Worker> sb24 = wf -> wf.isLongerThanYear(2) && !wf.isLongerThanYear(4);
		List<Worker> workers = new ArrayList<>();
		allEmployees.stream()
				.filter(e -> w.test(e) && sb24.test((Worker) e) && a.test((Worker)e,age))
				.forEach( e -> workers.add((Worker) e));
		return workers;
	}



	// assignment 02


	public static PayrollEntry checkForBonus(Employee e){
		PayrollEntry p;
		if(e instanceof Worker) {
			p = new PayrollEntry(e, e.getSalary(), ((Worker) e).getBonus());
		} else
			p = new PayrollEntry(e, e.getSalary(), BigDecimal.ZERO);
		return p;
	}

	//1
	public static List<PayrollEntry> payroll(List<Employee> employees) {
		List<PayrollEntry> payrollEntries = new ArrayList<>();
		employees.stream().forEach(e ->
			payrollEntries.add(checkForBonus(e)));
		return payrollEntries;
	}

	//2
	public static List<PayrollEntry> subordinatesPayroll(Manager manager) {
		List<PayrollEntry> payrollEntries = new ArrayList<>();
		manager.getAllSubordinates().stream().forEach(e -> payrollEntries.add(checkForBonus(e)));
		return payrollEntries;
	}

	//3
	public static BigDecimal addition(BigDecimal total, BigDecimal additor){
		return total.add(additor);
	}

	private static BigDecimal  totalBonus = new BigDecimal(0);
	public static BigDecimal bonusTotal(List<Employee> employees) {
		employees.stream().filter(
				e -> e instanceof Worker
		).forEach(
				e -> totalBonus = addition(totalBonus,((Worker)e).getBonus())
		);
		return totalBonus;
	}

	//4
	public static int calcDays(Period p){
		return p.getYears()*365 + p.getMonths()*31+p.getDays();
	}

	private static long lSeniority;
	private static Employee LS;
	public static Employee longestSenioritySearch(List<Employee> employees){
		lSeniority = 0;
		LS = null;
		employees.stream().forEach(
				e->{
					int tmpDays;
					if(e instanceof Worker){
						Period duration = Period.between(((Worker)e).getEmploymentDate(), LocalDate.now());
						tmpDays = calcDays(duration);
					} else {
						Period duration = Period.between(((Trainee)e).getPracticeStart(),LocalDate.now());
						tmpDays = calcDays(duration);
					}
					if(lSeniority < tmpDays){
						lSeniority = tmpDays;
						LS = e;
					}
				}
		);
		return LS ;
	}

	//5
	private static BigDecimal highestSalary;
	public static BigDecimal highestSalaryWithoutBonus(List<Employee> employees){
		highestSalary = employees.get(0).getSalary();
		employees.stream().forEach(
				e ->{
					if(highestSalary.intValue() < e.getSalary().intValue()){
						highestSalary = e.getSalary();
					}
				}
		);
		return highestSalary;
	}

	//6
	private static BigDecimal highestSalaryWB = new BigDecimal(0);
	public static BigDecimal highestSalaryWithBonuses(List<Employee> employees){
		List<PayrollEntry> pList = payroll(employees);
		highestSalary = pList.get(0).getSalaryPlusBonus();
		pList.stream().forEach(
				e->{
					if(highestSalaryWB.intValue() < e.getSalaryPlusBonus().intValue()){
						highestSalaryWB = e.getSalaryPlusBonus();
					}
				}
		);
		return highestSalaryWB;
	}

	//7
	public static List<Employee> employeeSurNameStartWithA(Manager manager){
		return manager.getAllSubordinates().stream()
				.filter(e -> e.getSurname().trim().toCharArray()[0] == 'A').collect(Collectors.toList());
	}

	//8
	public static List<Employee> employeesWithSalaryMoreThan1000PLN(List<Employee> employees){
		return employees.stream()
				.filter(e-> e.getSalary().intValue() > 1000).collect(Collectors.toList());
	}

}