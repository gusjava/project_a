package a.entity.gus06.sys.unrar1.listentries;

import a.framework.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20261109";}

	public static final String DELIM = "/";

	public static final String NAME = "Name: ";
	public static final String TYPE = "Type: ";
	public static final String DIRECTORY = "Directory";


	private Service exe;

	public EntityImpl() throws Exception
	{
		exe = Outside.service(this,"gus06.sys.unrar1.exe");
	}


	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		File exeFile = (File) exe.g();

		ProcessBuilder pb = new ProcessBuilder(
			exeFile.getAbsolutePath(),
			"lt",
			"-scfr",
			"--",
			file.getAbsolutePath()
		);
		pb.redirectError(ProcessBuilder.Redirect.DISCARD);

		Process p = pb.start();
		List list = readEntries(p);
		int code = p.waitFor();
		if(code!=0) throw new Exception("Failed to list entries of "+file+" (exit code "+code+")");

		Collections.sort(list);
		return list;
	}


	private List readEntries(Process p) throws Exception
	{
		List list = new ArrayList();
		BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream(),StandardCharsets.UTF_8));

		String name = null;
		String line;
		while((line = reader.readLine())!=null)
		{
			line = line.trim();
			if(line.startsWith(NAME)) name = line.substring(NAME.length());
			else if(line.startsWith(TYPE) && name!=null)
			{
				list.add(entryName(name,line.substring(TYPE.length()).equals(DIRECTORY)));
				name = null;
			}
		}
		reader.close();
		return list;
	}


	private String entryName(String name, boolean directory)
	{
		name = name.trim().replace("\\",DELIM);
		if(directory && !name.endsWith(DELIM)) name = name+DELIM;
		return name;
	}
}
