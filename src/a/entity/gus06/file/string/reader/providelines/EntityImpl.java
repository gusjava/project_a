package a.entity.gus06.file.string.reader.providelines;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160913";}


	


	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		
		FileInputStream fis = new FileInputStream(file);
		InputStreamReader isr = new InputStreamReader(fis);
		BufferedReader br = new BufferedReader(isr);
		
		return new Holder(br);
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
