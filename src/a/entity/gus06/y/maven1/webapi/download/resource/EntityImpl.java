package a.entity.gus06.y.maven1.webapi.download.resource;

import a.framework.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.HttpURLConnection;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20251214";}

	public EntityImpl() throws Exception {}

	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if (o.length != 5) throw new Exception("Expected 5 parameters: groupId, artifactId, version, type, targetFile");

		String groupId = (String) o[0];
		String artifactId = (String) o[1];
		String version = (String) o[2];
		String type = (String) o[3]; // "jar", "sources", "pom", "javadoc"...
		File targetFile = (File) o[4];

		String url = buildUrl(groupId, artifactId, version, type);

		downloadFile(url, targetFile);
	}

	private String buildUrl(String groupId, String artifactId, String version, String type)
	{
		String base = "https://repo1.maven.org/maven2/";
		String path = groupId.replace('.', '/') + "/" + artifactId + "/" + version + "/";
		String classifier = "";
		if (type.equals("sources")) classifier = "-sources";
		else if (type.equals("javadoc")) classifier = "-javadoc";
		else if (type.equals("pom")) classifier = "";
		else if (type.equals("jar")) classifier = "";

		return base + path + artifactId + "-" + version + classifier + (type.equals("pom") ? ".pom" : ".jar");
	}

	private void downloadFile(String urlStr, File target) throws Exception
	{
		URL url = new URL(urlStr);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setConnectTimeout(15000);
		conn.setReadTimeout(30000);
		conn.setRequestMethod("GET");

		if (conn.getResponseCode() != 200)
			throw new Exception("Failed to download " + urlStr + " - HTTP " + conn.getResponseCode());

		try (InputStream in = conn.getInputStream();
			 FileOutputStream out = new FileOutputStream(target)) {

			byte[] buffer = new byte[8192];
			int read;
			while ((read = in.read(buffer)) != -1) {
				out.write(buffer, 0, read);
			}
		}
	}
}