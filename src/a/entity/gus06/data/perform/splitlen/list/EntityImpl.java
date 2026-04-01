package a.entity.gus06.data.perform.splitlen.list;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180108";}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		List input = (List) o[0];
		int len = toInt(o[1]);
		
		if(len>0) return split(input,len);
		if(len<0) return splitNeg(input,-len);
		throw new Exception("Invalid splitting length: 0");
	}
	
	private int toInt(Object obj)
	{return Integer.parseInt(""+obj);}
	
	
	private List split(List l, int len)
	{
		int length = l.size();
		int nb = length/len;
		if(length%len>0) nb++;
		
		List output = new ArrayList();
		for(int i=0;i<nb;i++)
		{
			int start = i*len;
			int end = start+len;
			end = Math.min(end,length);
			
			List r = new ArrayList();
			output.add(r);
			
			for(int j=start;j<end;j++)
			r.add(l.get(j));
		}
		return output;
	}
	
	
	private List splitNeg(List l, int len)
	{
		int length = l.size();
		int nb = length/len;
		int offset = length%len;
		
		if(offset==0)
		{
			List output = new ArrayList();
			for(int i=0;i<nb;i++)
			{
				int start = i*len;
				int end = start+len;
				end = Math.min(end,length);
				
				List r = new ArrayList();
				output.add(r);
				
				for(int j=start;j<end;j++)
				r.add(l.get(j));
			}
			return output;
		}
		
		List output = new ArrayList();
		
		List r = new ArrayList();
		output.add(r);
		
		for(int j=0;j<offset;j++)
		r.add(l.get(j));
		
		for(int i=0;i<nb;i++)
		{
			int start = offset+i*len;
			int end = start+len;
			end = Math.min(end,length);
			
			r = new ArrayList();
			output.add(r);
			
			for(int j=start;j<end;j++)
			r.add(l.get(j));
		}
		return output;
	}
}
