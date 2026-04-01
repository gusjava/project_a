package a.entity.gus06.java.srccode.generate.setter;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160823";}


	
	
	public Object t(Object obj) throws Exception
	{
		String[] n = analyze(obj);
		
		String type = n[0];
		String name = n[1];
		
		
		StringBuffer b = new StringBuffer();
		
		b.append("\tpublic void set");
		b.append(name.substring(0,1).toUpperCase());
		if(name.length()>1) b.append(name.substring(1));
		b.append("("+type+" "+name+") {\n");
		
		b.append("\t\tthis."+name+" = "+name+";\n");
		b.append("\t}\n");
		
		return b.toString();
	}
	
	
	private String[] analyze(Object obj) throws Exception
	{
		if(obj instanceof String) return analyzeString((String) obj);
		if(obj instanceof String[]) return (String[]) obj;
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private String[] analyzeString(String s) throws Exception
	{
		s = s.trim();
		if(s.endsWith(";")) s = s.substring(0,s.length()-1);
		
		String[] n = s.split("\\s+");
		
		if(n.length==2) return n;
		if(n.length==3)
		{
			if(!n[0].equals("private") && !n[0].equals("protected") && !n[0].equals("public"))
				throw new Exception("Invalid java field definition: "+s);
			
			return new String[]{n[1],n[2]};
		}
		throw new Exception("Invalid java field definition: "+s);
	}
}
