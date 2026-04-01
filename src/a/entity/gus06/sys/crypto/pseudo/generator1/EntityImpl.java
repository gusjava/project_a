package a.entity.gus06.sys.crypto.pseudo.generator1;

import a.framework.*;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20141014";}


	private Service findPseudo;
	private Service generator;

	public EntityImpl() throws Exception
	{
		findPseudo = Outside.service(this,"gus06.entitydev.pseudo.find");
		generator = Outside.service(this,"gus06.sys.crypto.pseudo.generator");
	}
	
	public Object g() throws Exception
	{
		String pseudo = (String) findPseudo.g();
		if(pseudo==null) return null;
		return generator.t(pseudo);
	}
}
