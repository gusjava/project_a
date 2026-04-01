package a.entity.gus06.mail.address.findtype;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20201112";}
	
	public static final String TYPE_HOTMAIL = "HOTMAIL";
	public static final String TYPE_GMAIL = "GMAIL";

	
	
	public Object t(Object obj) throws Exception
	{
		String address = (String) obj;
		
		if(address.endsWith("@hotmail.fr")) return TYPE_HOTMAIL;
		if(address.endsWith("@hotmail.com")) return TYPE_HOTMAIL;
		
		if(address.endsWith("@gmail.fr")) return TYPE_GMAIL;
		if(address.endsWith("@gmail.com")) return TYPE_GMAIL;
		
		throw new Exception("Unsupported email address: "+address);
	}
}