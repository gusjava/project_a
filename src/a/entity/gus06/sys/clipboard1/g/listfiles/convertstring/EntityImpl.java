package a.entity.gus06.sys.clipboard1.g.listfiles.convertstring;

import a.framework.*;
import java.util.ArrayList;
import java.util.List;
import java.io.File;

public class EntityImpl implements Entity, T, P {

	public String creationDate() {return "20151006";}


	private Service buildFile;
	private Service writeFile;


	public EntityImpl() throws Exception
	{
		buildFile = Outside.service(this,"gus06.sys.clipboard1.g.listfiles.buildfile");
		writeFile = Outside.service(this,"gus06.file.write.string.autodetect");
	}

	
	public Object t(Object obj) throws Exception
	{
		File file = (File) buildFile.t("txt");
		writeFile.p(new Object[]{file,obj});
		return toList(file);
	}
	
	
	public void p(Object obj) throws Exception
	{t(obj);}
	
	
	private List toList(File file)
	{
		List list = new ArrayList();
		list.add(file);
		return list;
	}
}
