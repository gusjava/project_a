package a.entity.gus.y.gutenwatch1.store.knownids;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import a.framework.Entity;
import a.framework.G;
import a.framework.Outside;
import a.framework.P;

public class EntityImpl implements Entity, G, P {
	public String creationDate() {return "20260830";}

	public static final String FILENAME = "known.txt";

	private File file;
	private Set<String> ids;

	public EntityImpl() throws Exception
	{
		File dir = (File) Outside.resource(this, "defaultdir");
		dir.mkdirs();
		file = new File(dir, FILENAME);
		ids = new LinkedHashSet<String>();

		if (file.isFile()) {
			List<String> lines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
			for (String line : lines) {
				String url = line.trim();
				if (!url.isEmpty()) ids.add(url);
			}
		}
	}

	public Object g() throws Exception
	{
		return ids;
	}

	public void p(Object obj) throws Exception
	{
		String url = (String) obj;
		ids.add(url);
		appendLine(url);
	}

	private void appendLine(String url) throws Exception
	{
		PrintWriter writer = new PrintWriter(new FileWriter(file, true), false);
		try {writer.println(url);}
		finally {writer.close();}
	}
}
