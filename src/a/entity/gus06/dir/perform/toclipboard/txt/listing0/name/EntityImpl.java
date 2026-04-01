package a.entity.gus06.dir.perform.toclipboard.txt.listing0.name;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20250424";}


	private Service toClipboard;
	private Service buildListing;


	public EntityImpl() throws Exception
	{
		toClipboard = Outside.service(this,"gus06.clipboard.access.string");
		buildListing = Outside.service(this,"gus06.dir.info.listing0.dirtonames");
	}
	
	public void p(Object obj) throws Exception
	{
		File dir = (File) obj;
		String listing = (String) buildListing.t(dir);
		toClipboard.p(listing);
	}
}