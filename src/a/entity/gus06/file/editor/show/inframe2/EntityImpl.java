package a.entity.gus06.file.editor.show.inframe2;

import a.framework.*;
import java.io.File;
import java.util.List;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20221020";}


	private Service show;
	private Service newFileArrayViewer;
	private Service performFile;
	private Service toArray;

	public EntityImpl() throws Exception
	{
		show = Outside.service(this,"gus06.swing.frame.show");
		newFileArrayViewer = Outside.service(this,"factory#gus.data.viewer.filearray");
		performFile = Outside.service(this,"gus06.file.editor.show.inframe");
		toArray = Outside.service(this,"gus06.find.filearray");
	}

	
	public void p(Object obj) throws Exception
	{
		List files = (List) obj;
		if(files.isEmpty()) return;
		
		if(files.size()==1) performFile.p(files.get(0));
		else
		{
			File[] array = (File[]) toArray.t(files);
			Object viewer = newFileArrayViewer.g();
			((P)viewer).p(array);
		
			show.p(((I)viewer).i());
		}
	}
}