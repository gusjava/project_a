package a.entity.gus06.file.info.ebook.description;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191104";}


	private Service handleMobi;
	private Service handleEpub;
	private Service getExt;


	public EntityImpl() throws Exception
	{
		handleMobi = Outside.service(this,"gus06.file.mobi.properties.description");
		handleEpub = Outside.service(this,"gus06.file.epub.properties.description");
		getExt = Outside.service(this,"gus06.file.getextension.lowercase");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		String ext = (String) getExt.t(file);
		
		T t = find(ext);
		return t!=null ? t.t(file) : null;
	}
	
	
	private T find(String ext) throws Exception
	{
		if(ext.equals("mobi")) return handleMobi;
		if(ext.equals("epub")) return handleEpub;
		return null;
	}
}
