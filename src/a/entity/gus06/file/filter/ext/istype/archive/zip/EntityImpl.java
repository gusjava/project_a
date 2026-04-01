package a.entity.gus06.file.filter.ext.istype.archive.zip;

import java.io.File;
import java.io.FileFilter;
import a.framework.*;

public class EntityImpl implements Entity, F, G, FileFilter {

	public String creationDate() {return "20150628";}


	/*
	 * Le format JAR (Java Archive), 
	 * l'ODT (OpenDocument) et 
	 * l'Open XML (OOXML) sont bas�s sur le format ZIP.
	 */
	public boolean accept(File f)
	{
		if(f==null) return false;
		if(!f.isFile()) return false;
		
		String n = f.getName().toLowerCase();
		return n.endsWith(".zip") || 
				n.endsWith(".zip_gus") || //Java Archive 
				n.endsWith(".jar") || //Java Archive 
				n.endsWith(".cbz") || //Comic Book Zip
				n.endsWith(".odt") || //Open Document
				n.endsWith(".ooxml"); //Open XML
	}
	
	
	public boolean f(Object obj) throws Exception
	{return accept((File)obj);}
	
	
	public Object g() throws Exception
	{return this;}
}