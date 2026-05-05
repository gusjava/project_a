package a.entity.gus.x.crypto.secretkey.generator.des56;

import a.framework.*;
import java.security.KeyPairGenerator;
import javax.crypto.KeyGenerator;

public class EntityImpl implements Entity, G {
	public String creationDate() {return "20141005";}

	public static final String ALGO = "DES"; //Data Encryption Standard
	public static final int LENGTH = 56;

	private KeyGenerator kg;
	
	public EntityImpl() throws Exception
	{
		kg = KeyGenerator.getInstance(ALGO);
		kg.init(LENGTH);
	}
	
	public Object g() throws Exception
	{return kg.generateKey();}
}
