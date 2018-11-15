package solutions.exercise2;

import org.sopra.api.ConstructionCostCalculator;
import org.sopra.api.Scenario;
import org.sopra.api.exercises.ExerciseSubmission;
import org.sopra.api.model.PlayfieldElement;
import org.sopra.api.model.producer.ProducerType;

/**
 * This class is an implementation of the interface ConstructionCostCalculator.
 * The class implements the method calculateCost(location, type) according to
 * the game specifications. 
 * 
 * @author Adrian Paquin, Mehriban Kurbanova
 * @version 1.0
 *
 */
public class ConstructionCostCalculatorImpl implements ConstructionCostCalculator, ExerciseSubmission {
	private Scenario scenario;

	public ConstructionCostCalculatorImpl(Scenario scenario) {
		this.scenario = scenario;
	}
	
	/**
	 * This method calculates the building costs for the given
	 * playfield location and the corresponding type according
	 * to the game specifications.
	 * 
	 * @param location the playfield location for which the costs should be calculated
	 * @param type the corresponding producer type
	 * @return building costs for the given location and playfield type
	 */
	public double calculateCost(PlayfieldElement location, ProducerType type) {
		if (location == null || type == null)
			throw new IllegalArgumentException("parameter is not allowed to be null");

		double buildingCost = scenario.getEnergyNodeConfig().getBasicConstructionCost(type);
		
		if (location.getElementType().equals(PlayfieldElement.ElementType.BEACH)) {
			switch (type) {
			case BIOGAS_FIRED_POWER_PLANT:
				break;
			case COALFIRED_POWER_PLANT:
				break;
			case GASFIRED_POWER_PLANT:
				buildingCost *= 1.1;
				break;
			case HYDRO_POWER_PLANT:
				buildingCost *= 0.9;
				break;
			case NUCLEAR_POWER_PLANT:
				break;
			case SOLAR_POWER_PLANT:
				break;
			case WIND_POWER_PLANT:
				buildingCost *= 0.9;
				break;
			}
		}
		else if (location.getElementType().equals(PlayfieldElement.ElementType.GRASSLAND)) {
			switch (type) {
			case BIOGAS_FIRED_POWER_PLANT:
				break;
			case COALFIRED_POWER_PLANT:
				break;
			case GASFIRED_POWER_PLANT:
				break;
			case HYDRO_POWER_PLANT:
				break;
			case NUCLEAR_POWER_PLANT:
				buildingCost *= 1.1;
				break;
			case SOLAR_POWER_PLANT:
				break;
			case WIND_POWER_PLANT:
				break;
			}
		}
		else if (location.getElementType().equals(PlayfieldElement.ElementType.MOUNTAIN)) {
			switch (type) {
			case BIOGAS_FIRED_POWER_PLANT:
				break;
			case COALFIRED_POWER_PLANT:
				buildingCost *= 2;
				break;
			case GASFIRED_POWER_PLANT:
				buildingCost *= 2;
				break;
			case HYDRO_POWER_PLANT:
				break;
			case NUCLEAR_POWER_PLANT:
				break;
			case SOLAR_POWER_PLANT:
				buildingCost *= 2;
				break;
			case WIND_POWER_PLANT:
				buildingCost *= 2;
				break;
			}
		}
		else if (location.getElementType().equals(PlayfieldElement.ElementType.RIVER)) {
			switch (type) {
			case BIOGAS_FIRED_POWER_PLANT:
				buildingCost *= 2;
				break;
			case COALFIRED_POWER_PLANT:
				break;
			case GASFIRED_POWER_PLANT:
				buildingCost *= 2;
				break;
			case HYDRO_POWER_PLANT:
				buildingCost *= 0.8;
				break;
			case NUCLEAR_POWER_PLANT:
				break;
			case SOLAR_POWER_PLANT:
				buildingCost *= 2;
				break;
			case WIND_POWER_PLANT:
				buildingCost *= 1.5;
				break;
			}
		}
		else if (location.getElementType().equals(PlayfieldElement.ElementType.SEA)) {
			switch (type) {
			case BIOGAS_FIRED_POWER_PLANT:
				break;
			case COALFIRED_POWER_PLANT:
				break;
			case GASFIRED_POWER_PLANT:
				break;
			case HYDRO_POWER_PLANT:
				break;
			case NUCLEAR_POWER_PLANT:
				break;
			case SOLAR_POWER_PLANT:
				buildingCost *= 2;
				break;
			case WIND_POWER_PLANT:
				buildingCost *= 0.8;
				break;
			}
		}
		
		return buildingCost;
	}

	/**
	 * This method returns the team identifier.
	 * 
	 * @return - team identifier.
	 */
	@Override
	public String getTeamIdentifier() {
		return "G06T02";
	}
}
