package solutions.exercise6;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.sopra.api.Game;
import org.sopra.api.Scenario;
import org.sopra.api.exercises.ExerciseSubmission;
import org.sopra.api.exercises.exercise1.ScenarioUtil;
import org.sopra.api.model.EnergyNode;
import org.sopra.api.model.PlantLocation;
import org.sopra.api.model.PlayfieldElement;
import org.sopra.api.model.PlayfieldElement.ElementType;
import org.sopra.api.model.PowerLine;
import org.sopra.api.model.PowerLineType;
import org.sopra.api.model.consumer.ControllableConsumer;
import org.sopra.api.model.consumer.IndustrialPark;
import org.sopra.api.model.producer.BioGasFiredPowerPlant;
import org.sopra.api.model.producer.CoalFiredPowerPlant;
import org.sopra.api.model.producer.ControllableProducer;
import org.sopra.api.model.producer.GasFiredPowerPlant;
import org.sopra.api.model.producer.NuclearPowerPlant;
import org.sopra.api.model.producer.ProducerType;
import org.sopra.internal.game.command.CannotAssignCommandException;
import org.sopra.internal.game.command.CannotExecuteCommandException;
import org.sopra.internal.model.consumer.IndustrialParkImpl;
import org.sopra.internal.model.producer.BioGasFiredPowerPlantImpl;
import org.sopra.internal.model.producer.CoalFiredPowerPlantImpl;
import org.sopra.internal.model.producer.HydroPowerPlantImpl;
import org.sopra.internal.model.producer.NuclearPowerPlantImpl;
import org.sopra.internal.model.producer.WindPowerPlantImpl;

import solutions.exercise1.ScenarioUtilImpl;

/**
 * This class implements the class Game. It applies a simple strategy for 
 * building and controlling power plants as well as building the power lines.
 *
 */
public class ScenarioImpl implements Game, ExerciseSubmission {

	/**
	 * This phase is called for each round of the execution phase. 
	 * In each round, the non-controllable energy nodes change their 
	 * input or output of energy. This can be a random change or one 
	 * that depends on specific factors (e.g.: solar plants which 
	 * produce specific energy dependent on day time). During each 
	 * round several commands can be executed to regulate controllable
	 * energy nodes.
	 * 
	 * @param scenario the scenario of the game
	 * @param round round as number
	 */
	@Override
	public void executionPhase(Scenario scenario, int round) {
		controlIndustrialParks(scenario, round);
		controlNuclearPlants(scenario, round);
		controlGasFiredPlants(scenario, round);
		controlBioGasPlants(scenario, round);
		controlCoalFiredPlants(scenario, round);
	}
	
	/**
	 * This phase is called before the scenario actually starts. (In other words in round 0.) 
	 * During this phase, power lines can be upgraded and power plants can be built on transformer
	 * station locations.
	 * 
	 * @param scenario the scenario of the game
	 */
	@Override
	public void buildPhase(Scenario scenario) {
		buildPlants(scenario);
		buildPowerLines(scenario);
	}

	private void controlCoalFiredPlants(Scenario scenario, int round) {
		List<CoalFiredPowerPlant> coalFiredPlants = scenario.getGraph().getNodes().stream()
				.filter(n -> n instanceof CoalFiredPowerPlant).map(n -> (CoalFiredPowerPlant) n)
				.collect(Collectors.toList());

		if (round == 0) {
			for (CoalFiredPowerPlant p : coalFiredPlants) {
				Double amount = p.getMaximumEnergyLevel() * 0.1;

				try {
					scenario.getCommandFactory().createAdjustProducerCommand(p, amount.intValue()).assign();
				} catch (CannotAssignCommandException e) {
					throw new RuntimeException("Error in round " + round + ".", e);
				}
			}
		} else {
			int currentTime = round % 24;
			if (currentTime == 7) {
				for (CoalFiredPowerPlant p : coalFiredPlants) {
					Double amount = p.getMaximumEnergyLevel() * 0.3;

					try {
						scenario.getCommandFactory().createAdjustProducerCommand(p, amount.intValue()).assign();
					} catch (CannotAssignCommandException e) {
						throw new RuntimeException("Error in round " + round + ".", e);
					}
				}
			} else if (currentTime == 21) {
				for (CoalFiredPowerPlant p : coalFiredPlants) {
					Double amount = p.getMaximumEnergyLevel() * 0.3;

					try {
						scenario.getCommandFactory().createAdjustProducerCommand(p, -amount.intValue()).assign();
					} catch (CannotAssignCommandException e) {
						throw new RuntimeException("Error in round " + round + ".", e);
					}
				}
			}
		}

	}

	private void controlBioGasPlants(Scenario scenario, int round) {
		List<BioGasFiredPowerPlant> bioPlants = scenario.getGraph().getNodes().stream()
				.filter(n -> n instanceof BioGasFiredPowerPlant).map(n -> (BioGasFiredPowerPlant) n)
				.collect(Collectors.toList());
		if (round == 0) {
			for (BioGasFiredPowerPlant p : bioPlants) {
				Double amount = p.getMaximumEnergyLevel() * 0.1;

				try {
					scenario.getCommandFactory().createAdjustProducerCommand(p, amount.intValue()).assign();
				} catch (CannotAssignCommandException e) {
					throw new RuntimeException("Error in round " + round + ".", e);
				}
			}
		} else {
			int currentTime = round % 24;
			if (currentTime == 6) {
				for (BioGasFiredPowerPlant p : bioPlants) {
					Double amount = p.getMaximumEnergyLevel() * 0.8;

					try {
						scenario.getCommandFactory().createAdjustProducerCommand(p, amount.intValue()).assign();
					} catch (CannotAssignCommandException e) {
						throw new RuntimeException("Error in round " + round + ".", e);
					}
				}
			} else if (currentTime == 20) {
				for (BioGasFiredPowerPlant p : bioPlants) {
					Double amount = p.getMaximumEnergyLevel() * 0.8;

					try {
						scenario.getCommandFactory().createAdjustProducerCommand(p, -amount.intValue()).assign();
					} catch (CannotAssignCommandException e) {
						throw new RuntimeException("Error in round " + round + ".", e);
					}
				}
			}
		}
	}

	private void controlGasFiredPlants(Scenario scenario, int round) {
		List<GasFiredPowerPlant> gasPlants = scenario.getGraph().getNodes().stream()
				.filter(n -> n instanceof GasFiredPowerPlant).map(n -> (GasFiredPowerPlant) n)
				.collect(Collectors.toList());

		if (round == 0) {
			for (GasFiredPowerPlant p : gasPlants) {
				Double amount = p.getMaximumEnergyLevel() * 0.1;

				try {
					scenario.getCommandFactory().createAdjustProducerCommand(p, amount.intValue()).assign();
				} catch (CannotAssignCommandException e) {
					throw new RuntimeException("Error in round " + round + ".", e);
				}
			}
		} else {
			int currentTime = round % 24;
			if (currentTime == 6) {
				for (GasFiredPowerPlant p : gasPlants) {
					Double amount = p.getMaximumEnergyLevel() * 0.4;

					try {
						scenario.getCommandFactory().createAdjustProducerCommand(p, amount.intValue()).assign();
					} catch (CannotAssignCommandException e) {
						throw new RuntimeException("Error in round " + round + ".", e);
					}
				}
			} else if (currentTime == 20) {
				for (GasFiredPowerPlant p : gasPlants) {
					Double amount = p.getMaximumEnergyLevel() * 0.4;
					try {
						scenario.getCommandFactory().createAdjustProducerCommand(p, -amount.intValue()).assign();
					} catch (CannotAssignCommandException e) {
						throw new RuntimeException("Error in round " + round + ".", e);
					}
				}
			}
		}
	}

	private void controlIndustrialParks(Scenario scenario, int round) {
		List<IndustrialPark> parks = scenario.getGraph().getNodes().stream().filter(n -> n instanceof IndustrialPark)
				.map(n -> (IndustrialPark) n).collect(Collectors.toList());

		if (round == 0) {
			for (IndustrialPark p : parks) {
				int amount = 800;
				try {
					scenario.getCommandFactory().createAdjustConsumerCommand(p, -amount).assign();
				} catch (CannotAssignCommandException e) {
					throw new RuntimeException("Error in round " + round + ".", e);
				}
			}

		}

		else {
			for (IndustrialPark p : parks) {
				int amount = 600;
				try {
					int currentTime = round % 24;
					if (currentTime == 19) {
						scenario.getCommandFactory().createAdjustConsumerCommand(p, -amount).assign();
					}
					if (currentTime == 6) {
						scenario.getCommandFactory().createAdjustConsumerCommand(p, amount).assign();
					}
				 } catch (CannotAssignCommandException e) {
					 throw new RuntimeException("Error in round " + round + ".",e);
				 }
			}
		}
	}

	private void controlNuclearPlants(Scenario scenario, int round) {
		List<NuclearPowerPlant> nuclearPlants = scenario.getGraph().getNodes().stream()
				.filter(n -> n instanceof NuclearPowerPlant).map(n -> (NuclearPowerPlant) n)
				.collect(Collectors.toList());

		for (NuclearPowerPlant p : nuclearPlants) {
			int startAmount = 650;
			int controlAmount = 400;
			try {
				int currentTime = round % 24;

				if (round == 0) {
					scenario.getCommandFactory().createAdjustProducerCommand(p, startAmount).assign();
				}
				if (currentTime == 11) {
					scenario.getCommandFactory().createAdjustProducerCommand(p, -controlAmount).assign();
				}
				if (currentTime == 21) {
					scenario.getCommandFactory().createAdjustProducerCommand(p, controlAmount).assign();
				}

			} catch (CannotAssignCommandException e) {
				throw new RuntimeException("Error in round " + round + ".", e);
			}
		}
	}

	private void buildPlants(Scenario scenario) {
		ScenarioUtil scenarioUtil = new ScenarioUtilImpl();
		List<PlayfieldElement> transformerStations = new ArrayList<PlayfieldElement>();

		for (PlantLocation loc : scenario.getPlantLocations()) {
			if (loc.getTransformerStation().isPresent() && !loc.isBuilt()) {
				transformerStations.add(loc.getPlayfieldElement());
			}

		}

		if (transformerStations.size() > 0) {

			int totalRequiredEnergy = 0;
			List<ControllableConsumer> consumers = scenarioUtil.getControllableConsumers(scenario.getGraph());
			for (ControllableConsumer consumer : consumers) {
				totalRequiredEnergy += consumer.getRequiredPower();
			}

			int totalProvidedEnergy = 0;
			List<ControllableProducer> producers = scenarioUtil.getControllableProducers(scenario.getGraph());
			for (ControllableProducer producer : producers) {
				totalProvidedEnergy += producer.getProvidedPower();
			}

			if (totalProvidedEnergy == 0 || totalProvidedEnergy / totalRequiredEnergy < 1) {
				for (PlayfieldElement transformerStation : transformerStations) {
					PlantLocation target = getPlantLocation(scenario.getPlantLocations(), transformerStation);
					int xPos = transformerStation.getXPos();
					int yPos = transformerStation.getYPos();
					if (xPos == 4 && yPos == 2) {
						buildProducerPlant(target, scenario, ProducerType.NUCLEAR_POWER_PLANT);
					}

					else if (xPos == 1 && yPos == 1) {

					}

					else if (xPos == 0 && yPos == 3) {

					}

					else if (xPos == 6 && yPos == 3) {

					}

					else if (xPos == 6 && yPos == 2) {
						buildProducerPlant(target, scenario, ProducerType.WIND_POWER_PLANT);
					}

					else if (xPos == 2 && yPos == 4) {
						buildProducerPlant(target, scenario, ProducerType.HYDRO_POWER_PLANT);
					}
					else if (transformerStation.getElementType() == ElementType.SEA) {
						buildProducerPlant(target, scenario, ProducerType.WIND_POWER_PLANT);
					}
					else if (transformerStation.getElementType() == ElementType.MOUNTAIN) {
						buildProducerPlant(target, scenario, ProducerType.SOLAR_POWER_PLANT);
					}

					else if (transformerStation.getElementType() == ElementType.RIVER) {
						buildProducerPlant(target, scenario, ProducerType.HYDRO_POWER_PLANT);
					}

					else if (transformerStation.getElementType() == ElementType.GRASSLAND) {
						buildProducerPlant(target, scenario, ProducerType.COALFIRED_POWER_PLANT);
					}

					else if (transformerStation.getElementType() == ElementType.BEACH) {
						buildProducerPlant(target, scenario, ProducerType.WIND_POWER_PLANT);
					}
				}
			}
		}
	}
	
	private void buildProducerPlant(PlantLocation target, Scenario scenario, ProducerType producerType) {
		try {
			scenario.getCommandFactory().createBuildPlantCommand(target, producerType)
					.execute();
		} catch (CannotExecuteCommandException e) {
			throw new RuntimeException("Error during building producer plants.\n", e);
		}
	}

	private void buildPowerLines(Scenario scenario) {
		List<PowerLine> lines = scenario.getGraph().getEdges();
		
		for (PowerLine line : lines) {
			EnergyNode start = line.getStart();
			EnergyNode end = line.getEnd();

			if (line.getType() == PowerLineType.LOW_VOLTAGE) {
				try {
					if ((start.getName() == "Stadt0" || end.getName() == "Stadt0")
							&& (start instanceof HydroPowerPlantImpl
									|| end instanceof HydroPowerPlantImpl)) {

					} else if ((start.getXPos() == 6 || end.getXPos() == 6)
							&& (start.getYPos() == 3 || end.getYPos() == 3)) {

					} 

					else if ((start.getXPos() == 1 || start.getYPos() == 1)
							&& (end.getXPos() == 1 || end.getYPos() == 1)) {

					} else if (start instanceof HydroPowerPlantImpl
							|| end instanceof HydroPowerPlantImpl) {
						scenario.getCommandFactory().createUpgradeLineCommand(line, PowerLineType.MEDIUM_VOLTAGE)
								.execute();
					} else if (start instanceof NuclearPowerPlantImpl
							|| end instanceof NuclearPowerPlantImpl) {
						scenario.getCommandFactory().createUpgradeLineCommand(line, PowerLineType.MEDIUM_VOLTAGE)
								.execute();
					} else if (start instanceof WindPowerPlantImpl
							|| end instanceof WindPowerPlantImpl) {
						scenario.getCommandFactory().createUpgradeLineCommand(line, PowerLineType.MEDIUM_VOLTAGE)
								.execute();
					} else if (start instanceof CoalFiredPowerPlantImpl
							|| end instanceof CoalFiredPowerPlantImpl) {
						scenario.getCommandFactory().createUpgradeLineCommand(line, PowerLineType.MEDIUM_VOLTAGE)
								.execute();
					} else if (start instanceof BioGasFiredPowerPlantImpl
							|| end instanceof BioGasFiredPowerPlantImpl) {
						scenario.getCommandFactory().createUpgradeLineCommand(line, PowerLineType.MEDIUM_VOLTAGE)
								.execute();
					} else if (start instanceof IndustrialParkImpl
							|| end instanceof IndustrialParkImpl) {
						scenario.getCommandFactory().createUpgradeLineCommand(line, PowerLineType.MEDIUM_VOLTAGE)
								.execute();
					}
				} catch (CannotExecuteCommandException e) {
					throw new RuntimeException("Error during upgrading power lines.\n", e);
				}
			}
		}
	}

	private PlantLocation getPlantLocation(List<PlantLocation> locs, PlayfieldElement element) {
		for (PlantLocation loc : locs) {
			if (loc.getPlayfieldElement().equals(element) && loc.getTransformerStation().isPresent()) {
				if (!loc.isBuilt()) {
					return loc;
				}

			}
		}
		return null;
	}

	@Override
	public String getTeamIdentifier() {
		return "G06T02";
	}
}
