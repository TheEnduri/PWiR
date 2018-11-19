package Exercise2;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Philosoph implements Runnable {

	private static final Random random = new Random();
	
	public final Table table;
	public String name;
	public Map<CutleryType, Integer> cutleries = new HashMap<>();

	public Philosoph(Table table, String name) {
		this.table = table;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public void run() {

		do {
			think();
			eat();
		} while (table.StillCanEat());
		System.out.println(this.getName() + " zakonczyl ucztowanie i odszedl od stolu");

	}

	public void eat() {

		table.getSpoon(this);
		table.getKnife(this);
		table.changePhilosophStatus(PhilosophStatus.EAT);
		System.out.println(this.getName() + " je schabowego");

		try {
			Thread.sleep(Main.MIN_EATING_TIME + random.nextInt(Main.MAX_EATING_TIME - Main.MIN_EATING_TIME));
			table.addKnife();
			removeKnife();
			table.addSpoon();
			removeSpoon();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public void think() {

		table.changePhilosophStatus(PhilosophStatus.THINK);
		System.out.println(this.getName() + " mysli");
		try {
			Thread.sleep(Main.MIN_THINKING_TIME + random.nextInt(Main.MAX_THINKING_TIME - Main.MIN_THINKING_TIME));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void addKnife() {
		cutleries.put(CutleryType.KNIFE, cutleries.getOrDefault(CutleryType.KNIFE, 0) + 1);
		System.out.println(this.getName() + " otrzymal noz");
	}

	public void addSpoon() {
		cutleries.put(CutleryType.SPOON, cutleries.getOrDefault(CutleryType.SPOON, 0) + 1);
		System.out.println(this.getName() + " otrzymal lyzke");
	}

	public void removeKnife() {
		cutleries.put(CutleryType.KNIFE, cutleries.getOrDefault(CutleryType.KNIFE, 0) - 1);
		System.out.println(this.getName() + " zwrocil noz");
	}

	public void removeSpoon() {
		cutleries.put(CutleryType.SPOON, cutleries.getOrDefault(CutleryType.SPOON, 0) - 1);
		System.out.println(this.getName() + " zwrocil lyzke");
	}

}
