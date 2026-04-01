package a.entity.gus06.file.editor.ext.json.dataviewer;

import java.awt.BorderLayout;
import java.io.File;
import a.framework.*;
import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.event.ActionListener;
import java.util.Map;


public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20170308";}

	
	private Service jsonParser;
	private Service readFile;
	private Service objViewer;
	
	private File file;
	private Map data;
	

	public EntityImpl() throws Exception
	{
		jsonParser = Outside.service(this,"gus06.sys.jsonparser1.evaluate");
		readFile = Outside.service(this,"gus06.file.read.string.autodetect");
		objViewer = Outside.service(this,"*gus06.data.viewer.object");
	}
	
	
	public Object i() throws Exception
	{return objViewer.i();}
	
	
	
	
	public void p(Object obj) throws Exception
	{
		file = (File) obj;
		
		try
		{
			String s = (String) readFile.t(file);
			data = (Map) jsonParser.t(s);
			objViewer.p(data);
		}
		catch(Exception e)
		{
			objViewer.p(e);
		}
	}
}
