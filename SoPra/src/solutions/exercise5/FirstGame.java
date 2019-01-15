package solutions.exercise5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.sopra.api.ConstructionCostCalculator;
import org.sopra.api.Game;
import org.sopra.api.Scenario;
import org.sopra.api.model.EnergyNode;
import org.sopra.api.model.PlantLocation;
import org.sopra.internal.model.producer.WindPowerPlantImpl;
import org.sopra.api.model.producer.*;

import solutions.exercise2.ConstructionCostCalculatorImpl;

public class FirstGame implements Game {
	ConstructionCostCalculator costCalc;
	private ProducerType windPP;
	ProducerType WIND_POWER_PLANT = windPP;
	ArrayList<Double> costList;
	PlantLocation best;
	PlantLocation secondBest;

	@Override
	public void buildPhase(Scenario scenario) {
		
		//create list of plant locations
		List<PlantLocation> plantLocations = new ArrayList<PlantLocation>();
		plantLocations = scenario.getPlantLocations();
		if (plantLocations.size() < 2) {
			throw new RuntimeException ("Fewer than two potential wind power plant locations exist!");
		}
		
		//create cost calculator for this scenario
		this.costCalc = new ConstructionCostCalculatorImpl(scenario);
		
		//calculate best PlantLocation
		for (int i = 0; i < plantLocations.size(); i++) {
			costList.add(costCalc.calculateCost(plantLocations.get(i).getPlayfieldElement(), windPP));		}
		int minIndex = costList.indexOf(Collections.min(costList));
		best = plantLocations.get(minIndex);
		
		//remove best PlantLocation to calculate second best PlantLocation
		costList.remove(minIndex);
		plantLocations.remove(minIndex);
		
		//calculate second best PlantLocation
		for (int i = 0; i < plantLocations.size(); i++) {
			costList.add(costCalc.calculateCost(plantLocations.get(i).getPlayfieldElement(), windPP));
		int minIndex2 = costList.indexOf(Collections.min(costList));
		secondBest = plantLocations.get(minIndex2);
		
		//build wind power plant on best location
		int bestX = best.getPlayfieldElement().getXPos();
		int bestY = best.getPlayfieldElement().getYPos();
		EnergyNode wpp1 = new WindPowerPlantImpl(null, bestX, bestY);
		
		//build wind power plant on second best location
		int secondBestX = secondBest.getPlayfieldElement().getXPos();
		int secondBestY = secondBest.getPlayfieldElement().getYPos();
		EnergyNode wpp2 = new WindPowerPlantImpl(null, secondBestX, secondBestY);
		}

	}

	@Override
	public void executionPhase(Scenario scenario, int round) {
		// TODO Auto-generated method stub

	}

}
