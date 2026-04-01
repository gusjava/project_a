package a.entity.gus06.appli.usbwebprint.init.fileregister;

import a.framework.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.Map;

public class EntityImpl implements Entity, ActionListener {

	public String creationDate() {return "20140915";}


	private Service extractor;
	private Service prepareMap;
	private Service perform;


	public EntityImpl() throws Exception
	{
		extractor = Outside.service(this,"gus06.appli.usbwebprint.usbkey.extractor");
		prepareMap = Outside.service(this,"gus06.appli.usbwebprint.fileregister.preparemap");
		perform = Outside.service(this,"gus06.appli.usbwebprint.fileregister.perform");
		
		extractor.addActionListener(this);
	}


	public void actionPerformed(ActionEvent e)
	{handleFile();}
	
	
	
	private void handleFile()
	{
		try
		{
			File file = (File) extractor.g();
			File root = (File) extractor.r("root");
			
			Map map = (Map) prepareMap.t(new File[]{file,root});
			perform.p(map);
		}
		catch(Exception e)
		{Outside.err(this,"handleFile()",e);}
	}

}
