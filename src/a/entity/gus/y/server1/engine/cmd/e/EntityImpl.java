package a.entity.gus.y.server1.engine.cmd.e;

import java.util.List;
import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260414";}

	// commandes generales
	
	private Service help;
	private Service reload;
	private Service errors;
	private Service sql;
	
	// manipulations d'entite
	
	private Service create;
	private Service delete;
	private Service rename;
	private Service duplicate;
	private Service createtree;
	private Service importsrc;
	private Service editinsert;
	private Service editremove;
	private Service editreplace;
	private Service editmulti;
	
	// informations sur l'entite
	
	private Service src;
	private Service srcpart;
	private Service path;
	private Service features;
	private Service creationdate;
	
	// parcours descendant
	
	private Service downlinks;
	private Service downlinkstree;
	private Service downlinkstree2;
	
	// parcours montant
	
	private Service uplinks;
	private Service uplinkstree;
	private Service uplinkstree2;
	
	// comptages
	
	private Service count;
	private Service count_st;
	private Service count_en;
	private Service count_co;
	
	// nommages
	
	private Service names;
	private Service names_st;
	private Service names_en;
	private Service names_co;
	
	// recherche des features
	
	private Service findall_features;
	private Service findall_features_st;
	private Service findall_features_en;
	private Service findall_features_co;
	
	// recherche des dates de creation
	
	private Service findall_creationdate;
	private Service findall_creationdate_st;
	private Service findall_creationdate_en;
	private Service findall_creationdate_co;
	
	// recherche des dates de descriptions (name-features)
	
	private Service findall_desc;
	private Service findall_desc_st;
	private Service findall_desc_en;
	private Service findall_desc_co;
	
	// recherche globale
	
	private Service findall_st;
	private Service findall_en;
	private Service findall_co;
	
	
	public EntityImpl() throws Exception
	{
		// commandes generales
		
		help       = Outside.service(this, "gus.y.server1.engine.cmd.e.help");
		reload     = Outside.service(this, "gus.y.server1.engine.cmd.e.reload");
		errors      = Outside.service(this, "gus.y.server1.engine.cmd.e.errors");
		sql        = Outside.service(this, "gus.y.server1.engine.cmd.e_sql");
		
		// manipulations d'entite
		
		create     = Outside.service(this, "gus.y.server1.engine.cmd.e.create");
		delete     = Outside.service(this, "gus.y.server1.engine.cmd.e.delete");
		rename     = Outside.service(this, "gus.y.server1.engine.cmd.e.rename");
		duplicate  = Outside.service(this, "gus.y.server1.engine.cmd.e.duplicate");
		createtree  = Outside.service(this, "gus.y.server1.engine.cmd.e.createtree");
		importsrc   = Outside.service(this, "gus.y.server1.engine.cmd.e.importsrc");
		editinsert  = Outside.service(this, "gus.y.server1.engine.cmd.e.editinsert");
		editremove  = Outside.service(this, "gus.y.server1.engine.cmd.e.editremove");
		editreplace = Outside.service(this, "gus.y.server1.engine.cmd.e.editreplace");
		editmulti = Outside.service(this, "gus.y.server1.engine.cmd.e.editmulti");
		
		// informations sur l'entite
		
		src         = Outside.service(this, "gus.y.server1.engine.cmd.e.src");
		srcpart     = Outside.service(this, "gus.y.server1.engine.cmd.e.srcpart");
		path        = Outside.service(this, "gus.y.server1.engine.cmd.e.path");
		features    = Outside.service(this, "gus.y.server1.engine.cmd.e.features");
		creationdate = Outside.service(this, "gus.y.server1.engine.cmd.e.creationdate");
		
		// parcours descendant
		
		downlinks      = Outside.service(this, "gus.y.server1.engine.cmd.e.downlinks");
		downlinkstree  = Outside.service(this, "gus.y.server1.engine.cmd.e.downlinkstree");
		downlinkstree2 = Outside.service(this, "gus.y.server1.engine.cmd.e.downlinkstree2");
		
		// parcours montant
		
		uplinks        = Outside.service(this, "gus.y.server1.engine.cmd.e.uplinks");
		uplinkstree    = Outside.service(this, "gus.y.server1.engine.cmd.e.uplinkstree");
		uplinkstree2   = Outside.service(this, "gus.y.server1.engine.cmd.e.uplinkstree2");
		
		// comptages
		
		count       = Outside.service(this, "gus.y.server1.engine.cmd.e.count");
		count_st    = Outside.service(this, "gus.y.server1.engine.cmd.e.count_st");
		count_en    = Outside.service(this, "gus.y.server1.engine.cmd.e.count_en");
		count_co    = Outside.service(this, "gus.y.server1.engine.cmd.e.count_co");
		
		// nommages
		
		names       = Outside.service(this, "gus.y.server1.engine.cmd.e.names");
		names_st    = Outside.service(this, "gus.y.server1.engine.cmd.e.names_st");
		names_en    = Outside.service(this, "gus.y.server1.engine.cmd.e.names_en");
		names_co    = Outside.service(this, "gus.y.server1.engine.cmd.e.names_co");
		
		// recherche des features
		
		findall_features        = Outside.service(this, "gus.y.server1.engine.cmd.e.findall_features");
		findall_features_st     = Outside.service(this, "gus.y.server1.engine.cmd.e.findall_features_st");
		findall_features_en     = Outside.service(this, "gus.y.server1.engine.cmd.e.findall_features_en");
		findall_features_co     = Outside.service(this, "gus.y.server1.engine.cmd.e.findall_features_co");
		
		// recherche des dates de creation
		
		findall_creationdate    = Outside.service(this, "gus.y.server1.engine.cmd.e.findall_creationdate");
		findall_creationdate_st = Outside.service(this, "gus.y.server1.engine.cmd.e.findall_creationdate_st");
		findall_creationdate_en = Outside.service(this, "gus.y.server1.engine.cmd.e.findall_creationdate_en");
		findall_creationdate_co = Outside.service(this, "gus.y.server1.engine.cmd.e.findall_creationdate_co");
		
		// recherche des dates de descriptions (name-features)
		
		findall_desc            = Outside.service(this, "gus.y.server1.engine.cmd.e.findall_desc");
		findall_desc_st         = Outside.service(this, "gus.y.server1.engine.cmd.e.findall_desc_st");
		findall_desc_en         = Outside.service(this, "gus.y.server1.engine.cmd.e.findall_desc_en");
		findall_desc_co         = Outside.service(this, "gus.y.server1.engine.cmd.e.findall_desc_co");

		// recherche globale
		
		//findall (trop dangereux)
		findall_st  = Outside.service(this, "gus.y.server1.engine.cmd.e.findall_st");
		findall_en  = Outside.service(this, "gus.y.server1.engine.cmd.e.findall_en");
		findall_co  = Outside.service(this, "gus.y.server1.engine.cmd.e.findall_co");
		
	}

	public Object t(Object obj) throws Exception
	{
		Map payload = (Map) obj;
		List cmds = (List) payload.get("cmds");
		Object args = payload.get("args");

		if(cmds.size() != 2) throw new Exception("e: incorrect cmd number: "+cmds.size());
		String cmd = (String) cmds.get(1);
		
		// commandes generales
		
		if(cmd.equals("help"))             return help.t(args);
		if(cmd.equals("reload"))           return reload.t(args);
		if(cmd.equals("sql"))              return sql.t(args);
		if(cmd.equals("errors"))           return errors.t(args);
		
		// manipulations d'entite
		
		if(cmd.equals("create"))           return create.t(args);
		if(cmd.equals("delete"))           return delete.t(args);
		if(cmd.equals("rename"))           return rename.t(args);
		if(cmd.equals("duplicate"))        return duplicate.t(args);
		if(cmd.equals("createtree"))       return createtree.t(args);
		if(cmd.equals("importsrc"))        return importsrc.t(args);
		if(cmd.equals("editinsert"))       return editinsert.t(args);
		if(cmd.equals("editremove"))       return editremove.t(args);
		if(cmd.equals("editreplace"))      return editreplace.t(args);
		if(cmd.equals("editmulti"))        return editmulti.t(args);
	
		// informations sur l'entite
		
		if(cmd.equals("src"))              return src.t(args);
		if(cmd.equals("srcpart"))          return srcpart.t(args);
		if(cmd.equals("path"))             return path.t(args);
		if(cmd.equals("features"))         return features.t(args);
		if(cmd.equals("creationdate"))     return creationdate.t(args);
	
		// parcours descendant
		
		if(cmd.equals("downlinks"))        return downlinks.t(args);
		if(cmd.equals("downlinkstree"))    return downlinkstree.t(args);
		if(cmd.equals("downlinkstree2"))   return downlinkstree2.t(args);
		
		// parcours montant
		
		if(cmd.equals("uplinks"))          return uplinks.t(args);
		if(cmd.equals("uplinkstree"))      return uplinkstree.t(args);
		if(cmd.equals("uplinkstree2"))     return uplinkstree2.t(args);
		
		// comptages
		
		if(cmd.equals("count"))            return count.t(args);
		if(cmd.equals("count_st"))         return count_st.t(args);
		if(cmd.equals("count_en"))         return count_en.t(args);
		if(cmd.equals("count_co"))         return count_co.t(args);
		
		// nommages
		
		if(cmd.equals("names"))            return names.t(args);
		if(cmd.equals("names_st"))         return names_st.t(args);
		if(cmd.equals("names_en"))         return names_en.t(args);
		if(cmd.equals("names_co"))         return names_co.t(args);
		
		// recherche des features
		
		if(cmd.equals("findall_features"))        return findall_features.t(args);
		if(cmd.equals("findall_features_st"))     return findall_features_st.t(args);
		if(cmd.equals("findall_features_en"))     return findall_features_en.t(args);
		if(cmd.equals("findall_features_co"))     return findall_features_co.t(args);
		
		// recherche des dates de creation
		
		if(cmd.equals("findall_creationdate"))    return findall_creationdate.t(args);
		if(cmd.equals("findall_creationdate_st")) return findall_creationdate_st.t(args);
		if(cmd.equals("findall_creationdate_en")) return findall_creationdate_en.t(args);
		if(cmd.equals("findall_creationdate_co")) return findall_creationdate_co.t(args);
		
		// recherche des dates de descriptions (name-features)
		
		if(cmd.equals("findall_desc"))            return findall_desc.t(args);
		if(cmd.equals("findall_desc_st"))         return findall_desc_st.t(args);
		if(cmd.equals("findall_desc_en"))         return findall_desc_en.t(args);
		if(cmd.equals("findall_desc_co"))         return findall_desc_co.t(args);
		
		// recherche globale
		
		if(cmd.equals("findall_st"))       return findall_st.t(args);
		if(cmd.equals("findall_en"))       return findall_en.t(args);
		if(cmd.equals("findall_co"))       return findall_co.t(args);

		throw new Exception("e: commande inconnue: " + cmd);
	}
}
