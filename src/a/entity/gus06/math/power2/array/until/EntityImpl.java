package a.entity.gus06.math.power2.array.until;

import a.framework.*;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20231107";}

	public static final long[] POWERS = new long[]{
		1,2,4,8,
		16,32,64,128,
		256,512,1024,2048,
		4096,8192,16384,32768,
		65536,131072,262144,524288,
		1048576,2097152,4194304,8388608,
		16777216,33554432,67108864,134217728,
		268435456,536870912,1073741824,2147483648L,
		4294967296L, 8589934592L, 17179869184L, 34359738368L,
		68719476736L, 137438953472L, 274877906944L, 549755813888L};  
		
		
		//continuer jusq'au bout des longs

	
	public Object g() throws Exception
	{return POWERS;}
}