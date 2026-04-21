package a.entity.gus.y.entitysys1.dataloader.jar;

import java.io.File;
import java.sql.Connection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import a.framework.Entity;
import a.framework.Outside;
import a.framework.R;
import a.framework.Service;
import a.framework.T;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260416";}

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
	private Service jarEntries;
	private Service insertJar;
	private Service mergeClass;
	private Service deleteJar;
	private Service deleteClass;
	private Service searchMavenIdInsideJar;
	private Service searchMavenIdOnline;

	public EntityImpl() throws Exception {
		listing = Outside.service(this, "gus06.dir.listing.dirtofiles.forext.jar");
		buildSha1 = Outside.service(this, "gus06.crypto.hash.sha1.hexa");
		buildMd5 = Outside.service(this, "gus06.crypto.hash.md5.hexa");
		findAll = Outside.service(this, "gus.y.entitydb1.jar.findall.asmap");
		jarEntries = Outside.service(this, "gus.x.file.jar.build.entries");
		insertJar = Outside.service(this, "gus.y.entitydb1.jar.insert");
		mergeClass = Outside.service(this, "gus.y.entitydb1.jar_class.merge");
		deleteJar = Outside.service(this, "gus.y.entitydb1.jar.delete");
		deleteClass = Outside.service(this, "gus.y.entitydb1.jar_class.delete");
		searchMavenIdInsideJar = Outside.service(this, "gus06.file.jar.find.mavenpom.asid");
		searchMavenIdOnline = Outside.service(this, "gus06.y.maven1.webapi.solrsearch.by.sha1.id");
	}

	public Object t(Object obj) throws Exception {
		R engine = (R) obj;

		File libDir = (File) engine.r("libDir");
		Connection cx = (Connection) engine.r("cx");

		List jars = (List) listing.t(libDir);
		Map data = (Map) findAll.t(cx);

		Map mavenIdToName = new HashMap();
		Map sha1ToName = new HashMap();
		Map md5ToName = new HashMap();
		
		Iterator it = data.keySet().iterator();
		while(it.hasNext())
		{
			String name = (String) it.next();
			Map m = (Map) data.get(name);
			
			Object mavenId = m.get(COL_MAVEN_ID);
			Object sha1 = m.get(COL_SHA1);
			Object md5 = m.get(COL_MD5);
			
			if (mavenId != null) mavenIdToName.put(mavenId, name);
			if (sha1 != null) sha1ToName.put(sha1, name);
			if (md5 != null) md5ToName.put(md5, name);
		}
		
		Set nameFound = new HashSet();
		Set mavenIdFound = new HashSet();
		Set sha1Found = new HashSet();
		Set md5Found = new HashSet();

		for (int i = 0; i < jars.size(); i++) {
			File jar = (File) jars.get(i);
			String jarName = jar.getName();
			
			nameFound.add(jarName);
			if(data.containsKey(jarName)) continue;
			
			String sha1 = (String) buildSha1.t(jar);
			if(sha1ToName.containsKey(sha1))
			{
				String dbName = (String) sha1ToName.get(sha1);
				System.out.println("sha1["+sha1+"] : localName="+jarName+" & dbName="+dbName);
				continue;
			}
			if(sha1Found.contains(sha1))
			{
				System.out.println("sha1["+sha1+"] : found many times inside directory");
				continue;
			}
			sha1Found.add(sha1);

			String md5 = (String) buildMd5.t(jar);
			if(md5ToName.containsKey(md5))
			{
				String dbName = (String) md5ToName.get(md5);
				System.out.println("md5["+md5+"] : localName="+jarName+" & dbName="+dbName);
				continue;
			}
			if(md5Found.contains(md5))
			{
				System.out.println("md5["+md5+"] : found many times inside directory");
				continue;
			}
			md5Found.add(md5);
			
			long lastModified = jar.lastModified();
			List entries = (List) jarEntries.t(jar);
			Date modifDate = new Date(lastModified);

			String[] mavenInfos = retrieveMavenId(jar, sha1);
			String mavenId = mavenInfos[0];
			String mavenRetrieveMethod = mavenInfos[1];
			
			if(mavenId!=null)
			{
				if(mavenIdToName.containsKey(mavenId))
				{
					String dbName = (String) mavenIdToName.get(mavenId);
					System.out.println("mavenId["+mavenId+"] : localName="+jarName+" & dbName="+dbName);
					continue;
				}
				if(mavenIdFound.contains(mavenId))
					System.out.println("mavenId["+mavenId+"] : found many times inside directory");
				else mavenIdFound.add(mavenId);
			}

			Map m = new HashMap();
			m.put(COL_SHA1, sha1);
			m.put(COL_MD5, md5);
			m.put(COL_FILE_NAME, jarName);
			m.put(COL_CREATION_DATE, new Date());
			m.put(COL_FILE_MODIF_DATE, modifDate);
			m.put(COL_MAVEN_ID, mavenId);
			m.put(COL_MAVEN_RETRIEVE_METHOD, mavenRetrieveMethod);

			insertJar.p(new Object[]{cx, m});
			data.put(jarName, m);

			for (int j = 0; j < entries.size(); j++) {
				String entry = (String) entries.get(j);
				Map m1 = new HashMap();
				m1.put(COL_JAR_SHA1, sha1);
				m1.put(COL_JAR_CLASS, entry);
				doInsertClass(cx, m1);
			}
		}

		Iterator it2 = data.keySet().iterator();
		while (it2.hasNext()) {
			String fileName = (String) it2.next();
			if (!nameFound.contains(fileName)) {
				Map m = (Map) data.get(fileName);
				String sha1 = (String) m.get(COL_SHA1);
				deleteClass.p(new Object[]{cx, sha1});
				deleteJar.p(new Object[]{cx, sha1});
				it2.remove();
			}
		}

		return data;
	}

	private void doInsertClass(Connection cx, Map m1) {
		try {
			mergeClass.p(new Object[]{cx, m1});
		} catch (Exception e) {
			Outside.err(this, "doInsertClass", e);
		}
	}

	private String[] retrieveMavenId(File jar, String sha1) {
		try {
			String mavenId1 = (String) searchMavenIdInsideJar.t(jar);
			if (mavenId1 != null) return new String[]{mavenId1, "jar"};
			
//			String mavenId2 = (String) searchMavenIdOnline.t(sha1);
//			if (mavenId2 != null) return new String[]{mavenId2, "central"};
		}
		catch (Exception e)
		{Outside.err(this, "retrieveMavenId(File,String)", e);}
		return new String[]{null, "not_found"};
	}
}
