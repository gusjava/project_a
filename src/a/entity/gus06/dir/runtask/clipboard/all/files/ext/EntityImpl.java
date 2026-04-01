package a.entity.gus06.dir.runtask.clipboard.all.files.ext;

import a.framework.*;
import java.io.File;
import java.util.Set;
import java.util.List;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20220401";}

	public static final String MESSAGE = "Please, enter extensions:";


	private Service inputDialog;
	private Service buildListing;
	private Service toClipboard;
	
	public EntityImpl() throws Exception
	{
		inputDialog = Outside.service(this,"gus06.input.text.dialog");
		buildListing = Outside.service(this,"gus06.dir.listing.dirtofiles.forext2");
		toClipboard = Outside.service(this,"gus06.clipboard.access.listfiles");
	}
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File dir = (File) o[0];
		Object progress = o[1];
		Set interrupt = (Set) o[2];
		
		if(progress!=null) ((V)progress).v("size","1");
		String ext = (String) inputDialog.t(MESSAGE);
		if(ext!=null)
		{
			List listing = (List) buildListing.t(new Object[]{dir, ext});
			toClipboard.p(listing);
		}
		if(progress!=null) ((E)progress).e();
	}
}