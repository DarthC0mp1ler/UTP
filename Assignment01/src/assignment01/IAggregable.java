package assignment01;

public interface IAggregable<TElement extends IAggregable<TElement, TResult>, TResult> {

	TResult aggregate(TResult intermediateResult);
}