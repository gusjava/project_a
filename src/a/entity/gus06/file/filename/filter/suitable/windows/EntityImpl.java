package a.entity.gus06.file.filename.filter.suitable.windows;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20191211";}


	/*
	 * caract�res interdits dans les noms de fichier sous Windows :
	 *  \ / : * ? " < > | \n \t \r
	 */
	
	public static final char[] INVALID = "\\/:*?\"<>|\n\t\r".toCharArray();
	
	
	public boolean f(Object obj) throws Exception
	{
		if(obj==null) return false;
		
		String name = toName(obj);
		for(int i=0;i<name.length();i++)
		if(isInvalid(name.charAt(i))) return false;
		
		return name.trim().equals(name);
	}
	
	private boolean isInvalid(char c)
	{
		for(char i : INVALID) if(c==i) return true;
		return false;
	}
	
	private String toName(Object obj) throws Exception
	{
		if(obj instanceof String) return (String) obj;
		if(obj instanceof File) return ((File) obj).getName();
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
