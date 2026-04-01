package a.entity.gus06.app.entity.checkname.own;

import a.framework.*;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20140828";}


	private Service check;
	private Service findPseudo;


	public EntityImpl() throws Exception
	{
		check = Outside.service(this,"gus06.app.entity.checkname");
		findPseudo = Outside.service(this,"gus06.entitydev.pseudo.find");
	}
	
	
	public boolean f(Object obj) throws Exception
	{
		String name = (String) obj;
		if(!check.f(name)) return false;
		
		String pseudo = (String) findPseudo.g();
		if(pseudo==null) return false;
		
		return name.startsWith(pseudo+".");
	}
}
