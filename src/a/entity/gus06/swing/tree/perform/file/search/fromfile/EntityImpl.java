package a.entity.gus06.swing.tree.perform.file.search.fromfile;

import a.framework.*;
import java.io.File;
import javax.swing.JOptionPane;
import java.util.Map;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20220207";}
	
	public static final String NAME = "NAME";
	public static final String NAME0 = "NAME0";
	public static final String EXT = "EXT";
	public static final String SIZE = "SIZE";
	public static final String MD5 = "MD5";


	private Service getMd5;
	private Service handle;

	public EntityImpl() throws Exception
	{
		getMd5 = Outside.service(this,"gus.y.crypto1.hash.md5.hexa");
		handle = Outside.service(this,"gus06.swing.tree.perform.file.search.handle");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		Map search = (Map) o[0];
		File root = (File) o[1];
		File file = (File) o[2];
		
		String type = getSearchType();
		if(type==null || type.equals("")) return;
		
		F filter = buildFilter(type,file);
		if(filter==null) return;
		
		handle.p(new Object[]{search, root, filter});
	}
	
	
	private String getSearchType()
	{
           	return JOptionPane.showInputDialog(null,"Please, type a search type (name,ext,size,md5):",
		"Filename search",JOptionPane.PLAIN_MESSAGE);
	}
	
	
	
	private F buildFilter(String type, File file)
	{
		type = type.toUpperCase();
		if(type.equals(NAME)) return new FilterName(file);
		if(type.equals(NAME0)) return new FilterName0(file);
		if(type.equals(EXT)) return new FilterExt(file);
		if(type.equals(SIZE)) return new FilterSize(file);
		if(type.equals(MD5)) return new FilterMd5(file);
		
		return null;
	}
	
	
	
	private class FilterName implements F
	{
		private String name;
		public FilterName(File file)
		{name = file.getName();}
		
		public boolean f(Object obj) throws Exception
		{
			File f = (File) obj;
			if(!f.isFile()) return false;
			return f.getName().equals(name);
		}
	}
	
	
	private class FilterName0 implements F
	{
		private String name0;
		public FilterName0(File file)
		{name0 = getName0(file);}
		
		public boolean f(Object obj) throws Exception
		{
			File f = (File) obj;
			if(!f.isFile()) return false;
			return getName0(f).equals(name0);
		}
	}
	
	
	private class FilterExt implements F
	{
		private String ext;
		public FilterExt(File file)
		{ext = getExt(file);}
		
		public boolean f(Object obj) throws Exception
		{
			File f = (File) obj;
			if(!f.isFile()) return false;
			if(ext.equals("")) return false;
			return f.getName().toLowerCase().endsWith("."+ext);
		}
	}
	
	
	private class FilterSize implements F
	{
		private long size;
		public FilterSize(File file)
		{size = file.length();}
		
		public boolean f(Object obj) throws Exception
		{
			File f = (File) obj;
			if(!f.isFile()) return false;
			return f.length()==size;
		}
	}
	
	
	private class FilterMd5 implements F
	{
		private File file;
		private long size;
		private String md5;
		
		public FilterMd5(File file)
		{
			this.file = file;
			size = file.length();
		}
		
		public boolean f(Object obj) throws Exception
		{
			File f = (File) obj;
			if(!f.isFile()) return false;
			if(f.length()!=size) return false;
			
			if(md5==null) md5 = getMd5(file);
			return getMd5(f).equals(md5);
		}
	}
	
	
	
	
	
	private String getExt(File file)
	{
		String name = file.getName();
		if(!name.contains(".")) return "";
		String[] n = name.split("\\.");
		return n[n.length-1].toLowerCase();
	}
	
	private String getName0(File file)
	{
		String name = file.getName();
		if(!name.contains(".")) return name;
		String[] n = name.split("\\.");
		String ext = n[n.length-1];
		return name.substring(0,name.length()-ext.length()-1);
	}
	
	private String getMd5(File file) throws Exception
	{return (String) getMd5.t(file);}
}