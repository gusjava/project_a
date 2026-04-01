package a.entity.gus06.swing.frame.show.smart.build;

import a.framework.*;
import java.awt.Image;
import java.io.File;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20220613";}


	private Service newDataViewer;
	private Service newStringViewer;
	private Service newFileViewer;
	private Service newFileArrayViewer;
	private Service newImageViewer;
	private Service newFunctionViewer;
	private Service convertToFileArray;

	public EntityImpl() throws Exception
	{
		newDataViewer = Outside.service(this,"factory#gus.data.viewer.object");
		newStringViewer = Outside.service(this,"factory#gus.data.viewer.string.textarea.editor1");
		newFileViewer = Outside.service(this,"factory#gus.file.editor.main");
		newFileArrayViewer = Outside.service(this,"factory#gus.data.viewer.filearray");
		newImageViewer = Outside.service(this,"factory#gus.swing.panel.screen.image");
		newFunctionViewer = Outside.service(this,"factory#gus.sys.function1.screen");
		convertToFileArray = Outside.service(this,"gus06.convert.listtofilearray.strict");
	}
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return newDataViewer.g();
		
		if(obj instanceof String) return newStringViewer.g();
		if(obj instanceof File) return newFileViewer.g();
		if(obj instanceof File[]) return newFileArrayViewer.g();
		if(obj instanceof Image) return newImageViewer.g();
		if(obj instanceof H) return newFunctionViewer.g();
		
		if(obj instanceof List)
		{
			File[] array = (File[]) convertToFileArray.t(obj);
			if(array!=null) return newFileArrayViewer.g();
		}
		
		return newDataViewer.g();
	}
}
