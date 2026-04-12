package a.entity.gus.y.entitysys1.dataloader;

import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20240111";}

	private Service logger;
	private Service getListing;
	private Service analyzeEntity;
	private Service compileEntity;
	
	private Service insertEntity;
	private Service updateEntity;
	
	private Service insertResources;
	private Service insertServices;
	private Service insertLinks;
	private Service insertMissingLinks;
	private Service insertXyzErr;
	
	private Service deleteResources;
	private Service deleteServices;
	private Service deleteLinks;
	private Service deleteMissingLinks;
	private Service deleteXyzErr;
	
	private Service findAll;
	private Service remover;
	
	
	public EntityImpl() throws Exception {
		logger = Outside.service(this, "logger");
		getListing = Outside.service(this,"gus.x.entity.srcfile.map_lastmodified.root0");
		analyzeEntity = Outside.service(this,"gus.y.entitysys1.analyze.entity");
		compileEntity = Outside.service(this,"gus.y.entitysys1.compile.entity");
		
		insertEntity = Outside.service(this,"gus.y.entitydb1.entity.insert");
		updateEntity = Outside.service(this,"gus.y.entitydb1.entity.update");
		
		insertResources = Outside.service(this,"gus.y.entitysys1.insert.resources");
		insertServices = Outside.service(this,"gus.y.entitysys1.insert.services");
		insertLinks = Outside.service(this,"gus.y.entitysys1.insert.links");
		insertMissingLinks = Outside.service(this,"gus.y.entitysys1.insert.missinglinks");
		insertXyzErr = Outside.service(this,"gus.y.entitysys1.insert.xyzerr");
		
		deleteResources = Outside.service(this,"gus.y.entitydb1.entity_resource.delete");
		deleteServices = Outside.service(this,"gus.y.entitydb1.entity_service.delete");
		deleteLinks = Outside.service(this,"gus.y.entitydb1.entity_link.delete1");
		deleteMissingLinks = Outside.service(this,"gus.y.entitydb1.entity_missing_link.delete1");
		deleteXyzErr = Outside.service(this,"gus.y.entitydb1.entity_xyz_err.delete");
		
		findAll = Outside.service(this,"gus.y.entitydb1.entity.findall.asmap");
		remover = Outside.service(this,"gus.y.entitydb1.entity.remover");
	}
	
	public Object t(Object obj) throws Exception {
		Object engine = obj;
		
		Long lastTime = (Long) ((R) engine).r("lastTime");
		File rootDir = (File) ((R) engine).r("rootDir");
		Connection cx = (Connection) ((R) engine).r("cx");
		
		long t1 = System.currentTimeMillis();

		Map mapRoot = (Map) getListing.t(rootDir);
		Set setRoot = mapRoot.keySet();
		
		Map mapDb = (Map) findAll.t(cx);
		if(mapDb==null) throw new Exception("null data retrieved from cx");
		
		log("Src dir : "+mapRoot.size()+" entities found");
		log("Database : "+mapDb.size()+" entities found");
		
		Set over = new HashSet(mapDb.keySet());
		over.removeAll(setRoot);
		if(!over.isEmpty()) remover.p(new Object[] {cx, over});
		
		Map results = new HashMap();
		Set analyzed = new HashSet();
		
		int dirAnalyzedNb = 0;
		
		Iterator it = setRoot.iterator();
		while(it.hasNext()) {
			String entityName = (String) it.next();
			long lastModified = (long) mapRoot.get(entityName);
			
			boolean outDated = lastModified > lastTime;
			boolean dbFound = mapDb.containsKey(entityName);
			boolean shouldAnalyze = outDated || !dbFound;

			Map entityMap = null;
			
			if(shouldAnalyze) {
				dirAnalyzedNb++;
				entityMap = (Map) analyzeEntity.t(new Object[] {entityName, rootDir, setRoot});
				analyzed.add(entityName);
				
				if(dbFound) updateEntity.p(new Object[] {cx,entityMap});
				else insertEntity.p(new Object[] {cx,entityMap});
			}
			else entityMap = (Map) mapDb.get(entityName);
			
			results.put(entityName, entityMap);
		}
		
		// 1 - suppression de xyzErr et missingLink

		log("Removing xyzErr and missingLink");
		it = analyzed.iterator();
		while(it.hasNext()) {
			String entityName = (String) it.next();
			
			if(mapDb.containsKey(entityName)) {
				deleteXyzErr.p(new Object[] {cx, entityName});
				deleteMissingLinks.p(new Object[] {cx, entityName});
			}
		}

		// 2 - suppression des links, services, resources

		log("Removing links, services and resources");
		it = analyzed.iterator();
		while(it.hasNext()) {
			String entityName = (String) it.next();
			
			if(mapDb.containsKey(entityName)) {
				deleteLinks.p(new Object[] {cx, entityName});
				deleteServices.p(new Object[] {cx, entityName});
				deleteResources.p(new Object[] {cx, entityName});
			}
		}
		
		// 3 - compilation des entites

		log("Compiling entities");
		it = analyzed.iterator();
		while(it.hasNext()) {
			String entityName = (String) it.next();
			compileEntity.p(new Object[] {engine, entityName});
		}
		
		// 4 - insertion des links, services, resources

		log("Inserting links, services and resources");
		it = analyzed.iterator();
		while(it.hasNext()) {
			String entityName = (String) it.next();
			Map entityMap = (Map) results.get(entityName);
			
			insertServices.p(new Object[] {cx, entityMap});
			insertResources.p(new Object[] {cx, entityMap});
			insertLinks.p(new Object[] {cx, entityMap});
		}
		
		// 5 - insertion de xyzErr et missingLink

		log("Inserting xyzErr and missingLink");
		it = analyzed.iterator();
		while(it.hasNext()) {
			String entityName = (String) it.next();
			Map entityMap = (Map) results.get(entityName);
			
			insertMissingLinks.p(new Object[] {cx, entityMap});
			insertXyzErr.p(new Object[] {cx, entityMap, setRoot});
		}

		long duration = System.currentTimeMillis()-t1;
		log("Analyzed entity nb: "+dirAnalyzedNb+" (duration="+duration+")");
		
		return results;
	}
	
	/*
	 * LOGGER
	 */
	
	private void log(String msg) throws Exception {
		logger.p(new Object[] {this, msg});
	}
}
