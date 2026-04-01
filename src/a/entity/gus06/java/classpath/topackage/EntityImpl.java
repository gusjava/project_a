package a.entity.gus06.java.classpath.topackage;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170223";}

	
	
	public Object t(Object obj) throws Exception
	{
		String classpath = (String) obj;
		
		String[] nn = classpath.split("\\.");
		StringBuffer b = new StringBuffer(nn[0]);
		for(int i=1;i<nn.length;i++) if(isLower(nn[i]))
		b.append("."+nn[i]);
		
		return b.toString();
	}
	
	private boolean isLower(String s)
	{return s.toLowerCase().equals(s);}
}