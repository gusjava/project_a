package a.entity.gus06.entitydev.pseudo.check.gus;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20140829";}


	private Service findPseudo;
	
	public EntityImpl() throws Exception
	{
		findPseudo = Outside.service(this,"gus06.entitydev.pseudo.find");
	}
	
	
	public boolean f(Object obj) throws Exception
	{
		if((new File("gus")).exists()) return true;
		String pseudo = (String) findPseudo.g();
		return pseudo!=null && pseudo.equals("gus");
	}
}
