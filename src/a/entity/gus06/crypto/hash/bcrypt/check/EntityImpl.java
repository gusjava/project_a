package a.entity.gus06.crypto.hash.bcrypt.check;

import a.framework.*;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20180308";}
	
	
	public boolean f(Object obj) throws Exception
	{
		String[] o = (String[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		String plaintext = o[0];
		String hashed = o[1];
		
		return BCrypt.checkpw(plaintext, hashed);
	}
}
