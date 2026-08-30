package a.entity.gus.y.gutenwatch1.check;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.jsoup.nodes.Document;

import a.entity.gus.y.gutenwatch1.parse.Entry;
import a.entity.gus.y.gutenwatch1.store.detections.Detection;
import a.framework.E;
import a.framework.Entity;
import a.framework.Outside;
import a.framework.S1;
import a.framework.Service;

public class EntityImpl extends S1 implements Entity, E {
	public String creationDate() {return "20260830";}

	private Service fetch;
	private Service parse;
	private Service knownIds;
	private Service detections;

	public EntityImpl() throws Exception
	{
		fetch = Outside.service(this, "gus.y.gutenwatch1.fetch");
		parse = Outside.service(this, "gus.y.gutenwatch1.parse");
		knownIds = Outside.service(this, "gus.y.gutenwatch1.store.knownids");
		detections = Outside.service(this, "gus.y.gutenwatch1.store.detections");
	}

	public void e() throws Exception
	{
		Document doc = (Document) fetch.g();
		List<Entry> entries = (List<Entry>) parse.t(doc);

		Set<String> known = (Set<String>) knownIds.g();
		String detectedAt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

		List<Detection> batch = new ArrayList<Detection>();
		for (Entry entry : entries) {
			if (known.contains(entry.url)) continue;
			known.add(entry.url);
			knownIds.p(entry.url);
			batch.add(new Detection(detectedAt, entry));
		}

		if (batch.isEmpty()) return;

		detections.p(batch);
		newEntries();
	}

	private void newEntries()
	{
		send(this, "newEntries()");
	}
}
