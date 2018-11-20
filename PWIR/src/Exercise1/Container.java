package Exercise1;

import java.util.concurrent.BlockingQueue;

public class Container {																				
																										// deklarujemy zmienne typu queue przechowujace czerwonie i niebieksie produkty
	private BlockingQueue<String> produktyCzerwone;
	private BlockingQueue<String> produktyNiebieskie;

	private final int MAX_ILOSC = 4;																	// deklarujemy stala, ktora okresla max ilosc produktow
	
	public Container(BlockingQueue<String> produktyCzerwone, BlockingQueue<String> produktyNiebieskie) {// konstruktor
		this.produktyCzerwone = produktyCzerwone;
		this.produktyNiebieskie = produktyNiebieskie;
	}

	public void createProduct(String name) throws InterruptedException {										// metoda tworzaca produkty i ladujaca do buforu
																										
		if (name == "czerwony") {																		// jezeli nazwa producenta to czerwony to watki tych producentow maja dostep do
			synchronized (produktyCzerwone) {															// kolejki produktyCzerwone
																										
				while (produktyCzerwone.size() == MAX_ILOSC) {											// jezeli ilosc produktow czerwonych wynosi MAX_ILOSC to watek czeka az zostanie
					System.out.println("Czerwony producent: zbyt duzo produktow");						// pobrany produkt
					produktyCzerwone.wait();
				}
			} 																							
																										
			synchronized (produktyCzerwone) {															// dodaje produkt czerwony do kolejki po czym wysyla informacje, ze jest
				System.out.println("Czerwony producent: dodaje nowy produkt: " + name);					// mozliwosc pobrania produktu
				produktyCzerwone.add(name);
				Thread.sleep((long) (Math.random() * 1000));
				produktyCzerwone.notify();
			}
		} 																								
																										
		if (name == "niebieski") {																		// jezeli nazwa producenta to niebieski to watki tych producentow maja dostep do
			synchronized (produktyNiebieskie) {															// kolejki produktyNiebieskie
																																																
				while (produktyNiebieskie.size() == MAX_ILOSC) {										// jezeli ilosc produktow niebieskich wynosi MAX_ILOSC to watek czeka az
					System.out.println("Niebieski producent: zbyt duzo produktow");						// zostanie pobrany produkt
					produktyNiebieskie.wait();
				}
			}																							
																										
			synchronized (produktyNiebieskie) {															// dodaje produkt niebieski do kolejki po czym wysyla informacje, ze jest
				System.out.println("Niebieski producent: dodaje nowy produkt " + name);					// mozliwosc pobrania produktu
				produktyNiebieskie.add(name);
				Thread.sleep((long) (Math.random() * 1000));
				produktyNiebieskie.notify();
			}

		}

	}

	
	public void getProduct() throws InterruptedException {												// metoda pobierajaca produkty

		synchronized (produktyCzerwone) {
			
			while (produktyCzerwone.isEmpty()) {														// metoda czeka az dostepny jest produkt czerwony, po czym czeka czy dostepny
				System.out.println(																		// jest produkt niebieski, jezeli dostepne sa oba produkty to zostaja one pobrane
						Thread.currentThread().getName() + ": Brak wartosci czerwonej... Oczekiwanie na dodanie");
				produktyCzerwone.wait();
			}
			
			synchronized (produktyNiebieskie) {
				
				while (produktyNiebieskie.isEmpty()) {
					System.out.println(
							Thread.currentThread().getName() + ": Brak wartosci niebieskiej... Oczekiwanie na dodanie");
					produktyNiebieskie.wait();
				}

				Thread.sleep((long) Math.random() * 2000);
				System.out.println(Thread.currentThread().getName() + ": Pobranie wartosci: niebieski ["
						+ produktyNiebieskie.poll() + "], czerwony [" + produktyCzerwone.poll() + "]");
				produktyNiebieskie.notify();
				
			}
			produktyCzerwone.notify();
		}

	}

}
