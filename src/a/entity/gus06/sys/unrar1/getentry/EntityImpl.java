package a.entity.gus06.sys.unrar1.getentry;

import a.framework.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20261109";}

	public static final String DELIM = "/";


	private Service exe;

	public EntityImpl() throws Exception
	{
		exe = Outside.service(this,"gus06.sys.unrar1.exe");
	}


	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);

		File file = (File) o[0];
		String entry = (String) o[1];
		if(entry.endsWith(DELIM)) return null;

		File exeFile = (File) exe.g();

		ProcessBuilder pb = new ProcessBuilder(
			exeFile.getAbsolutePath(),
			"p",
			"-inul",
			"-scfr",
			"--",
			file.getAbsolutePath(),
			entry.replace(DELIM,"\\")
		);
		pb.redirectError(ProcessBuilder.Redirect.DISCARD);

		Process p = pb.start();
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		p.getInputStream().transferTo(bos);
		int code = p.waitFor();
		if(code!=0) throw new Exception("Entry name not found: "+entry);

		return new ByteArrayInputStream(bos.toByteArray());
	}
}
