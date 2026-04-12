package a.entity.gus06.sys.executor.executor1;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T, P {

	public String creationDate() {return "20180131";}
	
	public static final String KEY_PERFORM = "perform";
	public static final String KEY_ON_SUCCESS = "onSuccess";
	public static final String KEY_ON_ERROR = "onError";
	public static final String KEY_INPUT = "input";
	public static final String KEY_INPUTG = "inputG";
	public static final String KEY_MONITOR = "monitor";


	public EntityImpl() throws Exception
	{
	}
	
	public Object t(Object obj) throws Exception
	{
		Map map = (Map) obj;
		return new Executor(map);
	}
	
	public void p(Object obj) throws Exception
	{
		Map map = (Map) obj;
		execute(map);
	}
	
	
	private class Executor implements E
	{
		private Map map;
		public Executor(Map map) {this.map = map;}
		
		public void e() throws Exception
		{execute(map);}
	}
	
	
	private void execute(Map map) throws Exception
	{
		Object perform = get(map,KEY_PERFORM);
		if(perform==null) throw new Exception("Perform object is null");
		
		if(perform instanceof T) {executeT((T) perform,map);return;}
		if(perform instanceof F) {executeF((F) perform,map);return;}
		if(perform instanceof P) {executeP((P) perform,map);return;}
		if(perform instanceof G) {executeG((G) perform,map);return;}
		if(perform instanceof E) {executeE((E) perform,map);return;}
		
		throw new Exception("Invalid perform type: "+perform.getClass().getName());
	}
	
	
	
	
	
	private void executeT(T perform, Map map) throws Exception
	{
		Object input = findInput(map);
		
		Object result = null;
		Exception exception = null;
		
		long t1 = System.currentTimeMillis();
		
		try			{result = perform.t(input);}
		catch(Exception e)	{exception = e;}
		
		long t2 = System.currentTimeMillis();
		
		if(exception!=null) handleError(exception,map);
		else handleSuccess(result,map);
		
		handleMonitor(t1,t2,map);
	}
	
	
	private void executeF(F perform, Map map) throws Exception
	{
		Object input = findInput(map);
		
		boolean result = false;
		Exception exception = null;
		
		long t1 = System.currentTimeMillis();
		
		try			{result = perform.f(input);}
		catch(Exception e)	{exception = e;}
		
		long t2 = System.currentTimeMillis();
		
		if(exception!=null) handleError(exception,map);
		else handleSuccess(Boolean.valueOf(result),map);
		
		handleMonitor(t1,t2,map);
	}
	
	
	private void executeP(P perform, Map map) throws Exception
	{
		Object input = findInput(map);
		
		Exception exception = null;
		
		long t1 = System.currentTimeMillis();
		
		try			{perform.p(input);}
		catch(Exception e)	{exception = e;}
		
		long t2 = System.currentTimeMillis();
		
		if(exception!=null) handleError(exception,map);
		else handleSuccess(map);
		
		handleMonitor(t1,t2,map);
	}
	
	
	private void executeG(G perform, Map map) throws Exception
	{
		Object result = null;
		Exception exception = null;
		
		long t1 = System.currentTimeMillis();
		
		try			{result = perform.g();}
		catch(Exception e)	{exception = e;}
		
		long t2 = System.currentTimeMillis();
		
		if(exception!=null) handleError(exception,map);
		else handleSuccess(result,map);
		
		handleMonitor(t1,t2,map);
	}
	
	
	private void executeE(E perform, Map map) throws Exception
	{
		Exception exception = null;
		
		long t1 = System.currentTimeMillis();
		
		try			{perform.e();}
		catch(Exception e)	{exception = e;}
		
		long t2 = System.currentTimeMillis();
		
		if(exception!=null) handleError(exception,map);
		else handleSuccess(map);
		
		handleMonitor(t1,t2,map);
	}
	
	
	
	
	private Object findInput(Map map) throws Exception
	{
		Object inputG = get(map,KEY_INPUTG);
		if(inputG!=null) return ((G) inputG).g();
		
		return get(map,KEY_INPUT);
	}
	
	
	
	private void handleSuccess(Object result, Map map) throws Exception
	{
		Object onSuccess = get(map,KEY_ON_SUCCESS);
		if(onSuccess==null) return;
		
		if(onSuccess instanceof P)		((P) onSuccess).p(result);
		else if(onSuccess instanceof E)		((E) onSuccess).e();
		else throw new Exception("Invalid onSuccess type: "+onSuccess.getClass().getName());
	}
	
	
	
	private void handleSuccess(Map map) throws Exception
	{
		Object onSuccess = get(map,KEY_ON_SUCCESS);
		if(onSuccess==null) return;
		
		if(onSuccess instanceof E)		((E) onSuccess).e();
		else throw new Exception("Invalid onSuccess type: "+onSuccess.getClass().getName());
	}
	
	
	
	private void handleError(Exception e, Map map) throws Exception
	{
		Object onError = get(map,KEY_ON_ERROR);
		if(onError==null) return;
		
		if(onError instanceof P)		((P) onError).p(e);
		else if(onError instanceof E)		((E) onError).e();
		else throw new Exception("Invalid onError type: "+onError.getClass().getName());
	}
	
	
	
	private void handleMonitor(long t1, long t2, Map map) throws Exception
	{
		Object monitor = get(map,KEY_MONITOR);
		if(monitor==null) return;
		
		Map m = new HashMap();
		m.put("start",Long.valueOf(t1));
		m.put("end",Long.valueOf(t2));
		m.put("duration",Long.valueOf(t2-t1));
		
		((P) monitor).p(m);
	}
	
	
	
	private Object get(Map map, String key)
	{return map.containsKey(key) ? map.get(key) : null;}
}
