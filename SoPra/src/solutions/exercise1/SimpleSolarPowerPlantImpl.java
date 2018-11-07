package solutions.exercise1;

import org.sopra.api.exercises.ExerciseSubmission;
import org.sopra.api.exercises.exercise1.SimpleProducer;

public class SimpleSolarPowerPlantImpl extends SimpleEnergyNodeImpl implements ExerciseSubmission, SimpleProducer {

	public SimpleSolarPowerPlantImpl(int x, int y) {
		super(x, y);
	}

	/**
	 * Type of this of this PowerPlant is "SolarPowerPlant";
	 */
	@Override
	public String getProducerName() {
		return super.getName() + ": SolarPowerPlant";
	}

	@Override
	public String getTeamIdentifier() {
		return "Musterloesung";
	}

}
