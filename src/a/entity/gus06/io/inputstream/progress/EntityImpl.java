package a.entity.gus06.io.inputstream.progress;

import a.framework.*;
import java.io.InputStream;
import java.io.IOException;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20220611";}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=4) throw new Exception("Wrong data number: "+o.length);
		
		InputStream is = (InputStream) o[0];
		P p = (P) o[1];
		long total = (long) (Long) o[2];
		long lapse = (long) (Long) o[3];
		
		return new ProgressInputStream(is, p, total, lapse);
	}
	
	
	public class ProgressInputStream extends InputStream
	{
		private InputStream is;
		private P p;
		private long total;
		private long lapse;
		private long current;
		private long t;
		
		public ProgressInputStream(InputStream is, P p, long total, long lapse)
		{
			super();
			this.is = is;
			this.p = p;
			this.total = total;
			this.lapse = lapse;
			
			current = 0;
			t = System.currentTimeMillis();
		}
		
		public int read() throws IOException
		{
			int b = is.read();
			if(b!=-1) updateProgress(1);
			return b;
		}
		
		public int read(byte[] b) throws IOException
		{
			int nb = is.read(b);
			if(nb > 0) updateProgress(nb);
			return nb;
		}
		
		public int read(byte[] b, int off, int len) throws IOException
		{
			int nb = is.read(b, off, len);
			if(nb > 0) updateProgress(nb);
			return nb;
		}
		
		private void updateProgress(long nb)
		{
			current += nb;
			long t1 = System.currentTimeMillis();
			if (t1 - t > lapse)
			{
				double value = (double) current / (double) total;
				setValue(p, value);
				t = t1;
			}
		}
		
		public void close() throws IOException
		{
			is.close();
			setValue(p, 1);
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