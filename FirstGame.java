package solutions.exercise5;

import org.sopra.api.exercises.exercise2.*;
import org.sopra.api.Game;
import org.sopra.api.Scenario;
import org.sopra.api.exercises.ExerciseSubmission;
import org.sopra.api.model.PlantLocation;
import org.sopra.api.model.PlayfieldElement;
import org.sopra.api.model.producer.ProducerType;

import solutions.exercise2.ConstructionCostCalculatorImpl;

public class FirstGame implements Game, ExerciseSubmission {

	@Override
	public void buildPhase(Scenario scenario) {
	if(scenario.getPlantLocations().size() <3) 
	{
		throw new RuntimeException("Es müssen mindestens zwei Windkrafzwerke im scenario enthalten sein!");
	}
	
	double costtemp = 0;
	double costlow = 0;
	for(int i=0; i < scenario.getPlantLocations().size(); i++) 
	{
		costtemp = calculateCost(scenario.getPlantLocations().get(i).getPlayfieldElement(),ProducerType.WIND_POWER_PLANT);
		if(costtemp < costlow) 
		{
			costtemp = costlow;
		}
	}
	
	}

	private double calculateCost(PlayfieldElement playfieldElement, ProducerType windPowerPlant) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void executionPhase(Scenario arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getTeamIdentifier() {
	return "G06T02";
	}

}
