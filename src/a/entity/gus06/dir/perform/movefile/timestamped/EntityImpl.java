package a.entity.gus06.dir.perform.movefile.timestamped;

import a.framework.*;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20250322";}

	public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");


	private Service dirfileMove;

	public EntityImpl() throws Exception
	{
		dirfileMove = Outside.service(this,"gus06.dirfile.op.move");
	}
	
	
	public void p(Object obj) throws Exception
	{
		File[] o = (File[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File file = o[0];
		File dir = o[1];
		
		File file1 = new File(dir,now()+"_"+file.getName());
		dirfileMove.p(new File[]{file,file1});
	}
	
	private String now() throws Exception
	{return sdf.format(new Date());}
}