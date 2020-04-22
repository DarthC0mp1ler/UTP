package unitTesting;

import assignment01.sample.Hyundai;
import assignment01.sample.NeuralNetwork;
import assignment01.sample.Person;
import org.junit.jupiter.api.Test;

class ClassTest {

    @Test
    void hundaiTest(){
        Hyundai hundai = new Hyundai("HR-03-JR");
        System.out.println("Field value : " + hundai.getSerial_number());
        System.out.println("After Aggregation : " + hundai.aggregate("215"));
        System.out.println("Clone : " + hundai.deepClone().getSerial_number() + "\n");
    }

    @Test
    void personTest(){
        Person person = new Person(17);
        System.out.println("Field value : " + person.age());
        System.out.println("After Aggregation : " + person.aggregate(5));
        System.out.println("Clone : " + person.deepClone().age() + "\n");
    }

    @Test
    void neuralNetworkTest(){
        NeuralNetwork neuralNetwork = new NeuralNetwork(0.654);
        System.out.println("Field value : " + neuralNetwork.getNeuron_mass());
        System.out.println("After Aggregation : " + neuralNetwork.aggregate(3.045));
        System.out.println("Clone : " + neuralNetwork.deepClone().getNeuron_mass() + "\n");
    }


}