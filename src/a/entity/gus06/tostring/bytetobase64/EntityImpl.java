package a.entity.gus06.tostring.bytetobase64;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141010";}
	
	private static char[] C = 
	"ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof byte[])
			return new String(encode((byte[]) obj));
		throw new Exception("Invalid data type: "+obj);
	}
	
	
	
	private char[] encode(byte[] in)
	{
		int iLen = in.length;
		int oDataLen = (iLen*4+2)/3;
		int oLen = ((iLen+2)/3)*4;
		char[] out = new char[oLen];
		
		int ip = 0;
		int op = 0;
		
		while(ip < iLen)
		{
			int i0 = in[ip++] & 0xff;
			int i1 = ip < iLen ? in[ip++] & 0xff : 0;
			int i2 = ip < iLen ? in[ip++] & 0xff : 0;
			int o0 = i0 >>> 2;
			int o1 = ((i0 &	3) << 4) | (i1 >>> 4);
			int o2 = ((i1 & 0xf) << 2) | (i2 >>> 6);
			int o3 = i2 & 0x3F;
			
			out[op++] = C[o0];
			out[op++] = C[o1];
			
			out[op] = op < oDataLen ? C[o2] : '=';
			op++;
			out[op] = op < oDataLen ? C[o3] : '=';
			op++;
		}
		return out;
	}
}
