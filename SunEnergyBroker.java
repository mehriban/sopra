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
		
		boolean valid = false;
		
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
			valid = true;
		}
		else 
		{
			throw new RuntimeException("Scenario is invalid");
		}
		
		
		// If Playfield is accepted 
		if(valid) 
		{
			
		}
		
	}

	@Override
	public String getTeamIdentifier() {
		return "G06T02";
	}

}
