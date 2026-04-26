package a.entity.gus06.dir.runtask.fromclipboard.move.all.files;

import a.framework.*;
import java.io.File;
import java.util.Set;
import java.util.List;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20220403";}


	private Service fromClipboard;
	private Service buildListing;
	private Service move;
	
	public EntityImpl() throws Exception
	{
		fromClipboard = Outside.service(this,"gus.y.clipboard1.files");
		buildListing = Outside.service(this,"gus06.dir.listing.dir1tofiles");
		move = Outside.service(this,"gus06.file.op.move.autorename");
	}
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File dir = (File) o[0];
		Object progress = o[1];
		Set interrupt = (Set) o[2];
		
		List listing = (List) buildListing.t(fromClipboard.g());
		if(progress!=null) ((V)progress).v("size",""+listing.size());
		
		for(int i=0;i<listing.size();i++)
		{
			File input = (File) listing.get(i);
			File output = new File(dir, input.getName());
			move.p(new File[]{input, output});
			if(progress!=null) ((E)progress).e();
		}
	}
}