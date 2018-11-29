package solutions.exercise3;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import org.junit.Test;
import org.sopra.api.exercises.ExerciseSubmission;
import org.sopra.api.exercises.exercise3.AbstractFlowGraphTest;
import org.sopra.api.exercises.exercise3.FlowEdge;

public class FlowGraphTest extends AbstractFlowGraphTest implements ExerciseSubmission {


	@Override
	public String getTeamIdentifier() {
		return "G06T02";
	}

	@Override
	@Test
	public void test_addEdge() {
		for (String node: nodes) {
			sut.addNode(node);
		}
		for (FlowEdge<String> edge : edges) {
			sut.addEdge(edge.getStart(), edge.getEnd(), edge.getCapacity());
		}
		try {
			sut.addEdge("u", "s", -1);
			fail("Didn't throw exception for negative capacity.");
		}
		catch (IllegalArgumentException ex) {
			
		}
		
		FlowEdge<String> addedEdge = sut.addEdge("u", "s", 0);
		assertEquals(addedEdge.getStart(), "u");
		assertEquals(addedEdge.getEnd(), "s");
		assertNotNull(sut.getEdge("u", "s"));
		assertEquals(sut.getEdge("u", "s").getFlow(), 0);
		try {
			sut.addEdge("u", "a", 0);
			fail("a is not in graph but no exception was thrown.");
		}
		catch(NoSuchElementException ex) {
			
		}
		addedEdge = sut.addEdge("u", "s", 0);
		assertEquals(addedEdge.getStart(), "u");
		assertEquals(addedEdge.getEnd(), "s");
	}

	@Override
	@Test
	public void test_addNode() {
		for (String node: nodes) {
			sut.addNode(node);
		}
		for (FlowEdge<String> edge : edges) {
			sut.addEdge(edge.getStart(), edge.getEnd(), edge.getCapacity());
		}
		
		boolean gAdded = sut.addNode("g");
		assertTrue(gAdded);
		
		boolean uNotAdded = sut.addNode("u");
		assertFalse(uNotAdded);
		
		assertFalse(sut.addNode(null));
	}

	@Override
	@Test
	public void test_containsNode() {
		for (String node: nodes) {
			sut.addNode(node);
		}
		for (FlowEdge<String> edge : edges) {
			sut.addEdge(edge.getStart(), edge.getEnd(), edge.getCapacity());
		}
		
		assertTrue(sut.containsNode("u"));
		assertTrue(sut.containsNode("v"));
		assertTrue(sut.containsNode("s"));
		assertTrue(sut.containsNode("t"));
		assertFalse(sut.containsNode("a"));
	}

	@Override
	@Test
	public void test_edgesFrom() {
		for (String node: nodes) {
			sut.addNode(node);
		}
		for (FlowEdge<String> edge : edges) {
			sut.addEdge(edge.getStart(), edge.getEnd(), edge.getCapacity());
		}
		
		Iterator<FlowEdge<String>> edgesFromU = sut.edgesFrom("u").iterator();
		FlowEdge<String> uvEdge = edgesFromU.next();
		assertEquals(sut.edgesFrom("u").size(), 2);
		assertEquals(uvEdge.getStart(), "u");
		assertEquals(uvEdge.getEnd(), "t");
		assertEquals(uvEdge.getCapacity(), 4);
		assertEquals(uvEdge.getFlow(), 0);
		assertEquals(sut.getEdges().size(), 6);
		
		uvEdge = edgesFromU.next();
		assertEquals(sut.edgesFrom("u").size(), 2);
		assertEquals(uvEdge.getStart(), "u");
		assertEquals(uvEdge.getEnd(), "v");
		assertEquals(uvEdge.getCapacity(), 9);
		assertEquals(uvEdge.getFlow(), 0);
		assertEquals(sut.getEdges().size(), 6);
		
		try {
			sut.edgesFrom("a");
			fail("a should not be in graph but it was.");
		}
		catch (NoSuchElementException ex) {
			
		}
	}
	
	private void test_getEdgeWithNodes(String start, String end, int capacity, int flow) {
		FlowEdge<String> suEdge = sut.getEdge(start, end);
		assertEquals(suEdge.getStart(), start);
		assertEquals(suEdge.getEnd(), end);
		assertEquals(suEdge.getCapacity(), capacity);
		assertEquals(suEdge.getFlow(), flow);
	}

	@Override
	@Test
	public void test_getEdge() {
		for (String node: nodes) {
			sut.addNode(node);
		}
		for (FlowEdge<String> edge : edges) {
			sut.addEdge(edge.getStart(), edge.getEnd(), edge.getCapacity());
		}
		assertNull(sut.getEdge("u", "a"));
		assertNull(sut.getEdge("", null));
		assertNull(sut.getEdge(null, ""));
		assertNull(sut.getEdge(null, null));
		
		test_getEdgeWithNodes("s", "u", 5, 0);
		test_getEdgeWithNodes("s", "v", 7, 0);
		test_getEdgeWithNodes("v", "t", 6, 0);
		test_getEdgeWithNodes("u", "t", 4, 0);
		test_getEdgeWithNodes("u", "v", 9, 0);
		test_getEdgeWithNodes("v", "u", 9, 0);
	}

	@Override
	@Test
	public void test_getEdges() {
		assertEquals(sut.getEdges().size(), 0);
		for (String node: nodes) {
			sut.addNode(node);
		}
		for (FlowEdge<String> edge : edges) {
			sut.addEdge(edge.getStart(), edge.getEnd(), edge.getCapacity());
		}
		List<FlowEdge<String>> testEdges = sut.getEdges();
		assertEquals(testEdges.size(), 6);
	}

	@Override
	public void test_getNodes() {
		for (String node: nodes) {
			sut.addNode(node);
		}
		for (FlowEdge<String> edge : edges) {
			sut.addEdge(edge.getStart(), edge.getEnd(), edge.getCapacity());
		}
		
		Set<String> allNodes = sut.getNodes();
		assertEquals(allNodes.size(), 4);
		assertFalse(allNodes.add("u"));
		assertFalse(allNodes.add("v"));
		assertFalse(allNodes.add("s"));
		assertFalse(allNodes.add("t"));
	}

}
