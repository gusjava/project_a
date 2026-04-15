package a.entity.gus.y.server1.engine.cmd.e;

import java.util.List;
import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260414";}

	private Service help;
	private Service reload;
	private Service sql;
	private Service create;
	private Service rename;
	private Service duplicate;
	private Service delete;
	private Service downlinks;
	private Service uplinks;
	private Service uplinkstree;
	private Service downlinkstree;
	private Service uplinkstree2;
	private Service downlinkstree2;
	private Service createtree;
	private Service importsrc;
	private Service insertbloc;
	private Service removebloc;
	private Service replacebloc;
	private Service errors;
	private Service src;
	private Service path;
	private Service features;
	private Service creationdate;
	private Service findall_st;
	private Service findall_en;
	private Service findall_co;
	private Service names_st;
	private Service names_en;
	private Service names_co;
	private Service count_st;
	private Service count_en;
	private Service count_co;
	private Service findall_creationdate;
	private Service findall_features;
	private Service findall_desc;
	private Service findall_creationdate_st;
	private Service findall_creationdate_en;
	private Service findall_creationdate_co;
	private Service findall_features_st;
	private Service findall_features_en;
	private Service findall_features_co;
	private Service findall_desc_st;
	private Service findall_desc_en;
	private Service findall_desc_co;

	public EntityImpl() throws Exception
	{
		help       = Outside.service(this, "gus.y.server1.engine.cmd.e.help");
		reload     = Outside.service(this, "gus.y.server1.engine.cmd.e.reload");
		sql        = Outside.service(this, "gus.y.server1.engine.cmd.e_sql");
		create     = Outside.service(this, "gus.y.server1.engine.cmd.e.create");
		rename     = Outside.service(this, "gus.y.server1.engine.cmd.e.rename");
		duplicate  = Outside.service(this, "gus.y.server1.engine.cmd.e.duplicate");
		delete     = Outside.service(this, "gus.y.server1.engine.cmd.e.delete");
		downlinks      = Outside.service(this, "gus.y.server1.engine.cmd.e.downlinks");
		uplinks        = Outside.service(this, "gus.y.server1.engine.cmd.e.uplinks");
		uplinkstree    = Outside.service(this, "gus.y.server1.engine.cmd.e.uplinkstree");
		downlinkstree  = Outside.service(this, "gus.y.server1.engine.cmd.e.downlinkstree");
		uplinkstree2   = Outside.service(this, "gus.y.server1.engine.cmd.e.uplinkstree2");
		downlinkstree2 = Outside.service(this, "gus.y.server1.engine.cmd.e.downlinkstree2");
		createtree  = Outside.service(this, "gus.y.server1.engine.cmd.e.createtree");
		importsrc   = Outside.service(this, "gus.y.server1.engine.cmd.e.importsrc");
		insertbloc  = Outside.service(this, "gus.y.server1.engine.cmd.e.insertbloc");
		removebloc  = Outside.service(this, "gus.y.server1.engine.cmd.e.removebloc");
		replacebloc = Outside.service(this, "gus.y.server1.engine.cmd.e.replacebloc");
		errors      = Outside.service(this, "gus.y.server1.engine.cmd.e.errors");
		src         = Outside.service(this, "gus.y.server1.engine.cmd.e.src");
		path        = Outside.service(this, "gus.y.server1.engine.cmd.e.path");
		features    = Outside.service(this, "gus.y.server1.engine.cmd.e.features");
		creationdate = Outside.service(this, "gus.y.server1.engine.cmd.e.creationdate");
		findall_st  = Outside.service(this, "gus.y.server1.engine.cmd.e.findall_st");
		findall_en  = Outside.service(this, "gus.y.server1.engine.cmd.e.findall_en");
		findall_co  = Outside.service(this, "gus.y.server1.engine.cmd.e.findall_co");
		names_st    = Outside.service(this, "gus.y.server1.engine.cmd.e.names_st");
		names_en    = Outside.service(this, "gus.y.server1.engine.cmd.e.names_en");
		names_co    = Outside.service(this, "gus.y.server1.engine.cmd.e.names_co");
		count_st    = Outside.service(this, "gus.y.server1.engine.cmd.e.count_st");
		count_en    = Outside.service(this, "gus.y.server1.engine.cmd.e.count_en");
		count_co    = Outside.service(this, "gus.y.server1.engine.cmd.e.count_co");
		findall_creationdate    = Outside.service(this, "gus.y.server1.engine.cmd.e.findall_creationdate");
		findall_features        = Outside.service(this, "gus.y.server1.engine.cmd.e.findall_features");
		findall_desc            = Outside.service(this, "gus.y.server1.engine.cmd.e.findall_desc");
		findall_creationdate_st = Outside.service(this, "gus.y.server1.engine.cmd.e.findall_creationdate_st");
		findall_creationdate_en = Outside.service(this, "gus.y.server1.engine.cmd.e.findall_creationdate_en");
		findall_creationdate_co = Outside.service(this, "gus.y.server1.engine.cmd.e.findall_creationdate_co");
		findall_features_st     = Outside.service(this, "gus.y.server1.engine.cmd.e.findall_features_st");
		findall_features_en     = Outside.service(this, "gus.y.server1.engine.cmd.e.findall_features_en");
		findall_features_co     = Outside.service(this, "gus.y.server1.engine.cmd.e.findall_features_co");
		findall_desc_st         = Outside.service(this, "gus.y.server1.engine.cmd.e.findall_desc_st");
		findall_desc_en         = Outside.service(this, "gus.y.server1.engine.cmd.e.findall_desc_en");
		findall_desc_co         = Outside.service(this, "gus.y.server1.engine.cmd.e.findall_desc_co");
	}

	public Object t(Object obj) throws Exception
	{
		Map payload = (Map) obj;
		List cmds = (List) payload.get("cmds");
		Object args = payload.get("args");

		if(cmds.size() != 2) throw new Exception("e: incorrect cmd number: "+cmds.size());
		String cmd = (String) cmds.get(1);

		if(cmd.equals("help"))             return help.t(args);
		if(cmd.equals("reload"))           return reload.t(args);
		if(cmd.equals("sql"))              return sql.t(args);
		if(cmd.equals("create"))           return create.t(args);
		if(cmd.equals("rename"))           return rename.t(args);
		if(cmd.equals("duplicate"))        return duplicate.t(args);
		if(cmd.equals("delete"))           return delete.t(args);
		if(cmd.equals("downlinks"))        return downlinks.t(args);
		if(cmd.equals("uplinks"))          return uplinks.t(args);
		if(cmd.equals("uplinkstree"))      return uplinkstree.t(args);
		if(cmd.equals("downlinkstree"))    return downlinkstree.t(args);
		if(cmd.equals("uplinkstree2"))     return uplinkstree2.t(args);
		if(cmd.equals("downlinkstree2"))   return downlinkstree2.t(args);
		if(cmd.equals("createtree"))       return createtree.t(args);
		if(cmd.equals("importsrc"))        return importsrc.t(args);
		if(cmd.equals("insertbloc"))       return insertbloc.t(args);
		if(cmd.equals("removebloc"))       return removebloc.t(args);
		if(cmd.equals("replacebloc"))      return replacebloc.t(args);
		if(cmd.equals("errors"))           return errors.t(args);
		if(cmd.equals("src"))              return src.t(args);
		if(cmd.equals("path"))             return path.t(args);
		if(cmd.equals("features"))         return features.t(args);
		if(cmd.equals("creationdate"))     return creationdate.t(args);
		if(cmd.equals("findall_st"))       return findall_st.t(args);
		if(cmd.equals("findall_en"))       return findall_en.t(args);
		if(cmd.equals("findall_co"))       return findall_co.t(args);
		if(cmd.equals("names_st"))         return names_st.t(args);
		if(cmd.equals("names_en"))         return names_en.t(args);
		if(cmd.equals("names_co"))         return names_co.t(args);
		if(cmd.equals("count_st"))         return count_st.t(args);
		if(cmd.equals("count_en"))         return count_en.t(args);
		if(cmd.equals("count_co"))         return count_co.t(args);
		if(cmd.equals("findall_creationdate"))    return findall_creationdate.t(args);
		if(cmd.equals("findall_features"))        return findall_features.t(args);
		if(cmd.equals("findall_desc"))            return findall_desc.t(args);
		if(cmd.equals("findall_creationdate_st")) return findall_creationdate_st.t(args);
		if(cmd.equals("findall_creationdate_en")) return findall_creationdate_en.t(args);
		if(cmd.equals("findall_creationdate_co")) return findall_creationdate_co.t(args);
		if(cmd.equals("findall_features_st"))     return findall_features_st.t(args);
		if(cmd.equals("findall_features_en"))     return findall_features_en.t(args);
		if(cmd.equals("findall_features_co"))     return findall_features_co.t(args);
		if(cmd.equals("findall_desc_st"))         return findall_desc_st.t(args);
		if(cmd.equals("findall_desc_en"))         return findall_desc_en.t(args);
		if(cmd.equals("findall_desc_co"))         return findall_desc_co.t(args);

		throw new Exception("e: commande inconnue: " + cmd);
	}
}
