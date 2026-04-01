package a.entity.gus06.sys.filemanagement1.tool.export.dirpreview.aspdf;

import a.framework.*;
import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20200419";}

	public static final String KEY_CHILDREN = "children";
	public static final String KEY_MD5 = "md5";
	public static final String KEY_NAME = "name";
	
	public static final String KEY_IMAGE_DATA = "image_data";


	private Service keepFiles;
	private Service choosePdf;
	private Service pdfGenerator;
	private Service findPreview1;

	public EntityImpl() throws Exception
	{
		keepFiles = Outside.service(this,"gus06.sys.filemanagement1.tool.treemap.children.filetype");
		choosePdf = Outside.service(this,"gus06.file.choose.save.file.ext.pdf.en");
		pdfGenerator = Outside.service(this,"*gus06.sys.pdfgeneration1.engine");
		findPreview1 = Outside.service(this,"gus06.sys.filemanagement1.tool.preview1.find.data");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object engine = o[0];
		Map selected = (Map) o[1];
		
		
		if(selected==null) return;
		if(engine==null) return;
		
		File pdfFile = (File) choosePdf.g();
		if(pdfFile==null) return;
		
		List children = (List) selected.get(KEY_CHILDREN);
		children = (List) keepFiles.t(children);
		
		int nb = children.size();
		
		List list = new ArrayList();
		for(int i=0;i<nb;i++)
		{
			Map child = (Map) children.get(i);
			String md5 = (String) child.get(KEY_MD5);
			String name = (String) child.get(KEY_NAME);
			
			G dataProvider = new DataProvider(engine,md5);
			
			Map m = new HashMap();
			m.put(KEY_IMAGE_DATA,dataProvider);
			list.add(m);
		}
		
		pdfGenerator.v("pdfFile",pdfFile);
		pdfGenerator.p(list);
	}
	
	
	
	private class DataProvider implements G
	{
		private Object engine;
		private String md5;
		
		public DataProvider(Object engine, String md5)
		{
			this.engine = engine;
			this.md5 = md5;
		}
		
		public Object g() throws Exception
		{return findPreview1.t(new Object[]{engine,md5});}
	}
}
