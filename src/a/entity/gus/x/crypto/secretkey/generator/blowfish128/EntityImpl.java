package a.entity.gus.x.crypto.secretkey.generator.blowfish128;

import a.framework.*;
import java.security.KeyPairGenerator;
import javax.crypto.KeyGenerator;

public class EntityImpl implements Entity, G {
	public String creationDate() {return "20160302";}

	public static final String ALGO = "Blowfish"; //Blowfish
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
