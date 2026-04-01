package a.entity.gus06.file.runtask.report.infos1;

import a.framework.*;
import java.io.File;
import java.util.Set;
import java.util.Map;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150811";}


	private Service writeProp;
	private Service findInfos;

	public EntityImpl() throws Exception
	{
		writeProp = Outside.service(this,"gus06.file.write.properties");
		findInfos = Outside.service(this,"gus06.file.extract.infomap1");
	}


	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File file = (File) o[0];
		Object progress = o[1];
		Set interrupt = (Set) o[2];
		
		if(progress!=null) ((V)progress).v("size","1");
		
		File infosFile = new File(file.getAbsolutePath()+"_infos.properties");
		Map infosMap = (Map) findInfos.t(file);
		writeProp.p(new Object[]{infosFile,infosMap});
		
		if(progress!=null) ((E)progress).e();
	}
}
