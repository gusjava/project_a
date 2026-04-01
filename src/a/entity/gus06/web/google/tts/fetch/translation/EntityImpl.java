package a.entity.gus06.web.google.tts.fetch.translation;

import a.framework.*;
import java.net.URLEncoder;
import java.io.InputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.io.File;
import java.net.HttpURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class EntityImpl implements Entity, T {

	public String creationDate() { return "20250912"; }
	
	public static final String ROOT = "https://translate.googleapis.com/translate_a/single";
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if (o.length == 2) return translate((String) o[0], (String) o[1], "auto");
		if (o.length == 3) return translate((String) o[0], (String) o[1], (String) o[2]);
		
		throw new Exception("Wrong data number: " + o.length);
	}
	
	private String translate(String text, String targetLang, String sourceLang) throws Exception
	{
		String urlStr = ROOT+
		"?client=gtx"+
		"&sl="+encode(sourceLang)+
		"&tl="+encode(targetLang)+
		"&dt=t"+
		"&q="+encode(text);
		
		HttpURLConnection conn = (HttpURLConnection) new URL(urlStr).openConnection();
		conn.setRequestProperty("User-Agent", "Mozilla/5.0");
		conn.setConnectTimeout(10_000);
		conn.setReadTimeout(10_000);

		try (InputStream in = conn.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8)))
		{
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null)
			sb.append(line);
			String resp = sb.toString();

			// r�ponse du type [[["texte traduit",...]]]
			int start = resp.indexOf('"');
			if (start < 0) return "";
			start++;
			int end = resp.indexOf('"', start);
			if (end < 0) end = resp.length();
			return resp.substring(start, end);
		}
	}
	
	private String encode(String s) throws Exception
	{return URLEncoder.encode(s, "UTF-8");}
}