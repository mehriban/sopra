package solutions.exercise2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.Comparator;

import org.junit.Test;
import org.sopra.api.exercises.ExerciseSubmission;
import org.sopra.api.exercises.exercise2.AbstractQuicksortTest;

class IntComparator implements Comparator<Integer> {

	@Override
	public int compare(Integer v1, Integer v2) {
		return v1 < v2 ? -1 : v1 > v2 ? +1 : 0;
	}
}

public class QuicksortTest extends AbstractQuicksortTest implements ExerciseSubmission {
	
	public void printArray(Integer[] arr) {
		for (int el: arr) {
			System.out.print(el + ", ");
		}
		System.out.println("");
	}
	
	private <T> void testPartitions(T[] arr, int partitionPosition, T pivotElement, int left, int right) {
		T[] leftArray = Arrays.copyOfRange(arr, left, partitionPosition);
		T[] rightArray = Arrays.copyOfRange(arr, partitionPosition, right);
		for (T el: leftArray) {
			assertTrue((int) el <= (int) pivotElement);
		}
		for (T el: rightArray) {
			assertTrue((int) el > (int) pivotElement);
		}
	}
	
	@Override
	@Test
	public void testPartition() {
		// test general case
		// we tried our best to
		Integer[] arr = { 12, 5, 13, 10, 4, 8, 23, 0, 9 };
		int originalSize = arr.length;
		int left = 0;
		int right = 8;
		int pivotPosition = (left + right) / 2;
		int pivotElement = arr[pivotPosition];
		int position = sut.partition(arr, left, right);
		assertEquals(position, 2);
		
		// We could not find why the test from GUI was complaining about wrong pivot element.
		// So we tried our best to test the pivot element
		assertEquals(pivotElement, (int) arr[1]);
		
		assertEquals(originalSize, arr.length);
		testPartitions(arr, position, pivotElement, left, right);
		
		// test simple cases elementwise
		Integer[] arr2 = { 0, 2 };
		assertEquals((int) arr2[0], 0);
		assertEquals((int) arr2[1], 2);
		
		Integer[] arr3 = { 0, 2, 1 };
		sut.partition(arr3, 0, 2);
		assertEquals((int) arr3[0], 0);
		assertEquals((int) arr3[1], 1);
		assertEquals((int) arr3[2], 2);
		sut.partition(arr3, 1, 2);
		assertEquals((int) arr3[0], 0);
		assertEquals((int) arr3[1], 1);
		assertEquals((int) arr3[2], 2);
		
		Integer[] arr4 = { 0 };
		sut.partition(arr4, 0, 0);
		assertEquals((int) arr4[0], 0);
	}

	@Override
	@Test
	public void testPartition_Parameters() {
		// test if null array would throw an exception
		try {
			sut.partition(null, 0, 1);
			fail("array was null");
		} catch (IllegalArgumentException e) {

		}
		Integer[] arr = { 12, 5, 13, 10, 4, 8, 23, 0 };

		// test left boundary
		try {
			sut.partition(arr, -1, 7);
			fail("left boundary was negative");
		} catch (IllegalArgumentException e) {

		}
		try {
			sut.partition(arr, 8, 7);
			fail("left boundary was greater than the length");
		} catch (IllegalArgumentException e) {

		}

		// test right boundary
		try {
			sut.partition(arr, 0, -1);
			fail("right boundary was negative");
		} catch (IllegalArgumentException e) {

		}

		try {
			sut.partition(arr, 0, 8);
			fail("right boundary was greater than the length");
		} catch (IllegalArgumentException e) {

		}
	}
	
	private <T> void testIfArraySorted(T[] arr) {
		int previous = (int) arr[0];
		for (int i = 1; i < arr.length; i++) {
			if (previous > (int) arr[i]) 
				fail("Array is not sorted");
			else
				previous = (int) arr[i];
		}
	}

	@Override
	@Test
	public void testQuicksort() {
		// test general case if the resulting array is sorted
		Integer[] arr = { 12, 5, 13, 10, 4, 8, 23, 0 };
		int originalSize = arr.length;
		sut.quicksort(arr, 0, 7);
		assertEquals(originalSize, arr.length);
		testIfArraySorted(arr);
	}

	@Override
	@Test
	public void testQuicksort_Parameters() {
		// test if null array would throw an exception
		try {
			sut.quicksort(null, 0, 1);
			fail("array was null");
		} catch (IllegalArgumentException e) {

		}
		Integer[] arr = { 12, 5, 13, 10, 4, 8, 23, 0 };

		// test left boundary
		try {
			sut.quicksort(arr, -1, 7);
			fail("left boundary was negative");
		} catch (IllegalArgumentException e) {

		}
		try {
			sut.quicksort(arr, 8, 7);
			fail("left boundary was greater than the length");
		} catch (IllegalArgumentException e) {

		}

		// test right boundary
		try {
			sut.quicksort(arr, 0, -1);
			fail("right boundary was negative");
		} catch (IllegalArgumentException e) {

		}

		try {
			sut.quicksort(arr, 0, 8);
			fail("right boundary was greater than the length");
		} catch (IllegalArgumentException e) {

		}

	}

	@Override
	public String getTeamIdentifier() {
		return "G06T02";
	}
}
