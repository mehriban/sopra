package solutions.exercise1;
/**
 * This is the interface for SimpleEnergyNodeImpl.java.
 * 
 * @author Adrian Paquin
 * @version 1.0
 * @since 24.10.2018
 */
public interface SimpleEnergyNode {
	/**
	 * This method returns the x-coordinate of the EnergyNode.
	 * @return x-coordinate of the EnergyNode.
	 */
	int getXPos();
	
	/**
	 * This method returns the y-coordinate of the EnergyNode.
	 * @return y-coordinate of the EnergyNode.
	 */
	int getYPos();
	
	/**
	 * This method returns the energy level of the EnergyNode.
	 * @return energy level of the EnergyNode.
	 */
	int getEnergyLevel();
	
	/**
	 * This method returns the name of the EnergyNode.
	 * @return name of the EnergyNode.
	 */
	String getName();

	/**
	 * This method sets the energy Level of the EnergyNode.
	 * @param energy level of the EnergyNode.
	 */
	void setEnergyLevel(int energyLevel);

}
