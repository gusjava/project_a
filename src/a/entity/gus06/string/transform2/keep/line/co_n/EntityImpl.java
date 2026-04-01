package a.entity.gus06.string.transform2.keep.line.co_n;

import a.framework.*;

public class EntityImpl implements Entity, T
{
	public String creationDate() {return "20190314";}
	
	
	private Service normalize;
	
	public EntityImpl() throws Exception
	{normalize = Outside.service(this,"gus06.string.transform.normalize.diacritics.lower");}
	
	private String normalize(String s) throws Exception
	{return (String) normalize.t(s);}
	
	public Object t(Object obj) throws Exception
	{return new T1((String) obj);}
	
	
	public class T1 implements T
	{
		private String info;
		public T1(String info) throws Exception
		{this.info = normalize(info);}
	
		
		public Object t(Object obj) throws Exception
		{
			String s = (String) obj;
			if(info.equals("")) return obj;
			if(info.contains("\n"))return obj;
			
			String[] lines = s.split("\n",-1);
			StringBuffer b = new StringBuffer();
			for(int i=0;i<lines.length;i++)
			{
				String line = lines[i];
				if(normalize(line).contains(info))
				b.append(line+"\n");
			}
			if(b.length()>0) b.deleteCharAt(b.length()-1);
			return b.toString();
		}
	}
}
