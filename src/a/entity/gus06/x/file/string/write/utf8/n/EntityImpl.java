package a.entity.gus06.x.file.string.write.utf8.n;

import java.io.File;
import java.io.PrintStream;
import a.framework.*;
import java.nio.charset.StandardCharsets;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20251116";}
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if (o.length != 2) throw new Exception("Wrong data number: " + o.length);

		File file = (File) o[0];
		String text = (String) o[1];

		File parent = file.getParentFile();
		if (!parent.exists()) parent.mkdirs();

		PrintStream p = new PrintStream(file, StandardCharsets.UTF_8);
		p.print(text.replace("\n", System.lineSeparator()));
		p.close();
	}
}
