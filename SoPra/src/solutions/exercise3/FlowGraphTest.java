package solutions.exercise3;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Test;
import org.sopra.api.exercises.ExerciseSubmission;
import org.sopra.api.exercises.exercise3.AbstractFlowGraphTest;
import org.sopra.api.exercises.exercise3.FlowEdge;

public class FlowGraphTest extends AbstractFlowGraphTest implements ExerciseSubmission {

    @Test
    public void test_addNode() {
	assertTrue(sut.addNode("X"));
	assertFalse(sut.addNode("X"));
	assertFalse(sut.addNode(null));
    }

    @Test
    public void test_addEdge() {
	sut.addNode("u");
	sut.addNode("v");
	FlowEdge<String> edge = sut.addEdge("u", "v", 10);

	assertNotNull(edge);
	assertEquals(edge.getStart(), "u");
	assertEquals(edge.getEnd(), "v");
	assertEquals(edge.getCapacity(), 10);
	assertTrue(sut.getEdges().contains(edge));

	assertEquals(edge.getFlow(), 0);

	FlowEdge<String> edge2 = sut.addEdge("u", "v", 10);
	assertEquals(edge, edge2);

	try {
	    sut.addEdge("X", "v", 10);
	    fail("Expected an exception of type NoSuchElementException.");
	} catch (NoSuchElementException e) {
	}

	try {
	    sut.addEdge("u", "X", 10);
	    fail("Expected an exception of type NoSuchElementException.");
	} catch (NoSuchElementException e) {
	    
	}

    }

    @Test
    public void test_edgesFrom() {
	initSutWithTestData();

	assertNotNull(sut.edgesFrom("s"));
	assertEquals(sut.edgesFrom("s").size(), 2);
	assertNotNull(sut.edgesFrom("u"));
	assertEquals(sut.edgesFrom("u").size(), 2);
	assertNotNull(sut.edgesFrom("v"));
	assertEquals(sut.edgesFrom("v").size(), 2);
	assertNotNull(sut.edgesFrom("t"));
	assertEquals(sut.edgesFrom("t").size(), 0);

	for (String start : nodes) {
	    assertNotNull(sut.edgesFrom(start));
	    for (FlowEdge<String> edge : sut.edgesFrom(start)) {
		if (start.equals("s")) {
		    assertTrue(edge.getEnd().equals("u") || edge.getEnd().equals("v"));
		}
		if (start.equals("u")) {
		    assertTrue(edge.getEnd().equals("v") || edge.getEnd().equals("t"));
		}
		if (start.equals("v")) {
		    assertTrue(edge.getEnd().equals("u") || edge.getEnd().equals("t"));
		}
	    }
	}

	try {
	    sut.edgesFrom("X");
	    fail("Expected an exception of type NoSuchElementException.");
	} catch (NoSuchElementException e) {
	    
	}
    }

    @Test
    public void test_getEdges() {
	assertNotNull(sut.getEdges());

	assertTrue(sut.getEdges().isEmpty());

	initSutWithTestData();

	assertNotNull(sut.getEdges());

	assertEquals(sut.getEdges().size(), 6);

	for (FlowEdge<String> edge : sut.getEdges()) {
	    assertTrue(isEqual(edge, edges[0]) || isEqual(edge, edges[1]) || isEqual(edge, edges[2])
		    || isEqual(edge, edges[3]) || isEqual(edge, edges[4]) || isEqual(edge, edges[5]));
	}

    }

    @Test
    public void test_getEdge() {
	initSutWithTestData();

	assertNull(sut.getEdge(null, "t"));

	assertNull(sut.getEdge("s", null));

	assertNull(sut.getEdge("t", "u"));

	assertNull(sut.getEdge("s", "X"));

	assertNotNull(sut.getEdge("s", "u"));
	assertTrue(isEqual(sut.getEdge("s", "u"), edges[0]));
	assertNotNull(sut.getEdge("s", "v"));
	assertTrue(isEqual(sut.getEdge("s", "v"), edges[1]));
	assertNotNull(sut.getEdge("u", "v"));
	assertTrue(isEqual(sut.getEdge("u", "v"), edges[2]));
	assertNotNull(sut.getEdge("v", "u"));
	assertTrue(isEqual(sut.getEdge("v", "u"), edges[3]));
	assertNotNull(sut.getEdge("u", "t"));
	assertTrue(isEqual(sut.getEdge("u", "t"), edges[4]));
	assertNotNull(sut.getEdge("v", "t"));
	assertTrue(isEqual(sut.getEdge("v", "t"), edges[5]));
    }

    @Test
    public void test_getNodes() {

	assertNotNull(sut.getNodes());

	assertTrue(sut.getNodes().isEmpty());

	initSutWithTestData();

	assertEquals(sut.getNodes().size(), 4);

	assertTrue(sut.getNodes().contains("s"));
	assertTrue(sut.getNodes().contains("u"));
	assertTrue(sut.getNodes().contains("v"));
	assertTrue(sut.getNodes().contains("t"));
    }

    @Test
    public void test_containsNode() {
	initSutWithTestData();

	assertTrue(sut.containsNode("s"));
	assertTrue(sut.containsNode("u"));
	assertTrue(sut.containsNode("v"));
	assertTrue(sut.containsNode("t"));

	assertFalse(sut.containsNode("X"));
    }

    private void initSutWithTestData() {
	for (String n : nodes) {
	    sut.addNode(n);
	}
	for (FlowEdge<String> e : edges) {
	    sut.addEdge(e.getStart(), e.getEnd(), e.getCapacity());
	}
    }
    
    private boolean isEqual(FlowEdge<String> edge1, FlowEdge<String> edge2) {
	return edge1.getStart().equals(edge2.getStart()) && edge1.getEnd().equals(edge2.getEnd())
		&& edge1.getCapacity() == edge2.getCapacity();
    }

    @Override
    public String getTeamIdentifier() {
	return "Musterloesung";
    }

}
