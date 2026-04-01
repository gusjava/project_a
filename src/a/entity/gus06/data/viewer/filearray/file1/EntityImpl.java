package a.entity.gus06.data.viewer.filearray.file1;

import a.framework.*;
import java.io.File;


public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20221023";}


	private Service viewer;
    
	private File[] data;

	public EntityImpl() throws Exception
	{viewer = Outside.service(this,"*gus06.data.viewer.file");}
	
	public Object i() throws Exception
	{return viewer.i();}
	
	public void p(Object obj) throws Exception
	{
		data = (File[]) obj;
		if(data==null) resetGui();
		else updateGui();
	}
	
	private void updateGui() throws Exception
	{
		if(data.length<1)  throw new Exception("Invalid file number: "+data.length);
		viewer.p(data[0]);
	}
	
	private void resetGui() throws Exception
	{viewer.p(null);}
}