package a.entity.gus.y.server1.engine.cmd.e;

import java.util.List;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260412";}


	private Service entityEngine;
	private Service entityCreate;
	private Service entityRename;
	private Service entityDuplicate;
	private Service entityDelete;

	public EntityImpl() throws Exception
	{
		entityEngine = Outside.service(this,"gus.y.entitysys1.engine");
		entityCreate = Outside.service(this, "gus.y.entitysys1.perform.entity.create");
		entityRename = Outside.service(this, "gus.y.entitysys1.perform.entity.rename");
		entityDuplicate = Outside.service(this, "gus.y.entitysys1.perform.entity.duplicate");
		entityDelete = Outside.service(this, "gus.y.entitysys1.perform.entity.delete");
	}

	public Object t(Object obj) throws Exception
	{
		List args = (List) obj;
		if(args.isEmpty()) throw new Exception("e: commande manquante");

		String cmd = ((String) args.get(0)).toLowerCase();

		if(cmd.equals("help")) return help();
		if(cmd.equals("create")) return create(args);
		if(cmd.equals("rename")) return rename(args);
		if(cmd.equals("duplicate")) return duplicate(args);
		if(cmd.equals("delete")) return delete(args);

		throw new Exception("e: commande inconnue: " + cmd);
	}

	private Object help()
	{
		return
		"e create <rule> — crée le code source d'une nouvelle entité\n" +
		"e rename <name0> <name1> — renomme une entité (avec refactor des liens)\n" +
		"e duplicate <name0> <name1> — duplique une entité\n" +
		"e delete <name> — supprime une entité\n" +
		"e help — cette aide";
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
}