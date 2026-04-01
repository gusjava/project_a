package a.entity.gus06.file.string.info.wordnumber;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150823";}


	private Service split;
	
	
	public EntityImpl() throws Exception
	{
		split = Outside.service(this,"gus06.string.split.words1");
	}



	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		
		FileInputStream fis = new FileInputStream(file);
		InputStreamReader isr = new InputStreamReader(fis);
		BufferedReader br = new BufferedReader(isr);
		
		int number = 0;
		String line = null;
		
		while((line = br.readLine())!=null) number += nbWords(line);
		fis.close();
		
		return ""+number;
	}
	
	
	
	private int nbWords(String line) throws Exception
	{
		String[] n = (String[]) split.t(line);
		return n.length;
	}
}
