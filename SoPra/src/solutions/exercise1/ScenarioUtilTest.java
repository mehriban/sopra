package solutions.exercise1;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;
import org.junit.Test;
import org.sopra.api.exercises.ExerciseSubmission;
import org.sopra.api.exercises.exercise1.AbstractScenarioUtilTest;
import org.sopra.api.model.PowerLine;
import org.sopra.api.model.PowerLineType;
import org.sopra.api.model.consumer.Consumer;
import org.sopra.api.model.consumer.ControllableConsumer;
import org.sopra.api.model.producer.ControllableProducer;
import org.sopra.api.model.producer.Producer;

public class ScenarioUtilTest extends AbstractScenarioUtilTest implements ExerciseSubmission {

	@Test
	// Declaration that this method is a test method. If you run this class as JUnit
	// Test, only the declared methods will run
	public void testGetPowerLinesByType() {
		// The throws-declaration marks this method, that during the execution, there
		// could be thrown an exception.
		// Usually you use that declaration, if you don't want to handle the exception
		// in this method. The exception
		// will be given to the superior method, that called this method.
		List<PowerLine> highVoltageLines = sut.getPowerLinesByType(graph1, PowerLineType.HIGH_VOLTAGE);
		// Get a list of high voltage power lines
		assertTrue("Number of high voltage lines", highVoltageLines.size() == 11);
		// Assert the status after the comma, if the status is not as expected, then
		// return the string before the comma
		List<PowerLine> lowVoltageLines = sut.getPowerLinesByType(graph1, PowerLineType.LOW_VOLTAGE);
		// Get a map of low voltage power lines & booleans
		assertThat("Number of low voltage lines", lowVoltageLines.size(), is(3));
		// Assert the status after the comma, if the status is not as expected, then
		// return the string before the comma
		List<PowerLine> mediumVoltageLines = sut.getPowerLinesByType(graph1, PowerLineType.MEDIUM_VOLTAGE);
		assertThat("Number of medium voltage lines", mediumVoltageLines.size(), is(4));
		// Assert the status after the comma, if the status is not as expected, then
		// return the string before the comma
	}

	@Test
	// Declaration that this method is a test method. If you run this class as JUnit
	// Test, only the declared methods will run
	public void testGetPowerLinesByType_Parameters() {
		// The throws-declaration marks this method, that during the execution, there
		// could be thrown an exception.
		// Usually you use that declaration, if you don't want to handle the exception
		// in this method. The exception
		// will be given to the superior method, that called this method.
		try {
			// The keyword try marks a command-block that could throw an exception
			sut.getPowerLinesByType(null, null);
			// Try to call the method with that parameters, from sut (System Under Test)
			fail();
		} catch (IllegalArgumentException e) {
			// The keyword catch marks a block in which the exception is going to be handled
			// If this point is reached, the test will fail!
		}
		try {
			// The keyword try marks a command-block that could throw an exception
			sut.getPowerLinesByType(null, PowerLineType.HIGH_VOLTAGE);
			// Try to call the method with that parameters, from sut (System Under Test)
			// If this point is reached, the test will fail!
			fail();
		} catch (IllegalArgumentException e) {
			// The keyword catch marks a block in which the exception is going to be handled
		}
		try {
			// The keyword try marks a command-block that could throw an exception
			sut.getPowerLinesByType(graph1, PowerLineType.HIGH_VOLTAGE);
			// Try to call the method with that parameters, from sut (System Under Test)
			// If this point is reached, the test will fail!
		} catch (IllegalArgumentException e) {
			// The keyword catch marks a block in which the exception is going to be handled
			fail();
		}
		try {
			// The keyword try marks a command-block that could throw an exception
			sut.getPowerLinesByType(graph1, null);
			// Try to call the method with that parameters, from sut (System Under Test)
			fail();
			// If this point is reached, the test will fail!
		} catch (IllegalArgumentException e) {
			// The keyword catch marks a block in which the exception is going to be handled
		}
	}

	@Test
	public void testGetControllableProducers() {
		List<ControllableProducer> producers = sut.getControllableProducers(graph1);
		assertTrue(producers.size() == 4);
	}

	@Test
	public void testGetControllableProducers_Parameters() {
		try {
			sut.getControllableProducers(graph1);
		} catch (IllegalArgumentException e) {
			fail();
		}

		try {
			sut.getControllableProducers(null);
			fail();
		} catch (IllegalArgumentException e) {
		}
	}

	@Test
	public void testGetControllableConsumers() {
		List<ControllableConsumer> consumers = sut.getControllableConsumers(graph1);
		assertTrue(consumers.size() == 3);
	}

	@Test
	public void testGetControllableConsumers_Parameters() {
		try {
			sut.getControllableConsumers(graph1);
		} catch (IllegalArgumentException e) {
			fail();
		}

		try {
			sut.getControllableConsumers(null);
			fail();
		} catch (IllegalArgumentException e) {
		}

	}

	@Test
	public void testGetProducers() {
		List<Producer> consumers = sut.getProducers(graph1);
		assertTrue(consumers.size() == 10);

	}

	@Test
	public void testGetProducers_Parameters() {
		try {
			sut.getProducers(graph1);
		} catch (IllegalArgumentException e) {
			fail();
		}

		try {
			sut.getProducers(null);
			fail();
		} catch (IllegalArgumentException e) {
		}

	}

	@Test
	public void testGetConsumers() {
		List<Consumer> consumers = sut.getConsumers(graph1);
		assertTrue(consumers.size() == 8);

	}

	@Test
	public void testGetConsumers_Parameters() {
		try {
			sut.getConsumers(graph1);
		} catch (IllegalArgumentException e) {
			fail();
		}

		try {
			sut.getConsumers(null);
			fail();
		} catch (IllegalArgumentException e) {
		}

	}

	@Override
	public String getTeamIdentifier() {
		return "Group 6 Team 02";
	}
}
