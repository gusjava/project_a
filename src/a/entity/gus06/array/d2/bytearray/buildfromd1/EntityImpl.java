package a.entity.gus06.array.d2.bytearray.buildfromd1;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180331";}


	private Service findNb2;
	private Service findByte;
	
	public EntityImpl() throws Exception
	{
		findNb2 = Outside.service(this,"gus06.array.d2.objectarray.buildfromd1.findn2");
		findByte = Outside.service(this,"gus06.find.byte1");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] array = (Object[]) obj;
		
		int nb1 = array.length;
		if(nb1==0) return new int[0][0];
		
		int nb2 = ((Integer) findNb2.t(array)).intValue();
		byte[][] table = new byte[nb1][nb2];
		
		for(int i=0;i<nb1;i++)
		for(int j=0;j<nb2;j++)
		table[i][j] = findAt(array[i],j);
		
		return table;
	}
	
	
	private byte findAt(Object row, int pos) throws Exception
	{
		if(row instanceof List)
		{
			List l = (List) row;
			return pos<l.size() ? toByte(l.get(pos)) : (byte) 0;
		}
		if(row instanceof byte[])
		{
			byte[] l = (byte[]) row;
			return pos<l.length ? l[pos] : (byte) 0;
		}
		throw new Exception("Invalid row data type: "+row.getClass().getName());
	}
	
	
	private Byte toByte(Object obj) throws Exception
	{return (Byte) findByte.t(obj);}
}
