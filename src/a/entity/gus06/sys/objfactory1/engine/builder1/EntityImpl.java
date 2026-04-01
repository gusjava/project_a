package a.entity.gus06.sys.objfactory1.engine.builder1;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191122";}


	private Service findR;

	

	public EntityImpl() throws Exception
	{
		findR = Outside.service(this,"gus06.find.r");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		R r = (R) findR.t(obj);
		return new Engine(r);
	}
	
	
	
	private class Engine implements R
	{
		private R r;
		public Engine(R r) {this.r = r;}
		
		public Object r(String key) throws Exception
		{
			if(key==null) return null;
			if(key.equals("null")) return null;
			
			if(!key.contains(":")) throw new Exception("Invalid rule: "+key);
			
			String[] n = key.split(":",2);
			String seq = n[0];
			String info = n[1];
			
			String[] s = seq.split("\\|");
			int nb = s.length;
			
			T[] t = new T[nb];
			for(int i=0;i<nb;i++)
			{
				String id = s[nb-1-i];
				T elem = (T) r.r(id);
				if(elem==null) throw new Exception("Factory not found for id: "+id);
				t[i] = elem;
			}
			
			Object current = info;
			for(T elem : t) current = elem.t(current);
			return current;
		}
	}
}
