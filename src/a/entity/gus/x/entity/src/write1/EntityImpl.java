package a.entity.gus.x.entity.src.write1;

import a.framework.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.charset.StandardCharsets;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20260415";}

	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if (o.length != 2) throw new Exception("Wrong data number: " + o.length);

		File file = (File) o[0];
		String text = (String) o[1];

		File parent = file.getParentFile();
		if (!parent.exists()) parent.mkdirs();
		
		Files.writeString(file.toPath(), text, StandardCharsets.UTF_8);
	}
}
