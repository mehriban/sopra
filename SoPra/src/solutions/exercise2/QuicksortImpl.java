package solutions.exercise2;

import java.util.Comparator;

import org.sopra.api.exercises.ExerciseSubmission;
import org.sopra.api.exercises.exercise2.Quicksort;

/**
 * This class is an implementation of the Quicksort interface.
 * It contains an implementation for the quicksort-method that is
 * used to sort array using the given comparator.
 * 
 * @author Mehriban Kurbanova
 * @version 1.0
 */
public class QuicksortImpl<T> implements ExerciseSubmission, Quicksort<T> {
	private Comparator<T> comparator;

	public QuicksortImpl(Comparator<T> comparator) {
		if (comparator == null)
			throw new IllegalArgumentException("Parameter is not allowed to be null.");
		
		this.comparator = comparator;
		
	}

	/**
	 * This method is a helper method for the quicksort algorithm.
	 * It swaps in-place the elements of the given array according to 
	 * the pivot element which is in the middle of the array. 
	 * The elements smaller than the pivot element are placed left, 
	 * the elements that are  bigger than the pivot element are placed
	 * to the right. The  method also defines the place where an 
	 * array should be split for the next recursive step.
	 * 
	 * @param arr an array to be sorted
	 * @param left the left boundary of the elements in array to be sorted
	 * @param right the right boundary of the elements in array to be sorted
	 * @return the position in array where the array should be split in the next recursive step
	 * 
	 */
	@Override
	public int partition(T[] arr, int left, int right) {
		if (arr == null)
			throw new IllegalArgumentException("Parameter is not allowed to be null.");
		if (left < 0 || left > arr.length - 1 || right > arr.length-1 || right < 0)
			throw new IllegalArgumentException("Left or Right are invalid.");
		
		int leftPosition = left;
		int rightPosition = right;
		int pivotPosition = (left + right)/2;
		T pivot = arr[pivotPosition];
		while (leftPosition <= rightPosition) {
			while (comparator.compare(arr[leftPosition], pivot) < 0)
				leftPosition++;
			while (comparator.compare(arr[rightPosition], pivot) > 0)
				rightPosition--;
			if (leftPosition <= rightPosition) {
				T helpVariable = arr[leftPosition];
				arr[leftPosition] = arr[rightPosition];
				arr[rightPosition] = helpVariable;
				leftPosition++;
				rightPosition--;
			}
		}
		
		return leftPosition;
	}
	
	/**
	 * This method implements the quicksort algorithm.
	 * It recuresively calls the partition method until there are no
	 * more elements to be sorted. The method sorts the array in-place
	 * and the array has the elements of the original array in the
	 * increasing order.
	 * 
	 * @param arr an array to be sorted
	 * @param left the left boundary of the elements in array to be sorted
	 * @param right the right boundary of the elements in array to be sorted
	 */
	@Override
	public void quicksort(T[] arr, int left, int right) {
		if (arr == null)
			throw new IllegalArgumentException("Parameter is not allowed to be null.");
		if (left < 0 || left > arr.length - 1 || right > arr.length-1 || right < 0)
			throw new IllegalArgumentException("Left or Right interval is invalid.");
		if (left < right) {
			int leftPosition = partition(arr, left, right);
			quicksort(arr, left, leftPosition-1);
			quicksort(arr, leftPosition, right);
		}
	}
	
	/**
	 * This method returns the team identifier.
	 * 
	 * @return - team identifier.
	 */
	@Override
	public String getTeamIdentifier() {
		return "G06T02";
	}

}
