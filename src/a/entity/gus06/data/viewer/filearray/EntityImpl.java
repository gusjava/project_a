package a.entity.gus06.data.viewer.filearray;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, I, P, G {

	public String creationDate() {return "20190821";}


	private Service shiftPanel;
	private Service find;
	private Service viewerFile1;
	private Service viewerFile2;
	private Service viewerFiles;
    
	private File[] data;

	public EntityImpl() throws Exception
	{
		shiftPanel = Outside.service(this,"*gus06.swing.panel.shiftpanel");
		find = Outside.service(this,"gus06.find.filearray");
		viewerFile1 = Outside.service(this,"*gus06.data.viewer.filearray.file1");
		viewerFile2 = Outside.service(this,"*gus06.data.viewer.filearray.file2");
		viewerFiles = Outside.service(this,"*gus06.data.viewer.filearray.files");
	}
	
	public Object i() throws Exception
	{return shiftPanel.i();}
	
	
	public Object g() throws Exception
	{return data;}
	
	
	public void p(Object obj) throws Exception
	{
		data = (File[]) find.t(obj);
		
		viewerFile1.p(null);
		viewerFile2.p(null);
		viewerFiles.p(null);
		shiftPanel.p(initView());
	}
	
	private Object initView() throws Exception
	{
		if(data==null) return null;
		if(data.length==0) return null;
		
		if(data.length==1)
		{
			viewerFile1.p(data);
			return viewerFile1;
		}
		if(data.length==2)
		{
			viewerFile2.p(data);
			return viewerFile2;
		}
		
		viewerFiles.p(data);
		return viewerFiles;
	}
}