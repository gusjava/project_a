package a.entity.gus06.string.transform2.remove.line.en;

import a.framework.*;

public class EntityImpl implements Entity, T
{
	public String creationDate() {return "20151103";}
	
	public Object t(Object obj) throws Exception
	{return new T1((String) obj);}
	
	
	public class T1 implements T
	{
		private String info;
		public T1(String info) {this.info = info;}
	
		
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
				if(!line.endsWith(info))
				b.append(line+"\n");
			}
			if(b.length()>0) b.deleteCharAt(b.length()-1);
			return b.toString();
		}
	}
}
