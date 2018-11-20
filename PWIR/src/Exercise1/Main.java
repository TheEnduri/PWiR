package Exercise1;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {

	public static void main(String[] args) {

		BlockingQueue<String> redQueue = new LinkedBlockingQueue<>();
		BlockingQueue<String> blueQueue = new LinkedBlockingQueue<>();

		Container container = new Container(redQueue, blueQueue);

		Thread producent1 = new Thread(new Producer(container, "niebieski"));
		Thread producent2 = new Thread(new Producer(container, "czerwony"));

		Thread konsument1 = new Thread(new Consumer(container), "konsument1");
		Thread konsument2 = new Thread(new Consumer(container), "konsument2");
		Thread konsument3 = new Thread(new Consumer(container), "konsument3");

		producent1.start();
		producent2.start();
		konsument1.start();
		konsument2.start();
		konsument3.start();
	}
}
