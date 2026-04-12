package a.entity.gus06.entitydev.generate.srccode.printcode;

import java.io.File;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import a.framework.*;
import java.util.Set;
import java.util.HashSet;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20140905";}

	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	private String today() {return sdf.format(new Date());}
	
	public static final String FEATURES = "EFGHIPRVSTX";
	
	
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		PrintStream p = (PrintStream) o[0];
		Map map = (Map) o[1];
		
		String src = get0(map,GEN.KEY_SRC);
		if(!src.equals("")) p.print(src);
		else buildSrc(p,map);
	}
	
	
	
	
	private void buildSrc(PrintStream p, Map map) throws Exception
	{	
		String entityName = get1(map,GEN.KEY_ENTITYNAME);
		String ff = get0(map,GEN.KEY_FEATURES).toUpperCase();
		String cc = get0(map,GEN.KEY_CALLS);
		String today = today();
		
		boolean isE = ff.contains("E");
		boolean isF = ff.contains("F");
		boolean isG = ff.contains("G");
		boolean isH = ff.contains("H");
		boolean isI = ff.contains("I");
		boolean isP = ff.contains("P");
		boolean isR = ff.contains("R");
		boolean isS = ff.contains("S");
		boolean isV = ff.contains("V");
		boolean isT = ff.contains("T");
		boolean isX = ff.contains("X");
		
		p.println("package a.entity."+entityName+";");
		p.println();
		p.println("import a.framework.*;");
		
		p.println();
		p.println("public class EntityImpl"+element1(ff)+" implements Entity"+element2(ff)+" {");
		p.println();
		p.println("\tpublic String creationDate() {return \""+today+"\";}");
		p.println();
		
		for(int i=0;i<cc.length();i++)
		{
			if(cc.charAt(i)=='$') p.println("\tprivate Service s"+i+";");
			else if(cc.charAt(i)=='%') p.println("\tprivate Object s"+i+";");
		}
		
		p.println();
		p.println("\tpublic EntityImpl() throws Exception");
		p.println("\t{");
		
		for(int i=0;i<cc.length();i++)
		{
			if(cc.charAt(i)=='$') p.println("\t\ts"+i+" = Outside.service(this,\"s"+i+"\");");
			else if(cc.charAt(i)=='%') p.println("\t\ts"+i+" = Outside.resource(this,\"s"+i+"\");");
		}
		
		p.println("\t}");

		if(isE)
		{
			p.println("\t");
			p.println("\t");
			p.println("\tpublic void e() throws Exception");
			p.println("\t{");
			p.println("\t}");
		}

		if(isF)
		{
			p.println("\t");
			p.println("\t");
			p.println("\tpublic boolean f(Object obj) throws Exception");
			p.println("\t{");
			p.println("\t\treturn true;");
			p.println("\t}");
		}

		if(isG)
		{
			p.println("\t");
			p.println("\t");
			p.println("\tpublic Object g() throws Exception");
			p.println("\t{");
			p.println("\t\treturn null;");
			p.println("\t}");
		}

		if(isH)
		{
			p.println("\t");
			p.println("\t");
			p.println("\tpublic double h(double value) throws Exception");
			p.println("\t{");
			p.println("\t\treturn 0;");
			p.println("\t}");
		}

		if(isI)
		{
			p.println("\t");
			p.println("\t");
			p.println("\tpublic Object i() throws Exception");
			p.println("\t{");
			p.println("\t\treturn null;");
			p.println("\t}");
		}

		if(isP)
		{
			p.println("\t");
			p.println("\t");
			p.println("\tpublic void p(Object obj) throws Exception");
			p.println("\t{");
			p.println("\t\t");
			p.println("\t}");
		}

		if(isR)
		{
			p.println("\t");
			p.println("\t");
			p.println("\tpublic Object r(String key) throws Exception");
			p.println("\t{");
			p.println("\t\treturn null;");
			p.println("\t}");
		}

		if(isV)
		{
			p.println("\t");
			p.println("\t");
			p.println("\tpublic void v(String key, Object obj) throws Exception");
			p.println("\t{");
			p.println("\t\t");
			p.println("\t}");
		}

		if(isT)
		{
			p.println("\t");
			p.println("\t");
			p.println("\tpublic Object t(Object obj) throws Exception");
			p.println("\t{");
			p.println("\t\treturn null;");
			p.println("\t}");
		}

		if(isX)
		{
			p.println("\t");
			p.println("\t");
			p.println("\tpublic void run()");
			p.println("\t{");
			p.println("\t\t");
			p.println("\t}");
		}

		p.print("}");
	}
	
	
	
	private String element1(String ff)
	{
		if(!ff.contains("S")) return "";
		return " extends S1";
	}
	
	
	private String element2(String ff) throws Exception
	{
		if(ff.equals("")) return "";
		StringBuffer b = new StringBuffer();
		Set set = new HashSet();
		for(int i=0;i<ff.length();i++)
		{
			String c = ""+ff.charAt(i);
			if(c.equals("S")) continue;
			
			if(!FEATURES.contains(c)) throw new Exception("Invalid features list: "+ff);
			if(set.contains(c)) throw new Exception("Invalid features list: "+ff);
			
			if(c.equals("X")) c = "Runnable";
			b.append(", "+c);
			set.add(c);
		}
		return b.toString();
	}

	
	
	
	private String get1(Map map, String key) throws Exception
	{
		String value = get0(map,key);
		if(value.equals("")) throw new Exception("Key not found inside map: "+key);
		return value;
	}
	
	private String get0(Map map, String key) throws Exception
	{
		if(!map.containsKey(key)) return "";
		return (String) map.get(key);
	}
}