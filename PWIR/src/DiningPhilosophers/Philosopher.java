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
				wykonajZadanie(" mysli");
				synchronized (leftFork) {
					wykonajZadanie(": wzial widelec po lewej stronie");
					synchronized (rightKnife) {
						wykonajZadanie(" wzial noz po prawej stronie i zaczal jesc schabowego");
						wykonajZadanie(" odlozyl noz");
					}
					wykonajZadanie("odlozyl widelec i zaczal znow rozmyslac	");
				}
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
}