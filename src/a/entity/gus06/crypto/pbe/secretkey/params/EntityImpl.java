package a.entity.gus06.crypto.pbe.secretkey.params;

import java.security.AlgorithmParameters;
import javax.crypto.spec.PBEParameterSpec;
import a.framework.*;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20150613";}

	public static final String ALGO = "PBEWithMD5AndDES";
	
	private static final byte[] SALT  = new byte[] {0,0,0,0,0,0,0,0};
	private static final int ITERATION = 3;
	
	private AlgorithmParameters params;
	
	public EntityImpl() throws Exception
	{
		params = AlgorithmParameters.getInstance(ALGO);
		params.init(new PBEParameterSpec(SALT,ITERATION));
	}

	public Object g() throws Exception
	{return params;}
}