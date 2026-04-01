package a.entity.gus06.sys.filemanagement1.scan.builder.oncomplete;

import a.framework.*;
import java.awt.Toolkit;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20201125";}

	public static final String KEY_SCAN_ONCOMPLETE_WARN = "scan.oncomplete.warn";
	
	
	public void p(Object obj) throws Exception
	{
		String warnOnComplete = (String) ((R) obj).r("config:"+KEY_SCAN_ONCOMPLETE_WARN);
		if(warnOnComplete!=null)
		{
			if(warnOnComplete.equals("beep"))
			Toolkit.getDefaultToolkit().beep();
		}
	}
}