package a.entity.gus06.string.transform.exec.cmd;

import a.framework.*;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150323";}
	
	
	public Object t(Object obj) throws Exception
	{
		String command = (String) obj;
		Process p = Runtime.getRuntime().exec(command);
		
		InputStream is = p.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		
		StringBuffer b = new StringBuffer();
		String line = null;
		while((line = readLine(br)) != null)
			b.append(line+"\n");
		br.close();
		
		return b.toString();
	}
	
	
	private String readLine(BufferedReader br)
	{
		try{return br.readLine();}
		catch(IOException e)
		{return null;}
	}
}
