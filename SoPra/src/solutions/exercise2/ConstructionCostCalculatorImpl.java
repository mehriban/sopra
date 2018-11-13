package solutions.exercise2;

import org.sopra.api.ConstructionCostCalculator;
import org.sopra.api.Scenario;
import org.sopra.api.exercises.ExerciseSubmission;
import org.sopra.api.model.PlayfieldElement;
import org.sopra.api.model.producer.ProducerType;

public class ConstructionCostCalculatorImpl implements ConstructionCostCalculator, ExerciseSubmission {

	private Scenario scenario;

	public ConstructionCostCalculatorImpl(Scenario scenario) {
		this.scenario = scenario;
	}
	
	@Override
	public double calculateCost(PlayfieldElement location, ProducerType type) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getTeamIdentifier() {
		String team = "G06T02AB2";
		return team;
	}

}