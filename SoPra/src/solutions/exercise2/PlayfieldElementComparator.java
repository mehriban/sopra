package solutions.exercise2;

import org.sopra.api.Scenario;
import org.sopra.api.model.PlayfieldElement;
import org.sopra.api.model.producer.ProducerType;

public class PlayfieldElementComparator implements java.util.Comparator<PlayfieldElement> {

	private ProducerType type;
	private Scenario scen;

	public PlayfieldElementComparator(ProducerType type, Scenario scen) {
		if (type == null || scen == null) {
			throw new NullPointerException("Parameter can not be null.");
		}
		this.type = type;
		this.scen = scen;
	}
	
	@Override
	public int compare(PlayfieldElement o1, PlayfieldElement o2) {
		// TODO Auto-generated method stub
		return 0;
	}

}
