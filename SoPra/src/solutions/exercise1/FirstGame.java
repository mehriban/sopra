 package solutions.exercise5;

import org.sopra.api.exercises.exercise2.*;
import org.sopra.api.exercises.exercise3.FlowGraph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.sopra.api.ConstructionCostCalculator;
import org.sopra.api.Game;
import org.sopra.api.Scenario;
import org.sopra.api.exercises.ExerciseSubmission;
import org.sopra.api.model.EnergyNode;
import org.sopra.api.model.PlantLocation;
import org.sopra.api.model.PlayfieldElement;
import org.sopra.api.model.PowerLine;
import org.sopra.api.model.producer.ProducerType;

import solutions.exercise2.ConstructionCostCalculatorImpl;

public class FirstGame implements Game, ExerciseSubmission {
	
	
	
	ConstructionCostCalculator costCalc;

	ProducerType windPP = ProducerType.WIND_POWER_PLANT;

	ArrayList<Double> costList;

	PlantLocation best;

	PlantLocation secondBest;
	
	double budget = 20000;
	
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
		
		budget = budget - costList.get(minIndex);

		

		//remove best PlantLocation to calculate second best PlantLocation

		costList.remove(minIndex);

		plantLocations.remove(minIndex);

		

		//calculate second best PlantLocation

		for (int i = 0; i < plantLocations.size(); i++) 
		{
			costList.add(costCalc.calculateCost(plantLocations.get(i).getPlayfieldElement(), windPP));
		}

		int minIndex2 = costList.indexOf(Collections.min(costList));

		secondBest = plantLocations.get(minIndex2);
		
		budget = budget - costList.get(minIndex2);

		

		//build wind power plant on best location

		int bestX = best.getPlayfieldElement().getXPos();

		int bestY = best.getPlayfieldElement().getYPos();

		

		//build wind power plant on second best location

	    int secondBestX = secondBest.getPlayfieldElement().getXPos();

		int secondBestY = secondBest.getPlayfieldElement().getYPos();

		
		
		int pLineCnt = (int) budget/4500;
		
		EnergyNode loc1 = null;
		
		EnergyNode loc2 = null;
		
		List<EnergyNode> pLocs = new LinkedList<>();//List of Plantlocations
		
		List<PowerLine> outgoings1 = new LinkedList<>();
		
		List<PowerLine> outgoings2 = new LinkedList<>();
		
		
		
		for(EnergyNode node: scenario.getGraph().getNodes())
		{			
			if(node.getXPos() == bestX && node.getYPos() == bestY) 
			{
				loc1 = node;
			}
			else if(node.getXPos() == secondBestX && node.getYPos() == secondBestY) 
			{
				loc2 = node;
			}
		}
		
		
		//Fill with plant location1 and loc2
		pLocs.add(loc1);
		
		pLocs.add(loc2);
		//
		if(pLocs.size() < 2) 
		{
			throw new RuntimeException("Fewer than two potential wind power plant locations exist!");
		}
		
		
		
		for(PowerLine edges: scenario.getGraph().getEdges()) 
		{
			if(edges.getStart().equals(pLocs.get(0))) 
			{
				outgoings1.add(edges);				
			}
				
			else if(edges.getStart().equals(pLocs.get(1)))
			{
				outgoings2.add(edges);
			}
		}
		
		
		
		
		}

		

		

		

		@Override
		public String getTeamIdentifier() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void executionPhase(Scenario arg0, int arg1) {
			// TODO Auto-generated method stub
			
		}


}
