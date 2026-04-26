package a.entity.gus06.dir.runtask.clipboard.all.files;

import a.framework.*;
import java.io.File;
import java.util.Set;
import java.util.List;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20220401";}


	private Service buildListing;
	private Service toClipboard;
	
	public EntityImpl() throws Exception
	{
		buildListing = Outside.service(this,"gus06.dir.listing.dirtofiles");
		toClipboard = Outside.service(this,"gus.y.clipboard1.files");
	}
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File dir = (File) o[0];
		Object progress = o[1];
		Set interrupt = (Set) o[2];
		
		if(progress!=null) ((V)progress).v("size","1");
		List listing = (List) buildListing.t(dir);
		toClipboard.p(listing);
		if(progress!=null) ((V)progress).v("size","1");
	}
}