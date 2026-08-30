package a.entity.gus.y.gutenwatch1.parse;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import a.framework.Entity;
import a.framework.T;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260830";}

	public static final String BASE_URL = "https://www.gutenberg.org";

	public Object t(Object obj) throws Exception
	{
		Document doc = (Document) obj;
		Elements items = doc.select("li.booklink");

		List<Entry> entries = new ArrayList<Entry>();
		for (Element item : items) {
			Element link = item.select("a.link").first();
			if (link == null) continue;

			String url = BASE_URL + link.attr("href");
			String title = text(link.select("span.title").first());
			String author = text(link.select("span.subtitle").first());
			String releaseDate = text(link.select("span.extra").first());

			entries.add(new Entry(url, title, author, releaseDate));
		}
		return entries;
	}

	private String text(Element el)
	{
		if (el == null) return "";
		return el.text().replace('\t', ' ').replace('\n', ' ').replace('\r', ' ');
	}
}
