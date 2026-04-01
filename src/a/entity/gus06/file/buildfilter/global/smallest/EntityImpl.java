package a.entity.gus06.file.buildfilter.global.smallest;

import a.framework.*;
import java.util.Set;
import java.util.HashSet;
import java.io.File;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20220514";}


	public EntityImpl() throws Exception
	{
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		List roots = (List) o[0];
		Integer nb = (Integer) o[1];
		
		if(nb==1) return new Holder1(roots).compute();
		return new HolderN(roots, nb).compute();
	}
	
	
	private class Holder1
	{
		private List roots;
		
		private long value;
		private File file;
		
		public Holder1(List roots)
		{
			this.roots = roots;
		}
		
		public Set compute()
		{
			for(int i=0;i<roots.size();i++) handle((File) roots.get(i));
			
			Set results = new HashSet();
			if(file!=null) results.add(file.getAbsolutePath());
			return results;
		}
		
		private void handle(File f)
		{
			if(f.isDirectory())
			{
				File[] cc = f.listFiles();
				for(File c : cc) handle(c);
			}
			else if(f.isFile())
			{
				long v = f.length();
				if(f==null || v<value)
				{
					file = f;
					value = v;
				}
			}
		}
	}
	
	
	
	private class HolderN
	{
		private List roots;
		private int nb;
		
		private long value;
		private List list;
		
		public HolderN(List roots, int nb)
		{
			this.roots = roots;
			this.nb = nb;
			list = new ArrayList();
		}
		
		private Object[] dataAt(int i)
		{return (Object[]) list.get(i);}
		
		private String dataPathAt(int i)
		{return (String) dataAt(i)[0];}
		
		private Long dataLengthAt(int i)
		{return (Long) dataAt(i)[1];}
		
		
		
		public Set compute()
		{
			for(int i=0;i<roots.size();i++) handle((File) roots.get(i));
			
			Set results = new HashSet();
			for(int i=0;i<list.size();i++) results.add(dataPathAt(i));
			return results;
		}
		
		private void handle(File f)
		{
			if(f.isDirectory())
			{
				File[] cc = f.listFiles();
				for(File c : cc) handle(c);
			}
			else if(f.isFile())
			{
				long v = f.length();
				int nb1 = list.size();
				
				if(nb1==0)
				{
					list.add(new Object[]{f.getAbsolutePath(), v});
					value = v;
				}
				else if(nb1<nb)
				{
					if(v>value)
					{
						list.add(new Object[]{f.getAbsolutePath(), v});
						value = v;
					}
					else
					{
						int i = nb1-1;
						Long vi = dataLengthAt(i);
						
						while(v>vi && i>0)
						{
							i--;
							vi = dataLengthAt(i);
						}
						list.add(i,new Object[]{f.getAbsolutePath(), v});
						value = dataLengthAt(nb1);
					}
				}
				else if(v<value)
				{
					int i = nb-1;
					Long vi = dataLengthAt(i);
					
					while(v>vi && i>0)
					{
						i--;
						vi = dataLengthAt(i);
					}
					list.add(i, new Object[]{f.getAbsolutePath(), v});
					list.remove(nb);
					
					value = dataLengthAt(nb-1);
				}
			}
		}
	}
}