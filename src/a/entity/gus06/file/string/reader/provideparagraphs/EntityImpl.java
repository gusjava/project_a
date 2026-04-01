package a.entity.gus06.file.string.reader.provideparagraphs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190711";}


	


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
			StringBuffer b = new StringBuffer();
			
			try
			{
				line = br.readLine();
				while(line!=null && isBlank(line))
				{
					line = br.readLine();
				}
				while(line!=null && !isBlank(line))
				{
					b.append(line+"\n");
					line = br.readLine();
				}
			}
			finally
			{
				if(line==null)
				{
					br.close();
					over = true;
				}
			}
			
			if(b.length()>0) b.deleteCharAt(b.length()-1);
			return b.toString();
		}
	}
	
	
	
	private boolean isBlank(String line)
	{return line.trim().equals("");}
}
