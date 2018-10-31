package solutions.exercise1;
/**
 * This abstract class represents a simple energy node and implements the interfaces 
 * SimplEnergyNode.java and ExerciseSubmission.java.
 * 
 * @author Adrian Paquin
 * @version 1.0
 * @since 24.10.2018
 */
public abstract class SimpleEnergyNodeImpl implements SimpleEnergyNode, ExerciseSubmission {

	private final int x;
	private final int y;
	
	/**
	 * This instance variable stores the current energy level of the EnergyNode.
	 */
	protected int energyLevel;
	
	/**
	 * This constructor initializes the EnergyNode with the input x-coordinate and y-coordinate 
	 * and sets energyLevel to 0.
	 * 
	 * @param x: The x-coordinate the EnergyNode is supposed to be placed at.
	 * @param y: The y-coordinate the EnergyNode is supposed to be placed at.
	 */
	protected SimpleEnergyNodeImpl(int x, int y) {
		this.x = x;
		this.y = y;
		energyLevel = 0;
	}
	
	/**
	 * This method returns the x-coordinate of the EnergyNode.
	 * @return x-coordinate of the EnergyNode.
	 */
	@Override
	public int getXPos() {
		return x;
	}
	
	/**
	 * This method returns the y-coordinate of the EnergyNode.
	 * @return y-coordinate of the EnergyNode.
	 */
	@Override
	public int getYPos() {
		return y;
	}
	
	/**
	 * This method returns the energy level of the EnergyNode.
	 * @return energy level of the EnergyNode.
	 */
	@Override
	public int getEnergyLevel() {
		return energyLevel;
	}
	
	/**
	 * This method returns the name of the EnergyNode.
	 * @return name of the EnergyNode.
	 */
	@Override
	public String getName() {
		return "EnergyNode";
	}
	
	/**
	 * This method sets the energy Level of the EnergyNode.
	 * @param energy level of the EnergyNode.
	 */
	@Override
	public void setEnergyLevel(int energyLevel) {
		this.energyLevel = energyLevel;
	}
	
}
