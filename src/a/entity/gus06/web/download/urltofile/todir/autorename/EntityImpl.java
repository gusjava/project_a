package a.entity.gus06.web.download.urltofile.todir.autorename;

import a.framework.*;
import java.io.File;
import java.net.URL;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20250503";}


	private Service moveOp;
	private Service urlToFile;


	public EntityImpl() throws Exception
	{
		moveOp = Outside.service(this,"gus06.dirfile.op.move.autorename");
		urlToFile = Outside.service(this,"gus06.web.download.urltofile");
	}

	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		URL url = (URL) o[0];
		File dir = (File) o[1];
		
		File in = (File) urlToFile.t(url);
		File out = new File(dir,in.getName());
		moveOp.p(new File[]{in,out});
	}
}
