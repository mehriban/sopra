package solutions.exercise4;

import static org.junit.Assert.*;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Vector;
import java.util.NoSuchElementException;

import org.junit.Test;
import org.sopra.api.exercises.ExerciseSubmission;
import org.sopra.api.exercises.exercise3.FlowEdge;
import org.sopra.api.exercises.exercise3.ResidualEdge;
import org.sopra.api.exercises.exercise3.ResidualGraph;
import org.sopra.api.exercises.exercise4.AbstractFordFulkersonTest;

import solutions.exercise3.ResidualGraphImpl;

public class FordFulkersonTest extends AbstractFordFulkersonTest implements ExerciseSubmission {
    
    	@Override
    	@Test
	public void test_findPath1() {
		ResidualGraph<String> resGraph = new ResidualGraphImpl<>(flowGraph1);
		
		Deque<ResidualEdge<String>> path = sut.findPath("s", "t", resGraph);
		assertNotNull(path);
		
		Vector<ResidualEdge<String>> finalPath = new Vector<>(path);
		
		String strPath[] = { "s", "a", "d", "t" };
		int count1 = strPath.length - 1;
		int count2 = finalPath.size() - 1;
		int length = ((count1>count2) ? count1 : count2);
		
		for (int index = 0 ; index < length ; index++) {
		    try {
		    assertNotNull("Node mustn't be null.", finalPath.get(index));
		    assertTrue("Start/End node doesn't match.", finalPath.get(index).getEnd() == strPath[count1--] && finalPath.get(index).getStart() == strPath[count1]);
		    } catch (IndexOutOfBoundsException exc) {
			fail("Size doesn't match.");
		    }
		}
	}

	@Override
	@Test
	public void test_findPath2() {
		ResidualGraph<String> resGraph = new ResidualGraphImpl<>(flowGraph2);

		Deque<ResidualEdge<String>> path = sut.findPath("s", "t", resGraph);
		assertNotNull(path);
		
		Vector<ResidualEdge<String>> finalPath = new Vector<>(path);
		
		String strPath[] = { "s", "b", "e", "t" };
		int count1 = strPath.length - 1;
		int count2 = finalPath.size() - 1;
		int length = ((count1>count2) ? count1 : count2);
		
		for (int index = 0 ; index < length ; index++) {
		    try {
		    assertNotNull("Node mustn't be null.", finalPath.get(index));
		    assertTrue("Start/End node doesn't match.", finalPath.get(index).getEnd() == strPath[count1--] && finalPath.get(index).getStart() == strPath[count1]);
		    } catch (IndexOutOfBoundsException exc) {
			fail("Size doesn't match.");
		    }
		}
	}

	@Override
	@Test
	public void test_findPath_IsNull() {
		ResidualGraph<String> resGraph = new ResidualGraphImpl<>(flowGraph3);

		Deque<ResidualEdge<String>> path = sut.findPath("s", "t", resGraph);

		assertNull(path);
	}

	@Override
	@Test(expected = IllegalArgumentException.class)
	public void test_findPath_ParameterStartIsNull() {
		ResidualGraph<String> resGraph = new ResidualGraphImpl<>(flowGraph2);
		sut.findPath(null, "t", resGraph);
	}

	@Override
	@Test(expected = IllegalArgumentException.class)
	public void test_findPath_ParameterTargetIsNull() {
		ResidualGraph<String> resGraph = new ResidualGraphImpl<>(flowGraph2);
		sut.findPath("s", null, resGraph);
	}

	@Override
	@Test(expected = IllegalArgumentException.class)
	public void test_findPath_ParameterGraphIsNull() {
		sut.findPath("s", "t", null);
	}

	@Override
	@Test
	public void test_augmentPath1() {
		ResidualGraph<String> resGraph = new ResidualGraphImpl<>(flowGraph1);

		Deque<ResidualEdge<String>> path = new ArrayDeque<>();
		path.add(resGraph.getEdge("s", "b"));
		path.add(resGraph.getEdge("b", "a"));
		path.add(resGraph.getEdge("a", "d"));
		path.add(resGraph.getEdge("d", "t"));

		sut.augmentPath(path);
		
		assertNotNull(resGraph.getEdge("s", "a"));
		assertNotNull(resGraph.getEdge("a", "s"));
		assertTrue(resGraph.getEdge("s", "a").getCapacity() == 10);
		assertTrue(resGraph.getEdge("a", "s").getCapacity() == 10);
		
		assertNotNull(resGraph.getEdge("s", "b"));
		assertNotNull(resGraph.getEdge("b", "s"));
		assertTrue(resGraph.getEdge("s", "b").getCapacity() == 4);
		assertTrue(resGraph.getEdge("b", "s").getCapacity() == 10);
		
		assertNotNull(resGraph.getEdge("a", "b"));
		assertNotNull(resGraph.getEdge("b", "a"));
		assertTrue(resGraph.getEdge("a", "b").getCapacity() == 6);
		assertTrue(resGraph.getEdge("b", "a").getCapacity() == 0);
		
		assertNotNull(resGraph.getEdge("a", "d"));
		assertNotNull(resGraph.getEdge("d", "a"));
		assertTrue(resGraph.getEdge("a", "d").getCapacity() == 5);
		assertTrue(resGraph.getEdge("d", "a").getCapacity() == 11);
		
		assertNotNull(resGraph.getEdge("c", "d"));
		assertNotNull(resGraph.getEdge("d", "c"));
		assertTrue(resGraph.getEdge("c", "d").getCapacity() == 11);
		assertTrue(resGraph.getEdge("d", "c").getCapacity() == 11);
		
		assertNotNull(resGraph.getEdge("c", "t"));
		assertNotNull(resGraph.getEdge("t", "c"));
		assertTrue(resGraph.getEdge("c", "t").getCapacity() == 13);
		assertTrue(resGraph.getEdge("t", "c").getCapacity() == 13);
		
		assertNotNull(resGraph.getEdge("d", "t"));
		assertNotNull(resGraph.getEdge("t", "d"));
		assertTrue(resGraph.getEdge("d", "t").getCapacity() == 3);
		assertTrue(resGraph.getEdge("t", "d").getCapacity() == 9);
	}

	@Override
	@Test
	public void test_augmentPath2() {
		ResidualGraph<String> resGraph = new ResidualGraphImpl<>(flowGraph2);

		Deque<ResidualEdge<String>> path = new ArrayDeque<>();
		path.add(resGraph.getEdge("s", "a"));
		path.add(resGraph.getEdge("a", "c"));
		path.add(resGraph.getEdge("c", "b"));
		path.add(resGraph.getEdge("b", "e"));
		path.add(resGraph.getEdge("e", "t"));

		sut.augmentPath(path);
		
		assertNotNull(resGraph.getEdge("s", "a"));
		assertNotNull(resGraph.getEdge("a", "s"));
		assertTrue(resGraph.getEdge("s", "a").getCapacity() == 7);
		assertTrue(resGraph.getEdge("a", "s").getCapacity() == 17);
		
		assertNotNull(resGraph.getEdge("s", "b"));
		assertNotNull(resGraph.getEdge("b", "s"));
		assertTrue(resGraph.getEdge("s", "b").getCapacity() == 11);
		assertTrue(resGraph.getEdge("b", "s").getCapacity() == 11);
		
		assertNotNull(resGraph.getEdge("a", "c"));
		assertNotNull(resGraph.getEdge("c", "a"));
		assertTrue(resGraph.getEdge("a", "c").getCapacity() == 2);
		assertTrue(resGraph.getEdge("c", "a").getCapacity() == 12);
		
		assertNotNull(resGraph.getEdge("a", "d"));
		assertNotNull(resGraph.getEdge("d", "a"));
		assertTrue(resGraph.getEdge("a", "d").getCapacity() == 0);
		assertTrue(resGraph.getEdge("d", "a").getCapacity() == 0);
		
		assertNotNull(resGraph.getEdge("b", "c"));
		assertNotNull(resGraph.getEdge("c", "b"));
		assertTrue(resGraph.getEdge("b", "c").getCapacity() == 10);
		assertTrue(resGraph.getEdge("c", "b").getCapacity() == 0);
		
		assertNotNull(resGraph.getEdge("b", "e"));
		assertNotNull(resGraph.getEdge("e", "b"));
		assertTrue(resGraph.getEdge("b", "e").getCapacity() == 4);
		assertTrue(resGraph.getEdge("e", "b").getCapacity() == 14);
		
		assertNotNull(resGraph.getEdge("c", "d"));
		assertNotNull(resGraph.getEdge("d", "c"));
		assertTrue(resGraph.getEdge("c", "d").getCapacity() == 4);
		assertTrue(resGraph.getEdge("d", "c").getCapacity() == 4);
		
		assertNotNull(resGraph.getEdge("d", "e"));
		assertNotNull(resGraph.getEdge("e", "d"));
		assertTrue(resGraph.getEdge("d", "e").getCapacity() == 3);
		assertTrue(resGraph.getEdge("e", "d").getCapacity() == 3);
		
		assertNotNull(resGraph.getEdge("d", "t"));
		assertNotNull(resGraph.getEdge("t", "d"));
		assertTrue(resGraph.getEdge("d", "t").getCapacity() == 13);
		assertTrue(resGraph.getEdge("t", "d").getCapacity() == 13);
		
		assertNotNull(resGraph.getEdge("e", "t"));
		assertNotNull(resGraph.getEdge("t", "e"));
		assertTrue(resGraph.getEdge("e", "t").getCapacity() == 2);
		assertTrue(resGraph.getEdge("t", "e").getCapacity() == 12);
	}

	@Override
	@Test(expected = IllegalArgumentException.class)
	public void test_augmentPath_ParameterNull() {
		sut.augmentPath(null);
	}

	@Override
	@Test
	public void test_findMaxFlow_flowGraphA() {

		sut.findMaxFlow(flowGraphA, "s", "t");

		int dispatched = 0, arrived = 0;

		FlowEdge<String> e = flowGraphA.getEdge("s", "a");
		assertNotNull(e);
		dispatched += e.getFlow();

		e = flowGraphA.getEdge("s", "b");
		assertNotNull(e);
		dispatched += e.getFlow();

		e = flowGraphA.getEdge("d", "t");
		assertNotNull(e);
		arrived += e.getFlow();

		e = flowGraphA.getEdge("e", "t");
		assertNotNull(e);
		arrived += e.getFlow();
		
		assertEquals("Maxflow wrong", dispatched, 17);
		assertTrue("Input flow equals not output flow", dispatched == arrived);
	}

	@Override
	@Test
	public void test_findMaxFlow_flowGraphB() {

		sut.findMaxFlow(flowGraphB, "s", "t");

		int dispatched = 0, arrived = 0;

		FlowEdge<String> e = flowGraphB.getEdge("s", "a");
		assertNotNull(e);
		dispatched += e.getFlow();
		e = flowGraphB.getEdge("s", "b");
		assertNotNull(e);
		dispatched += e.getFlow();
		e = flowGraphB.getEdge("d", "t");
		assertNotNull(e);
		arrived += e.getFlow();
		e = flowGraphB.getEdge("e", "t");
		assertNotNull(e);
		arrived += e.getFlow();

		assertEquals("Maxflow wrong", dispatched, 24);
		assertTrue(arrived == dispatched);
	}

	@Override
	@Test
	public void test_findMaxFlow_flowGraphC() {

		sut.findMaxFlow(flowGraphC, "s", "t");

		int dispatched = 0, arrived = 0;

		FlowEdge<String> e = flowGraphC.getEdge("s", "a");
		assertNotNull(e);
		dispatched += e.getFlow();
		e = flowGraphC.getEdge("s", "b");
		assertNotNull(e);
		dispatched += e.getFlow();
		e = flowGraphC.getEdge("d", "t");
		assertNotNull(e);
		arrived += e.getFlow();
		e = flowGraphC.getEdge("e", "t");
		assertNotNull(e);
		arrived += e.getFlow();

		assertEquals("Maxflow wrong", dispatched, 10);
		assertTrue(arrived == dispatched);
	}

	@Override
	@Test(expected = IllegalArgumentException.class)
	public void test_findMaxFlow_ParameterGraphIsNull() {
	    sut.findMaxFlow(null, "s", "t");
	}

	@Override
	@Test
	public void test_findMaxFlow_ParameterStartIsNull() {
	    try {
		sut.findMaxFlow(flowGraphB, null, "t");
		fail();
	    } catch (NoSuchElementException e) {
		fail();
	    } catch (IllegalArgumentException e) {
		return;
	    }
	}

	@Override
	@Test
	public void test_findMaxFlow_ParameterTargetIsNull() {
	    try {
		sut.findMaxFlow(flowGraphB, "s", null);
		fail();
	    } catch (NoSuchElementException exc) {
		fail();
	    } catch (IllegalArgumentException e) {
		return;
	    }
	}

	@Override
	@Test(expected = NoSuchElementException.class)
	public void test_findMaxFlow_ParameterTargetNotInGraph() {
		sut.findMaxFlow(flowGraphB, "s", "X");
	}

	@Override
	public String getTeamIdentifier() {
		return "Musterloesung";
	}

}
