package a.entity.gus06.tostring.bytetobinary;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160303";}


	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof byte[])
		return convertFromByteArray((byte[])obj);
		throw new Exception("Invalid data type: "+obj);
	}
	
	
	
	private String convertFromByteArray(byte[] array) throws Exception
	{
		StringBuffer b = new StringBuffer(array.length*8);
		for(int i=0;i<array.length;i++)
		{b.append(toBinaryString(array[i]));}
		return b.toString();
	}
    
    
	public static String toBinaryString(byte n)
	{
		StringBuilder sb = new StringBuilder("00000000");
		for(int bit=0; bit<8; bit++)
		{
			if(((n >> bit) & 1) > 0)
			sb.setCharAt(7-bit, '1');
		}
		return sb.toString();
	}
}
