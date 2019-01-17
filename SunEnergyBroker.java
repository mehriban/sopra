package solutions.exercise5;

import org.sopra.api.Scenario;
import org.sopra.api.exercises.ExerciseSubmission;
import org.sopra.api.exercises.exercise5.AbstractSunEnergyBroker;
import org.sopra.api.model.EnergyNode;
import org.sopra.api.model.consumer.Consumer;
import org.sopra.api.model.consumer.ConsumerType;
import org.sopra.api.model.producer.Producer;
import org.sopra.api.model.producer.ProducerType;

public class SunEnergyBroker extends AbstractSunEnergyBroker implements ExerciseSubmission {

	@Override
	public void executionPhase(Scenario scenario, int round) {
		
		boolean valid1 = false; // is true if only Indus. and SolarPP
		
		boolean valid2 = false; // is true if amount of producers and consumers appropriate
		
		boolean valid3 = false; // is true if the PowerLine-Capacity is enough
		
		boolean valid = false; // is true if the scenario meets all requirements
				
		int count = 0;
		
		for(EnergyNode node: scenario.getGraph().getNodes())  //iteriere über alle Knoten des Graphen
		{
			if( node instanceof Producer && ((Producer) node).getType().equals(ProducerType.SOLAR_POWER_PLANT)
				
					|| node instanceof Consumer && ((Consumer) node).getType().equals(ConsumerType.INDUSTRIAL_PARK)
					
					|| node.getEnergyLevel() ==0) 
			{
				count = count +1;
			}
			
		} //If the EnergyNode contains SolarPP or Inuds.Park count++
		
		//If total number of EnergyNodes equals count number 
		if(count == scenario.getGraph().getNodes().size()) 
		{
			valid1 = true;
		}
		else 
		{
			throw new RuntimeException("Scenario is invalid");
		}
		
		
		if(valid1 && valid2 && valid3) 
		{
			valid = true; //scenario is accepted
		}
	
		
	}


	@Override
	public String getTeamIdentifier() {
		return "G06T02";
	}

}
