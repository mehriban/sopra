package solutions.exercise4;

import static org.junit.Assert.fail;

import java.util.Deque;
import java.util.LinkedList;

import org.junit.Test;
import org.sopra.api.exercises.ExerciseSubmission;
import org.sopra.api.exercises.exercise3.ResidualEdge;
import org.sopra.api.exercises.exercise3.ResidualGraph;
import org.sopra.api.exercises.exercise4.AbstractFordFulkersonTest;

import solutions.exercise3.ResidualGraphImpl;

/**
 * @author Mehriban Malik
 *
 */
public class FordFulkersonTest extends AbstractFordFulkersonTest implements ExerciseSubmission {


	public FordFulkersonTest() {
		
	}
	
	public void printDeque(Deque<ResidualEdge<String>> que) {
		for (ResidualEdge<String> edge : que) {
			System.out.print(edge.getCapacity() + " ");
		}
		System.out.println("");
	}

	@Override
	@Test
	public void test_augmentPath1() {
		Deque<ResidualEdge<String>> testPath = new LinkedList<ResidualEdge<String>>();
		
		ResidualGraph<String> resGraph1 = new ResidualGraphImpl<String>(flowGraph1);
		
		testPath.add(resGraph1.getEdge("s", "b"));
		testPath.add(resGraph1.getEdge("b", "a"));
		testPath.add(resGraph1.getEdge("a", "d"));
		testPath.add(resGraph1.getEdge("d", "t"));
		
		printDeque(testPath);
		
		sut.augmentPath(testPath);
		
		Integer[] correctValues = {4, 0, 5, 3};
		
		int current_index = 0;
		for (ResidualEdge<String> edge : testPath) {
			if (edge.getCapacity() != correctValues[current_index])
				fail("Incorrect value in edge");
 			
			current_index++;
		}
		
		printDeque(testPath);
	}

	@Override
	@Test
	public void test_augmentPath2() {
		Deque<ResidualEdge<String>> testPath = new LinkedList<ResidualEdge<String>>();
		
		ResidualGraph<String> resGraph2 = new ResidualGraphImpl<String>(flowGraph2);
		
		testPath.add(resGraph2.getEdge("s", "a"));
		testPath.add(resGraph2.getEdge("a", "c"));
		testPath.add(resGraph2.getEdge("c", "b"));
		testPath.add(resGraph2.getEdge("b", "e"));
		testPath.add(resGraph2.getEdge("e", "t"));
		
		printDeque(testPath);
		
		sut.augmentPath(testPath);
		
		Integer[] correctValues = {7, 2, 0, 4, 2};
		
		int current_index = 0;
		for (ResidualEdge<String> edge : testPath) {
			if (edge.getCapacity() != correctValues[current_index])
				fail("Incorrect value in edge");
 			
			current_index++;
		}
		
		printDeque(testPath);
	}

	@Override
	@Test
	public void test_augmentPath_ParameterNull() {
		try {
			sut.augmentPath(null);
			fail("Argument should not be null");
		} catch (IllegalArgumentException ex) {
			
		}
	}

	@Override
	@Test
	public void test_findMaxFlow_ParameterGraphIsNull() {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Test
	public void test_findMaxFlow_ParameterStartIsNull() {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Test
	public void test_findMaxFlow_ParameterTargetIsNull() {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Test
	public void test_findMaxFlow_ParameterTargetNotInGraph() {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Test
	public void test_findMaxFlow_flowGraphA() {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Test
	public void test_findMaxFlow_flowGraphB() {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Test
	public void test_findMaxFlow_flowGraphC() {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Test
	public void test_findPath1() {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Test
	public void test_findPath2() {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Test
	public void test_findPath_IsNull() {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Test
	public void test_findPath_ParameterGraphIsNull() {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Test
	public void test_findPath_ParameterStartIsNull() {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Test
	public void test_findPath_ParameterTargetIsNull() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getTeamIdentifier() {
		return "G06T02";
	}

}
