package a.entity.gus06.sys.expression1.apply.op._chrono;

import a.framework.*;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160719";}
	
	
	private long t1 = -1;
	private List hist;
	
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof String) return handleChrono((String) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private Object handleChrono(String s) throws Exception
	{
		if(s.equals("reset")) return new E0();
		if(s.equals("tt")) return new E1();
		if(s.equals("t")) return top();
		if(s.equals("values")) return hist;
		if(s.equals("count")) return Integer.valueOf(count());
		if(s.equals("total")) return Long.valueOf(total());
		if(s.equals("avg")) return Double.valueOf(avg());
		
		throw new Exception("Invalid command: "+s);
	}
	
	private int count()
	{
		if(hist==null) return 0;
		return hist.size();
	}
	
	private long total()
	{
		if(hist==null) return 0;
		long total = 0;
		int count = hist.size();
		for(int i=0;i<count;i++) total += ((Long) hist.get(i)).longValue();
		return total;
	}
	
	private double avg()
	{
		if(hist==null) return 0;
		long total = 0;
		int count = hist.size();
		for(int i=0;i<count;i++) total += ((Long) hist.get(i)).longValue();
		return (double) total/count;
	}
	
	
	private Long top()
	{
		Long dt = null;
		long t2 = System.currentTimeMillis();
		if(t1!=-1) {dt = Long.valueOf(t2-t1);hist.add(dt);}
		t1 = t2;
		return dt;
	}
	
	
	private class E0 implements E
	{
		public void e() throws Exception
		{
			hist = new ArrayList();
			t1 = -1;
		}
	}
	
	private class E1 implements E
	{
		public void e() throws Exception
		{top();}
	}
}
