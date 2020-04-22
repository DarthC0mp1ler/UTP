package assignment01;

import java.util.Arrays;
import java.util.List;

public class Container<TElement extends IAggregable<TElement,TResult> & IDeeplyCloneable<TElement>,TResult>
        implements IContainer<TElement,TResult>{

    private List<TElement> listOfElements;

    public Container(TElement ... arr){
        listOfElements = Arrays.asList(arr);
    }

    @Override
    public List<TElement> elements() {
        return listOfElements;
    }

    @Override
    public TResult aggregateAllElements() {
        if(elements() == null){
            return null;
        }
        TResult tmp = null;
        for (int i = elements().size() - 1; i >= 0; i--) {
            tmp = elements().get(i).aggregate( tmp);
        }
        return tmp;
    }

    @Override
    public TElement cloneElementAtIndex(int index) {
        if(elements() == null || index > elements().size() || index < 0){
            return null;
        }
        return elements().get(index).deepClone();
    }


}


