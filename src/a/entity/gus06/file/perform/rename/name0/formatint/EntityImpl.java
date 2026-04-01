package a.entity.gus06.file.perform.rename.name0.formatint;

import a.framework.*;
import java.io.File;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class EntityImpl implements Entity, P, T {

	public String creationDate() {return "20250211";}

	private Service name0ext;
	private Service autoRename;
	private Pattern p;
	
	public EntityImpl() throws Exception
	{
		name0ext = Outside.service(this,"gus06.file.getname0ext");
		autoRename = Outside.service(this,"gus06.file.newfile.autorename2");
		p = Pattern.compile("[0-9]+");
	}
	
	
	
	public void p(Object obj) throws Exception
	{t(obj);}
	
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File file = (File) o[0];
		int length = toInt(o[1]);
		
		String[] infos = (String[]) name0ext.t(file);
		String name0 = infos[0];
		String ext = infos[1];
		
		String newName0 = formatFirstInt(name0,length);
		if(newName0==null) return file;
		
		String newName = newName0+"."+ext;
		if(file.getName().equals(newName)) return file;
		
		File file1 = new File(file.getParentFile(),newName);
		file1 = (File) autoRename.t(file1);
		rename(file,file1);
		return file1;
	}
	
	
	
	private void rename(File file0, File file1) throws Exception
	{
		boolean r = file0.renameTo(file1);
		if(!r) throw new Exception("Failed to rename file: "+file0+" to file "+file1);
	}
	
	
	private int toInt(Object obj) throws Exception
	{return Integer.parseInt(""+obj);}
	
	
	private String formatFirstInt(String name, int length)
	{
		Matcher m = p.matcher(name);
		if(!m.find()) return null;
		String s = m.group();
		
		String repl = format(s, length);
		
		StringBuffer b = new StringBuffer();
		m.appendReplacement(b,repl);
		m.appendTail(b);
		return b.toString();
	}
	
	private String format(String s, int length)
	{
		if(length==0) return truncateZeros(s);
		while(s.length()<length) s = "0"+s;
		return s;
	}
	
	private String truncateZeros(String s)
	{
		while(s.startsWith("0") && s.length()>1) s = s.substring(1);
		return s;
	}
}