package Exercise1;

public class Consumer implements Runnable {							// implementujemy Runnable, co uruchamia nam watek na kazdego konsumenta
																	// utworzonego
	private Container container;

	public Consumer(Container container) {							// tworzymy konstruktor przypisujacy kontener
		this.container = container;
	}

	@Override
	public void run() {												// wywolujemy nadpisana metode, ktora pobiera wartosc i jezeli ja pobierze to
																	// usypia watek
		while (true) {
			
			try {
				container.getValue();
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
	}
}
