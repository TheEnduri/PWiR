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

	public synchronized void changePhilosophStatus(PhilosophStatus status) {
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

	public synchronized void getFork(Philosoph philosoph) {
		Integer amountForks;
		while ((amountForks = cutleries.getOrDefault(CutleryType.FORK, 0)) <= 0) {
			try {
				wait();
				notify();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		cutleries.put(CutleryType.FORK, amountForks - 1);
		philosoph.addFork();
		notify();
	}

	public synchronized void getKnife(Philosoph philosoph) {
		Integer amountKnives;
		while ((amountKnives = cutleries.getOrDefault(CutleryType.KNIFE, 0)) <= 0) {
			try {
				wait();
				notify();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		cutleries.put(CutleryType.KNIFE, amountKnives - 1);
		philosoph.addKnife();
		notify();
	}

	public synchronized void addFork() {
		cutleries.put(CutleryType.FORK, cutleries.getOrDefault(CutleryType.FORK, 0) + 1);
		notify();
	}

	public synchronized void addKnife() {
		cutleries.put(CutleryType.KNIFE, cutleries.getOrDefault(CutleryType.KNIFE, 0) + 1);
		notify();
	}

}
