package unitTesting;

import assignment01.Container;
import assignment01.sample.Hyundai;
import assignment01.sample.NeuralNetwork;
import assignment01.sample.Person;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

class ContainerTest {

    @Test
    void PersonContainer() {
        Person person = new Person(22);
        Container<Person, Integer> container = new Container<>(new Person(17), person,
                new Person(45), new Person(3));
        System.out.println("All elements : " + container.elements() + "\nAggregation : " + container.aggregateAllElements()
                + "\nClone test : " + cloneCheckPerson(container.cloneElementAtIndex(1), person) + "\n");
    }

    @Test
    void HundaiConteiner() {
        Hyundai hundai = new Hyundai("HR-3J-0T");
        Container<Hyundai, String> container = new Container<>(new Hyundai("TM-SZ-01"), hundai,
                new Hyundai("MC-WR-NR"),
                new Hyundai("PR-CK-69"));
        System.out.println("All elements : " + container.elements() + "\nAggregation : " + container.aggregateAllElements()
                + "\nClone test : " + cloneCheckHundai(container.cloneElementAtIndex(1), hundai) + "\n");
        Assert.assertNotEquals(container.cloneElementAtIndex(1),hundai);
        Assert.assertEquals(container.cloneElementAtIndex(1).toString(),hundai.toString());
    }

    @Test
    void NeuralNetworkConteiner() {
        NeuralNetwork neuralNetwork = new NeuralNetwork(0.645);
        Container<NeuralNetwork, Double> container = new Container<>(new NeuralNetwork(2.345), neuralNetwork,
                new NeuralNetwork(3.45001),
                new NeuralNetwork(5.3621));
        System.out.println("All elements : " + container.elements() + "\nAggregation : " + container.aggregateAllElements()
                + "\nClone test : " + cloneCheckNN(container.cloneElementAtIndex(1), neuralNetwork) + "\n");
    }

    boolean cloneCheckPerson(Person p1, Person p2) {
        return p1.getClass().equals(p2.getClass()) && p1.age() == p2.age();
    }

    boolean cloneCheckHundai(Hyundai h1, Hyundai h2) {
        return h1.getClass().equals(h2.getClass()) && h1.getSerial_number().equals(h2.getSerial_number());
    }

    boolean cloneCheckNN(NeuralNetwork n1, NeuralNetwork n2) {
        return n1.getClass().equals(n2.getClass()) && n1.getNeuron_mass() == n2.getNeuron_mass();
    }

}