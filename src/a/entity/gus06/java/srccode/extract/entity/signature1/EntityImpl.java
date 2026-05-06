package a.entity.gus06.java.srccode.extract.entity.signature1;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150526";}

	public static final String ST_CLASSHEADER = "public class EntityImpl ";
	

	private Service toArray;

	public EntityImpl() throws Exception
	{toArray = Outside.service(this,"gus.x.java.srccode.toarray");}

	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		String[] lines = (String[]) toArray.t(obj);
		StringBuffer signature = new StringBuffer();
		
		for(String line:lines)
		{
			if(line.startsWith(ST_CLASSHEADER))
			{
				boolean extendsS1 = line.contains(" extends S1 ");
				String[] n = line.split(" implements ");
				if(n.length!=2) throw new Exception("Invalid entity class header: "+line);
				
				String part = " "+n[1].replace(","," ")+" ";
				
				if(part.contains(" E ")) signature.append("e");
				if(part.contains(" F ")) signature.append("f");
				if(part.contains(" G ")) signature.append("g");
				if(part.contains(" H ")) signature.append("h");
				if(part.contains(" I ")) signature.append("i");
				if(part.contains(" P ")) signature.append("p");
				if(part.contains(" R ")) signature.append("r");
				if(extendsS1 || part.contains(" S ")) signature.append("s");
				if(part.contains(" T ")) signature.append("t");
				if(part.contains(" V ")) signature.append("v");
				if(part.contains(" Runnable ")) signature.append("x");
			}
		}
		
		return signature.toString();
	}
}
