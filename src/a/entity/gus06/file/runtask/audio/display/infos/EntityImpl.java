package a.entity.gus06.file.runtask.audio.display.infos;

import a.framework.*;
import java.io.File;
import java.util.Set;
import java.util.Map;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20191202";}
	
	public static final String TITLE = "Audio info";


	private Service getInfos;
	private Service showData;

	public EntityImpl() throws Exception
	{
		getInfos = Outside.service(this,"gus06.file.audio.dsj.infomap");
		showData = Outside.service(this,"gus06.swing.frame.show.data");
	}

	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File file = (File) o[0];
		Object progress = o[1];
		Set interrupt = (Set) o[2];
		
		if(progress!=null) ((V)progress).v("size","1");
		
		Map map = (Map) getInfos.t(file);
		
		if(progress!=null) ((E)progress).e();
		
		showData.v(TITLE,map);
	}
}
