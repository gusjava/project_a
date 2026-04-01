package a.entity.gus06.file.image.perform.convert.totiff.self1;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, P, T {

	public String creationDate() {return "20180307";}


	private Service perform;

	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.file.image.perform.convert.totiff");
	}
	
	public void p(Object obj) throws Exception
	{t(obj);}
	
	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		File file1 = new File(file.getAbsolutePath()+".tiff");
		
		perform.p(new File[]{file,file1});
		return file1;
	}
}
