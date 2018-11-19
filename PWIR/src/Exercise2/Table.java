package Exercise2;

import java.util.HashMap;
import java.util.Map;

public class Table {
	private final Map<CutleryType, Integer> cutleries;
	private int numberMeals = 0;

	public Table() {
		cutleries = new HashMap<>(Main.CUTLERY);
	}

	public synchronized boolean StillCanEat() {
		if (numberMeals <= Main.NUMBER_OF_MEALS) {
			notify();
			return true;
		}
		notify();
		return false;

	}

	public synchronized void changePhilosophStatus(PhilosophStatus status, int id) {
		switch (status) {
		case EAT:
			numberMeals++;
			notify();
			break;
		case THINK:
			notify();
			break;

		default:
			break;
		}
	}

	public synchronized void getSpoon(Philosoph philosoph) {
		Integer number;
		while ((number = cutleries.getOrDefault(CutleryType.SPOON, 0)) <= 0) {
			try {
				notify();
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		cutleries.put(CutleryType.SPOON, number - 1);
		philosoph.addSpoon();
		notify();
	}

	public synchronized void getKnife(Philosoph philosoph) {
		Integer number;
		while ((number = cutleries.getOrDefault(CutleryType.KNIFE, 0)) <= 0) {		
			try {
				notify();
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		cutleries.put(CutleryType.KNIFE, number - 1);
		philosoph.addKnife();
		notify();
	}

	public synchronized void addSpoon() {
		cutleries.put(CutleryType.SPOON, cutleries.getOrDefault(CutleryType.SPOON, 0) + 1);
		notify();
	}

	public synchronized void addKnife() {
		cutleries.put(CutleryType.KNIFE, cutleries.getOrDefault(CutleryType.KNIFE, 0) + 1);
		notify();
	}

}
