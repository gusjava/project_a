package a.entity.gus.x.test.javaparsing.test2;

import a.framework.Entity;
import java.util.*;
import java.util.function.*;
import java.io.*;

/**
 * Entité de test — syntaxe Java exhaustive.
 * Contient un exemple simple de chaque construction syntaxique Java.
 */
public class EntityImpl implements Entity {

	// =========================================================
	// 1. CONSTANTES & CHAMPS
	// =========================================================

	/** Constante de classe */
	public static final String CONSTANT = "value";
	private static final int MAX = 100;

	/** Champs primitifs */
	private int count;
	private long bigCount;
	private double ratio;
	private boolean active;
	private char initial;
	private byte flags;
	private short index;
	private float score;

	/** Champs objet */
	private String name;
	private Object raw;

	/** Champs génériques */
	private List<String> items;
	private Map<String, Integer> map;
	private Set<Long> ids;

	/** Champ volatile */
	private volatile boolean running;

	// =========================================================
	// 2. ENUM
	// =========================================================

	public enum Status {
		ACTIVE, INACTIVE, PENDING;

		/** Méthode dans enum */
		public boolean isActive() { return this == ACTIVE; }
	}

	// =========================================================
	// 3. INTERFACE INTERNE (avec default method)
	// =========================================================

	public interface Processor<T> {
		T process(T input);
		default T identity(T input) { return input; }
	}

	// =========================================================
	// 4. CLASSE INTERNE STATIQUE
	// =========================================================

	public static class Config {
		private final String key;
		private final Object value;

		public Config(String key, Object value) {
			this.key = key;
			this.value = value;
		}

		public String getKey() { return key; }
		public Object getValue() { return value; }

		@Override
		public String toString() { return key + "=" + value; }
	}

	// =========================================================
	// 5. CLASSE INTERNE NON-STATIQUE
	// =========================================================

	public class Inner {
		public String describe() { return "inner of " + name; }
	}

	// =========================================================
	// 6. CONSTRUCTEURS
	// =========================================================

	/** Constructeur par défaut — délègue */
	public EntityImpl() {
		this("default", 0);
	}

	/** Constructeur principal */
	public EntityImpl(String name, int count) {
		this.name = name;
		this.count = count;
		this.active = true;
		this.items = new ArrayList<>();
		this.map = new HashMap<>();
		this.ids = new HashSet<>();
	}

	// =========================================================
	// 7. INTERFACE Entity (obligatoire)
	// =========================================================

	@Override
	public String creationDate() { return "20260419"; }

	// =========================================================
	// 8. ANNOTATIONS
	// =========================================================

	@SuppressWarnings("unchecked")
	public void annotatedMethod() {}

	@Deprecated
	public void oldMethod() {}

	// =========================================================
	// 9. GENERICS
	// =========================================================

	/** Méthode générique bornée */
	public <T extends Comparable<T>> T max(T a, T b) {
		return a.compareTo(b) >= 0 ? a : b;
	}

	/** Wildcard */
	public double sumList(List<? extends Number> list) {
		double total = 0;
		for (Number n : list) total += n.doubleValue();
		return total;
	}

	// =========================================================
	// 10. VARARGS
	// =========================================================

	public int sum(int... values) {
		int total = 0;
		for (int v : values) total += v;
		return total;
	}

	public String join(String sep, String... parts) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < parts.length; i++) {
			if (i > 0) sb.append(sep);
			sb.append(parts[i]);
		}
		return sb.toString();
	}

	// =========================================================
	// 11. CONTROL FLOW
	// =========================================================

	/** if / else if / else */
	public String sign(int x) {
		if (x < 0) {
			return "negative";
		} else if (x == 0) {
			return "zero";
		} else {
			return "positive";
		}
	}

	/** switch / case / default */
	public String describe(Status s) {
		switch (s) {
			case ACTIVE:   return "active";
			case INACTIVE: return "inactive";
			default:       return "other";
		}
	}

	/** Boucles : for, for-each, while, do-while, break, continue */
	public void loops() {
		// for classique
		for (int i = 0; i < MAX; i++) {
			if (i == 5) continue;
			if (i == 10) break;
		}
		// for-each
		for (String item : items) {
			System.out.println(item);
		}
		// while
		int n = 10;
		while (n > 0) n--;
		// do-while
		do { n++; } while (n < 3);
	}

	// =========================================================
	// 12. EXCEPTIONS
	// =========================================================

	/** try / catch (multi) / finally */
	public String safeLookup(String key) {
		try {
			if (key == null) throw new IllegalArgumentException("null key");
			return map.get(key).toString();
		} catch (IllegalArgumentException e) {
			return "bad key: " + e.getMessage();
		} catch (NullPointerException | ClassCastException e) {
			return "data error";
		} finally {
			// toujours exécuté
		}
	}

	/** try-with-resources */
	public void tryWithResources() throws Exception {
		try (AutoCloseable r = () -> {}) {
			// ressource fermée automatiquement
		}
	}

	/** throws déclaré */
	public void riskyMethod() throws IOException, IllegalStateException {
		throw new IOException("example");
	}

	// =========================================================
	// 13. LAMBDAS & REFERENCES DE METHODES
	// =========================================================

	public void lambdas() {
		// lambda sans paramètre
		Runnable r = () -> System.out.println("run");

		// lambda avec paramètres et corps
		Comparator<String> comp = (a, b) -> {
			return a.compareTo(b);
		};

		// référence de méthode statique
		Function<String, Integer> parse = Integer::parseInt;

		// référence de méthode d'instance (non liée)
		Function<String, String> upper = String::toUpperCase;

		// référence de méthode d'instance (liée)
		Supplier<Integer> size = items::size;

		// référence de constructeur
		Supplier<ArrayList<String>> newList = ArrayList::new;

		// Predicate
		Predicate<String> notEmpty = s -> !s.isEmpty();

		// streams
		items.stream()
		     .filter(notEmpty)
		     .map(upper)
		     .sorted(comp)
		     .forEach(System.out::println);
	}

	// =========================================================
	// 14. CLASSE ANONYME
	// =========================================================

	public Runnable anonymousClass() {
		return new Runnable() {
			@Override
			public void run() {
				System.out.println("anonymous: " + name);
			}
		};
	}

	// =========================================================
	// 15. CAST & instanceof
	// =========================================================

	public String typeCheck(Object obj) {
		if (obj instanceof String) {
			String s = (String) obj;
			return "string: " + s.length();
		} else if (obj instanceof Integer) {
			return "int: " + obj;
		} else if (obj instanceof List) {
			return "list: " + ((List<?>) obj).size();
		}
		return "unknown";
	}

	// =========================================================
	// 16. TERNAIRE, CONCAT, StringBuilder
	// =========================================================

	public String ternaryAndStrings(int x) {
		String sign = (x >= 0) ? "positive" : "negative";
		String concat = "x is " + sign + " (" + x + ")";
		StringBuilder sb = new StringBuilder();
		sb.append(sign).append(": ").append(x);
		return sb.toString();
	}

	// =========================================================
	// 17. TABLEAUX
	// =========================================================

	public int[] arrayOps() {
		int[] a = {1, 2, 3, 4, 5};
		int[][] matrix = {{1, 0}, {0, 1}};
		int[] copy = Arrays.copyOf(a, a.length);
		Arrays.sort(copy);
		return copy;
	}

	// =========================================================
	// 18. METHODES STATIQUES & FACTORY
	// =========================================================

	public static EntityImpl of(String name) {
		return new EntityImpl(name, 0);
	}

	public static int clamp(int value, int min, int max) {
		return Math.max(min, Math.min(max, value));
	}

	// =========================================================
	// 19. SYNCHRONIZED
	// =========================================================

	public synchronized void increment() { count++; }

	public void syncBlock() {
		synchronized (this) {
			count--;
		}
	}

	// =========================================================
	// 20. OPERATEURS BITWISE & SHIFT
	// =========================================================

	public int bitOps(int a, int b) {
		int and  = a & b;
		int or   = a | b;
		int xor  = a ^ b;
		int not  = ~a;
		int left  = a << 2;
		int right = a >> 1;
		int uright = a >>> 1;
		return and | or | xor | not | left | right | uright;
	}

	// =========================================================
	// 21. INITIALISATION STATIQUE & D'INSTANCE
	// =========================================================

	private static final Map<String, Integer> CODES;
	static {
		CODES = new HashMap<>();
		CODES.put("a", 1);
		CODES.put("b", 2);
	}

	private final List<String> defaults;
	{
		defaults = new ArrayList<>();
		defaults.add("init");
	}

	// =========================================================
	// 22. GETTERS / SETTERS
	// =========================================================

	public int getCount() { return count; }
	public void setCount(int count) { this.count = count; }
	public String getName() { return name; }
	public boolean isActive() { return active; }
}