package a.entity.gus06.file.perform.duplicate.ask.keepext;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, P, F, T {

	public String creationDate() {return "20150615";}


	private Service input;
	private Service copy;
	private Service format;
	private Service getExtension;


	public EntityImpl() throws Exception
	{
		input = Outside.service(this,"gus06.input.text.dialog");
		copy = Outside.service(this,"gus06.file.op.copy");
		format = Outside.service(this,"gus06.string.transform.format.pathinput1");
		getExtension = Outside.service(this,"gus06.file.getextension");
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
		if(newName==null || newName.equals("")) return null;
		
		
		if(newName.equals(oldName)) return null;
		
		newName = format(newName);
		if(!oldExt.equals("") && !newName.contains("."))
		newName = newName+"."+oldExt;
			
		if(newName.equals(oldName)) return null;
		
		File file1 = new File(file.getParentFile(),newName);
		copy.p(new File[]{file,file1});
		return file1;
	}
	
	
	
	private String format(String s) throws Exception
	{return (String) format.t(s);}
}
