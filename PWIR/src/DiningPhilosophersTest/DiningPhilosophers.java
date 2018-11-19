package DiningPhilosophersTest;

public class DiningPhilosophers {

	public static void main(String[] args) throws Exception {
		int amountOfCutlery = 5;

		Philosopher[] philosophers = new Philosopher[5];
		Fork[] forks = new Fork[amountOfCutlery];
		Knife[] knives = new Knife[amountOfCutlery];

		for (int i = 0; i < amountOfCutlery; i++) {
			forks[i] = new Fork();
			knives[i] = new Knife();
		}

		for (int i = 0; i < philosophers.length; i++) {
			
			Fork leftFork = forks[i];
			Knife rightKnife = knives[i];

			philosophers[i] = new Philosopher(leftFork, rightKnife);
			Thread t = new Thread(philosophers[i], "Filozof " + (i + 1));
			t.start();
		}
	}
}