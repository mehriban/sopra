/**
 * @author Adrian Paquin
 * @date 16.01.2019 
 */

package solutions.exercise5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.sopra.api.ConstructionCostCalculator;
import org.sopra.api.Game;
import org.sopra.api.Scenario;
import org.sopra.api.exercises.exercise3.FlowGraph;
import org.sopra.api.model.Edge;
import org.sopra.api.model.EnergyNode;
import org.sopra.api.model.Graph;
import org.sopra.api.model.PlantLocation;
import org.sopra.api.model.PowerLine;
import org.sopra.internal.model.producer.WindPowerPlantImpl;
import org.sopra.api.model.producer.*;

import solutions.exercise2.ConstructionCostCalculatorImpl;
import solutions.exercise4.FordFulkersonImpl;

/**
 * This class represents the game and how it will play. It is separated into a build phase and an execution phase.
 */
public class FirstGame implements Game {
	ConstructionCostCalculator costCalc;
	ProducerType windPP = ProducerType.WIND_POWER_PLANT;
	ArrayList<Double> costList;
	PlantLocation best;
	PlantLocation secondBest;
/**
 * This method represents the build phase, in which consumers, producers and power lines are built on the playfield.
 * @param scenario - The scenario XML file that the game is played on. It contains the game data such as the playfield.
 */
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
			costList.add(costCalc.calculateCost(plantLocations.get(i).getPlayfieldElement(), windPP));		
			}
		int minIndex = costList.indexOf(Collections.min(costList));
		best = plantLocations.get(minIndex);
		
		//remove best PlantLocation to calculate second best PlantLocation
		costList.remove(minIndex);
		plantLocations.remove(minIndex);
		
		//calculate second best PlantLocation
		for (int i = 0; i < plantLocations.size(); i++) {
			costList.add(costCalc.calculateCost(plantLocations.get(i).getPlayfieldElement(), windPP));
		}
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
		
		//find maxFlow
		FlowGraph<Object> g = (FlowGraph) scenario.getGraph();
		FordFulkersonImpl<Object> ff = new FordFulkersonImpl<Object>();
		ff.findMaxFlow(g, wpp1, wpp2);
		
		//max cost 20000
		double totalCost = 0;
		totalCost = costCalc.calculateCost(best.getPlayfieldElement(), windPP)
					+ costCalc.calculateCost(secondBest.getPlayfieldElement(), windPP);
		if (totalCost > 20000) {
			throw new RuntimeException("The total cost of power plants and voltage lines must not exceed 20000!");
		}
		
		
	}
/**
 * This method represents the execution phase, in which commands are executed and values are altered. It is called once per round.
 * @param scenario - The scenario XML file that the game is played on. It contains the game data such as the playfield.
 * @param round - The round that the game is currently in.
 */
	@Override
	public void executionPhase(Scenario scenario, int round) {
		// TODO Auto-generated method stub

	} 

}
