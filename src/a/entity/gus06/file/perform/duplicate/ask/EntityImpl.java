package a.entity.gus06.file.perform.duplicate.ask;

import a.framework.*;
import java.io.File;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, P, F, T {

	public String creationDate() {return "20140907";}

	private Service input;
	private Service format;
	private Service getExtension;
	private Service copy;
	private Service refactorJava;

	public EntityImpl() throws Exception
	{
		input = Outside.service(this,"gus06.input.text.dialog");
		format = Outside.service(this,"gus06.string.transform.format.pathinput1");
		getExtension = Outside.service(this,"gus06.file.getextension");
		copy = Outside.service(this,"gus.x.file.op.copy");
		refactorJava = Outside.service(this,"gus06.java.srccode.adapttofile.changename");
	}
	
	public void p(Object obj) throws Exception
	{t(obj);}
	
	public boolean f(Object obj) throws Exception
	{
		List result = handleFile((File) obj);
		return result!=null && !result.isEmpty();
	}
	
	public Object t(Object obj) throws Exception
	{return handleFile((File) obj);}
	
	
	private List handleFile(File file) throws Exception
	{
		String oldName = file.getName();
		
		String newName = (String) input.t(new String[]{"Enter new name:",oldName});
		if(newName==null || newName.equals("")) return null;
		
		List result = new ArrayList();
		String[] n = newName.split(";");
		for(int i=0;i<n.length;i++)
		if(!n[i].trim().equals(""))
		{
			File f = duplicate(file,n[i]);
			if(f!=null) result.add(f);
		}
		return result;
	}
	
	private File duplicate(File file, String newName) throws Exception
	{
		newName = format(newName);
		
		String oldExt = (String) getExtension.t(file);
		if(!oldExt.equals("") && newName.endsWith(" "))
		newName = newName.substring(0,newName.length()-1)+"."+oldExt;
		
		if(newName.equals(file.getName())) return null;
		
		File file1 = new File(file.getParentFile(),newName);
		file1.getParentFile().mkdirs();
		
		copy.p(new File[]{file,file1});
		if(file1.getName().endsWith(".java")) refactorJava.p(file1);
		return file1;
	}
	
	private String format(String s) throws Exception
	{return (String) format.t(s);}
}
