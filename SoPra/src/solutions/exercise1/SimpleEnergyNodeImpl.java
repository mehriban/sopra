package solutions.exercise1;

import org.sopra.api.exercises.ExerciseSubmission;
import org.sopra.api.exercises.exercise1.SimpleEnergyNode;

abstract class SimpleEnergyNodeImpl implements ExerciseSubmission, SimpleEnergyNode {

	private final int x;
	private final int y;
	protected int energyLevel = 0;
	
	protected SimpleEnergyNodeImpl(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public int getXPos() {
		return x;
	}

	@Override
	public int getYPos() {
		return y;
	}

	@Override
	public int getEnergyLevel() {
		return energyLevel;
	}

	@Override
	public String getName() {
		return "EnergyNode";
	}

	@Override
	public void setEnergyLevel(int energyLevel) {
		this.energyLevel = energyLevel;
	}
	
	@Override
	public String getTeamIdentifier() {
		return "Musterloesung";
	}
}
