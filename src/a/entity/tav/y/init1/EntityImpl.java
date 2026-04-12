package a.entity.tav.y.init1;

import java.util.HashMap;
import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity, E {
	public String creationDate() {return "20240104";}

	public static final String POOL = "pool";
	public static final String KEY_ARGS = "args";
	public static final String KEY_ERR = "err";
	public static final String KEY_SERVICE = "service";
	public static final String KEY_RESOURCE = "resource";
	public static final String KEY_ENTITY_GENERATOR = "entity_generator";
	
	private Map pool;

	public EntityImpl() throws Exception {
		pool = (Map) Outside.resource(this, POOL);
	}

	public void e() throws Exception {
		String[] args = (String[]) pool.get(KEY_ARGS);
		if (args.length < 2)
			throw new Exception("Incorrect arg number: " + args.length);
		String configId = args[1];
		
		Map main = new HashMap();
		
		T entityGenerator = new EntityGenerator();
		
		Object resourceBuilder = newEntity("tav.y.init1.builder.resource");
		Object serviceBuilder = newEntity("tav.y.init1.builder.service");
		Object errHandler = newEntity("tav.y.init1.handler.err");
		
		((V) resourceBuilder).v("main", main);
		((V) serviceBuilder).v("main", main);
		((V) errHandler).v("main", main);

		pool.put(KEY_ENTITY_GENERATOR, entityGenerator);
		pool.put(KEY_RESOURCE, resourceBuilder);
		pool.put(KEY_SERVICE, serviceBuilder);
		pool.put(KEY_ERR, errHandler);

		P launcher = (P) newEntity("tav.y.init1.launcher");
		launcher.p(configId);
	}
	
	
	private class EntityGenerator implements T {
		public Object t(Object obj) throws Exception {
			return newEntity((String) obj);
		}
	}
	
	private Object newEntity(String entityName) throws Exception {
		Class c = Class.forName("a.entity." + entityName + ".EntityImpl");
		return c.getDeclaredConstructor().newInstance();
	}
}
