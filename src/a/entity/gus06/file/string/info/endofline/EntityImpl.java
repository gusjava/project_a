package a.entity.gus06.file.string.info.endofline;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20221108";}


	private Service read;
	
	public EntityImpl() throws Exception
	{
		read = Outside.service(this,"gus.x.file.string.read.v1");
	}

	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		String text = (String) read.t(file);
		
		if(text.contains("\r\n")) return "CRLF";
		if(text.contains("\r")) return "CR";
		if(text.contains("\n")) return "LF";
		return "?";
	}
}