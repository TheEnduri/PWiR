package Exercise2;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Main {

	public static final int MIN_THINKING_TIME = 1000;
	public static final int MAX_THINKING_TIME = 3000;

	public static final int MIN_EATING_TIME = 1000;
	public static final int MAX_EATING_TIME = 3000;

	public static final int NUMBER_OF_MEALS = 7;

	public static final Map<CutleryType, Integer> CUTLERY;
	static {
		Map<CutleryType, Integer> cutleryMap = new HashMap<CutleryType, Integer>();
		cutleryMap.put(CutleryType.KNIFE, 1);
		cutleryMap.put(CutleryType.SPOON, 4);
		CUTLERY = Collections.unmodifiableMap(cutleryMap);
	}

	public static void main(String[] args) {

		Table table = new Table();
		Thread filozof1 = new Thread(new Philosoph(table, "Arystoteles"));
		Thread filozof2 = new Thread(new Philosoph(table, "Sokrates"));
		Thread filozof3 = new Thread(new Philosoph(table, "Platon"));
		Thread filozof4 = new Thread(new Philosoph(table, "Demokryt"));
		Thread filozof5 = new Thread(new Philosoph(table, "Heraklit"));

		filozof1.start();
		filozof2.start();
		filozof3.start();
		filozof4.start();
		filozof5.start();

	}

}
