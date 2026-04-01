package a.entity.gus06.y.maven1.webapi.get;

import a.framework.*;
import java.net.URLEncoder;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.net.URL;
import java.net.HttpURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20251228";}
	

	public Object t(Object obj) throws Exception
	{return download((String) obj);}

	private String download(String urlStr) throws Exception
	{
		URL url = new URL(urlStr);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setConnectTimeout(15000);
		conn.setReadTimeout(30000);

		if (conn.getResponseCode() != 200)
			throw new Exception("Failed to query Maven Central - HTTP " + conn.getResponseCode());

		try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream())))
		{
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = br.readLine()) != null) sb.append(line);
			return sb.toString();
		}
	}
}
