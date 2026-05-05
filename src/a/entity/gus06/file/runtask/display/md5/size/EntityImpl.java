package a.entity.gus06.file.runtask.display.md5.size;

import a.framework.*;
import java.io.File;
import java.util.Set;
import javax.swing.JOptionPane;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20200427";}
	
	public static final String TITLE = "File's information";


	private Service toMd5;

	public EntityImpl() throws Exception
	{
		toMd5 = Outside.service(this,"gus.y.crypto1.hash.md5.hexa");
	}

	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File file = (File) o[0];
		Object progress = o[1];
		Set interrupt = (Set) o[2];
		
		if(progress!=null) ((V)progress).v("size","1");
		perform(file);
		if(progress!=null) ((E)progress).e();
	}
	
	
	private void perform(File file)
	{
		try
		{
			String message = buildMessage(file);
			JOptionPane.showMessageDialog(null,message,TITLE,JOptionPane.INFORMATION_MESSAGE);
		}
		catch(Exception e)
		{Outside.err(this,"perform(File)",e);}
	}
	
	
	private String buildMessage(File file) throws Exception
	{
		if(file==null) return "Target file is null";
		if(!file.exists()) return "Target file does not exist";
		if(file.isDirectory()) return "Target file is a directory";
		
		long size = file.length();
		String md5 = (String) toMd5.t(file);
		
		return "MD5="+md5+"\nSize="+size;
	}
}
