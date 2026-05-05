package a.entity.gus06.file.runtask.comparefile.toclipboard;

import a.framework.*;
import java.io.File;
import java.util.Set;
import javax.swing.JOptionPane;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20200427";}
	
	public static final String TITLE = "Comparison's result";


	private Service clipboard;
	private Service toMd5;

	public EntityImpl() throws Exception
	{
		clipboard = Outside.service(this,"gus06.clipboard.access.file");
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
	
	
	private String buildMessage(File file1) throws Exception
	{
		if(file1==null) return "Target file is null";
		if(!file1.exists()) return "Target file does not exist";
		if(file1.isDirectory()) return "Target file is a directory";
		
		File file2 = (File) clipboard.g();
		if(file2==null) return "File not found in clipboard";
		if(!file2.exists()) return "Clipboard file does not exist";
		if(file2.isDirectory()) return "Clipboard file is a directory";
		
		long size1 = file1.length();
		long size2 = file2.length();
		
		if(size1!=size2) return "Different sizes detected: target="+size1+" clipboard="+size2;
		
		String md1 = (String) toMd5.t(file1);
		String md2 = (String) toMd5.t(file2);
		
		if(!md1.equals(md2)) return "Different MD5 detected: target="+md1+" clipboard="+md2;
		
		return "Clipboard and target files are identicals (md5="+md1+")";
	}
}
