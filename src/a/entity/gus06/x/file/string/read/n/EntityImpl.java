package a.entity.gus06.x.file.string.read.n;

import java.io.File;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20251111";}
	
	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		StringBuilder sb = new StringBuilder();

		try(BufferedReader br = new BufferedReader(
			new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8)))
		{
			String line;
			while((line = br.readLine()) != null)
			{sb.append(line).append("\n");}
		}
		return sb.toString();
	}
}