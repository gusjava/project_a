package a.entity.gus06.sys.script1.executor.type.el.r.for1.range;

import a.framework.*;
import java.util.Map;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160110";}

	
	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof Integer) return fromInt1(int_(obj));
		if(obj instanceof List) return fromList((List) obj);
		if(obj instanceof Map) return fromMap((Map) obj);
		if(obj instanceof Object[]) return fromArray((Object[]) obj);
		if(obj instanceof int[]) return fromInts((int[]) obj);
		if(obj instanceof String) return fromString((String) obj);
		
		throw new Exception("Invalid range type: "+obj.getClass().getName());
	}
	
	
	
	private int[] fromInt1(int end)
	{
		if(end>0) return new int[]{0,end,1};
		if(end<0) return new int[]{0,end,-1};
		return new int[]{0,0,1};
	}
	
	private int[] fromInt2(int start, int end)
	{
		if(start<end) return new int[]{start,end,1};
		if(start>end) return new int[]{start,end,-1};
		return new int[]{start,start,1};
	}
	
	private int[] fromInt3(int start, int end, int incr) throws Exception
	{
		if(start<end && incr<=0) return null;
		if(start>end && incr>=0) return null;
		
		if(start==end) return new int[]{start,start,1};
	
		int l0 = Math.min(start,end);
		int l1 = Math.max(start,end);
		
		int i = start;
		int end1 = start;
		
		while(i>=l0 && i<=l1)
		{
			end1 = i;
			i+=incr;
		}
		return new int[]{start,end1,incr};
	}
	
	
	
	
	
	private int[] fromList(List l) throws Exception
	{
		int nb = l.size();
		if(nb==1) return fromInt1(int_(l.get(0)));
		if(nb==2) return fromInt2(int_(l.get(0)),int_(l.get(1)));
		if(nb==3) return fromInt3(int_(l.get(0)),int_(l.get(1)),int_(l.get(2)));
		
		throw new Exception("Invalid length for range as List: "+nb);
	}
	
	
	
	private int[] fromMap(Map m) throws Exception
	{
		int start = has(m,"start")?int_(m.get("start")):0;
		int end = has(m,"end")?int_(m.get("end")):0;
		int incr = has(m,"incr")?int_(m.get("incr")):incr(start,end);
		
		return fromInt3(start,end,incr);
	}
	
	
	
	
	private int[] fromArray(Object[] oo) throws Exception
	{
		int nb = oo.length;
		if(nb==1) return fromInt1(int_(oo[0]));
		if(nb==2) return fromInt2(int_(oo[0]),int_(oo[1]));
		if(nb==3) return fromInt3(int_(oo[0]),int_(oo[1]),int_(oo[2]));
		
		throw new Exception("Invalid length for range as Object[]: "+nb);
	}
	
	
	
	private int[] fromInts(int[] ii) throws Exception
	{
		int nb = ii.length;
		if(nb==1) return fromInt1(ii[0]);
		if(nb==2) return fromInt2(ii[0],ii[1]);
		if(nb==3) return fromInt3(ii[0],ii[1],ii[2]);
		
		throw new Exception("Invalid length for range as int[]: "+nb);
	}
	
	
	
	private int[] fromString(String s) throws Exception
	{
		String[] n = s.replace("..",":").split(":");
		int[] k = new int[n.length];
		for(int i=0;i<n.length;i++) k[i] = int_(n[i]);
		return fromInts(k);
	}
	
	
	
	
	
	private int int_(Object o)
	{return Integer.parseInt(""+o);}
	
	private boolean has(Map m, String key)
	{return m.containsKey(key);}
	
	private int incr(int start, int end)
	{
		if(start==end) return 0;
		return start<end?1:-1;
	}
}