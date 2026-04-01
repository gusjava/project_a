package a.entity.gus06.entitydev.pseudo.isvalid;

import a.framework.*;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20140826";}
	
	public static final int MAX = 10;
	
	
	private Service isKeyword;
	
	public EntityImpl() throws Exception
	{
		isKeyword = Outside.service(this,"gus06.java.iskeyword");
	}

	
	public boolean f(Object obj) throws Exception
	{
		String pseudo = (String) obj;
		
		if(pseudo==null) return false;
		if(pseudo.equals("")) return false;
		if(pseudo.length()>MAX) return false;
		if(isKeyword.f(pseudo)) return false;
		
		return pseudo.matches("[a-z][a-z][a-z][a-z0-9]*");
	}
}
