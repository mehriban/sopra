package solutions.exercise2;

import java.util.Comparator;

import org.sopra.api.exercises.ExerciseSubmission;
import org.sopra.api.exercises.exercise2.Quicksort;

public class QuicksortImpl<T> implements ExerciseSubmission, Quicksort<T> {
	private Comparator<T> comparator;

	public QuicksortImpl(Comparator<T> comparator) {
		if (comparator == null)
			throw new IllegalArgumentException("Parameter is not allowed to be null.");
		
		this.comparator = comparator;
		
	}

	/**
	 * 
	 */
	@Override
	public int partition(T[] arr, int left, int right) {
		if (arr == null)
			throw new IllegalArgumentException("Parameter is not allowed to be null.");
		if (left < 0 || right > arr.length-1)
			throw new IllegalArgumentException("Left or Right are invalid.");
		
		
		int leftPosition = left;
		int rightPosition = right;
		int pivotPosition = (left + right)/2;
		T pivot = arr[pivotPosition];
		while (leftPosition <= rightPosition) {
			while (comparator.compare(arr[leftPosition], pivot) == -1)
				leftPosition++;
			while (comparator.compare(arr[rightPosition], pivot) == 1)
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

	@Override
	public void quicksort(T[] arr, int left, int right) {
		if (arr == null)
			throw new IllegalArgumentException("Parameter is not allowed to be null.");
		if (left < 0 || right > arr.length-1)
			throw new IllegalArgumentException("Left or Right are invalid.");
		
		if (left < right) {
			int leftPosition = partition(arr, left, right);
			quicksort(arr, left, leftPosition-1);
			quicksort(arr, leftPosition, right);
		}
	}
	

	@Override
	public String getTeamIdentifier() {
		return "G06T02";
	}

}
