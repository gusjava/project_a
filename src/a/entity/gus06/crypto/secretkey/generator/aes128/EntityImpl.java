package a.entity.gus06.crypto.secretkey.generator.aes128;

import a.framework.*;
import java.security.KeyPairGenerator;
import javax.crypto.KeyGenerator;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20160115";}

	public static final String ALGO = "AES"; //Advanced Encryption Standard
	public static final int LENGTH = 128;
	

	private KeyGenerator kg;
	
	public EntityImpl() throws Exception
	{
		kg = KeyGenerator.getInstance(ALGO);
		kg.init(LENGTH);
	}
	
	
	public Object g() throws Exception
	{return kg.generateKey();}
}
