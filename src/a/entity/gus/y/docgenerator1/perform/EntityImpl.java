package a.entity.gus.y.docgenerator1.perform;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import a.framework.Entity;
import a.framework.G;
import a.framework.Outside;
import a.framework.Service;

public class EntityImpl implements Entity, G {
	public String creationDate() {return "20231203";}
	
	public static final String PATH_DOC_X = "doc1/fr/src/entity/x/";
	public static final String PATH_DOC_Y = "doc1/fr/src/entity/y/";
	public static final String PATH_DOC_Z = "doc1/fr/src/entity/z/";

	private Service readFile;
	private Service writeFile;
	private Service listingX;
	private Service listingY;
	private Service listingZ;
	private Service findDev;
	
	private File entityRoot;
	private File configRoot;

	public EntityImpl() throws Exception {
		readFile = Outside.service(this, "gus.x.file.string.read");
		writeFile = Outside.service(this, "gus.x.file.string.write");
		listingX = Outside.service(this, "gus.x.entity.srcfile.listing.root1.dev.x");
		listingY = Outside.service(this, "gus.x.entity.srcfile.listing.root1.dev.y");
		listingZ = Outside.service(this, "gus.x.entity.srcfile.listing.root1.dev.z");
		findDev = Outside.service(this, "gus.y.srcroot1.dev");
		
		entityRoot = (File) Outside.service(this, "gus.y.srcroot1.find.entity").g();
		configRoot = (File) Outside.service(this, "gus.y.srcroot1.find.config").g();
	}

	public Object g() throws Exception {
		String devId = (String) findDev.g();
		if (devId == null) return null;
		
		List entitiesX = (List) listingX.t(new Object[] {entityRoot, devId});
		List entitiesY = (List) listingY.t(new Object[] {entityRoot, devId});
		List entitiesZ = (List) listingZ.t(new Object[] {entityRoot, devId});
		
		Map entitiesByYName = buildYMap(devId, entitiesY);
		Map entitiesByZName = buildZMap(devId, entitiesZ);
		
		List yNames = new ArrayList(entitiesByYName.keySet());
		List zNames = new ArrayList(entitiesByZName.keySet());
		
		Collections.sort(yNames);
		Collections.sort(zNames);
		
		File docXDir = new File(new File(configRoot, devId), PATH_DOC_X);
		File docYDir = new File(new File(configRoot, devId), PATH_DOC_Y);
		File docZDir = new File(new File(configRoot, devId), PATH_DOC_Z);

		docXDir.mkdirs();
		docYDir.mkdirs();
		docZDir.mkdirs();

		StringBuffer b = new StringBuffer();

		b.append("------------------\n");
		b.append("DOC FOR X ENTITIES\n");
		for (int i=0;i<entitiesX.size();i++) {
			String entityX = (String) entitiesX.get(i);
			String xName = entityX.substring(devId.length()+3);
			String docName = xName + ".txt";

			File docFile = new File(docXDir, docName);
			if (!docFile.exists()) {
				String docInit = buildXDocInit(xName, entityX);
				writeFile.p(new Object[] {docFile, docInit});
				b.append(xName + "\n");
			}
			else {
				String s = (String) readFile.t(docFile);
				if(!s.startsWith("@")) {
					String docInit = buildXDocInit(xName, entityX);
					writeFile.p(new Object[] {docFile, docInit+"\n"+s});
					b.append(">" + xName + "\n");
				}
			}
		}

		b.append("------------------\n");
		b.append("DOC FOR Y ENTITIES\n");
		for (int i=0;i<yNames.size();i++) {
			String yName = (String) yNames.get(i);
			String docName = yName + ".txt";

			File docFile = new File(docYDir, docName);
			if (!docFile.exists()) {
				List entitiesForDoc = (List) entitiesByYName.get(yName);
				String docInit = buildYDocInit(devId, yName, entitiesForDoc);
				writeFile.p(new Object[] {docFile, docInit});
				b.append(yName + "\t" + entitiesForDoc.size() + "\n");
			}
		}

		b.append("------------------\n");
		b.append("DOC FOR Z ENTITIES\n");
		for (int i=0;i<zNames.size();i++) {
			String zName = (String) zNames.get(i);
			String docName = zName + ".txt";

			File docFile = new File(docZDir, docName);
			if (!docFile.exists()) {
				List entitiesForDoc = (List) entitiesByZName.get(zName);
				String docInit = buildZDocInit(devId, zName, entitiesForDoc);
				writeFile.p(new Object[] {docFile, docInit});
				b.append(zName + "\t" + entitiesForDoc.size() + "\n");
			}
		}
		return b.toString();
	}
	
	private String buildXDocInit(String xName, String entityX) throws Exception {
		StringBuffer b = new StringBuffer();
		b.append("@description\n");
		b.append("Description de l'entité "+entityX+"\n\n");
		b.append("@infos\n");
		b.append("state=GENERATED\n");
		b.append("sign=\n");
		b.append("tags=");
		return b.toString();
	}
	
	private String buildYDocInit(String devId, String yName, List entities) throws Exception {
		StringBuffer b = new StringBuffer();
		b.append("@description\n");
		b.append("Description du système "+devId+".y."+yName+"\n\n");
		for(int i=0;i<entities.size();i++) {
			String entity = (String) entities.get(i);
			b.append(entity+"\n");
		}
		b.append("@infos\n");
		b.append("state=GENERATED\n");
		b.append("entry=\n");
		b.append("tags=");
		return b.toString();
	}
	
	private String buildZDocInit(String devId, String zName, List entities) throws Exception {
		StringBuffer b = new StringBuffer();
		b.append("@description\n");
		b.append("Description de l'application "+devId+"."+zName+"\n\n");
		for(int i=0;i<entities.size();i++) {
			String entity = (String) entities.get(i);
			b.append(entity+"\n");
		}
		b.append("@infos\n");
		b.append("state=GENERATED\n");
		b.append("maingui=\n");
		b.append("tags=");
		return b.toString();
	}
	
	private Map buildYMap(String devId, List entities) throws Exception {
		Map map = new HashMap();
		for(int i=0;i<entities.size();i++) {
			String entity = (String) entities.get(i);
			if(!entity.startsWith(devId+".y.")) throw new Exception("Invalid y entity: "+entity);
			String yName = entity.substring(devId.length()+3).split("\\.")[0];
			
			if(!map.containsKey(yName))
				map.put(yName, new ArrayList());
			((List) map.get(yName)).add(entity);
		}
		return map;
	}
	
	private Map buildZMap(String devId, List entities) throws Exception {
		Map map = new HashMap();
		for(int i=0;i<entities.size();i++) {
			String entity = (String) entities.get(i);
			if(!entity.startsWith(devId+".z.")) throw new Exception("Invalid z entity: "+entity);
			String zName = entity.substring(devId.length()+3).split("\\.")[0];
			
			if(!map.containsKey(zName))
				map.put(zName, new ArrayList());
			((List) map.get(zName)).add(entity);
		}
		return map;
	}
}
