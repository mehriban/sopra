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
	public void executionPhase(Scenario scenario, int power) {
		boolean valid = false;
		for(EnergyNode node: scenario.getGraph().getNodes())  //iteriere über alle Knoten des Graphen
		{
			if(node instanceof Producer && ((Producer) node).getType().equals(ProducerType.SOLAR_POWER_PLANT)
				|| node instanceof Consumer && ((Consumer) node).getType().equals(ConsumerType.INDUSTRIAL_PARK)) 
				//Wenn nur Solar und Industr. vorhanden (es fehlt TransformerStation)
			{
				valid = true;//übergebenes Spielfeld wird akzeptiert
			}
		}
		
		if(valid) //Wenn Spielfeld akzeptiert wird
		{
			
		}
		
	}

	@Override
	public String getTeamIdentifier() {
		return "G06T02";
	}

}
