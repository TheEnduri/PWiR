package DiningPhilosophersProblem;

public class Philosopher implements Runnable {

	private final Object leftFork;
	private final Object rightFork;

	Philosopher(Object left, Object right) {
		this.leftFork = left;
		this.rightFork = right;
	}

	private void doThing(String thing) throws InterruptedException {
		System.out.println(Thread.currentThread().getName() + " " + thing);
		Thread.sleep(((int) (Math.random() * 3000)));
	}

	@Override
	public void run() {
		try {
			while (true) {
				doThing(": Thinking"); // thinking
				synchronized (leftFork) {
					doThing(": Picked up left fork");
					synchronized (rightFork) {
						doThing(": Picked up right fork - eating"); // eating
						doThing(": Put down right fork");
					}
					doThing(": Put down left fork. Returning to thinking");
				}
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
}