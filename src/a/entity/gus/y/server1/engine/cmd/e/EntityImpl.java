package a.entity.gus.y.server1.engine.cmd.e;

import java.util.List;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260412";}


	private Service entityEngine;
	private Service entityCreate;

	public EntityImpl() throws Exception
	{
		entityEngine = Outside.service(this,"gus.y.entitysys1.engine");
		entityCreate = Outside.service(this, "gus.y.entitysys1.perform.entity.create");
	}

	public Object t(Object obj) throws Exception
	{
		List args = (List) obj;
		if(args.isEmpty()) throw new Exception("e: commande manquante");

		String cmd = ((String) args.get(0)).toLowerCase();

		if(cmd.equals("help")) return help();
		if(cmd.equals("create")) return create(args);

		throw new Exception("e: commande inconnue: " + cmd);
	}

	private Object help()
	{
		return 
		"e create <rule> — crée le code source d'une nouvelle entité\n" +
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
}