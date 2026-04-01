package a.entity.gus06.convert.stringtobytearray.hexa;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141014";}

	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		return convert((String) obj);
	}

	private byte[] convert(String hexa) throws Exception
	{
		hexa = hexa.replace(".","").toUpperCase();
		
		int n = hexa.length()/2;
		byte[] b = new byte[n];
		
		for(int i=0;i<n;i++)
		{
			int n1 = charToBit(hexa.charAt(i*2));
			int n2 = charToBit(hexa.charAt(i*2+1));
			b[i] = (byte) ((n1 << 4) | n2);
		}
		return b;
	}


	private int charToBit(char c) throws Exception
	{
		switch (c)
		{
			case '0': return 0;
			case '1': return 1;
			case '2': return 2;
			case '3': return 3;
			case '4': return 4;
			case '5': return 5;
			case '6': return 6;
			case '7': return 7;
			case '8': return 8;
			case '9': return 9;
			case 'A': return 10;
			case 'B': return 11;
			case 'C': return 12;
			case 'D': return 13;
			case 'E': return 14;
			case 'F': return 15;
			default: throw new Exception(c+" is not a valid value for hexadecimal char");
		}
	}
}
