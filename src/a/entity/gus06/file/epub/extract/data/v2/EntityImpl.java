package a.entity.gus06.file.epub.extract.data.v2;

import a.framework.*;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipFile;
import java.util.zip.ZipEntry;
import java.io.InputStream;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.List;
import java.awt.image.BufferedImage;
import java.util.Set;
import java.util.Iterator;
import java.io.ByteArrayInputStream;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.parser.Parser;

public class EntityImpl implements Entity,T {

	public String creationDate() {return "20251005";}
	
	public static final String CONTAINER_PATH = "META-INF/container.xml";
	
	private Pattern p_path = Pattern.compile("full-path=\"([^\"]+)\"");

	private Service buildZipFile;
	private Service isToString;
	private Service buildEntryMap;
	private Service extractDc;
	private Service extractMeta;
	private Service extractItem;
	private Service extractReference;
	private Service coverFinder;
	private Service extractContent;
	
	public EntityImpl() throws Exception
	{
		buildZipFile = Outside.service(this,"gus06.file.zip.build.zipfile");
		isToString = Outside.service(this,"gus06.io.transfer.tostring.utf8");
		buildEntryMap = Outside.service(this,"gus06.file.zip.zipfile.entrymap");
		extractDc = Outside.service(this,"gus06.file.epub.extract.data.v2.dc");
		extractMeta = Outside.service(this,"gus06.file.epub.extract.data.v2.meta");
		extractItem = Outside.service(this,"gus06.file.epub.extract.data.v2.item");
		extractReference = Outside.service(this,"gus06.file.epub.extract.data.v2.reference");
		coverFinder = Outside.service(this,"gus06.file.epub.extract.data.v2.coverfinder");
		extractContent = Outside.service(this,"gus06.file.epub.extract.data.v2.content");
	}

	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		
		if(file==null) return null;
		if(!file.exists()) return null;
		if(file.length()==0) return null;
		
		Map map = new HashMap();
		try {perform(file, map);}
		catch(Exception e)
		{
			String message = "Failed to extract epub data from file: "+file;
			throw new Exception(message,e);
		}
		return map;
	}
	
	private void perform(File file, Map map) throws Exception
	{
		try
		{
			ZipFile zipFile = (ZipFile) buildZipFile.t(file);
			Map entryMap = (Map) buildEntryMap.t(zipFile);
			
			String container = entryAsString(zipFile, entryMap, CONTAINER_PATH);
			
			Matcher m_path = p_path.matcher(container);
			if(!m_path.find()) throw new Exception("Invalid container.xml data: full-path not found");
			String fullPath = m_path.group(1);
			
			String content = entryAsString(zipFile, entryMap, fullPath);
			String root = findRoot(fullPath);
			
			Document doc = Jsoup.parse(content, "", Parser.xmlParser());
			
			Map map_dc = (Map) extractDc.t(doc);
			Map map_meta = (Map) extractMeta.t(doc);
			Map map_item = (Map) extractItem.t(doc);
			Map map_reference = (Map) extractReference.t(doc);
			
			String coverId = get1(map_meta,"cover");
			if(coverId==null) coverId = "cover";
			
			String coverPath0 = null;
			if(coverId!=null) coverPath0 = get1(map_item, coverId);
			if(coverPath0==null) coverPath0 = get1(map_reference, coverId);
			if(coverPath0==null) coverPath0 = coverId;
			
			String coverPath = null;
			if(root!=null && coverPath0!=null) coverPath = root + coverPath0;
			
			Map coverMap = (Map) coverFinder.t(new Object[]{zipFile, entryMap, coverPath});
			coverPath = (String) coverMap.get("coverPath");
			BufferedImage cover = (BufferedImage) coverMap.get("coverImage");
			
			List chapters = (List) extractContent.t(new Object[]{zipFile, entryMap, doc, map_item, root});
			
			map.put("root",root);
			map.put("container",container);
			map.put("fullPath",fullPath);
			map.put("content",content);
			map.put("map_dc",map_dc);
			map.put("map_meta",map_meta);
			map.put("map_item",map_item);
			map.put("chapters",chapters);
			map.put("coverId",coverId);
			map.put("coverPath0",coverPath0);
			map.put("coverPath",coverPath);
			map.put("cover",cover);
			
			zipFile.close();
		}
		catch(Exception e)
		{
			map.put("exception",e);
			throw e;
		}
	}
	
	private String entryAsString(ZipFile zipFile, Map entryMap, String name) throws Exception
	{
		try
		{
			ZipEntry entry = (ZipEntry) entryMap.get(name);
			InputStream is = zipFile.getInputStream(entry);
			return (String) isToString.t(is);
		}
		catch(Exception e)
		{throw new Exception("failed to read entry as string: "+name,e);}
	}
	
	private String findRoot(String path)
	{
		if(!path.contains("/")) return "";
		String[] nn = path.split("/");
		StringBuffer b = new StringBuffer();
		for(int i=0;i<nn.length-1;i++)
		{
			b.append(nn[i]);
			b.append("/");
		}
		return b.toString();
	}
	
	private String get1(Map map, String key) throws Exception
	{
		if(!map.containsKey(key)) return null;
		return (String) map.get(key);
	}
}