package a.entity.gus.y.gutenwatch1.store.detections;

import java.io.File;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import a.entity.gus.y.gutenwatch1.parse.Entry;
import a.framework.Entity;
import a.framework.G;
import a.framework.Outside;
import a.framework.P;

public class EntityImpl implements Entity, G, P {
	public String creationDate() {return "20260830";}

	public static final String FILENAME = "detections.tsv";

	private File file;
	private List<Detection> detections;

	public EntityImpl() throws Exception
	{
		File dir = (File) Outside.resource(this, "defaultdir");
		dir.mkdirs();
		file = new File(dir, FILENAME);
		detections = new ArrayList<Detection>();

		if (file.isFile()) {
			List<String> lines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
			for (String line : lines) {
				Detection d = parseLine(line);
				if (d != null) detections.add(d);
			}
		}
	}

	public Object g() throws Exception
	{
		return detections;
	}

	public void p(Object obj) throws Exception
	{
		List<Detection> batch = (List<Detection>) obj;
		detections.addAll(0, batch);
		rewrite();
	}

	private Detection parseLine(String line)
	{
		String[] parts = line.split("\t", -1);
		if (parts.length != 5) return null;
		Entry entry = new Entry(parts[1], parts[2], parts[3], parts[4]);
		return new Detection(parts[0], entry);
	}

	private void rewrite() throws Exception
	{
		PrintWriter writer = new PrintWriter(file, "UTF-8");
		try {
			for (Detection d : detections) {
				writer.println(d.detectedAt + "\t" + d.entry.url + "\t" + d.entry.title + "\t" + d.entry.author + "\t" + d.entry.releaseDate);
			}
		}
		finally {writer.close();}
	}
}
