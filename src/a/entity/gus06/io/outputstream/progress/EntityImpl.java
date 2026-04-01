package a.entity.gus06.io.outputstream.progress;

import a.framework.*;
import java.io.OutputStream;
import java.io.IOException;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20220611";}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=4) throw new Exception("Wrong data number: "+o.length);
		
		OutputStream os = (OutputStream) o[0];
		P p = (P) o[1];
		long total = (long) (Long) o[2];
		long lapse = (long) (Long) o[3];
		
		return new ProgressOutputStream(os, p, total, lapse);
	}
	
	
	public class ProgressOutputStream extends OutputStream
	{
		private OutputStream os;
		private P p;
		private long total;
		private long lapse;
		private long current;
		private long t;
		
		public ProgressOutputStream(OutputStream os, P p, long total, long lapse)
		{
			super();
			this.os = os;
			this.p = p;
			this.total = total;
			this.lapse = lapse;
			
			current = 0;
			t = System.currentTimeMillis();
		}
		
		public void write(int b) throws IOException
		{
			os.write(b);
			current++;
			
			long t1 = System.currentTimeMillis();
			if(t1-t>lapse)
			{
				double value = (double) current/ (double) total;
				setValue(p,value);
				t = t1;
			}
		}
		
		public void close() throws IOException
		{
			super.close();
			os.close();
		}
	}
	
	
	
	private void setValue(P p, double value)
	{
		try
		{
			p.p(value);
		}
		catch(Exception e)
		{Outside.err(this,"setValue(P,double)",e);}
	}
}