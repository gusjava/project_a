package a.entity.gus.y.server1.engine.cmd.e;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260414";}

	private Service entityEngine;
	private Service entityCreate;
	private Service entityRename;
	private Service entityDuplicate;
	private Service entityDelete;
	private Service findDownLinks;
	private Service findUplinks;
	private Service findAllSt;
	private Service findAllEn;
	private Service findAllCo;
	private Service namesSt;
	private Service namesEn;
	private Service namesCo;
	private Service countSt;
	private Service countEn;
	private Service countCo;
	private Service findCompileErrors;
	private Service findCompileErrorsAll;
	private Service findSrc;
	private Service findMainFile;
	private Service importFromSrc;
	private Service findFeatures;
	private Service findCreationDate;
	private Service findAllCreationDateMap;
	private Service findAllCreationDateSt;
	private Service findAllCreationDateEn;
	private Service findAllCreationDateCo;
	private Service findAllFeaturesMap;
	private Service findAllFeaturesSt;
	private Service findAllFeaturesEn;
	private Service findAllFeaturesCo;
	private Service findAllDescList;
	private Service findAllDescSt;
	private Service findAllDescEn;
	private Service findAllDescCo;
	private Service uplinksTree;
	private Service downlinksTree;
	private Service uplinksTree2;
	private Service downlinksTree2;
	private Service entityCreateTree;
	private Service cmdESql;

	public EntityImpl() throws Exception
	{
		entityEngine     = Outside.service(this, "gus.y.entitysys1.engine");
		entityCreate     = Outside.service(this, "gus.y.entitysys1.perform.entity.create");
		entityRename     = Outside.service(this, "gus.y.entitysys1.perform.entity.rename");
		entityDuplicate  = Outside.service(this, "gus.y.entitysys1.perform.entity.duplicate");
		entityDelete     = Outside.service(this, "gus.y.entitysys1.perform.entity.delete");
		findDownLinks    = Outside.service(this, "gus.y.entitydb1.entity_link.find2.sorted");
		findUplinks      = Outside.service(this, "gus.y.entitydb1.entity_link.find1.sorted");
		findAllSt        = Outside.service(this, "gus.y.entitydb1.entity.findall.asmap.st");
		findAllEn        = Outside.service(this, "gus.y.entitydb1.entity.findall.asmap.en");
		findAllCo        = Outside.service(this, "gus.y.entitydb1.entity.findall.asmap.co");
		namesSt          = Outside.service(this, "gus.y.entitydb1.entity.findall.names.st");
		namesEn          = Outside.service(this, "gus.y.entitydb1.entity.findall.names.en");
		namesCo          = Outside.service(this, "gus.y.entitydb1.entity.findall.names.co");
		countSt          = Outside.service(this, "gus.y.entitydb1.entity.count.st");
		countEn          = Outside.service(this, "gus.y.entitydb1.entity.count.en");
		countCo          = Outside.service(this, "gus.y.entitydb1.entity.count.co");
		findCompileErrors    = Outside.service(this, "gus.y.entitydb1.entity_compile_err.find");
		findCompileErrorsAll = Outside.service(this, "gus.y.entitydb1.entity_compile_err.findall");
		findSrc          = Outside.service(this, "gus.y.entitysys1.find.src");
		findMainFile     = Outside.service(this, "gus.y.entitysys1.find.mainfile");
		importFromSrc    = Outside.service(this, "gus.y.entitysys1.perform.entity.importsrc");
		findFeatures     = Outside.service(this, "gus.y.entitydb1.entity.find.features");
		findCreationDate = Outside.service(this, "gus.y.entitydb1.entity.find.creationdate");
		findAllCreationDateMap = Outside.service(this, "gus.y.entitydb1.entity.findall.creationdate.asmap");
		findAllCreationDateSt  = Outside.service(this, "gus.y.entitydb1.entity.findall.creationdate.asmap.st");
		findAllCreationDateEn  = Outside.service(this, "gus.y.entitydb1.entity.findall.creationdate.asmap.en");
		findAllCreationDateCo  = Outside.service(this, "gus.y.entitydb1.entity.findall.creationdate.asmap.co");
		findAllFeaturesMap = Outside.service(this, "gus.y.entitydb1.entity.findall.features.asmap");
		findAllFeaturesSt  = Outside.service(this, "gus.y.entitydb1.entity.findall.features.asmap.st");
		findAllFeaturesEn  = Outside.service(this, "gus.y.entitydb1.entity.findall.features.asmap.en");
		findAllFeaturesCo  = Outside.service(this, "gus.y.entitydb1.entity.findall.features.asmap.co");
		findAllDescList = Outside.service(this, "gus.y.entitydb1.entity.findall.desc");
		findAllDescSt   = Outside.service(this, "gus.y.entitydb1.entity.findall.desc.st");
		findAllDescEn   = Outside.service(this, "gus.y.entitydb1.entity.findall.desc.en");
		findAllDescCo   = Outside.service(this, "gus.y.entitydb1.entity.findall.desc.co");
		uplinksTree   = Outside.service(this, "gus.y.entitydb1.entity.uplinkstree");
		downlinksTree = Outside.service(this, "gus.y.entitydb1.entity.downlinkstree");
		uplinksTree2  = Outside.service(this, "gus.y.entitydb1.entity.uplinkstree2");
		downlinksTree2 = Outside.service(this, "gus.y.entitydb1.entity.downlinkstree2");
		entityCreateTree = Outside.service(this, "gus.y.entitysys1.perform.entity.createtree");
		cmdESql          = Outside.service(this, "gus.y.server1.engine.cmd.e_sql");
	}

	public Object t(Object obj) throws Exception
	{
		Map payload = (Map) obj;
		List cmds = (List) payload.get("cmds");
		Object args = payload.get("args");

		if(cmds.size() != 2) throw new Exception("e: incorrect cmd number: "+cmds.size());
		String cmd = (String) cmds.get(1);

		if(cmd.equals("help"))             return help();
		if(cmd.equals("reload"))           return reload();
		
		if(cmd.equals("sql"))              return cmdESql.t(args);
		if(cmd.equals("create"))           return create(args);
		if(cmd.equals("rename"))           return rename(args);
		if(cmd.equals("duplicate"))        return duplicate(args);
		if(cmd.equals("delete"))           return delete(args);
		if(cmd.equals("downlinks"))        return downlinks(args);
		if(cmd.equals("uplinks"))          return uplinks(args);
		if(cmd.equals("uplinkstree"))      return uplinksTree(args);
		if(cmd.equals("downlinkstree"))    return downlinksTree(args);
		if(cmd.equals("uplinkstree2"))     return uplinksTree2(args);
		if(cmd.equals("downlinkstree2"))   return downlinksTree2(args);
		if(cmd.equals("createtree"))       return createtree(args);
		if(cmd.equals("import"))           return import_(args);
		
		if(cmd.equals("errors"))           return errors(args);
		if(cmd.equals("src"))              return src(args);
		if(cmd.equals("path"))             return path(args);
		if(cmd.equals("features"))         return features(args);
		if(cmd.equals("creationdate"))     return creationdate(args);
		
		if(cmd.equals("findall_st"))       return applyOnCxArg(findAllSt, args);
		if(cmd.equals("findall_en"))       return applyOnCxArg(findAllEn, args);
		if(cmd.equals("findall_co"))       return applyOnCxArg(findAllCo, args);
		
		if(cmd.equals("names_st"))         return applyOnCxArg(namesSt, args);
		if(cmd.equals("names_en"))         return applyOnCxArg(namesEn, args);
		if(cmd.equals("names_co"))         return applyOnCxArg(namesCo, args);
		
		if(cmd.equals("count_st"))         return applyOnCxArg(countSt, args);
		if(cmd.equals("count_en"))         return applyOnCxArg(countEn, args);
		if(cmd.equals("count_co"))         return applyOnCxArg(countCo, args);
		
		if(cmd.equals("findall_creationdate")) return applyOnCx(findAllCreationDateMap);
		if(cmd.equals("findall_features"))     return applyOnCx(findAllFeaturesMap);
		if(cmd.equals("findall_desc"))         return applyOnCx(findAllDescList);
		
		if(cmd.equals("findall_creationdate_st")) return applyOnCxArg(findAllCreationDateSt, args);
		if(cmd.equals("findall_creationdate_en")) return applyOnCxArg(findAllCreationDateEn, args);
		if(cmd.equals("findall_creationdate_co")) return applyOnCxArg(findAllCreationDateCo, args);
		
		if(cmd.equals("findall_features_st")) return applyOnCxArg(findAllFeaturesSt, args);
		if(cmd.equals("findall_features_en")) return applyOnCxArg(findAllFeaturesEn, args);
		if(cmd.equals("findall_features_co")) return applyOnCxArg(findAllFeaturesCo, args);
		
		if(cmd.equals("findall_desc_st")) return applyOnCxArg(findAllDescSt, args);
		if(cmd.equals("findall_desc_en")) return applyOnCxArg(findAllDescEn, args);
		if(cmd.equals("findall_desc_co")) return applyOnCxArg(findAllDescCo, args);

		throw new Exception("e: commande inconnue: " + cmd);
	}
	
	// SPECIFIC

	private Object help()
	{
		return
		"e-createtree :<json> \u2014 cr\u00e9e un arbre d'entit\u00e9s depuis un JSON [[\"name-features\",[children...]]] (DFS post-order)\n" +
		"e-create <entity> [features] \u2014 cr\u00e9e le code source d'une nouvelle entit\u00e9 (features : BEFGHIPRSTV, ex: GT)\n" +
		"e-rename <name0> <name1> \u2014 renomme une entit\u00e9 (avec refactor des liens)\n" +
		"e-duplicate <name0> <name1> \u2014 duplique une entit\u00e9\n" +
		"e-delete <name> \u2014 supprime une entit\u00e9\n" +
		"e-downlinks <entity> \u2014 liste les entit\u00e9s qui d\u00e9pendent de <entity>\n" +
		"e-uplinks <entity> \u2014 liste les d\u00e9pendances de <entity>\n" +
		"e-uplinkstree <entity> <maxDeep> \u2014 arbre r\u00e9cursif des uplinks jusqu'\u00e0 la profondeur <maxDeep>\n" +
		"e-downlinkstree <entity> <maxDeep> \u2014 arbre r\u00e9cursif des downlinks jusqu'\u00e0 la profondeur <maxDeep>\n" +
		"e-uplinkstree2 <entity> <maxDeep> \u2014 arbre r\u00e9cursif des uplinks avec descriptions (nom-features)\n" +
		"e-downlinkstree2 <entity> <maxDeep> \u2014 arbre r\u00e9cursif des downlinks avec descriptions (nom-features)\n" +
		"e-import <src> \u2014 cr\u00e9e une entit\u00e9 correspondant au code source <src>\n" +
		"e-sql <sql> \u2014 SQL brut sur entitydb1\n" +
		"e-findall_st <prefix> \u2014 liste les entit\u00e9s dont le nom commence par <prefix>\n" +
		"e-findall_en <suffix> \u2014 liste les entit\u00e9s dont le nom se termine par <suffix>\n" +
		"e-findall_co <fragment> \u2014 liste les entit\u00e9s dont le nom contient <fragment>\n" +
		"e-names_st <prefix> \u2014 liste les noms d'entit\u00e9s commen\u00e7ant par <prefix>\n" +
		"e-names_en <suffix> \u2014 liste les noms d'entit\u00e9s se terminant par <suffix>\n" +
		"e-names_co <fragment> \u2014 liste les noms d'entit\u00e9s contenant <fragment>\n" +
		"e-count_st <prefix> \u2014 nombre d'entit\u00e9s dont le nom commence par <prefix>\n" +
		"e-count_en <suffix> \u2014 nombre d'entit\u00e9s dont le nom se termine par <suffix>\n" +
		"e-count_co <fragment> \u2014 nombre d'entit\u00e9s dont le nom contient <fragment>\n" +
		"e-errors [entity] \u2014 erreurs de compilation (toutes, ou filtr\u00e9es par entit\u00e9)\n" +
		"e-src <entity> \u2014 affiche le code source de l'entit\u00e9\n" +
		"e-path <entity> \u2014 retourne le filepath de EntityImpl.java\n" +
		"e-features <entity> \u2014 retourne les features impl\u00e9ment\u00e9es par l'entit\u00e9\n" +
		"e-creationdate <entity> \u2014 retourne la date de cr\u00e9ation de l'entit\u00e9\n" +
		"e-findall_creationdate \u2014 toutes les entit\u00e9s avec leur date de cr\u00e9ation\n" +
		"e-findall_creationdate_st <prefix> \u2014 entit\u00e9s dont la date de cr\u00e9ation commence par <prefix>\n" +
		"e-findall_creationdate_en <suffix> \u2014 entit\u00e9s dont la date de cr\u00e9ation se termine par <suffix>\n" +
		"e-findall_creationdate_co <fragment> \u2014 entit\u00e9s dont la date de cr\u00e9ation contient <fragment>\n" +
		"e-findall_features \u2014 toutes les entit\u00e9s avec leurs features\n" +
		"e-findall_features_st <prefix> \u2014 entit\u00e9s dont les features commencent par <prefix>\n" +
		"e-findall_features_en <suffix> \u2014 entit\u00e9s dont les features se terminent par <suffix>\n" +
		"e-findall_features_co <fragment> \u2014 entit\u00e9s dont les features contiennent <fragment>\n" +
		"e-findall_desc \u2014 toutes les entit\u00e9s (nom-features) tri\u00e9es par nom\n" +
		"e-findall_desc_st <prefix> \u2014 entit\u00e9s dont le nom commence par <prefix> (nom-features)\n" +
		"e-findall_desc_en <suffix> \u2014 entit\u00e9s dont le nom se termine par <suffix> (nom-features)\n" +
		"e-findall_desc_co <fragment> \u2014 entit\u00e9s dont le nom contient <fragment> (nom-features)\n" +
		"e-reload \u2014 recharge le moteur entity\n" +
		"e-help \u2014 cette aide\n" +
		"(les actions import, create, rename, duplicate, delete sont asynchrones \u2014 patienter un instant avant de v\u00e9rifier)";
	}

	private Object reload() throws Exception
	{
		entityEngine.e();
		return "reloading... (wait 1s before compilation and db update is complete)";
	}

	private Object createtree(Object args) throws Exception
	{
		if(args == null) throw new Exception("Usage: e-createtree :<json>");
		List tree = parseTree((List) args);
		boolean done = (Boolean) entityCreateTree.f(new Object[]{entityEngine, tree});
		return done ? "done" : "createtree failed (entity already exists or invalid name)";
	}

	private List parseTree(List jsonTree) throws Exception
	{
		List result = new ArrayList();
		for(int i=0; i<jsonTree.size(); i++) {
			List node = (List) jsonTree.get(i);
			String desc = (String) node.get(0);
			List children = parseTree((List) node.get(1));
			result.add(new Object[]{desc, children});
		}
		return result;
	}

	private Object import_(Object args) throws Exception
	{
		List list = (List) args;
		if(list == null || list.isEmpty()) throw new Exception("Usage: e-import <src>");
		boolean done = importFromSrc.f(new Object[]{entityEngine, joinArgs(list)});
		return done ? "done" : "import failed";
	}

	private Object create(Object args) throws Exception
	{
		List list = (List) args;
		if(list == null || list.isEmpty()) throw new Exception("Usage: e-create <entity> [features]");
		boolean done = entityCreate.f(new Object[]{entityEngine, joinArgs(list)});
		return done ? "done" : "create failed";
	}

	private Object rename(Object args) throws Exception
	{
		List list = (List) args;
		if(list == null || list.size() < 2) throw new Exception("Usage: e-rename <name0> <name1>");
		String name0 = (String) list.get(0);
		String name1 = (String) list.get(1);
		boolean done = (Boolean) entityRename.f(new Object[]{entityEngine, name0, name1, true});
		return done ? "done" : "rename failed (entity not found or target already exists)";
	}

	private Object duplicate(Object args) throws Exception
	{
		List list = (List) args;
		if(list == null || list.size() < 2) throw new Exception("Usage: e-duplicate <name0> <name1>");
		String name0 = (String) list.get(0);
		String name1 = (String) list.get(1);
		boolean done = (Boolean) entityDuplicate.f(new Object[]{entityEngine, name0, name1});
		return done ? "done" : "duplicate failed (entity not found or target already exists)";
	}

	private Object delete(Object args) throws Exception
	{
		List list = (List) args;
		if(list == null || list.isEmpty()) throw new Exception("Usage: e-delete <name>");
		String name = (String) list.get(0);
		boolean done = (Boolean) entityDelete.f(new Object[]{entityEngine, name});
		return done ? "done" : "delete failed (entity not found or outside devId scope)";
	}

	private Object downlinks(Object args) throws Exception
	{
		List list = (List) args;
		if(list == null || list.isEmpty()) throw new Exception("Usage: e-downlinks <entity>");
		return (List) applyOnCxArg(findDownLinks, list);
	}

	private Object uplinks(Object args) throws Exception
	{
		List list = (List) args;
		if(list == null || list.isEmpty()) throw new Exception("Usage: e-uplinks <entity>");
		return (List) applyOnCxArg(findUplinks, list);
	}

	private Object uplinksTree(Object args) throws Exception
	{
		List list = (List) args;
		if(list == null || list.size() < 2) throw new Exception("Usage: e-uplinkstree <entity> <maxDeep>");
		return uplinksTree.t(new Object[]{cx(), (String) list.get(0), Integer.parseInt((String) list.get(1))});
	}

	private Object downlinksTree(Object args) throws Exception
	{
		List list = (List) args;
		if(list == null || list.size() < 2) throw new Exception("Usage: e-downlinkstree <entity> <maxDeep>");
		return downlinksTree.t(new Object[]{cx(), (String) list.get(0), Integer.parseInt((String) list.get(1))});
	}

	private Object uplinksTree2(Object args) throws Exception
	{
		List list = (List) args;
		if(list == null || list.size() < 2) throw new Exception("Usage: e-uplinkstree2 <entity> <maxDeep>");
		return uplinksTree2.t(new Object[]{cx(), (String) list.get(0), Integer.parseInt((String) list.get(1))});
	}

	private Object downlinksTree2(Object args) throws Exception
	{
		List list = (List) args;
		if(list == null || list.size() < 2) throw new Exception("Usage: e-downlinkstree2 <entity> <maxDeep>");
		return downlinksTree2.t(new Object[]{cx(), (String) list.get(0), Integer.parseInt((String) list.get(1))});
	}

	private Object errors(Object args) throws Exception
	{
		List list = (List) args;
		if(list != null && !list.isEmpty()) return (List) applyOnCxArg(findCompileErrors, list);
		return applyOnCx(findCompileErrorsAll);
	}

	private Object src(Object args) throws Exception
	{
		List list = (List) args;
		if(list == null || list.isEmpty()) throw new Exception("Usage: e-src <entity>");
		String name = (String) list.get(0);
		Object src = findSrc.t(new Object[]{entityEngine, name});
		if(src == null) throw new Exception("Entity not found: " + name);
		return src;
	}

	private Object path(Object args) throws Exception
	{
		List list = (List) args;
		if(list == null || list.isEmpty()) throw new Exception("Usage: e-path <entity>");
		String name = (String) list.get(0);
		Object file = findMainFile.t(new Object[]{entityEngine, name});
		if(file == null) throw new Exception("Entity not found: " + name);
		return file.toString();
	}

	private Object creationdate(Object args) throws Exception
	{
		List list = (List) args;
		if(list == null || list.isEmpty()) throw new Exception("Usage: e-creationdate <entity>");
		String name = (String) list.get(0);
		Object creationDate = findCreationDate.t(new Object[]{cx(), name});
		if(creationDate == null) throw new Exception("Entity not found: " + name);
		return creationDate.toString();
	}

	private Object features(Object args) throws Exception
	{
		List list = (List) args;
		if(list == null || list.isEmpty()) throw new Exception("Usage: e-features <entity>");
		String name = (String) list.get(0);
		String features = (String) findFeatures.t(new Object[]{cx(), name});
		if(features == null) throw new Exception("Entity not found: " + name);
		return features.toUpperCase();
	}
	
	// GENERIC

	private Object applyOnCx(Service service) throws Exception
	{return service.t(cx());}

	private Object applyOnCxArg(Service service, Object args) throws Exception
	{return service.t(new Object[]{cx(), joinArgs(args)});}

	private Connection cx() throws Exception
	{return (Connection) entityEngine.r("cx");}

	private String joinArgs(Object args) throws Exception
	{
		if(args instanceof String) return (String) args;
		if(args instanceof List) return joinArgs((List) args);
		throw new Exception("Invalid args type: "+args.getClass().getSimpleName());
	}
	
	private String joinArgs(List args)
	{
		StringBuilder sb = new StringBuilder((String) args.get(0));
		for(int i=1; i<args.size(); i++) sb.append(" ").append(args.get(i));
		return sb.toString();
	}
}
