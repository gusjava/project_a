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
	private Service findCompileErrors;
	private Service findCompileErrorsAll;

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
		findCompileErrors = Outside.service(this, "gus.y.entitydb1.entity_compile_err.find");
		findCompileErrorsAll = Outside.service(this, "gus.y.entitydb1.entity_compile_err.findall");
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
		if(cmd.equals("st")) return findFiltered(args, findAllSt, "st");
		if(cmd.equals("en")) return findFiltered(args, findAllEn, "en");
		if(cmd.equals("co")) return findFiltered(args, findAllCo, "co");
		if(cmd.equals("errors")) return errors(args);

		throw new Exception("e: commande inconnue: " + cmd);
	}

	private Object help()
	{
		return
		"e create <entity> [features] — crée le code source d'une nouvelle entité (features : BEFGHIPRSTV, ex: GT)\n" +
		"e rename <name0> <name1> — renomme une entité (avec refactor des liens)\n" +
		"e duplicate <name0> <name1> — duplique une entité\n" +
		"e delete <name> — supprime une entité\n" +
		"e downlinks <entity> — liste les entités qui dépendent de <entity>\n" +
		"e uplinks <entity> — liste les dépendances de <entity>\n" +
		"e st <prefix> — liste les entités dont le nom commence par <prefix>\n" +
		"e en <suffix> — liste les entités dont le nom se termine par <suffix>\n" +
		"e co <fragment> — liste les entités dont le nom contient <fragment>\n" +
		"e help — cette aide\n" +
		"e errors [entity] — erreurs de compilation (toutes, ou filtrées par entité)";
	}
	
	private Object reload() throws Exception
	{
		entityEngine.e();
		return "reloading...";
	}

	private Object create(List args) throws Exception
	{
		if(args.size()<2) throw new Exception("Usage: e create <entity> [features]");
		StringBuilder rule = new StringBuilder((String) args.get(1));
		for(int i=2; i<args.size(); i++) rule.append(" ").append(args.get(i));
		entityCreate.f(new Object[]{entityEngine, rule.toString()});
		return "done";
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

	private Object errors(List args) throws Exception
	{
		Connection cx = (Connection) entityEngine.r("cx");
		if(args.size() >= 2)
			return (List) findCompileErrors.t(new Object[]{cx, (String) args.get(1)});
		return (Map) findCompileErrorsAll.t(cx);
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
}
