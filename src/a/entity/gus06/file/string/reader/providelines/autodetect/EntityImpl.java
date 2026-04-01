package a.entity.gus06.file.string.reader.providelines.autodetect;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import a.framework.*;
import java.nio.charset.Charset;
import java.io.InputStream;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160913";}


	private Service findCharset;
	
	public EntityImpl() throws Exception
	{
		findCharset = Outside.service(this,"gus06.file.string.info.charset");
	}	


	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		
		FileInputStream fis = new FileInputStream(file);
		InputStreamReader isr = new InputStreamReader(fis,charset(file));
		BufferedReader br = new BufferedReader(isr);
		
		return new Holder(br);
	}
	
	
	private Charset charset(File file) throws Exception
	{
		Charset charset = (Charset) findCharset.t(file);
		return charset!=null?charset:Charset.defaultCharset();
	}
	
	
	
	private class Holder implements G
	{
		private BufferedReader br;
		private boolean over = false;
		
		public Holder(BufferedReader br)
		{this.br = br;}
		
		public Object g() throws Exception
		{
			if(over) return null;
			String line = null;
			
			try
			{
				line = br.readLine();
			}
			finally
			{
				if(line==null)
				{
					br.close();
					over = true;
				}
			}
			return line;
		}
	}
}