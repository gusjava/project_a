package a.entity.gus06.y.entitysys1.dataloader.entity;

import a.framework.*;
import java.io.File;
import java.sql.Connection;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Iterator;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20251111";}

	private Service getListing;
	private Service findAll;
	private Service remover;
	private Service analyzeEntity;
	private Service compileEntity;
	private Service insertEntity;
	private Service updateEntity;
	
	private Service deleteImports;
	private Service deleteResources;
	private Service deleteServices;
	private Service deleteLinks;
	private Service deleteMissingLinks;
	
	private Service insertImports;
	private Service insertResources;
	private Service insertServices;
	private Service insertLinks;
	private Service insertMissingLinks;

	public EntityImpl() throws Exception
	{
		getListing = Outside.service(this,"gus06.x.entity.srcfile.map_lastmodified.root0");
		findAll = Outside.service(this,"gus06.y.entitydb1.entity.findall.asmap");
		remover = Outside.service(this,"gus06.y.entitydb1.entity.remover");
		analyzeEntity = Outside.service(this,"gus06.y.entitysys1.analyze.entity");
		compileEntity = Outside.service(this,"gus06.y.entitysys1.compile.entity");
		insertEntity = Outside.service(this,"gus06.y.entitydb1.entity.insert");
		updateEntity = Outside.service(this,"gus06.y.entitydb1.entity.update");
		
		deleteImports = Outside.service(this,"gus06.y.entitydb1.entity_import.delete");
		deleteResources = Outside.service(this,"gus06.y.entitydb1.entity_resource.delete");
		deleteServices = Outside.service(this,"gus06.y.entitydb1.entity_service.delete");
		deleteLinks = Outside.service(this,"gus06.y.entitydb1.entity_link.delete1");
		deleteMissingLinks = Outside.service(this,"gus06.y.entitydb1.entity_missing_link.delete1");
		
		insertImports = Outside.service(this,"gus06.y.entitysys1.insert.imports");
		insertResources = Outside.service(this,"gus06.y.entitysys1.insert.resources");
		insertServices = Outside.service(this,"gus06.y.entitysys1.insert.services");
		insertLinks = Outside.service(this,"gus06.y.entitysys1.insert.links");
		insertMissingLinks = Outside.service(this,"gus06.y.entitysys1.insert.missinglinks");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object engine = obj;
		
		Long lastTime = (Long) ((R) engine).r("lastTime");
		File srcDir = (File) ((R) engine).r("srcDir");
		Connection cx = (Connection) ((R) engine).r("cx");
		Set ignore1 = (Set) ((R) engine).r("ignore1");
		
		long t1 = System.currentTimeMillis();
		
		Map mapRoot = (Map) getListing.t(srcDir);
		Set setRoot = mapRoot.keySet();
		
		Map mapDb = (Map) findAll.t(cx);
		if(mapDb==null) throw new Exception("null data retrieved from cx");
		
		Set over = new HashSet(mapDb.keySet());
		over.removeAll(setRoot);
		if(!over.isEmpty()) remover.p(new Object[] {cx, over});
		
		Map results = new HashMap();
		Set analyzed = new HashSet();
		
		int dirAnalyzedNb = 0;
		
		Iterator it = setRoot.iterator();
		while(it.hasNext())
		{
			String entityName = (String) it.next();
			Long lastModified = (Long) mapRoot.get(entityName);
			
			boolean outDated = lastTime==null || lastModified==null || lastModified > lastTime;
			boolean dbFound = mapDb.containsKey(entityName);
			boolean shouldAnalyze = outDated || !dbFound;

			Map entityMap = null;
			
			if(shouldAnalyze)
			{
				dirAnalyzedNb++;
				entityMap = (Map) analyzeEntity.t(new Object[] {entityName, srcDir, setRoot, ignore1});
				analyzed.add(entityName);
				
				if(dbFound) updateEntity.p(new Object[] {cx,entityMap});
				else insertEntity.p(new Object[] {cx,entityMap});
			}
			else entityMap = (Map) mapDb.get(entityName);
			
			results.put(entityName, entityMap);
		}

		// suppressions

		it = analyzed.iterator();
		while(it.hasNext())
		{
			String entityName = (String) it.next();
			if(mapDb.containsKey(entityName))
			{
				deleteImports.p(new Object[] {cx, entityName});
				deleteLinks.p(new Object[] {cx, entityName});
				deleteServices.p(new Object[] {cx, entityName});
				deleteResources.p(new Object[] {cx, entityName});
				deleteMissingLinks.p(new Object[] {cx, entityName});
			}
		}
		
		// compilations

		it = analyzed.iterator();
		while(it.hasNext())
		{
			String entityName = (String) it.next();
			compileEntity.p(new Object[] {engine, entityName});
		}
		
		// insertions

		it = analyzed.iterator();
		while(it.hasNext())
		{
			String entityName = (String) it.next();
			Map entityMap = (Map) results.get(entityName);
			
			insertImports.p(new Object[] {cx, entityMap});
			insertServices.p(new Object[] {cx, entityMap});
			insertResources.p(new Object[] {cx, entityMap});
			insertLinks.p(new Object[] {cx, entityMap});
		}
		
		// 5 - insertion de missingLink

		it = analyzed.iterator();
		while(it.hasNext())
		{
			String entityName = (String) it.next();
			Map entityMap = (Map) results.get(entityName);
			
			insertMissingLinks.p(new Object[] {cx, entityMap});
		}
		return results;
	}
}