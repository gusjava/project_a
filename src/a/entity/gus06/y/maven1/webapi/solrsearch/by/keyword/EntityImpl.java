package a.entity.gus06.y.maven1.webapi.solrsearch.by.keyword;

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

	public String creationDate() {return "20251214";}
	
	public static final int ROW_MAX = 20;

	private Service parseJson;
	private Service get;

	public EntityImpl() throws Exception
	{
		parseJson = Outside.service(this,"gus06.file.convert.json.parser");
		get = Outside.service(this,"gus06.y.maven1.webapi.get");
	}

	public Object t(Object obj) throws Exception
	{
		String keyword = (String) obj;

		String queryUrl = "https://search.maven.org/solrsearch/select?q=" +
			URLEncoder.encode(keyword, "UTF-8") +
			"&rows=" + ROW_MAX +
			"&wt=json";

		String json = download(queryUrl);
		Map result = parseJson(json);
		List docs = (List) ((Map)result.get("response")).get("docs");

		List docsList = new ArrayList();
		for (int i=0;i<docs.size();i++)
		{
			Map doc = (Map) docs.get(i);
			Map m = new HashMap();
			m.put("groupId", doc.get("g"));
			m.put("artifactId", doc.get("a"));
			m.put("version", doc.get("v"));
			docsList.add(m);
		}
		
		Map output = new HashMap();
		output.put("numFound", ((Map)result.get("response")).get("numFound"));
		output.put("docs", docsList);
		return output;
	}

	private String download(String urlStr) throws Exception
	{return (String) get.t(urlStr);}

	private Map parseJson(String json) throws Exception
	{return (Map) parseJson.t(json);}
}
