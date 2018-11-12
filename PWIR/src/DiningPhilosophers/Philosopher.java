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
				wykonajZadanie(" my�li");
				synchronized (leftFork) {
					wykonajZadanie(": wzia� widelec po lewej stronie");
					synchronized (rightKnife) {
						wykonajZadanie(" wzi�� n� po prawej stronie i zacz�� je�� schabowego");
						wykonajZadanie(" od�o�y� n�");
					}
					wykonajZadanie("od�o�y� widelec i zacz�� zn�w rozmy�la�");
				}
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
}