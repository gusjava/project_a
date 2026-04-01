package a.entity.gus06.io.inputstream.build.withprogress;

import a.framework.*;
import java.io.File;
import java.io.InputStream;
import java.util.Map;
import java.net.Socket;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20220616";}


	private Service convertFile;
	private Service perform;

	public EntityImpl() throws Exception
	{
		convertFile = Outside.service(this,"gus06.convert.filetoinputstream");
		perform = Outside.service(this,"gus06.io.inputstream.progress");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length==2) return build(o[0],o[1]);
		if(o.length==3) return build(o[0],o[1],o[2]);
		
		throw new Exception("Wrong data number: "+o.length);
	}
	
	
	private Object build(Object input, Object progress) throws Exception
	{
		if(input instanceof File)
		{
			File file = (File) input;
			long length = file.length();
			
			InputStream is = (InputStream) convertFile.t(file);
			ProgressP progressP = new ProgressP(progress, length);
			
			P p = progressP;
			long total = progressP.getTotal();
			long lapse = progressP.getLapse();
			
			return perform.t(new Object[]{is,p,total,lapse});
		}
		throw new Exception("Unsupported input type: "+input.getClass().getSimpleName());
	}
	
	
	private Object build(Object input, Object progress, Object size) throws Exception
	{
		if(input instanceof File)
		{
			File file = (File) input;
			long length = file.length();
			
			InputStream is = (InputStream) convertFile.t(file);
			ProgressP progressP = new ProgressP(progress, length);
			
			P p = progressP;
			long total = progressP.getTotal();
			long lapse = progressP.getLapse();
			
			return perform.t(new Object[]{is,p,total,lapse});
		}
		if(input instanceof Socket)
		{
			Socket socket = (Socket) input;
			long length = Long.parseLong(""+size);
			
			InputStream is = socket.getInputStream();
			ProgressP progressP = new ProgressP(progress, length);
			
			P p = progressP;
			long total = progressP.getTotal();
			long lapse = progressP.getLapse();
			
			return perform.t(new Object[]{is,p,total,lapse});
		}
		throw new Exception("Unsupported input type: "+input.getClass().getSimpleName());
	}
	
	
	
	private class ProgressP implements P
	{
		private long total;
		private long lapse;
		
		private Object progress;
		private int progressSize;
		
		
		public ProgressP(Object progress, long total) throws Exception
		{
			this.progress = progress;
			this.total = total;
			lapse = 100L;
			
			progressSize = 1000;
			((V)progress).v("size",progressSize);
		}
		
		public void p(Object obj) throws Exception
		{
			Double value = (Double) obj;
			if(value==null) return;
			
			int progressValue = (int) (value * progressSize);
			((V)progress).v("set",progressValue);
			((V)progress).v("tooltip","value="+value);
		}
		
		public long getTotal()
		{return total;}
		
		public long getLapse()
		{return lapse;}
	}
}