package a.entity.gus06.file.string.reader.providelines.utf8;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import a.framework.*;
import java.io.InputStream;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160913";}
	
	public static final String CHARSET = "UTF-8";

	


	public Object t(Object obj) throws Exception
	{
		InputStream is = toInputStream(obj);
		InputStreamReader isr = new InputStreamReader(is, CHARSET);
		BufferedReader br = new BufferedReader(isr);
		
		return new Holder(br);
	}
	
	
	private InputStream toInputStream(Object obj) throws Exception
	{
		if(obj instanceof InputStream) return (InputStream) obj;
		if(obj instanceof File) return new FileInputStream((File) obj);
		throw new Exception("Invalid data type: "+obj.getClass().getName());
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
