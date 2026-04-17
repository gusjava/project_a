package a.entity.gus.y.server1.engine.cmd.e.help;

import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260415";}

	public Object t(Object obj) throws Exception
	{
		return
		"commandes generales:\n" +
		"e-help \u2014 cette aide\n" +
		"e-reload \u2014 recharge le moteur entity\n" +
		"e-errors [entity] \u2014 erreurs de compilation (toutes, ou filtr\u00e9es par entit\u00e9)\n" +
		"e-sql <sql> \u2014 SQL brut sur entitydb1\n" +
		"\n" +
		"manipulations d'entite:\n" +
		"e-create <entity> [features] \u2014 cr\u00e9e le code source d'une nouvelle entit\u00e9 (features : BEFGHIPRSTV, ex: GT)\n" +
		"e-delete <name> \u2014 supprime une entit\u00e9\n" +
		"e-rename <name0> <name1> \u2014 renomme une entit\u00e9 (avec refactor des liens)\n" +
		"e-duplicate <name0> <name1> \u2014 duplique une entit\u00e9\n" +
		"e-createtree :<json> \u2014 cr\u00e9e un arbre d'entit\u00e9s depuis un JSON [[\"name-features\",[children...]]] (DFS post-order)\n" +
		"e-importsrc :{src} \u2014 cr\u00e9e une entit\u00e9 \u00e0 partir du code source <src>\n" +
		"e-editinsert :{name,pos,src} \u2014 ins\u00e8re src \u00e0 la position pos dans l'entit\u00e9 name\n" +
		"e-editremove :{name,start,end} \u2014 supprime les lignes start\u2192end dans l'entit\u00e9 name\n" +
		"e-editreplace :{name,start,end,src} \u2014 remplace les lignes start\u2192end par src dans l'entit\u00e9 name\n" +
		"e-editmulti :{name,op:[{start,end,replace},...]} \u2014 remplacements multiples dans l'entit\u00e9 name\n" +
		"\n" +
		"informations sur l'entite:\n" +
		"e-src <entity> \u2014 affiche le code source de l'entit\u00e9\n" +
		"e-srcpart <entity> <start> <end> \u2014 affiche une section du code source de l'entit\u00e9 comprise entre les positions <start> et <end>\n" +
		"e-path <entity> \u2014 retourne le filepath de EntityImpl.java\n" +
		"e-features <entity> \u2014 retourne les features impl\u00e9ment\u00e9es par l'entit\u00e9\n" +
		"e-creationdate <entity> \u2014 retourne la date de cr\u00e9ation de l'entit\u00e9\n" +
		"\n" +
		"parcours descendant:\n" +
		"e-downlinks <entity> \u2014 liste les entit\u00e9s qui d\u00e9pendent de <entity>\n" +
		"e-downlinkstree <entity> <maxDeep> \u2014 arbre r\u00e9cursif des downlinks jusqu'\u00e0 la profondeur <maxDeep>\n" +
		"e-downlinkstree2 <entity> <maxDeep> \u2014 arbre r\u00e9cursif des downlinks avec descriptions (nom-features)\n" +
		"\n" +
		"parcours montant:\n" +
		"e-uplinks <entity> \u2014 liste les d\u00e9pendances de <entity>\n" +
		"e-uplinkstree <entity> <maxDeep> \u2014 arbre r\u00e9cursif des uplinks jusqu'\u00e0 la profondeur <maxDeep>\n" +
		"e-uplinkstree2 <entity> <maxDeep> \u2014 arbre r\u00e9cursif des uplinks avec descriptions (nom-features)\n" +
		"\n" +
		"comptages:\n" +
		"e-count \u2014 nombre total d'entit\u00e9s\n" +
		"e-count_st <prefix> \u2014 nombre d'entit\u00e9s dont le nom commence par <prefix>\n" +
		"e-count_en <suffix> \u2014 nombre d'entit\u00e9s dont le nom se termine par <suffix>\n" +
		"e-count_co <fragment> \u2014 nombre d'entit\u00e9s dont le nom contient <fragment>\n" +
		"\n" +
		"nommages:\n" +
		"e-names \u2014 liste tous les noms d'entit\u00e9s\n" +
		"e-names_st <prefix> \u2014 liste les noms d'entit\u00e9s commen\u00e7ant par <prefix>\n" +
		"e-names_en <suffix> \u2014 liste les noms d'entit\u00e9s se terminant par <suffix>\n" +
		"e-names_co <fragment> \u2014 liste les noms d'entit\u00e9s contenant <fragment>\n" +
		"\n" +
		"recherche des features:\n" +
		"e-findall_features \u2014 toutes les entit\u00e9s avec leurs features\n" +
		"e-findall_features_st <prefix> \u2014 entit\u00e9s dont les features commencent par <prefix>\n" +
		"e-findall_features_en <suffix> \u2014 entit\u00e9s dont les features se terminent par <suffix>\n" +
		"e-findall_features_co <fragment> \u2014 entit\u00e9s dont les features contiennent <fragment>\n" +
		"\n" +
		"recherche des dates de creation:\n" +
		"e-findall_creationdate \u2014 toutes les entit\u00e9s avec leur date de cr\u00e9ation\n" +
		"e-findall_creationdate_st <prefix> \u2014 entit\u00e9s dont la date de cr\u00e9ation commence par <prefix>\n" +
		"e-findall_creationdate_en <suffix> \u2014 entit\u00e9s dont la date de cr\u00e9ation se termine par <suffix>\n" +
		"e-findall_creationdate_co <fragment> \u2014 entit\u00e9s dont la date de cr\u00e9ation contient <fragment>\n" +
		"\n" +
		"recherche des descriptions (name-features):\n" +
		"e-findall_desc \u2014 toutes les entit\u00e9s (nom-features) tri\u00e9es par nom\n" +
		"e-findall_desc_st <prefix> \u2014 entit\u00e9s dont le nom commence par <prefix> (nom-features)\n" +
		"e-findall_desc_en <suffix> \u2014 entit\u00e9s dont le nom se termine par <suffix> (nom-features)\n" +
		"e-findall_desc_co <fragment> \u2014 entit\u00e9s dont le nom contient <fragment> (nom-features)\n" +
		"\n" +
		"recherche globale:\n" +
		"e-findall_st <prefix> \u2014 liste les entit\u00e9s dont le nom commence par <prefix>\n" +
		"e-findall_en <suffix> \u2014 liste les entit\u00e9s dont le nom se termine par <suffix>\n" +
		"e-findall_co <fragment> \u2014 liste les entit\u00e9s dont le nom contient <fragment>\n" +
		"\n" +
		"(les actions create, delete, rename, duplicate, createtree, importsrc, edit* sont asynchrones \u2014 patienter un instant avant de v\u00e9rifier)";
	}
}
