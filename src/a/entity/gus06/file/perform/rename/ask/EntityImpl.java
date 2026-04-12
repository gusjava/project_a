package a.entity.gus06.file.perform.rename.ask;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, P, F, T {

	public String creationDate() {return "20140907";}

	private Service input;
	private Service format;
	private Service getExtension;
	private Service refactorJava;

	public EntityImpl() throws Exception
	{
		input = Outside.service(this,"gus06.input.text.dialog");
		format = Outside.service(this,"gus06.string.transform.format.pathinput1");
		getExtension = Outside.service(this,"gus06.file.getextension");
		refactorJava = Outside.service(this,"gus06.java.srccode.adapttofile.changesrc");
	}
	
	public void p(Object obj) throws Exception
	{t(obj);}
	
	public boolean f(Object obj) throws Exception
	{return t(obj)!=null;}
	
	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		
		String oldName = file.getName();
		String oldExt = (String) getExtension.t(file);
		
		String newName = (String) input.t(new String[]{"Enter new name:",oldName});
		
		if(newName==null) return null;
		if(newName.equals("")) return null;
		
		newName = format(newName);
		if(!oldExt.equals("") && newName.endsWith(" "))
		newName = newName.substring(0,newName.length()-1)+"."+oldExt;
		
		if(newName.equals(oldName)) return null;
		
		File file1 = new File(file.getParentFile(),newName).getCanonicalFile();
		file1.getParentFile().mkdirs();
		
		rename(file,file1);
		
		if(file1.getName().endsWith(".java")) refactorJava.p(file1);
		
		return file1;
	}
	
	private void rename(File file0, File file1) throws Exception
	{
		boolean r = file0.renameTo(file1);
		if(!r) throw new Exception("Failed to rename file: "+file0+" to file "+file1);
	}
	
	private String format(String s) throws Exception
	{return (String) format.t(s);}
}
