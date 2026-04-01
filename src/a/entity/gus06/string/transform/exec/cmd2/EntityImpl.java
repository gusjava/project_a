package a.entity.gus06.string.transform.exec.cmd2;

import a.framework.*;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150323";}
	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		String[] n = s.split("\n");
		
		String command = n[0];
		String dirpath = n.length==2?n[1]:null;
		
		Process p = buildProcess(command,dirpath);
		
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
	
	
	private Process buildProcess(String command, String dirpath) throws Exception
	{
		if(dirpath!=null && !dirpath.equals(""))
		{
			File dir = new File(dirpath);
			if(!dir.isDirectory()) throw new Exception("Directory not found: "+dir);
			return Runtime.getRuntime().exec(command,null,dir);
		}
		return Runtime.getRuntime().exec(command);
	}
	
	
	private String readLine(BufferedReader br)
	{
		try{return br.readLine();}
		catch(IOException e)
		{return null;}
	}
}
