package Exercise1;

public class Producer implements Runnable {					//implementujemy Runnable, co uruchamia nam watek na kazdego producenta utworzonego
    
    private Container container;
    private String name;
   
    public Producer(Container container, String name) {		//tworzymy konstrkutor, ktory za argumenty przyjmuje kontener w ktorym ma pracowac oraz swoja nazwe
        this.container = container;
        this.name = name;
    }

    
    @Override
    public void run() {										//implementujemy metode run, ktora dziala w nieskonczonosc i wprowadza produkt do kolejki

        while(true){
            try {
                container.createProduct(name);
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
    }
}
