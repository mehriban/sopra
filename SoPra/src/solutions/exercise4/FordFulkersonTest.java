package solutions.exercise4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import org.junit.Test;
import org.sopra.api.exercises.ExerciseSubmission;
import org.sopra.api.exercises.exercise3.FlowEdge;
import org.sopra.api.exercises.exercise3.ResidualEdge;
import org.sopra.api.exercises.exercise3.ResidualGraph;
import org.sopra.api.exercises.exercise4.AbstractFordFulkersonTest;

import solutions.exercise3.FlowEdgeImpl;
import solutions.exercise3.ResidualGraphImpl;


/**
 * @author Mehriban Malik, Adrian Paquin
 *
 */
public class FordFulkersonTest extends AbstractFordFulkersonTest implements ExerciseSubmission {


	public FordFulkersonTest() {
		
	}
	
	@Test
	public void testOurMaxFlow() {
		FordFulkersonImpl<String> f = new FordFulkersonImpl<String>();
		
		f.findMaxFlow(flowGraphA, "s", "t");
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
		
		sut.augmentPath(testPath);
		
		Integer[] correctValues = {4, 0, 5, 3};
		
		int current_index = 0;
		for (ResidualEdge<String> edge : testPath) {
			if (edge.getCapacity() != correctValues[current_index])
				fail("Incorrect value in edge");
 			
			current_index++;
		}
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
		
		sut.augmentPath(testPath);
		
		Integer[] correctValues = {7, 2, 0, 4, 2};
		
		int current_index = 0;
		for (ResidualEdge<String> edge : testPath) {
			if (edge.getCapacity() != correctValues[current_index])
				fail("Incorrect value in edge");
 			
			current_index++;
		}
	}

	@Override
	@Test (expected = IllegalArgumentException.class)
	public void test_augmentPath_ParameterNull() {
		sut.augmentPath(null);
		}

	@Override
	@Test (expected = IllegalArgumentException.class)
	public void test_findMaxFlow_ParameterGraphIsNull() {
		sut.findMaxFlow(null, "s", "t");
	}

	@Override
	@Test (expected = IllegalArgumentException.class)
	public void test_findMaxFlow_ParameterStartIsNull() {
		sut.findMaxFlow(flowGraphA, null, "t");		
	}

	@Override
	@Test (expected = IllegalArgumentException.class)
	public void test_findMaxFlow_ParameterTargetIsNull() {
		sut.findMaxFlow(flowGraphA, "s", null);		
	}

	@Override
	@Test (expected = NoSuchElementException.class)
	public void test_findMaxFlow_ParameterTargetNotInGraph() {
		String target = "Target";
		sut.findMaxFlow(flowGraphA, "s", target);		
	}

	@Override
	@Test
	public void test_findMaxFlow_flowGraphA() {
		List<FlowEdge<String>> expectedResult = new ArrayList<FlowEdge<String>>();
		
		FlowEdge<String> expectedEdge = new FlowEdgeImpl<String>("a", "b", 11);
		expectedEdge.setFlow(0);
		expectedResult.add(expectedEdge);
		
		expectedEdge = new FlowEdgeImpl<String>("a", "s", 15);
		expectedEdge.setFlow(0);
		expectedResult.add(expectedEdge);
		
		expectedEdge = new FlowEdgeImpl<String>("a", "c", 3);
		expectedEdge.setFlow(3);
		expectedResult.add(expectedEdge);
		
		expectedEdge = new FlowEdgeImpl<String>("a", "d", 6);
		expectedEdge.setFlow(6);
		expectedResult.add(expectedEdge);
		
		expectedEdge = new FlowEdgeImpl<String>("b", "a", 11);
		expectedEdge.setFlow(0);
		expectedResult.add(expectedEdge);
		
		expectedEdge = new FlowEdgeImpl<String>("b", "s", 20);
		expectedEdge.setFlow(0);
		expectedResult.add(expectedEdge);
		
		expectedEdge = new FlowEdgeImpl<String>("b", "c", 4);
		expectedEdge.setFlow(0);
		expectedResult.add(expectedEdge);
		
		expectedEdge = new FlowEdgeImpl<String>("b", "e", 8);
		expectedEdge.setFlow(8);
		expectedResult.add(expectedEdge);
		
		expectedEdge = new FlowEdgeImpl<String>("s", "a", 15);
		expectedEdge.setFlow(9);
		expectedResult.add(expectedEdge);
		
		expectedEdge = new FlowEdgeImpl<String>("s", "b", 20);
		expectedEdge.setFlow(8);
		expectedResult.add(expectedEdge);
		
		expectedEdge = new FlowEdgeImpl<String>("c", "a", 3);
		expectedEdge.setFlow(0);
		expectedResult.add(expectedEdge);
		
		expectedEdge = new FlowEdgeImpl<String>("c", "b", 4);
		expectedEdge.setFlow(0);
		expectedResult.add(expectedEdge);
		
		expectedEdge = new FlowEdgeImpl<String>("c", "d", 6);
		expectedEdge.setFlow(2);
		expectedResult.add(expectedEdge);
		
		expectedEdge = new FlowEdgeImpl<String>("c", "e", 1);
		expectedEdge.setFlow(1);
		expectedResult.add(expectedEdge);
		
		expectedEdge = new FlowEdgeImpl<String>("d", "a", 6);
		expectedEdge.setFlow(0);
		expectedResult.add(expectedEdge);
		
		expectedEdge = new FlowEdgeImpl<String>("d", "c", 6);
		expectedEdge.setFlow(0);
		expectedResult.add(expectedEdge);
		
		expectedEdge = new FlowEdgeImpl<String>("d", "t", 7);
		expectedEdge.setFlow(7);
		expectedResult.add(expectedEdge);
		
		expectedEdge = new FlowEdgeImpl<String>("d", "e", 7);
		expectedEdge.setFlow(1);
		expectedResult.add(expectedEdge);
		
		expectedEdge = new FlowEdgeImpl<String>("t", "d", 7);
		expectedEdge.setFlow(0);
		expectedResult.add(expectedEdge);
		
		expectedEdge = new FlowEdgeImpl<String>("t", "e", 10);
		expectedEdge.setFlow(0);
		expectedResult.add(expectedEdge);
		
		expectedEdge = new FlowEdgeImpl<String>("e", "b", 8);
		expectedEdge.setFlow(0);
		expectedResult.add(expectedEdge);
		
		expectedEdge = new FlowEdgeImpl<String>("e", "c", 1);
		expectedEdge.setFlow(0);
		expectedResult.add(expectedEdge);
		
		expectedEdge = new FlowEdgeImpl<String>("e", "d", 7);
		expectedEdge.setFlow(0);
		expectedResult.add(expectedEdge);
		
		expectedEdge = new FlowEdgeImpl<String>("e", "t", 10);
		expectedEdge.setFlow(10);
		expectedResult.add(expectedEdge);
		
		sut.findMaxFlow(flowGraphA, "s", "t");
		
		List<FlowEdge<String>> testResult = flowGraphA.getEdges();
		int index = 0;
		for (FlowEdge<String> edge : testResult) {
			assertEquals(edge.getStart(), expectedResult.get(index).getStart());
			assertEquals(edge.getEnd(), expectedResult.get(index).getEnd());
			assertEquals(edge.getCapacity(), expectedResult.get(index).getCapacity());
			assertEquals(edge.getFlow(), expectedResult.get(index).getFlow());
			index++;
		}

	}

	@Override
	@Test
	public void test_findMaxFlow_flowGraphB() {
		List<FlowEdge<String>> expectedResult = new ArrayList<FlowEdge<String>>();
		
		FlowEdge<String> expectedEdge = new FlowEdgeImpl<String>("a", "b", 5);
		expectedEdge.setFlow(4);
		expectedResult.add(expectedEdge);
		
		expectedEdge = new FlowEdgeImpl<String>("a", "s", 16);
		expectedEdge.setFlow(0);
		expectedResult.add(expectedEdge);
		
		expectedEdge = new FlowEdgeImpl<String>("a", "c", 7);
		expectedEdge.setFlow(7);
		expectedResult.add(expectedEdge);
		
		expectedEdge = new FlowEdgeImpl<String>("a", "d", 3);
		expectedEdge.setFlow(3);
		expectedResult.add(expectedEdge);
		
		expectedEdge = new FlowEdgeImpl<String>("b", "a", 5);
		expectedEdge.setFlow(0);
		expectedResult.add(expectedEdge);
		
		expectedEdge = new FlowEdgeImpl<String>("b", "s", 10);
		expectedEdge.setFlow(0);
		expectedResult.add(expectedEdge);
		
		expectedEdge = new FlowEdgeImpl<String>("b", "c", 7);
		expectedEdge.setFlow(4);
		expectedResult.add(expectedEdge);
		
		expectedEdge = new FlowEdgeImpl<String>("b", "e", 10);
		expectedEdge.setFlow(10);
		expectedResult.add(expectedEdge);
		
		expectedEdge = new FlowEdgeImpl<String>("s", "a", 16);
		expectedEdge.setFlow(14);
		expectedResult.add(expectedEdge);
		
		expectedEdge = new FlowEdgeImpl<String>("s", "b", 10);
		expectedEdge.setFlow(10);
		expectedResult.add(expectedEdge);
		
		expectedEdge = new FlowEdgeImpl<String>("c", "a", 7);
		expectedEdge.setFlow(0);
		expectedResult.add(expectedEdge);
		
		expectedEdge = new FlowEdgeImpl<String>("c", "b", 7);
		expectedEdge.setFlow(0);
		expectedResult.add(expectedEdge);
		
		expectedEdge = new FlowEdgeImpl<String>("c", "d", 11);
		expectedEdge.setFlow(11);
		expectedResult.add(expectedEdge);
		
		expectedEdge = new FlowEdgeImpl<String>("d", "a", 3);
		expectedEdge.setFlow(0);
		expectedResult.add(expectedEdge);
		
		expectedEdge = new FlowEdgeImpl<String>("d", "c", 11);
		expectedEdge.setFlow(0);
		expectedResult.add(expectedEdge);
		
		expectedEdge = new FlowEdgeImpl<String>("d", "t", 14);
		expectedEdge.setFlow(14);
		expectedResult.add(expectedEdge);
		
		expectedEdge = new FlowEdgeImpl<String>("d", "e", 9);
		expectedEdge.setFlow(0);
		expectedResult.add(expectedEdge);
		
		expectedEdge = new FlowEdgeImpl<String>("t", "d", 14);
		expectedEdge.setFlow(0);
		expectedResult.add(expectedEdge);
		
		expectedEdge = new FlowEdgeImpl<String>("t", "e", 12);
		expectedEdge.setFlow(0);
		expectedResult.add(expectedEdge);
		
		expectedEdge = new FlowEdgeImpl<String>("e", "b", 10);
		expectedEdge.setFlow(0);
		expectedResult.add(expectedEdge);
		
		expectedEdge = new FlowEdgeImpl<String>("e", "d", 9);
		expectedEdge.setFlow(0);
		expectedResult.add(expectedEdge);
		
		expectedEdge = new FlowEdgeImpl<String>("e", "t", 12);
		expectedEdge.setFlow(10);
		expectedResult.add(expectedEdge);
		
		sut.findMaxFlow(flowGraphB, "s", "t");
		
		List<FlowEdge<String>> testResult = flowGraphB.getEdges();
		int index = 0;
		for (FlowEdge<String> edge : testResult) {
			assertEquals(edge.getStart(), expectedResult.get(index).getStart());
			assertEquals(edge.getEnd(), expectedResult.get(index).getEnd());
			assertEquals(edge.getCapacity(), expectedResult.get(index).getCapacity());
			assertEquals(edge.getFlow(), expectedResult.get(index).getFlow());
			index++;
		}

	}

	@Override
	@Test
	public void test_findMaxFlow_flowGraphC() {
		List<FlowEdge<String>> expectedResult = new ArrayList<FlowEdge<String>>();
		FlowEdge<String> expectedEdge = new FlowEdgeImpl<String>("a", "b", 7);
		expectedEdge.setFlow(0);
		expectedResult.add(expectedEdge);
		
		expectedEdge = new FlowEdgeImpl<String>("a", "s", 11);
		expectedEdge.setFlow(0);
		expectedResult.add(expectedEdge);
		
		expectedEdge = new FlowEdgeImpl<String>("a", "c", 6);
		expectedEdge.setFlow(6);
		expectedResult.add(expectedEdge);
		
		expectedEdge = new FlowEdgeImpl<String>("b", "a", 7);
		expectedEdge.setFlow(0);
		expectedResult.add(expectedEdge);
		
		expectedEdge = new FlowEdgeImpl<String>("b", "s", 5);
		expectedEdge.setFlow(0);
		expectedResult.add(expectedEdge);
		
		expectedEdge = new FlowEdgeImpl<String>("b", "c", 4);
		expectedEdge.setFlow(4);
		expectedResult.add(expectedEdge);
		
		expectedEdge = new FlowEdgeImpl<String>("s", "a", 11);
		expectedEdge.setFlow(6);
		expectedResult.add(expectedEdge);
		
		expectedEdge = new FlowEdgeImpl<String>("s", "b", 5);
		expectedEdge.setFlow(4);
		expectedResult.add(expectedEdge);
		
		expectedEdge = new FlowEdgeImpl<String>("c", "a", 6);
		expectedEdge.setFlow(0);
		expectedResult.add(expectedEdge);
		
		expectedEdge = new FlowEdgeImpl<String>("c", "b", 4);
		expectedEdge.setFlow(0);
		expectedResult.add(expectedEdge);
		
		expectedEdge = new FlowEdgeImpl<String>("c", "d", 11);
		expectedEdge.setFlow(7);
		expectedResult.add(expectedEdge);
		
		expectedEdge = new FlowEdgeImpl<String>("c", "e", 3);
		expectedEdge.setFlow(3);
		expectedResult.add(expectedEdge);
		
		expectedEdge = new FlowEdgeImpl<String>("d", "c", 11);
		expectedEdge.setFlow(0);
		expectedResult.add(expectedEdge);
		
		expectedEdge = new FlowEdgeImpl<String>("d", "t", 6);
		expectedEdge.setFlow(6);
		expectedResult.add(expectedEdge);
		
		expectedEdge = new FlowEdgeImpl<String>("d", "e", 8);
		expectedEdge.setFlow(1);
		expectedResult.add(expectedEdge);
		
		expectedEdge = new FlowEdgeImpl<String>("t", "d", 6);
		expectedEdge.setFlow(0);
		expectedResult.add(expectedEdge);
		
		expectedEdge = new FlowEdgeImpl<String>("t", "e", 7);
		expectedEdge.setFlow(0);
		expectedResult.add(expectedEdge);
		
		expectedEdge = new FlowEdgeImpl<String>("e", "c", 3);
		expectedEdge.setFlow(0);
		expectedResult.add(expectedEdge);
		
		expectedEdge = new FlowEdgeImpl<String>("e", "d", 8);
		expectedEdge.setFlow(0);
		expectedResult.add(expectedEdge);
		
		expectedEdge = new FlowEdgeImpl<String>("e", "t", 7);
		expectedEdge.setFlow(4);
		expectedResult.add(expectedEdge);
		
		sut.findMaxFlow(flowGraphC, "s", "t");
		
		List<FlowEdge<String>> testResult = flowGraphC.getEdges();
		int index = 0;
		for (FlowEdge<String> edge : testResult) {
			assertEquals(edge.getStart(), expectedResult.get(index).getStart());
			assertEquals(edge.getEnd(), expectedResult.get(index).getEnd());
			assertEquals(edge.getCapacity(), expectedResult.get(index).getCapacity());
			assertEquals(edge.getFlow(), expectedResult.get(index).getFlow());
			index++;
		}
	}

	@Override
	@Test
	public void test_findPath1() {
		ResidualGraph<String> resGraph1 = new ResidualGraphImpl<String>(flowGraph1);
		Deque<ResidualEdge<String>> foundPath = sut.findPath("s", "t", resGraph1);
		
		assertEquals(foundPath.size(), 3);
		ResidualEdge<String> edge = foundPath.removeLast();
		assertEquals(edge.getStart(), "s");
		assertEquals(edge.getEnd(), "a");
		assertEquals(edge.getCapacity(), 10);
		
		edge = foundPath.removeLast();
		assertEquals(edge.getStart(), "a");
		assertEquals(edge.getEnd(), "d");
		assertEquals(edge.getCapacity(), 8);
		
		edge = foundPath.removeLast();
		assertEquals(edge.getStart(), "d");
		assertEquals(edge.getEnd(), "t");
		assertEquals(edge.getCapacity(), 6);
	}

	@Override
	@Test
	public void test_findPath2() {
		ResidualGraph<String> resGraph2 = new ResidualGraphImpl<String>(flowGraph2);
		Deque<ResidualEdge<String>> foundPath = sut.findPath("s", "t", resGraph2);
		
		assertEquals(foundPath.size(), 3);
		ResidualEdge<String> edge = foundPath.removeLast();
		assertEquals(edge.getStart(), "s");
		assertEquals(edge.getEnd(), "b");
		assertEquals(edge.getCapacity(), 11);
		
		edge = foundPath.removeLast();
		assertEquals(edge.getStart(), "b");
		assertEquals(edge.getEnd(), "e");
		assertEquals(edge.getCapacity(), 9);
		
		edge = foundPath.removeLast();
		assertEquals(edge.getStart(), "e");
		assertEquals(edge.getEnd(), "t");
		assertEquals(edge.getCapacity(), 7);
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
		Deque<ResidualEdge<String>> foundPath = sut.findPath("s", "t", resGraph3);
		assertNull(foundPath);
	}

	@Override
	@Test (expected = IllegalArgumentException.class)
	public void test_findPath_ParameterGraphIsNull() {
		sut.findPath("s", "t", null);		
	}

	@Override
	@Test (expected = IllegalArgumentException.class)
	public void test_findPath_ParameterStartIsNull() {
		ResidualGraph<String> resGraph = new ResidualGraphImpl<String>(flowGraph1);
		sut.findPath(null, "t", resGraph);		
	}

	@Override
	@Test (expected = IllegalArgumentException.class)
	public void test_findPath_ParameterTargetIsNull() {
		ResidualGraph<String> resGraph = new ResidualGraphImpl<String>(flowGraph1);
		sut.findPath("s", null, resGraph);		
	}

	@Override
	public String getTeamIdentifier() {
		return "G06T02";
	}

}