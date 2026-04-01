package a.entity.gus06.sys.javaprojectviewer1.root0.find.roots;

import a.framework.*;
import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170220";}


	private Service findFiles;
	private Service extract;


	public EntityImpl() throws Exception
	{
		findFiles = Outside.service(this,"gus06.dir.listing.dirtofiles.forext.java");
		extract = Outside.service(this,"gus06.java.srcfile.extract.root.checked");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		File root0 = (File) obj;
		List files = (List) findFiles.t(root0);
		
		Set set = new HashSet();
		for(int i=0;i<files.size();i++)
		{
			File file = (File) files.get(i);
			if(isValid(file)) handleFile(set,file);
		}
		return set;
	}
	
	
	
	private void handleFile(Set set, File file)
	{
		try
		{
			File root = (File) extract.t(file);
			set.add(root);
		}
		catch(Exception e)
		{
			Outside.err(this,"handleFile(Set,File)",e);
		}
	}
	
	
	private boolean isValid(File file)
	{
		String name = file.getName();
		return !name.equals("package-info.java");
	}
}
