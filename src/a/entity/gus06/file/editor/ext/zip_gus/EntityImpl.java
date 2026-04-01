package a.entity.gus06.file.editor.ext.zip_gus;

import a.framework.*;

import java.io.File;
import javax.swing.*;
import java.util.*;
import java.awt.BorderLayout;
import java.util.zip.ZipFile;
import java.util.zip.ZipEntry;
import java.io.InputStream;

public class EntityImpl implements Entity, I, P, G {

	public String creationDate() {return "20231124";}

	public static final String ENTRY_INIT = "init.gus";

	private Service shiftPanel;
	private Service buildZipFile;
	private Service isToString;
	private Service executeScript;
	private Service exceptionViewer;

	private File file;
	

	public EntityImpl() throws Exception
	{
		shiftPanel = Outside.service(this,"*gus06.swing.panel.shiftpanel");
		buildZipFile = Outside.service(this,"gus06.file.zip.build.zipfile");
		isToString = Outside.service(this,"gus06.io.transfer.tostring.autodetect");
		executeScript = Outside.service(this,"gus06.sys.script1.build2.g");
		exceptionViewer = Outside.service(this,"*gus06.data.viewer.exception");
	}
	
	
	public Object i() throws Exception
	{return shiftPanel.i();}
	
	
	public Object g() throws Exception
	{return file;}
	
	
	
	
	public void p(Object obj) throws Exception
	{
		file = (File) obj;
		if(file==null || !file.isFile() || file.length()==0) resetGui();
		else updateGui();
	}
	
	
	
	
	private void updateGui() throws Exception
	{
		ZipFile zipFile = (ZipFile) buildZipFile.t(file);
		ZipEntry zipEntry = zipFile.getEntry(ENTRY_INIT);
		if(zipEntry==null) throw new Exception("ZipEntry not found: "+ENTRY_INIT+" for file "+file);
		
		InputStream is = zipFile.getInputStream(zipEntry);
		String script = (String) isToString.t(is);
		is.close();
		zipFile.close();
		
		Map data = new HashMap();
		data.put("file",file);
		G g = (G) executeScript.t(new Object[]{script,data});
		
		try
		{
			Object result = g.g();
			shiftPanel.p(result);
		}
		catch(Exception e)
		{
			exceptionViewer.p(e);
			shiftPanel.p(exceptionViewer);
		}
	}
	
	private void resetGui() throws Exception
	{
		shiftPanel.p(null);
	}
}