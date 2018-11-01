package solutions.exercise1;

import org.sopra.api.exercises.ExerciseSubmission;
import org.sopra.api.exercises.exercise1.SimpleProducer;
import org.sopra.api.model.producer.ProducerType;
import org.sopra.api.model.producer.SolarPowerPlant;

/**
 * This class represents a simple solar power plant and extends SimpleEnergyNodeImpl.java.
 * It implements SimpleProducer.java and ExerciseSubmission.
 * 
 * @author Adrian Paquin, Mehriban Kurbanova
 * @version 1.0
 * @since 24.10.2018
 */
public class SimpleSolarPowerPlantImpl extends SimpleEnergyNodeImpl 
implements SolarPowerPlant, SimpleProducer, ExerciseSubmission{
	
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
		String teamIdentifier = "G06T02AB1";
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
	
	/**
	 * Returns provided power of SolarPowerPlant
	 * @return the provided power of SolarPowerPlant
	 */
	@Override
	public int getProvidedPower() {
		int round = 2; // we didn't found how to retrieve the current round so we assume here that it is always 2
		
		// compute the provided power according to game manuals on pages 3-5
		int currentTime = round % 24;
		double providedPower = 0;
		if (currentTime >= 0 && currentTime <= 3) 
			providedPower = 0 * getMaximumEnergyLevel();
		else if (currentTime >= 4 && currentTime <= 6)
			providedPower = 0.3 * getMaximumEnergyLevel();
		else if (currentTime >= 7 && currentTime <= 9)
			providedPower = 0.6 * getMaximumEnergyLevel();
		else if (currentTime >= 10 && currentTime <= 14)
			providedPower = 1 * getMaximumEnergyLevel();
		else if (currentTime >= 15 && currentTime <= 17)
			providedPower = 0.6 * getMaximumEnergyLevel();
		else if (currentTime >= 18 && currentTime <= 21)
			providedPower = 0.3 * getMaximumEnergyLevel();
		else if (currentTime >= 22)
			providedPower = 0 * getMaximumEnergyLevel();
		return (int) providedPower;
	}
	
	/**
	 * Returns SolarPowerPlant as ProducerType
	 * @return the type of SolarPowerPlant as ProducerType
	 */
	@Override
	public ProducerType getType() {
		return ProducerType.SOLAR_POWER_PLANT;
	}
	
	/**
	 * Returns maximum energy level for SolarPowerPlant
	 * @return the maximum level energy for SolarPowerPlant
	 */
	@Override
	public int getMaximumEnergyLevel() {
		// according to game manual on page 8
		return 200;
	}
}