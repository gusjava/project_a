package a.entity.gus06.string.transform.format.html.clean2;

import a.framework.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.text.Normalizer;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20251108";}
	
	private static final List<String> REMOVE_SELECTORS = Arrays.asList(
		"script", "style", "noscript", "header", "footer", "aside",
		".ad", ".ads", ".advert", ".cookie", ".timestamp", ".last-updated",
		".social", ".breadcrumbs", "#header", "#footer"
		);
	
	public Object t(Object obj) throws Exception
	{
		String html = (String) obj;
		Document doc = Jsoup.parse(html);
		
		for (String sel : REMOVE_SELECTORS)
		{
			Elements els = doc.select(sel);
			for (Element e : els) e.remove();
		}
		
		doc.select("*").forEach(e -> 
		{
			List<Node> children = new ArrayList<>(e.childNodes());
			for (Node n : children)
			{if (n.nodeName().equals("#comment"))  n.remove();}
		});
		
		String text = doc.body() != null ? doc.body().text() : doc.text();

		// Normalisation Unicode (NFKC), minuscules, collapses espaces
		String normalized = Normalizer.normalize(text, Normalizer.Form.NFKC).toLowerCase().replaceAll("\\s+", " ").trim();
		
		// Enlever timestamps / dates simples et nombres (optionnel)
		normalized = normalized
		.replaceAll("\\b\\d{1,2}:\\d{2}\\b", "")
		.replaceAll("\\b\\d{4}-\\d{2}-\\d{2}\\b", "")
		.replaceAll("\\b\\d+\\b", "");
		
		return normalized;
	}
}
