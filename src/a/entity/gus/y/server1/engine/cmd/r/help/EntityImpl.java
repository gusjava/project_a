package a.entity.gus.y.server1.engine.cmd.r.help;

import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260417";}

	public Object t(Object obj) throws Exception
	{
		return "r-show <table>                   \u2014 colonnes d'une table\n"
			 + "r-count <table>                  \u2014 nombre de lignes\n"
			 + "r-tables                         \u2014 liste des tables\n"
			 + "r-get <table> <id>               \u2014 un enregistrement par id\n"
			 + "r-list <table> [limit]           \u2014 derniers enregistrements (d\u00e9faut 20)\n"
			 + "r-search <table> <field> <value> \u2014 recherche LIKE sur un champ\n"
			 + "r-sql <sql>                      \u2014 SQL brut sur roadmapdb1\n"
			 + "r-createobjective :<json>        \u2014 ins\u00e8re dans objective\n"
			 + "r-createtask :<json>             \u2014 ins\u00e8re dans task\n"
			 + "r-createnote :<json>             \u2014 ins\u00e8re dans note\n"
			 + "r-createsprint :<json>           \u2014 ins\u00e8re dans sprint\n"
			 + "r-createsprintentry :<json>      \u2014 ins\u00e8re dans sprint_entry\n"
			 + "r-updateobjective :<json>        \u2014 met \u00e0 jour dans objective (id requis)\n"
			 + "r-updatetask :<json>             \u2014 met \u00e0 jour dans task (id requis)\n"
			 + "r-updatenote :<json>             \u2014 met \u00e0 jour dans note (id requis)\n"
			 + "r-updatesprint :<json>           \u2014 met \u00e0 jour dans sprint (id requis)\n"
			 + "r-updatesprintentry :<json>      \u2014 met \u00e0 jour dans sprint_entry (id requis)\n"
			 + "r-deleteobjective <id>           \u2014 supprime dans objective\n"
			 + "r-deletetask <id>                \u2014 supprime dans task\n"
			 + "r-deletenote <id>                \u2014 supprime dans note\n"
			 + "r-deletesprint <id>              \u2014 supprime dans sprint\n"
			 + "r-deletesprintentry <id>         \u2014 supprime dans sprint_entry\n"
			 + "r-detailofnote <id>              \u2014 d\u00e9tail complet (data+tags)\n"
			 + "r-detailofobjective <id>         \u2014 d\u00e9tail complet (data+tags)\n"
			 + "r-detailoftask <id>              \u2014 d\u00e9tail complet (data+tags)\n"
			 + "r-detailofsprint <id>            \u2014 d\u00e9tail complet (data)\n"
			 + "r-detailofsprintentry <id>       \u2014 d\u00e9tail complet (data)\n"
			 + "r-tags                           \u2014 tous les tags distincts (*_tag)\n"
			 + "r-tagsof <table> <id>            \u2014 tags d'un enregistrement\n"
			 + "r-addtag <table> <id> <tag>      \u2014 ajouter un tag\n"
			 + "r-removetag <table> <id> <tag>   \u2014 supprimer un tag\n"
			 + "r-help                           \u2014 cette aide";
	}
}