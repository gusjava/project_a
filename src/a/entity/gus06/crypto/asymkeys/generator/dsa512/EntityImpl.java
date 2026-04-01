package a.entity.gus06.crypto.asymkeys.generator.dsa512;

import a.framework.*;
import java.security.KeyPairGenerator;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20141014";}

	public static final String ALGO = "DSA"; //Digital Signature Algorithm
	public static final int LENGTH = 512;
	

	private KeyPairGenerator kpg;
	
	public EntityImpl() throws Exception
	{
		kpg = KeyPairGenerator.getInstance(ALGO);
		kpg.initialize(LENGTH);
	}
	
	
	public Object g() throws Exception
	{return kpg.genKeyPair();}
}
