package Implementation;

import Implementation.comparators.BirthdateComparator;
import Implementation.comparators.FirstNameComparator;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public final class PersonDatabase {

    private List<Person> by_SN_N_BDT;
    private List<Person> by_N;
    private List<Person> by_BDT;
    private Map<Date, List<Person>> sby_BDT;

    public PersonDatabase(List<Person> list) {
        by_SN_N_BDT = list;
        by_N = new ArrayList<>(list);
        by_BDT = new ArrayList<>(list);

        by_SN_N_BDT.sort(Comparator.naturalOrder());
        by_N.sort(new FirstNameComparator());
        by_BDT.sort(new BirthdateComparator());

        sby_BDT = list.stream().collect(Collectors.groupingBy(Person::getBirthdate, TreeMap::new, Collectors.mapping(p -> p, Collectors.toList())));

    }

    public PersonDatabase(File file) {
        this(InputParser.parse(file));
    }

    public List<Person> sortedByFirstName() {
        return by_N;
    }

    public List<Person> sortedBySurnameFirstNameAndBirthdate() {
        return by_SN_N_BDT;
    }

    public List<Person> sortedByBirthdate() {
        return by_BDT;
    }

    public List<Person> bornOnDay(Date date) {
        return sby_BDT.get(date);
    }
}