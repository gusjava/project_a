package a.core.tav.mini1;

import java.util.HashMap;
import java.util.Map;
import a.framework.*;

public class Main implements Manager {

	public static final String POOL = "pool";
	public static final String KEY_ARGS = "args";
	public static final String KEY_SERVICE = "service";
	public static final String KEY_RESOURCE = "resource";
	public static final String KEY_ERR = "err";

	private Map pool = new HashMap<>();

	public Main(String[] args) {
		try {
			if (args.length == 0)
				throw new Exception("No arg found");
			String entityName = args[0];

			pool.put(KEY_ARGS, args);
			Outside.setManager(this);

			Class c = Class.forName("a.entity." + entityName + ".EntityImpl");
			E launcher = (E) c.getDeclaredConstructor().newInstance();
			launcher.e();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	public Object resource(Entity entity, String id) throws Exception {
		if (pool.containsKey(KEY_RESOURCE))
			return ((T) pool.get(KEY_RESOURCE)).t(new Object[] { entity, id });
		if (id.equals(POOL))
			return pool;
		throw new Exception("Resource builder not initialized yet");
	}

	public Service service(Entity entity, String id) throws Exception {
		if (pool.containsKey(KEY_SERVICE))
			return (Service) ((T) pool.get(KEY_SERVICE)).t(new Object[] { entity, id });
		throw new Exception("Service builder not initialized yet");
	}

	public void err(Entity entity, String id, Exception e) {
		try {
			if (pool.containsKey(KEY_ERR))
				((P) pool.get(KEY_ERR)).p(new Object[] { entity, id, e });
			else
				throw new Exception("Err handler not initialized yet");
		} catch (Exception e1) {
			e1.printStackTrace();
			System.exit(1);
		}
	}

	public static void main(String[] args) {
		new Main(args);
	}
}
