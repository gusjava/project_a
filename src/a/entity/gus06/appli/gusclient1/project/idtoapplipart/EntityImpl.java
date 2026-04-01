package a.entity.gus06.appli.gusclient1.project.idtoapplipart;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140905";}


	private Service findPseudo;
	
	public EntityImpl() throws Exception
	{
		findPseudo = Outside.service(this,"gus06.entitydev.pseudo.find");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		String id = (String) obj;
		String pseudo = (String) findPseudo.g();
		
		return pseudo+".appli."+id+".";
	}
}
