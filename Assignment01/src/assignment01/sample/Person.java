package assignment01.sample;


import assignment01.IAggregable;
import assignment01.IDeeplyCloneable;

public class Person implements IAggregable<Person, Integer>, IDeeplyCloneable<Person> {
	
	private int _age;
	
	public Person() {
	}
	
	public Person(int age) {
		_age = age;
	}
	
	public int age() {
		return _age;
	}

	public Integer aggregate(Integer intermediateResult) {
		if  (intermediateResult == null) {
			return _age;
		}
		return _age + intermediateResult;
	}
	
	public Person deepClone() {
		Person clone = new Person();
		clone._age = _age;
		return clone;
	}

}