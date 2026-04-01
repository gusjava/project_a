package a.entity.gus06.moduledev.refactor.movepos;

import a.framework.*;
import java.io.File;
import java.util.List;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20140926";}


	private Service buildListing;
	private File dir;


	public EntityImpl() throws Exception
	{
		buildListing = Outside.service(this,"gus06.dir.listing.dirtofiles");
		dir = (File) Outside.resource(this,"path#path.dev.managerdir2");
	}
	
	
	public void p(Object obj) throws Exception
	{
		String rule = (String) obj;
		String[] n = rule.split(" ");
		
		int pos = int_(n[0]);
		int move = int_(n[1]);
		
		List listing = (List) buildListing.t(dir);
		for(Object f:listing) handleFile((File)f,pos,move);
	}
	
	
	private int int_(String s)
	{return Integer.parseInt(s);}
	
	
	
	private void handleFile(File file, int pos, int move)
	{
		try
		{
			// A voir ....
		}
		catch(Exception e)
		{Outside.err(this,"handleFile(File,int,int)",e);}
	}
	
	
}
