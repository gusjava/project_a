package a.entity.gus06.sys.parser3.resolver1.op.seq.sum.motley.longarray.tolongarray;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180112";}


	
	public Object t(Object obj) throws Exception
	{
		Object[] oo = (Object[]) obj;
		
		long[] array = (long[]) oo[0];
		int len = array.length;
		
		long[] output = new long[len];
		for(int i=0;i<len;i++) output[i] = array[i];
		
		for(int i=1;i<oo.length;i++) add(len,output,oo[i]);
		return output;
	}
	
	
	
	
	
	private void add(int len, long[] output, Object o) throws Exception
	{
		if(o==null) throw new Exception("Invalid null value");
		
		if(o instanceof Long)
		{
			long n = ((Long) o).longValue();
			for(int i=0;i<len;i++) output[i] += n;
			return;
		}
		
		if(o instanceof long[])
		{
			long[] nn = (long[]) o;
			if(nn.length!=len)
				throw new Exception("Invalid array lengths for sum: "+nn.length+" & "+len);
			for(int i=0;i<len;i++) output[i] += nn[i];
			return;
		}
		
		throw new Exception("Invalid data type: "+o.getClass().getName());
	}
}
