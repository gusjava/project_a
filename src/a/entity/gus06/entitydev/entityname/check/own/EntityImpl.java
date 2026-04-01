package a.entity.gus06.entitydev.entityname.check.own;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140712";}


	private Service findPseudo;
	private Service isValid;
	
	public EntityImpl() throws Exception
	{
		findPseudo = Outside.service(this,"gus06.entitydev.pseudo.find");
		isValid = Outside.service(this,"gus06.app.entity.isvalid");
	}
	
	
	
	public Object t(Object obj) throws Exception
	{
		String name = (String) obj;
		
		if(!isValid.f(name)) throw new Exception("Invalid entity name: "+name);
		
		String pseudo = (String) findPseudo.g();
		if(pseudo==null) throw new Exception("Pseudo not initialized yet");
		
		if(name.startsWith(pseudo+".")) return name;
		return pseudo+"."+name;
	}
}
