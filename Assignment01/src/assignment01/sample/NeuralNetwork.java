package assignment01.sample;

import assignment01.IAggregable;
import assignment01.IDeeplyCloneable;

public class NeuralNetwork implements IAggregable<NeuralNetwork,Double>, IDeeplyCloneable<NeuralNetwork>{

    private double neuron_mass;

    public NeuralNetwork() {}

    public NeuralNetwork(double neuron_mass){
        this.neuron_mass = neuron_mass;
    }

    public double getNeuron_mass(){
        return neuron_mass;
    }

    @Override
    public Double aggregate(Double intermediateResult) {
        if(intermediateResult == null){
            return neuron_mass;
        }
        return (neuron_mass + intermediateResult)/0.25;
    }

    @Override
    public NeuralNetwork deepClone() {
        NeuralNetwork clone = new NeuralNetwork();
        clone.neuron_mass = neuron_mass;
        return clone;
    }
}
