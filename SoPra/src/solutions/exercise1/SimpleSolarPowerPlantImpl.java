package solutions.exercise1;

import org.sopra.api.exercises.ExerciseSubmission;

/**
 * This class represents a simple solar power plant and extends SimpleEnergyNodeImpl.java.
 * It implements SimpleProducer.java and ExerciseSubmission.
 * 
 * @author Adrian Paquin
 * @version 1.0
 * @since 24.10.2018
 */
public class SimpleSolarPowerPlantImpl extends SimpleEnergyNodeImpl 
implements SimpleProducer, ExerciseSubmission{
	
	/**
	 * This constructor calls the constructor of it's superclass and initializes it with the input
	 * x-coordinate and y-coordinate.
	 * @param x: The x-coordinate the SolarPowerPlant is supposed to be placed at.
	 * @param y: The y-coordinate the SolarPowerPlant is supposed to be placed at.
	 */
	public SimpleSolarPowerPlantImpl(int x, int y) {
		super(x, y);
	}
   /**
    * This method returns the team identifier.
    * @return team identifier.
    */
	@Override
	public String getTeamIdentifier() {
		String teamIdentifier = "G06T02";
		return teamIdentifier;			
	}
	/**
	 * This method returns the producer name.
	 * @return producer name.
	 */
	@Override
	public String getProducerName() {
		String name = super.getName() + ": SolarPowerPlant";
		return name;
	}
}
