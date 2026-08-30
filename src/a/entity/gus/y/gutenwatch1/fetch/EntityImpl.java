package a.entity.gus.y.gutenwatch1.fetch;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import a.framework.Entity;
import a.framework.G;

public class EntityImpl implements Entity, G {
	public String creationDate() {return "20260830";}

	public static final String URL = "https://www.gutenberg.org/ebooks/search/?sort_order=release_date";
	public static final String USER_AGENT = "Mozilla/5.0 (compatible; ProjectA-GutenWatch1/1.0)";
	public static final int TIMEOUT_MS = 15000;

	public Object g() throws Exception
	{
		return Jsoup.connect(URL).userAgent(USER_AGENT).timeout(TIMEOUT_MS).get();
	}
}
