import employee.*;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

class HRSTest {

    @org.junit.jupiter.api.Test

    public void test() {
        ArrayList<Employee> employees = new ArrayList<>();
        ArrayList<Trainee> trainees = new ArrayList<>();

        //managers - 2
        Manager manager1 = new Manager("Kibutsudzi", "Mudzan", new Date(1200, 1, 7), new BigDecimal(1000), null, LocalDate.of(1275, 1, 8), new BigDecimal(400));
        Manager manager2 = new Manager("Gendalf", "TheGray", new Date(1400, 4, 5), new BigDecimal(1000), null, LocalDate.of(1400, 1, 1), new BigDecimal(400));

        //workers
        Worker bilbo = new Worker("Bilbo", "Baggins", new Date(1999, 12, 12), new BigDecimal(1000), manager2, LocalDate.of(2000, 1, 31), new BigDecimal(300));
        Worker nedzuko = new Worker("Nedzuko", "Kamado", new Date(1900, 2, 17), new BigDecimal(1000), manager2, LocalDate.of(2016, 1, 23), new BigDecimal(100));
        Worker toho = new Worker("Toho", "Mitsubishi", new Date(1900, 5, 27), new BigDecimal(1000), manager2, LocalDate.of(2016, 1, 21), new BigDecimal(100));
        Worker benedicto = new Worker("Benedicto", "Drrivedercci", new Date(1900, 7, 9), new BigDecimal(1000), manager2, LocalDate.of(2019, 1, 3), new BigDecimal(100));
        Worker biedronka = new Worker("Bidronka", "Zabkowski", new Date(1999, 11, 19), new BigDecimal(1000), manager2, LocalDate.of(2019, 1, 1), new BigDecimal(100));
        Worker andzej = new Worker("Andzej", "Sapkowski", new Date(1999, 4, 1), new BigDecimal(1000), manager2, LocalDate.of(2019, 1, 6), new BigDecimal(100));
        Worker dmitrij = new Worker("Dmitrij", "Kuplinov", new Date(1999, 1, 1), new BigDecimal(1000), manager2, LocalDate.of(2000, 1, 17), new BigDecimal(100));
        Worker procek = new Worker("Procek", "the69", new Date(1999, 11, 6), new BigDecimal(1000), manager2, LocalDate.of(2019, 1, 1), new BigDecimal(100));

        ArrayList<Employee> workers = new ArrayList<>(Arrays.asList(procek, bilbo, toho, nedzuko, dmitrij, biedronka, benedicto, andzej));

        Worker tanjiro =new Worker("Tandziro", "Kamado", new Date(1700, 12, 12), new BigDecimal(1050), manager1, LocalDate.of(1999, 12, 31), new BigDecimal(100));
        Worker john = new Worker("John", "Morkheart", new Date(1999, 1, 14), new BigDecimal(1000), manager1, LocalDate.of(2019, 1, 7), new BigDecimal(100));
        Worker hideo = new Worker("Hideo", "Kojima", new Date(1999, 5, 18), new BigDecimal(1000), manager1, LocalDate.of(2019, 1, 1), new BigDecimal(100));
        Worker victoria = new Worker("Victoria", "Venger", new Date(1999, 17, 8), new BigDecimal(1000), manager1, LocalDate.of(2019, 1, 16), new BigDecimal(100));
        Worker petro = new Worker("Petro", "Glowacki", new Date(1999, 12, 12), new BigDecimal(1000), manager1, LocalDate.of(2019, 1, 31), new BigDecimal(100));

        ArrayList<Employee> workers1 = new ArrayList<>(Arrays.asList(tanjiro, john, hideo, victoria, petro));

        Trainee denia = new Trainee("Denia", "Apalacci", new Date(2000, 10, 7), new BigDecimal(100), manager1, LocalDate.of(2019, 9, 15), 150);
        trainees.add(new Trainee("Tsujoku", "Naretu", new Date(2000, 1, 1), new BigDecimal(100), manager2, LocalDate.of(2019, 10, 15), 150));
        trainees.add(new Trainee("Sakio", "Gore", new Date(2000, 9, 2), new BigDecimal(100), manager1, LocalDate.of(2019, 10, 15), 150));
        trainees.add(new Trainee("Caprio", "Riviva", new Date(2000, 4, 24), new BigDecimal(100), manager1, LocalDate.of(2019, 10, 15), 150));
        trainees.add(new Trainee("Morshynska", "Oknowska", new Date(2000, 6, 4), new BigDecimal(100), manager1, LocalDate.of(2019, 10, 15), 150));

        manager1.addImmediateSubordinate(denia);
        manager2.addImmediateSubordinate(trainees);
        manager1.addSubordinate(workers1);
        manager2.addSubordinate(workers);

        employees.addAll(workers);
        employees.addAll(workers1);
        employees.add(2, manager1);
        employees.add(5, manager2);
        employees.addAll(trainees);
        employees.add(7, denia);

        //assignment 03
        // * search for Employees older than given employee and earning less than him
        Assert.assertEquals(HumanResourcesStatistics.olderThenAndEarnMore(employees,tanjiro),
                Arrays.asList(manager1,manager2));
        // * search for Trainees whose practice length is longer than given number of days and raise their salary by 5%
        Assert.assertEquals(HumanResourcesStatistics.practiceLengthLongerThan(employees,15),
                Collections.singletonList(denia));
        // * search for Workers whose seniority is longer than given number of months and give them bonus of 300 if their bonus is smaller
        Assert.assertEquals(HumanResourcesStatistics.seniorityLongerThan(employees,40),
                Arrays.asList(bilbo,manager1,toho,nedzuko,manager2,dmitrij,tanjiro));
        // * search for Workers whose seniority is between 1 and 3 years and give them raise of salary by 10%
        Assert.assertEquals(HumanResourcesStatistics.seniorityBetweenOneAndThreeYears(employees),
                Arrays.asList(toho,nedzuko));
        // * search for Workers whose seniority is longer than the seniority of a given employee and earn less than him and align their salary with the given employee
        Assert.assertEquals(HumanResourcesStatistics.seniorityLongerThanAllignSilary(employees,tanjiro),
                Arrays.asList(manager1,manager2));
        // * search for Workers whose seniority is between 2 and 4 years and whose age is greater than given number of years
        Assert.assertEquals(HumanResourcesStatistics.seniorityBetweenTwoAndFourYearsAndAgeGreaterThan(employees,80),
                Arrays.asList(toho,nedzuko));

//        System.out.println("olderThenAndEarnMore:\n" +
//                //output: Gendalf, Kibutsudzi
//        HumanResourcesStatistics.olderThenAndEarnMore(employees,tanjiro, (w,u) -> w.isYounger(u),(u, t) -> u.isSalGreater(t.getSalary()))
//                + "\n============================================================================\npracticeLengthLongerThan:\n"
//                //output: denia, 105 salary
//                + HumanResourcesStatistics.practiceLengthLongerThan(employees,15,
//                t -> t instanceof Trainee, (t,d) -> t.isLongerPracticeThan(d),
//                e -> e.setSalary(e.getSalary().multiply(new BigDecimal(0.05).add(e.getSalary()))))
//                + "\n============================================================================\nseniorityLongerThan:\n"
//                // output: bilbo, kuplinov(bonus up), tanjiro(bonus up), mudzan, gendalf
//                + HumanResourcesStatistics.seniorityLongerThan(employees,40,
//                w -> w instanceof Worker,Worker::isLongerThanMonths,
//                w -> w.getBonus().intValue() < 300, w-> w.setBonus(new BigDecimal(300)))
//                + "\n============================================================================\nseniorityBetweenOneAndThreeYears:\n"
//                //output: toho, nedzuko sal = 1100
//                + HumanResourcesStatistics.seniorityBetweenOneAndThreeYears(employees,w -> w instanceof Worker,
//                w -> w.isLongerThanYear(1) && !w.isLongerThanYear(3),w -> w.setSalary(w.getSalary().multiply(new BigDecimal(0.1)).add(w.getSalary())))
//                + "\n============================================================================\nseniorityLongerThanAllignSilary:\n"
//                //output: gendalf, kibutsudzi
//                + HumanResourcesStatistics.seniorityLongerThanAllignSilary(employees,tanjiro,w -> w instanceof Worker,
//                (e,w)-> w.isLongerThanMonths(Period.between(
//                        e.getEmploymentDate(),LocalDate.now()).getYears()*12 + Period.between(
//                        e.getEmploymentDate(),LocalDate.now()).getMonths()),(e,w) -> e.isSalGreater(w.getSalary()),(e,w) -> w.setSalary(e.getSalary()))
//                + "\n============================================================================\nseniorityBetweenTwoAndFourYearsAndAgeGreaterThan:\n"
//                //output: toho, nedzuko
//                + HumanResourcesStatistics.seniorityBetweenTwoAndFourYearsAndAgeGreaterThan(employees,80,
//                w -> w instanceof Worker, (w,y) -> w.getAge() > y,w -> w.isLongerThanYear(2) && !w.isLongerThanYear(4))
//        );

    }
}

