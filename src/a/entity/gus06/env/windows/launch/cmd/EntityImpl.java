package a.entity.gus06.env.windows.launch.cmd;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20190319";}


	public void p(Object obj) throws Exception
	{
		File dir = (File) obj;
		String cmd = "cmd.exe /c start /D \""+dir+"\"";
		Runtime.getRuntime().exec(cmd);
	}
}
