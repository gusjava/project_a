package a.entity.gus06.y.entitysys1.dataloader.jar;

import a.framework.*;
import java.io.File;
import java.sql.Connection;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.Iterator;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20251228";}
	
	public static final String COL_SHA1 = "sha1";
	public static final String COL_MD5 = "md5";
	public static final String COL_FILE_NAME = "file_name";
	public static final String COL_FILE_MODIF_DATE = "file_modif_date";
	public static final String COL_CREATION_DATE = "creation_date";
	public static final String COL_MAVEN_ID = "maven_id";
	public static final String COL_MAVEN_RETRIEVE_METHOD = "maven_retrieve_method";

	public static final String COL_JAR_SHA1 = "jar_sha1";
	public static final String COL_JAR_CLASS = "jar_class";


	private Service listing;
	private Service buildSha1;
	private Service buildMd5;
	private Service findAll;
	private Service jarListing;
	private Service insertJar;
	private Service insertClass;
	private Service deleteJar;
	private Service deleteClass;
	private Service searchMavenIdInsideJar;
	private Service searchMavenIdOnline;
	
	public EntityImpl() throws Exception
	{
		listing = Outside.service(this,"gus06.dir.listing.dirtofiles.forext.jar");
		buildSha1 = Outside.service(this,"gus06.crypto.hash.sha1.hexa");
		buildMd5 = Outside.service(this,"gus06.crypto.hash.md5.hexa");
		findAll = Outside.service(this,"gus06.y.entitydb1.jar.findall.asmap");
		jarListing = Outside.service(this,"gus06.file.jar.findclasspaths");
		insertJar = Outside.service(this,"gus06.y.entitydb1.jar.insert");
		insertClass = Outside.service(this,"gus06.y.entitydb1.jar_class.insert");
		deleteJar = Outside.service(this,"gus06.y.entitydb1.jar.delete");
		deleteClass = Outside.service(this,"gus06.y.entitydb1.jar_class.delete");
		searchMavenIdInsideJar = Outside.service(this,"gus06.file.jar.find.mavenpom.asid");
		searchMavenIdOnline = Outside.service(this,"gus06.y.maven1.webapi.solrsearch.by.sha1.id");
	}
	
	public Object t(Object obj) throws Exception
	{
		R engine = (R) obj;
		
		Long lastTime = (Long) engine.r("lastTime");
		File libDir = (File) engine.r("libDir");
		Connection cx = (Connection) engine.r("cx");
		
		Map data = (Map) findAll.t(cx);
		
		List jars = (List) listing.t(libDir);
		Set nameFound = new HashSet();
		
		for(int i=0;i<jars.size();i++)
		{
			File jar = (File) jars.get(i);
			String fileName = jar.getName();
			nameFound.add(fileName);
			
			if(!data.containsKey(fileName))
			{
				String md5 = (String) buildMd5.t(jar);
				String sha1 = (String) buildSha1.t(jar);
				long lastModified = jar.lastModified();
				List entries = (List) jarListing.t(jar);
				Date modifDate = new Date(lastModified);
				
				Map m = new HashMap();
				m.put(COL_SHA1, sha1);
				m.put(COL_MD5, md5);
				m.put(COL_FILE_NAME, fileName);
				m.put(COL_CREATION_DATE, new Date());
				m.put(COL_FILE_MODIF_DATE, modifDate);
				
				retrieveMavenId(m, jar, sha1);
				insertJar.p(new Object[]{cx, m});
				data.put(sha1, m);
				
				for(int j=0;j<entries.size();j++)
				{
					String entry = (String) entries.get(j);
					
					Map m1 = new HashMap();
					m1.put(COL_JAR_SHA1, sha1);
					m1.put(COL_JAR_CLASS, entry);
					insertClass.p(new Object[]{cx, m1});
				}
			}
		}
		
		Iterator it = data.keySet().iterator();
		while(it.hasNext())
		{
			String fileName = (String) it.next();
			if(!nameFound.contains(fileName))
			{
				String sha1 = (String) data.get(COL_SHA1);
				deleteClass.p(new Object[]{cx, sha1});
				deleteJar.p(new Object[]{cx, sha1});
				it.remove();
			}
		}
		
		return data;
	}
	
	private void retrieveMavenId(Map m, File jar, String sha1)
	{
		try
		{
			String mavenId1 = (String) searchMavenIdInsideJar.t(jar);
			if(mavenId1!=null)
			{
				m.put(COL_MAVEN_ID, mavenId1);
				m.put(COL_MAVEN_RETRIEVE_METHOD, "jar");
				return;
			}
			String mavenId2 = (String) searchMavenIdOnline.t(sha1);
			if(mavenId2!=null)
			{
				m.put(COL_MAVEN_ID, mavenId2);
				m.put(COL_MAVEN_RETRIEVE_METHOD, "central");
				return;
			}
			m.put(COL_MAVEN_RETRIEVE_METHOD, "not_found");
		}
		catch(Exception e)
		{Outside.err(this,"retrieveMavenId(Map,File,String)",e);}
	}
}
