package solutions.exercise2;

import org.sopra.api.Scenario;
import org.sopra.api.ConstructionCostCalculator;
import org.sopra.api.Multiplicator;
import org.sopra.api.exercises.ExerciseSubmission;
import org.sopra.api.model.PlayfieldElement;
import org.sopra.api.model.PlayfieldElement.ElementType;
import org.sopra.api.model.producer.ProducerType;

public class ConstructionCostCalculatorImpl implements ConstructionCostCalculator, ExerciseSubmission {
	
	private Scenario scenario;
	
	public ConstructionCostCalculatorImpl(Scenario scenario) {
		this.scenario = scenario;
	}

	@Override
	public double calculateCost(PlayfieldElement location, ProducerType producer) {
		if (location==null){
			throw new IllegalArgumentException("PlayfieldElement(Location) is not allowed to be null");
		}
		else if(producer==null){
			throw new IllegalArgumentException("ProducerType is not allowed to be null");
		}
		else {
			return basicCosts(producer)*multiplikator(location.getElementType(), producer).getFactor();
		}
	}
	
	private double basicCosts(ProducerType prod){
		return scenario.getEnergyNodeConfig().getBasicConstructionCost(prod);
	}
	
	private Multiplicator multiplikator(ElementType location, ProducerType prod){
		switch (location){
		case BEACH:		return beach(prod);
		case GRASSLAND:		return grass(prod);
		case MOUNTAIN:		return mountain(prod);
		case RIVER:		return river(prod);
		case SEA:		return sea(prod);
		default:		throw new IllegalArgumentException("Unknown Location Type: "+location);
		}
	}
	
	private Multiplicator beach(ProducerType prod) {
		switch(prod){
		case GASFIRED_POWER_PLANT:		return Multiplicator.HIGH;
		case HYDRO_POWER_PLANT:			return Multiplicator.LOW;
		case NUCLEAR_POWER_PLANT:		return Multiplicator.MEDIUM;
		case SOLAR_POWER_PLANT:			return Multiplicator.MEDIUM;
		case WIND_POWER_PLANT:			return Multiplicator.LOW;
		default: 				throw new IllegalArgumentException(prod.name()+" is not allowed to be built on "+ElementType.BEACH);
		}
	}
	
	private Multiplicator grass(ProducerType prod) {
		switch(prod){
		case BIOGAS_FIRED_POWER_PLANT:		return Multiplicator.MEDIUM;
		case COALFIRED_POWER_PLANT:		return Multiplicator.MEDIUM;
		case GASFIRED_POWER_PLANT:		return Multiplicator.MEDIUM;
		case NUCLEAR_POWER_PLANT:		return Multiplicator.HIGH;
		case SOLAR_POWER_PLANT:			return Multiplicator.MEDIUM;
		case WIND_POWER_PLANT:			return Multiplicator.MEDIUM;
		default: 				throw new IllegalArgumentException(prod.name()+" is not allowed to be built on "+ElementType.GRASSLAND);
		}
	}

	private Multiplicator mountain(ProducerType prod) {
		switch(prod){
		case COALFIRED_POWER_PLANT:		return Multiplicator.EXTREMELY_HIGH;
		case GASFIRED_POWER_PLANT:		return Multiplicator.EXTREMELY_HIGH;
		case SOLAR_POWER_PLANT:			return Multiplicator.EXTREMELY_HIGH;
		case WIND_POWER_PLANT:			return Multiplicator.EXTREMELY_HIGH;
		default: 				throw new IllegalArgumentException(prod.name()+" is not allowed to be built on "+ElementType.MOUNTAIN);
		}
	}
	
	private Multiplicator river(ProducerType prod) {
		switch(prod){
		case BIOGAS_FIRED_POWER_PLANT:		return Multiplicator.EXTREMELY_HIGH;
		case COALFIRED_POWER_PLANT:		return Multiplicator.MEDIUM;
		case GASFIRED_POWER_PLANT:		return Multiplicator.EXTREMELY_HIGH;
		case HYDRO_POWER_PLANT:			return Multiplicator.VERY_LOW;
		case NUCLEAR_POWER_PLANT:		return Multiplicator.MEDIUM;
		case SOLAR_POWER_PLANT:			return Multiplicator.EXTREMELY_HIGH;
		case WIND_POWER_PLANT:			return Multiplicator.VERY_HIGH;
		default: 				throw new IllegalArgumentException(prod.name()+" is not allowed to be built on "+ElementType.RIVER);
		}
	}
	
	private Multiplicator sea(ProducerType prod) {
		switch(prod){
		case HYDRO_POWER_PLANT:			return Multiplicator.MEDIUM;
		case SOLAR_POWER_PLANT:			return Multiplicator.EXTREMELY_HIGH;
		case WIND_POWER_PLANT:			return Multiplicator.VERY_LOW;
		default: 				throw new IllegalArgumentException(prod.name()+" is not allowed to be built on "+ElementType.SEA);
		}
	}
	
	@Override
	public String getTeamIdentifier() {
	    return "Musterloesung";
	}
}
