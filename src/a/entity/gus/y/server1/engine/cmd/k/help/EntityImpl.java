package a.entity.gus.y.server1.engine.cmd.k.help;

import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260417";}

	public Object t(Object obj) throws Exception
	{
		return "k-show <table>                             \u2014 colonnes d'une table\n"
			 + "k-count <table>                            \u2014 nombre de lignes\n"
			 + "k-tables                                   \u2014 liste des tables\n"
			 + "k-get <table> <id>                         \u2014 un enregistrement par id\n"
			 + "k-list <table> [limit]                     \u2014 derniers enregistrements (d\u00e9faut 20)\n"
			 + "k-search <table> <field> <value>           \u2014 recherche LIKE sur un champ\n"
			 + "k-sql <sql>                                \u2014 SQL brut sur knowledgedb1\n"
			 + "k-createknowledge :<json>                  \u2014 ins\u00e8re dans knowledge\n"
			 + "k-createtodo :<json>                       \u2014 ins\u00e8re dans todo\n"
			 + "k-createdocx :<json>                       \u2014 ins\u00e8re dans doc_x\n"
			 + "k-createdocy :<json>                       \u2014 ins\u00e8re dans doc_y\n"
			 + "k-createdocz :<json>                       \u2014 ins\u00e8re dans doc_z\n"
			 + "k-createknowledgefeedback :<json>          \u2014 ins\u00e8re dans knowledge_feedback\n"
			 + "k-updateknowledge :<json>                  \u2014 met \u00e0 jour dans knowledge (id requis)\n"
			 + "k-updatetodo :<json>                       \u2014 met \u00e0 jour dans todo (id requis)\n"
			 + "k-updatedocx :<json>                       \u2014 met \u00e0 jour dans doc_x (id requis)\n"
			 + "k-updatedocy :<json>                       \u2014 met \u00e0 jour dans doc_y (id requis)\n"
			 + "k-updatedocz :<json>                       \u2014 met \u00e0 jour dans doc_z (id requis)\n"
			 + "k-updateknowledgefeedback :<json>          \u2014 met \u00e0 jour dans knowledge_feedback (id requis)\n"
			 + "k-deleteknowledge <id>                     \u2014 supprime dans knowledge\n"
			 + "k-deletetodo <id>                          \u2014 supprime dans todo\n"
			 + "k-deletedocx <id>                          \u2014 supprime dans doc_x\n"
			 + "k-deletedocy <id>                          \u2014 supprime dans doc_y\n"
			 + "k-deletedocz <id>                          \u2014 supprime dans doc_z\n"
			 + "k-deleteknowledgefeedback <id>             \u2014 supprime dans knowledge_feedback\n"
			 + "k-findknowledge <id>                       \u2014 trouve un enregistrement knowledge par id\n"
			 + "k-tags                                     \u2014 tous les tags distincts (*_tag)\n"
			 + "k-tagsof <table> <id>                      \u2014 tags d'un enregistrement\n"
			 + "k-addtag <table> <id> <tag>                \u2014 ajouter un tag\n"
			 + "k-removetag <table> <id> <tag>             \u2014 supprimer un tag\n"
			 + "k-detailofknowledge <id>                   \u2014 d\u00e9tail complet (data+tags+linkers+linked)\n"
			 + "k-detailoftodo <id>                        \u2014 d\u00e9tail complet (data+tags+linkers+linked)\n"
			 + "k-detailofdocx <id>                        \u2014 d\u00e9tail complet (data+tags)\n"
			 + "k-detailofdocy <id>                        \u2014 d\u00e9tail complet (data+tags)\n"
			 + "k-detailofdocz <id>                        \u2014 d\u00e9tail complet (data+tags)\n"
			 + "k-detailofknowledgefeedback <id>           \u2014 d\u00e9tail complet (data)\n"
			 + "k-linksof <table> <id>                     \u2014 liens d'un enregistrement\n"
			 + "k-addlink <table> <id1> <id2> [type]       \u2014 cr\u00e9er un lien\n"
			 + "k-removelink <table> <id1> <id2>           \u2014 supprimer un lien\n"
			 + "k-addtodoknowledge <id_todo> <id_knowledge> [type] \u2014 lier un todo \u00e0 un knowledge\n"
			 + "k-removetodoknowledge <id_todo> <id_knowledge>     \u2014 supprimer ce lien\n"
			 + "k-help                                     \u2014 cette aide";
	}
}
