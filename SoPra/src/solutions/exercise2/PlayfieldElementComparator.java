package solutions.exercise2;

import org.sopra.api.Scenario;
import org.sopra.api.exercises.ExerciseSubmission;
import org.sopra.api.model.PlayfieldElement;
import org.sopra.api.model.producer.ProducerType;

/**
 * This class acts as a comparator for objects of the "PlayfieldElement" type.
 * 
 * @author Adrian Paquin
 * @version 1.0
 */
public class PlayfieldElementComparator implements java.util.Comparator<PlayfieldElement>, ExerciseSubmission {

	private ProducerType type;
	private Scenario scenario;

	/**
	 * This constructor initializes the comparator with the relevant ProducerType
	 * and Scenario objects.
	 * 
	 * @param type - The ProducerType, the base cost of which is used in addition to
	 *             PlayfieldElement to compare costs.
	 * @param scen - The Scenario which defines the position and connection of
	 *             PlayfieldElements on the playfield.
	 */
	public PlayfieldElementComparator(ProducerType type, Scenario scen) {
		if (type == null || scen == null) {
			throw new NullPointerException("Parameter can not be null.");
		}
		this.type = type;
		this.scenario = scen;
	}

	/**
	 * This method compares the cost of two PlayfieldElements relative to the cost
	 * of the ProducerType.
	 * 
	 * @param e1 - The first PlayfieldElement, the cost of which is compared to the
	 *           second PlayfieldElement.
	 * @param e2 - The second PlayfieldElement, the cost of which is compared to the
	 *           first PlayfieldElement.
	 * @return - Returns a different value for each case: - The cost of the first
	 *         PlayfieldElement is greater than the cost of the second
	 *         PlayfieldElement: returns -1. - The cost of the first
	 *         PlayfieldElement is less than the cost of the second
	 *         PlayfieldElement: returns 1. - The cost of both PlayfieldElements is
	 *         equal: returns 0.
	 */
	@Override
	public int compare(PlayfieldElement e1, PlayfieldElement e2) {
		if (e1 == null || e2 == null) {
			throw new NullPointerException("Parameter can not be null.");
		}
		ConstructionCostCalculatorImpl calc = new ConstructionCostCalculatorImpl(scenario);
		double e1cost = calc.calculateCost(e1, type);
		double e2cost = calc.calculateCost(e2, type);
		if (e1cost > e2cost) {
			return -1;
		} else if (e1cost < e2cost) {
			return 1;
		} else {
			return 0;
		}
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