package DiningPhilosophers;

public class Philosopher implements Runnable {

	private final Fork leftFork;
	private final Knife rightKnife;

	Philosopher(Fork left, Knife right) {
		this.leftFork = left;
		this.rightKnife = right;
	}

	private void wykonajZadanie(String zadanie) throws InterruptedException {
		System.out.println(Thread.currentThread().getName() + " " + zadanie);
		Thread.sleep(((int) (Math.random() * 10000)));
	}

	@Override
	public void run() {
		try {
			while (true) {
				wykonajZadanie(" myœli");
				synchronized (leftFork) {
					wykonajZadanie(": wzia³ widelec po lewej stronie");
					synchronized (rightKnife) {
						wykonajZadanie(" wzi¹³ nó¿ po prawej stronie i zacz¹³ jeœæ schabowego");
						wykonajZadanie(" od³o¿y³ nó¿");
					}
					wykonajZadanie("od³o¿y³ widelec i zacz¹³ znów rozmyœlaæ");
				}
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
}