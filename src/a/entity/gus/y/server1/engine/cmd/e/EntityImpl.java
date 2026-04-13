package a.entity.gus.y.server1.engine.cmd.e;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260412";}

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
	private Service jsonParser;

	public EntityImpl() throws Exception
	{
		entityEngine = Outside.service(this,"gus.y.entitysys1.engine");
		entityCreate = Outside.service(this, "gus.y.entitysys1.perform.entity.create");
		entityRename = Outside.service(this, "gus.y.entitysys1.perform.entity.rename");
		entityDuplicate = Outside.service(this, "gus.y.entitysys1.perform.entity.duplicate");
		entityDelete = Outside.service(this, "gus.y.entitysys1.perform.entity.delete");
		findDownLinks = Outside.service(this, "gus.y.entitydb1.entity_link.find2.sorted");
		findUplinks = Outside.service(this, "gus.y.entitydb1.entity_link.find1.sorted");
		findAllSt = Outside.service(this, "gus.y.entitydb1.entity.findall.asmap.st");
		findAllEn = Outside.service(this, "gus.y.entitydb1.entity.findall.asmap.en");
		findAllCo = Outside.service(this, "gus.y.entitydb1.entity.findall.asmap.co");
		namesSt = Outside.service(this, "gus.y.entitydb1.entity.findall.names.st");
		namesEn = Outside.service(this, "gus.y.entitydb1.entity.findall.names.en");
		namesCo = Outside.service(this, "gus.y.entitydb1.entity.findall.names.co");
		countSt = Outside.service(this, "gus.y.entitydb1.entity.count.st");
		countEn = Outside.service(this, "gus.y.entitydb1.entity.count.en");
		countCo = Outside.service(this, "gus.y.entitydb1.entity.count.co");
		findCompileErrors = Outside.service(this, "gus.y.entitydb1.entity_compile_err.find");
		findCompileErrorsAll = Outside.service(this, "gus.y.entitydb1.entity_compile_err.findall");
		findSrc = Outside.service(this, "gus.y.entitysys1.find.src");
		findMainFile = Outside.service(this, "gus.y.entitysys1.find.mainfile");
		importFromSrc = Outside.service(this, "gus.y.entitysys1.perform.entity.importsrc");
		findFeatures = Outside.service(this, "gus.y.entitydb1.entity.find.features");
		findCreationDate = Outside.service(this, "gus.y.entitydb1.entity.find.creationdate");
		findAllCreationDateMap = Outside.service(this, "gus.y.entitydb1.entity.findall.creationdate.asmap");
		findAllCreationDateSt = Outside.service(this, "gus.y.entitydb1.entity.findall.creationdate.asmap.st");
		findAllCreationDateEn = Outside.service(this, "gus.y.entitydb1.entity.findall.creationdate.asmap.en");
		findAllCreationDateCo = Outside.service(this, "gus.y.entitydb1.entity.findall.creationdate.asmap.co");
		findAllFeaturesMap = Outside.service(this, "gus.y.entitydb1.entity.findall.features.asmap");
		findAllFeaturesSt = Outside.service(this, "gus.y.entitydb1.entity.findall.features.asmap.st");
		findAllFeaturesEn = Outside.service(this, "gus.y.entitydb1.entity.findall.features.asmap.en");
		findAllFeaturesCo = Outside.service(this, "gus.y.entitydb1.entity.findall.features.asmap.co");
		findAllDescList = Outside.service(this, "gus.y.entitydb1.entity.findall.desc");
		findAllDescSt = Outside.service(this, "gus.y.entitydb1.entity.findall.desc.st");
		findAllDescEn = Outside.service(this, "gus.y.entitydb1.entity.findall.desc.en");
		findAllDescCo = Outside.service(this, "gus.y.entitydb1.entity.findall.desc.co");
		uplinksTree = Outside.service(this, "gus.y.entitydb1.entity.uplinkstree");
		downlinksTree = Outside.service(this, "gus.y.entitydb1.entity.downlinkstree");
		uplinksTree2 = Outside.service(this, "gus.y.entitydb1.entity.uplinkstree2");
		downlinksTree2 = Outside.service(this, "gus.y.entitydb1.entity.downlinkstree2");
		entityCreateTree = Outside.service(this, "gus.y.entitysys1.perform.entity.createtree");
		jsonParser = Outside.service(this, "gus06.file.convert.json.parser");
	}

	public Object t(Object obj) throws Exception
	{
		List args = (List) obj;
		if(args.isEmpty()) throw new Exception("e: commande manquante");

		String cmd = ((String) args.get(0)).toLowerCase();

		if(cmd.equals("help")) return help();
		if(cmd.equals("reload")) return reload();
		if(cmd.equals("create")) return create(args);
		if(cmd.equals("rename")) return rename(args);
		if(cmd.equals("duplicate")) return duplicate(args);
		if(cmd.equals("delete")) return delete(args);
		if(cmd.equals("downlinks")) return downlinks(args);
		if(cmd.equals("uplinks")) return uplinks(args);
		if(cmd.equals("uplinkstree")) return uplinksTree(args);
		if(cmd.equals("downlinkstree")) return downlinksTree(args);
		if(cmd.equals("uplinkstree2")) return uplinksTree2(args);
		if(cmd.equals("downlinkstree2")) return downlinksTree2(args);
		if(cmd.equals("createtree")) return createtree(args);
		if(cmd.equals("import")) return import_(args);
		if(cmd.equals("findall_st")) return findFiltered(args, findAllSt, "findall_st");
		if(cmd.equals("findall_en")) return findFiltered(args, findAllEn, "findall_en");
		if(cmd.equals("findall_co")) return findFiltered(args, findAllCo, "findall_co");
		if(cmd.equals("names_st")) return findFilteredNames(args, namesSt, "names_st");
		if(cmd.equals("names_en")) return findFilteredNames(args, namesEn, "names_en");
		if(cmd.equals("names_co")) return findFilteredNames(args, namesCo, "names_co");
		if(cmd.equals("count_st")) return countFiltered(args, countSt, "count_st");
		if(cmd.equals("count_en")) return countFiltered(args, countEn, "count_en");
		if(cmd.equals("count_co")) return countFiltered(args, countCo, "count_co");
		if(cmd.equals("errors")) return errors(args);
		if(cmd.equals("src")) return src(args);
		if(cmd.equals("path")) return path(args);
		if(cmd.equals("features")) return features(args);
		if(cmd.equals("creationdate")) return creationdate(args);
		if(cmd.equals("findall_creationdate")) return findAll(findAllCreationDateMap);
		if(cmd.equals("findall_creationdate_st")) return findFilteredMap(args, findAllCreationDateSt, "findall_creationdate_st");
		if(cmd.equals("findall_creationdate_en")) return findFilteredMap(args, findAllCreationDateEn, "findall_creationdate_en");
		if(cmd.equals("findall_creationdate_co")) return findFilteredMap(args, findAllCreationDateCo, "findall_creationdate_co");
		if(cmd.equals("findall_features")) return findAll(findAllFeaturesMap);
		if(cmd.equals("findall_features_st")) return findFilteredMap(args, findAllFeaturesSt, "findall_features_st");
		if(cmd.equals("findall_features_en")) return findFilteredMap(args, findAllFeaturesEn, "findall_features_en");
		if(cmd.equals("findall_features_co")) return findFilteredMap(args, findAllFeaturesCo, "findall_features_co");
		if(cmd.equals("findall_desc")) return findAll(findAllDescList);
		if(cmd.equals("findall_desc_st")) return findFilteredNames(args, findAllDescSt, "findall_desc_st");
		if(cmd.equals("findall_desc_en")) return findFilteredNames(args, findAllDescEn, "findall_desc_en");
		if(cmd.equals("findall_desc_co")) return findFilteredNames(args, findAllDescCo, "findall_desc_co");

		throw new Exception("e: commande inconnue: " + cmd);
	}

	private Object help()
	{
		return
		"e createtree :<json> \u2014 cr\u00e9e un arbre d'entit\u00e9s depuis un JSON [[\"name-features\",[children...]]] (DFS post-order)\n" +
		"e create <entity> [features] \u2014 cr\u00e9e le code source d'une nouvelle entit\u00e9 (features : BEFGHIPRSTV, ex: GT)\n" +
		"e rename <name0> <name1> \u2014 renomme une entit\u00e9 (avec refactor des liens)\n" +
		"e duplicate <name0> <name1> \u2014 duplique une entit\u00e9\n" +
		"e delete <name> \u2014 supprime une entit\u00e9\n" +
		"e downlinks <entity> \u2014 liste les entit\u00e9s qui d\u00e9pendent de <entity>\n" +
		"e uplinks <entity> \u2014 liste les d\u00e9pendances de <entity>\n" +
		"e uplinkstree <entity> <maxDeep> \u2014 arbre r\u00e9cursif des uplinks jusqu'\u00e0 la profondeur <maxDeep>\n" +
		"e downlinkstree <entity> <maxDeep> \u2014 arbre r\u00e9cursif des downlinks jusqu'\u00e0 la profondeur <maxDeep>\n" +
		"e uplinkstree2 <entity> <maxDeep> \u2014 arbre r\u00e9cursif des uplinks avec descriptions (nom-features)\n" +
		"e downlinkstree2 <entity> <maxDeep> \u2014 arbre r\u00e9cursif des downlinks avec descriptions (nom-features)\n" +
		"e import <src> \u2014 cr\u00e9e une entit\u00e9 correspondant au code source <src>\n" +
		"e findall_st <prefix> \u2014 liste les entit\u00e9s dont le nom commence par <prefix>\n" +
		"e findall_en <suffix> \u2014 liste les entit\u00e9s dont le nom se termine par <suffix>\n" +
		"e findall_co <fragment> \u2014 liste les entit\u00e9s dont le nom contient <fragment>\n" +
		"e names_st <prefix> \u2014 liste les noms d'entit\u00e9s commen\u00e7ant par <prefix>\n" +
		"e names_en <suffix> \u2014 liste les noms d'entit\u00e9s se terminant par <suffix>\n" +
		"e names_co <fragment> \u2014 liste les noms d'entit\u00e9s contenant <fragment>\n" +
		"e count_st <prefix> \u2014 nombre d'entit\u00e9s dont le nom commence par <prefix>\n" +
		"e count_en <suffix> \u2014 nombre d'entit\u00e9s dont le nom se termine par <suffix>\n" +
		"e count_co <fragment> \u2014 nombre d'entit\u00e9s dont le nom contient <fragment>\n" +
		"e help \u2014 cette aide\n" +
		"e errors [entity] \u2014 erreurs de compilation (toutes, ou filtr\u00e9es par entit\u00e9)\n" +
		"e src <entity> \u2014 affiche le code source de l'entit\u00e9\n" +
		"e path <entity> \u2014 retourne le filepath de EntityImpl.java\n" +
		"e features <entity> \u2014 retourne les features impl\u00e9ment\u00e9es par l'entit\u00e9\n" +
		"e creationdate <entity> \u2014 retourne la date de cr\u00e9ation de l'entit\u00e9\n" +
		"e findall_creationdate \u2014 toutes les entit\u00e9s avec leur date de cr\u00e9ation\n" +
		"e findall_creationdate_st <prefix> \u2014 entit\u00e9s dont la date de cr\u00e9ation commence par <prefix>\n" +
		"e findall_creationdate_en <suffix> \u2014 entit\u00e9s dont la date de cr\u00e9ation se termine par <suffix>\n" +
		"e findall_creationdate_co <fragment> \u2014 entit\u00e9s dont la date de cr\u00e9ation contient <fragment>\n" +
		"e findall_features \u2014 toutes les entit\u00e9s avec leurs features\n" +
		"e findall_features_st <prefix> \u2014 entit\u00e9s dont les features commencent par <prefix>\n" +
		"e findall_features_en <suffix> \u2014 entit\u00e9s dont les features se terminent par <suffix>\n" +
		"e findall_features_co <fragment> \u2014 entit\u00e9s dont les features contiennent <fragment>\n" +
		"e findall_desc \u2014 toutes les entit\u00e9s (nom-features) tri\u00e9es par nom\n" +
		"e findall_desc_st <prefix> \u2014 entit\u00e9s dont le nom commence par <prefix> (nom-features)\n" +
		"e findall_desc_en <suffix> \u2014 entit\u00e9s dont le nom se termine par <suffix> (nom-features)\n" +
		"e findall_desc_co <fragment> \u2014 entit\u00e9s dont le nom contient <fragment> (nom-features)\n" +
		"(les actions import, create, rename, duplicate, delete sont asynchrones \u2014 patienter un instant avant de v\u00e9rifier)";
	}

	private Object reload() throws Exception
	{
		entityEngine.e();
		return "reloading... (wait 1s before compilation and db update is complete)";
	}

	private Object createtree(List args) throws Exception
	{
		if(args.size()<2) throw new Exception("Usage: e createtree :<json>");
		String json = joinArgs(args);
		if(json.startsWith(":")) json = json.substring(1);
		List tree = parseTree((List) jsonParser.t(json));
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

	private Object import_(List args) throws Exception
	{
		if(args.size()<2) throw new Exception("Usage: e import <src>");
		boolean done = importFromSrc.f(new Object[]{entityEngine, joinArgs(args)});
		return done ? "done" : "import failed";
	}

	private Object create(List args) throws Exception
	{
		if(args.size()<2) throw new Exception("Usage: e create <entity> [features]");
		boolean done = entityCreate.f(new Object[]{entityEngine, joinArgs(args)});
		return done ? "done" : "create failed";
	}

	private Object rename(List args) throws Exception
	{
		if(args.size()<3) throw new Exception("Usage: e rename <name0> <name1>");
		String name0 = (String) args.get(1);
		String name1 = (String) args.get(2);
		boolean done = (Boolean) entityRename.f(new Object[]{entityEngine, name0, name1, true});
		return done ? "done" : "rename failed (entity not found or target already exists)";
	}

	private Object duplicate(List args) throws Exception
	{
		if(args.size()<3) throw new Exception("Usage: e duplicate <name0> <name1>");
		String name0 = (String) args.get(1);
		String name1 = (String) args.get(2);
		boolean done = (Boolean) entityDuplicate.f(new Object[]{entityEngine, name0, name1});
		return done ? "done" : "duplicate failed (entity not found or target already exists)";
	}

	private Object delete(List args) throws Exception
	{
		if(args.size()<2) throw new Exception("Usage: e delete <name>");
		String name = (String) args.get(1);
		boolean done = (Boolean) entityDelete.f(new Object[]{entityEngine, name});
		return done ? "done" : "delete failed (entity not found or outside devId scope)";
	}

	private Object downlinks(List args) throws Exception
	{
		if(args.size()<2) throw new Exception("Usage: e downlinks <entity>");
		String name = (String) args.get(1);
		Connection cx = (Connection) entityEngine.r("cx");
		return (List) findDownLinks.t(new Object[]{cx, name});
	}

	private Object uplinks(List args) throws Exception
	{
		if(args.size()<2) throw new Exception("Usage: e uplinks <entity>");
		String name = (String) args.get(1);
		Connection cx = (Connection) entityEngine.r("cx");
		return (List) findUplinks.t(new Object[]{cx, name});
	}

	private Object uplinksTree(List args) throws Exception
	{
		if(args.size()<3) throw new Exception("Usage: e uplinkstree <entity> <maxDeep>");
		String name = (String) args.get(1);
		int maxDeep = Integer.parseInt((String) args.get(2));
		Connection cx = (Connection) entityEngine.r("cx");
		return uplinksTree.t(new Object[]{cx, name, maxDeep});
	}

	private Object downlinksTree(List args) throws Exception
	{
		if(args.size()<3) throw new Exception("Usage: e downlinkstree <entity> <maxDeep>");
		String name = (String) args.get(1);
		int maxDeep = Integer.parseInt((String) args.get(2));
		Connection cx = (Connection) entityEngine.r("cx");
		return downlinksTree.t(new Object[]{cx, name, maxDeep});
	}

	private Object uplinksTree2(List args) throws Exception
	{
		if(args.size()<3) throw new Exception("Usage: e uplinkstree2 <entity> <maxDeep>");
		String name = (String) args.get(1);
		int maxDeep = Integer.parseInt((String) args.get(2));
		Connection cx = (Connection) entityEngine.r("cx");
		return uplinksTree2.t(new Object[]{cx, name, maxDeep});
	}

	private Object downlinksTree2(List args) throws Exception
	{
		if(args.size()<3) throw new Exception("Usage: e downlinkstree2 <entity> <maxDeep>");
		String name = (String) args.get(1);
		int maxDeep = Integer.parseInt((String) args.get(2));
		Connection cx = (Connection) entityEngine.r("cx");
		return downlinksTree2.t(new Object[]{cx, name, maxDeep});
	}

	private Object errors(List args) throws Exception
	{
		Connection cx = (Connection) entityEngine.r("cx");
		if(args.size() >= 2) return (List) findCompileErrors.t(new Object[]{cx, (String) args.get(1)});
		return (Map) findCompileErrorsAll.t(cx);
	}

	private Object src(List args) throws Exception
	{
		if(args.size()<2) throw new Exception("Usage: e src <entity>");
		String name = (String) args.get(1);
		Object src = findSrc.t(new Object[]{entityEngine, name});
		if(src == null) throw new Exception("Entity not found: " + name);
		return src;
	}

	private Object path(List args) throws Exception
	{
		if(args.size()<2) throw new Exception("Usage: e path <entity>");
		String name = (String) args.get(1);
		Object file = findMainFile.t(new Object[]{entityEngine, name});
		if(file == null) throw new Exception("Entity not found: " + name);
		return file.toString();
	}

	private String joinArgs(List args)
	{
		StringBuilder sb = new StringBuilder((String) args.get(1));
		for(int i=2; i<args.size(); i++) sb.append(" ").append(args.get(i));
		return sb.toString();
	}

	private Object creationdate(List args) throws Exception
	{
		if(args.size()<2) throw new Exception("Usage: e creationdate <entity>");
		String name = (String) args.get(1);
		Connection cx = (Connection) entityEngine.r("cx");
		Object creationDate = findCreationDate.t(new Object[]{cx, name});
		if(creationDate == null) throw new Exception("Entity not found: " + name);
		return creationDate.toString();
	}

	private Object features(List args) throws Exception
	{
		if(args.size()<2) throw new Exception("Usage: e features <entity>");
		String name = (String) args.get(1);
		Connection cx = (Connection) entityEngine.r("cx");
		String features = (String) findFeatures.t(new Object[]{cx, name});
		if(features == null) throw new Exception("Entity not found: " + name);
		return features.toUpperCase();
	}

	private Object findFiltered(List args, Service service, String cmd) throws Exception
	{
		if(args.size()<2) throw new Exception("Usage: e " + cmd + " <filtre>");
		String filter = (String) args.get(1);
		Connection cx = (Connection) entityEngine.r("cx");
		Map result = (Map) service.t(new Object[]{cx, filter});
		List names = new ArrayList(result.keySet());
		Collections.sort(names);
		return names;
	}

	private Object findFilteredNames(List args, Service service, String cmd) throws Exception
	{
		if(args.size()<2) throw new Exception("Usage: e " + cmd + " <filtre>");
		String filter = (String) args.get(1);
		Connection cx = (Connection) entityEngine.r("cx");
		return (List) service.t(new Object[]{cx, filter});
	}

	private Object countFiltered(List args, Service service, String cmd) throws Exception
	{
		if(args.size()<2) throw new Exception("Usage: e " + cmd + " <filtre>");
		String filter = (String) args.get(1);
		Connection cx = (Connection) entityEngine.r("cx");
		return service.t(new Object[]{cx, filter});
	}

	private Object findAll(Service service) throws Exception
	{
		Connection cx = (Connection) entityEngine.r("cx");
		return service.t(cx);
	}

	private Object findFilteredMap(List args, Service service, String cmd) throws Exception
	{
		if(args.size()<2) throw new Exception("Usage: e " + cmd + " <filtre>");
		String filter = (String) args.get(1);
		Connection cx = (Connection) entityEngine.r("cx");
		return service.t(new Object[]{cx, filter});
	}
}
