package a.entity.gus06.sys.git1.create;

import a.framework.*;
import java.io.File;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;

public class EntityImpl implements Entity, P, T {

	public String creationDate() {return "20201128";}

	
	public void p(Object obj) throws Exception
	{t(obj);}
	
	
	public Object t(Object obj) throws Exception
	{
		File dir = (File) obj;
		if(dir==null) return null;
		
		Repository repo = FileRepositoryBuilder.create(new File(dir, ".git"));
		repo.create();
		return repo;
	}
}