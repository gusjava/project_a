package a.entity.gus06.file.string.info.linenumber;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150822";}


	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		
		FileInputStream fis = new FileInputStream(file);
		InputStreamReader isr = new InputStreamReader(fis);
		BufferedReader br = new BufferedReader(isr);
		
		int number = 0;
		while((br.readLine())!=null) number++;
		fis.close();
		
		return ""+number;
	}
}