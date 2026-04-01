package a.entity.gus06.sys.script1.context.builder1.a.output;

import a.framework.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150905";}

	public static final String ENABLED = "enabled";
	public static final String MODE = "mode";
	public static final String CLEAR = "clear";
	public static final String ADD = "add";
	public static final String REMOVE = "remove";
	public static final String REDIRECT = "redirect";
	
	public static final String MODE_ONLYPRINT = "onlyprint";
	public static final String MODE_ONLYTEXT = "onlytext";
	public static final String MODE_ALL = "all";
	
	
	private Service buildP;
	
	public EntityImpl() throws Exception
	{
		buildP = Outside.service(this,"gus06.find.p");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		return new Output(obj);
	}
	
	
	
	
	private class Output extends S1 implements P, V, R, F {
		
		private StringBuffer b;
		private P p;
		private P p0;
		
		private List<T> trans;
		private boolean enabled;
		private String mode;
		
		public Output(Object obj) throws Exception
		{
			b = new StringBuffer();
			p = (P) buildP.t(obj);
			p0 = null;
			trans = new ArrayList<>();
			enabled = true;
			mode = MODE_ALL;
		}
		
		public void p(Object obj) throws Exception
		{
			if(!enabled) return;
			
			if(obj==null) obj = "null";
			for(T t:trans) obj = t.t(obj);
			
			b.append(obj);
			if(p0!=null) p0.p(obj);
			else if(p!=null) p.p(obj);
			
			outputCalled();
		}
		
		public void v(String key, Object obj) throws Exception
		{
			if(key.equals(ENABLED)) {enabled((String) obj);return;}
			if(key.equals(MODE)) {setMode((String)obj);return;}
			if(key.equals(REDIRECT)) {redirect(obj);return;}
			if(key.equals(ADD)) {add((T) obj);return;}
			if(key.equals(REMOVE)) {remove((T) obj);return;}
			if(key.equals(CLEAR)) {clear();return;}
			
			throw new Exception("Unknown key: "+key);
		}
		
		
		public Object r(String key) throws Exception
		{
			if(key.equals("trans")) return trans;
			if(key.equals("mode")) return mode;
			if(key.equals("p")) return p;
			if(key.equals("p0")) return p0;
			if(key.equals("text")) return b.toString();
			
			if(key.equals("keys")) return new String[]{"trans","mode","p","p0","text"};
			throw new Exception("Unkwown key: "+key);
		}
		
		
		public boolean f(Object obj) throws Exception
		{
			if(obj==null) return enabled;
			
			String s = (String) obj;
			if(s.equals("print")) return mode.equals(MODE_ALL) || mode.equals(MODE_ONLYPRINT);
			if(s.equals("text")) return mode.equals(MODE_ALL) || mode.equals(MODE_ONLYTEXT);
			
			throw new Exception("Invalid command: "+s);
		}
		
		
		
		
		private void enabled(String v)
		{enabled = Boolean.parseBoolean(v);}
		
		
		private void redirect(Object obj) throws Exception
		{
			closeP0();
			p0 = (P) buildP.t(obj);
		}
		
		private void add(T t)
		{trans.add(t);}
		
		private void remove(T t)
		{trans.remove(t);}
		
		private void setMode(String mode)
		{this.mode = mode;}
		
		
		private void clear() throws Exception
		{
			trans.clear();
			closeP0();
		}
		
		
		private void closeP0() throws Exception
		{
			if(p0==null) return;
			if(p0 instanceof E) ((E) p0).e();
			p0 = null;
		}
		
		
		private void outputCalled()
		{send(this,"outputCalled()");}
	}
}