package a.entity.gus.x.crypto.hash.bcrypt.generate;

import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20180308";}
	
	public Object t(Object obj) throws Exception
	{
		String plaintext = (String) obj;
		
		String salt = BCrypt.gensalt();
		return BCrypt.hashpw(plaintext, salt);
	}
}