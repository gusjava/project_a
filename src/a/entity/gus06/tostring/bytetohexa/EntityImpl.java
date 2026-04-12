package a.entity.gus06.tostring.bytetohexa;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140704";}

	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		if(obj instanceof byte[])
		return convertFromByteArray((byte[])obj);
		throw new Exception("Invalid data type: "+obj);
	}
	
	private String convertFromByteArray(byte[] array) throws Exception
	{
		StringBuilder b = new StringBuilder(array.length*2);
		for(int i=0;i<array.length;i++)
		{
			b.append(bitsToChar(array[i] >> 4));
			b.append(bitsToChar(array[i] & 0x0000000F));
		}
		return b.toString();
	}
	
	private char bitsToChar(int bits) throws Exception
	{
		int j = bits & 0x0000000F;
		switch (j)
		{
			case 0: return '0';
			case 1: return '1';
			case 2: return '2';
			case 3: return '3';
			case 4: return '4';
			case 5: return '5';
			case 6: return '6';
			case 7: return '7';
			case 8: return '8';
			case 9: return '9';
			case 10: return 'A';
			case 11: return 'B';
			case 12: return 'C';
			case 13: return 'D';
			case 14: return 'E';
			case 15: return 'F';
			default: throw new Exception(j+" is not a valid value for hexadecimal digit");
		}
	}
}
