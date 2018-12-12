package solutions.exercise4;

import static org.junit.Assert.fail;

import java.sql.Array;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Set;

import org.junit.Test;
import org.sopra.api.exercises.ExerciseSubmission;
import org.sopra.api.exercises.exercise3.FlowEdge;
import org.sopra.api.exercises.exercise3.FlowGraph;
import org.sopra.api.exercises.exercise3.ResidualEdge;
import org.sopra.api.exercises.exercise3.ResidualGraph;
import org.sopra.api.exercises.exercise4.AbstractFordFulkersonTest;

import solutions.exercise3.ResidualGraphImpl;


/**
 * @author Mehriban Malik, Adrian Paquin
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
		try {
			sut.findMaxFlow(null, "s", "t");
			fail("Argument should not be null");
		} catch (IllegalArgumentException ex) {
			
		}	
	}

	@Override
	@Test
	public void test_findMaxFlow_ParameterStartIsNull() {
		try {
			sut.findMaxFlow(flowGraphA, null, "t");
			fail("Argument should not be null");
		} catch (IllegalArgumentException ex) {
			
		}
		
	}

	@Override
	@Test
	public void test_findMaxFlow_ParameterTargetIsNull() {
		try {
			sut.findMaxFlow(flowGraphA, "s", null);
			fail("Argument should not be null");
		} catch (IllegalArgumentException ex) {
			
		}
		
	}

	@Override
	@Test
	public void test_findMaxFlow_ParameterTargetNotInGraph() {
		String target = "z";
		Set<String> nodes = flowGraphA.getNodes();
		if (!nodes.contains(target))
		try {
			sut.findMaxFlow(flowGraphA, "s", target);
			fail("Graph must contain the target node");
		} catch (IllegalArgumentException ex) {
			
		}
		
	}

	@Override
	@Test
	public void test_findMaxFlow_flowGraphA() {
		int maxFlow = 17;
		
		FlowEdge<String> sourceEdge1 = flowGraphA.getEdge("s", "a");
		FlowEdge<String> sourceEdge2 = flowGraphA.getEdge("s", "b");
		FlowEdge<String> targetEdge1 = flowGraphA.getEdge("t", "d");
		FlowEdge<String> targetEdge2 = flowGraphA.getEdge("t", "e");
		
		sut.findMaxFlow(flowGraphA, "s", "t");
		
		if (!(sourceEdge1.getCapacity() + sourceEdge2.getCapacity() == maxFlow)
			|| !(targetEdge1.getCapacity() + targetEdge2.getCapacity() == maxFlow)) {
			fail("Maximum flow was not calculated correctly. Correct maximum flow is: "+ maxFlow);
		}
		
	}

	@Override
	@Test
	public void test_findMaxFlow_flowGraphB() {
		int maxFlow = 24;
		
		FlowEdge<String> sourceEdge1 = flowGraphB.getEdge("s", "a");
		FlowEdge<String> sourceEdge2 = flowGraphB.getEdge("s", "b");
		FlowEdge<String> targetEdge1 = flowGraphB.getEdge("t", "d");
		FlowEdge<String> targetEdge2 = flowGraphB.getEdge("t", "e");
		
		sut.findMaxFlow(flowGraphB, "s", "t");
		
		if (!(sourceEdge1.getCapacity() + sourceEdge2.getCapacity() == maxFlow)
			|| !(targetEdge1.getCapacity() + targetEdge2.getCapacity() == maxFlow)) {
			fail("Maximum flow was not calculated correctly. Correct maximum flow is: "+ maxFlow);
		}
		
		
	}

	@Override
	@Test
	public void test_findMaxFlow_flowGraphC() {
		int maxFlow = 10;
		
		FlowEdge<String> sourceEdge1 = flowGraphC.getEdge("s", "a");
		FlowEdge<String> sourceEdge2 = flowGraphC.getEdge("s", "b");
		FlowEdge<String> targetEdge1 = flowGraphC.getEdge("t", "d");
		FlowEdge<String> targetEdge2 = flowGraphC.getEdge("t", "e");
		
		sut.findMaxFlow(flowGraphC, "s", "t");
		
		if (!(sourceEdge1.getCapacity() + sourceEdge2.getCapacity() == maxFlow)
			|| !(targetEdge1.getCapacity() + targetEdge2.getCapacity() == maxFlow)) {
			fail("Maximum flow was not calculated correctly. Correct maximum flow is: "+ maxFlow);
		}
		
		
	}

	@Override
	@Test
	public void test_findPath1() {
		
		Deque<ResidualEdge<String>> testPath = new LinkedList<ResidualEdge<String>>();
		ResidualGraph<String> resGraph1 = new ResidualGraphImpl<String>(flowGraph1);
		
		testPath.add(resGraph1.getEdge("s", "a"));
		testPath.add(resGraph1.getEdge("a", "d"));
		testPath.add(resGraph1.getEdge("d", "t"));
		
		printDeque(testPath);
		
		sut.findPath("s", "t", resGraph1);
		
		Integer[] correctValues = {10, 8, 6};
		
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
	public void test_findPath2() {
		Deque<ResidualEdge<String>> testPath = new LinkedList<ResidualEdge<String>>();
		ResidualGraph<String> resGraph2 = new ResidualGraphImpl<String>(flowGraph2);
		
		testPath.add(resGraph2.getEdge("s", "b"));
		testPath.add(resGraph2.getEdge("b", "e"));
		testPath.add(resGraph2.getEdge("e", "t"));
		
		printDeque(testPath);
		
		sut.findPath("s", "t", resGraph2);
		
		Integer[] correctValues = {11, 9, 7};
		
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
	public void test_findPath_IsNull() {
		Deque<ResidualEdge<String>> testPath = new LinkedList<ResidualEdge<String>>();
		ResidualGraph<String> resGraph3 = new ResidualGraphImpl<String>(flowGraph3);
		
		testPath.add(resGraph3.getEdge("s", "a"));
		testPath.add(resGraph3.getEdge("a", "c"));
		testPath.add(resGraph3.getEdge("c", "e"));
		testPath.add(resGraph3.getEdge("e", "t"));
		
		printDeque(testPath);
		
		sut.findPath("s", "t", resGraph3);
		
		Integer[] correctValues = {13, 8, 0, 18};
		
		int current_index = 0;
		for (ResidualEdge<String> edge : testPath) {
			if (edge.getCapacity() != correctValues[current_index])
				fail("Incorrect value in edge");
 			if (edge.getCapacity() == 0)
 				fail("Capacity can not be 0");
			current_index++;
		}
		
		printDeque(testPath);
		
		
	}

	@Override
	@Test
	public void test_findPath_ParameterGraphIsNull() {
		try {
			sut.findPath("s", "t", null);
			fail("Argument should not be null");
		} catch (IllegalArgumentException ex) {
			
		}
		
	}

	@Override
	@Test
	public void test_findPath_ParameterStartIsNull() {
		ResidualGraph<String> resGraph = new ResidualGraphImpl<String>(flowGraph1);
		
		try {
			sut.findPath(null, "t", resGraph);
			fail("Argument should not be null");
		} catch (IllegalArgumentException ex) {
			
		}
		
	}

	@Override
	@Test
	public void test_findPath_ParameterTargetIsNull() {
ResidualGraph<String> resGraph = new ResidualGraphImpl<String>(flowGraph1);
		
		try {
			sut.findPath("s", null, resGraph);
			fail("Argument should not be null");
		} catch (IllegalArgumentException ex) {
			
		}
		
		
	}

	@Override
	public String getTeamIdentifier() {
		return "G06T02";
	}

}