package a.entity.gus06.string.transform.httpcode.desc.fr;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190715";}


	private Map map;

	public EntityImpl() throws Exception
	{
		map = new HashMap();

		put("100","Attente de la suite de la requ�te.");
		put("101","Acceptation du changement de protocole.");
		put("102","WebDAV RFC 2518 [ 3 ] : Traitement en cours (�vite que le client d�passe le temps d�attente limite).");
		put("103","RFC 8297 [ 4 ] : (Exp�rimental) Dans l'attente de la r�ponse d�finitive, le serveur retourne des liens que le client peut commencer � t�l�charger.");
		put("200","Requ�te trait�e avec succ�s. La r�ponse d�pendra de la m�thode de requ�te utilis�e.");
		put("201","Requ�te trait�e avec succ�s et cr�ation d�un document.");
		put("202","Requ�te trait�e, mais sans garantie de r�sultat.");
		put("203","Information retourn�e, mais g�n�r�e par une source non certifi�e.");
		put("204","Requ�te trait�e avec succ�s mais pas d�information � renvoyer.");
		put("205","Requ�te trait�e avec succ�s, la page courante peut �tre effac�e.");
		put("206","Une partie seulement de la ressource a �t� transmise.");
		put("207","WebDAV : R�ponse multiple.");
		put("208","WebDAV : Le document a �t� envoy� pr�c�demment dans cette collection.");
		put("210","WebDAV : La copie de la ressource c�t� client diff�re de celle du serveur (contenu ou propri�t�s).");
		put("226","RFC 3229 [ 5 ] : Le serveur a accompli la requ�te pour la ressource, et la r�ponse est une repr�sentation du r�sultat d'une ou plusieurs manipulations d'instances appliqu�es � l'instance actuelle.");
		put("300","L� URI demand�e se rapporte � plusieurs ressources.");
		put("301","Document d�plac� de fa�on permanente.");
		put("302","Document d�plac� de fa�on temporaire.");
		put("303","La r�ponse � cette requ�te est ailleurs.");
		put("304","Document non modifi� depuis la derni�re requ�te.");
		put("305","La requ�te doit �tre r�-adress�e au proxy .");
		put("306","Code utilis� par une ancienne version de la RFC 2616 [ 6 ] , � pr�sent r�serv�. Elle signifiait \"Les requ�tes suivantes doivent utiliser le proxy sp�cifi�\" [ 7 ] .");
		put("307","La requ�te doit �tre redirig�e temporairement vers l� URI sp�cifi�e.");
		put("308","La requ�te doit �tre redirig�e d�finitivement vers l� URI sp�cifi�e.");
		put("310","La requ�te doit �tre redirig�e de trop nombreuses fois, ou est victime d�une boucle de redirection.");
		put("400","La syntaxe de la requ�te est erron�e.");
		put("401","Une authentification est n�cessaire pour acc�der � la ressource.");
		put("402","Paiement requis pour acc�der � la ressource.");
		put("403","Le serveur a compris la requ�te, mais refuse de l'ex�cuter. Contrairement � l' erreur 401 , s'authentifier ne fera aucune diff�rence. Sur les serveurs o� l'authentification est requise, cela signifie g�n�ralement que l'authentification a �t� accept�e mais que les droits d'acc�s ne permettent pas au client d'acc�der � la ressource.");
		put("404","Ressource non trouv�e.");
		put("405","M�thode de requ�te non autoris�e.");
		put("406","La ressource demand�e n'est pas disponible dans un format qui respecterait les en-t�tes \"Accept\" de la requ�te.");
		put("407","Acc�s � la ressource autoris� par identification avec le proxy.");
		put("408","Temps d�attente d�une requ�te du client, �coul� c�t� serveur. D'apr�s les sp�cifications HTTP: \"Le client n'a pas produit de requ�te dans le d�lai que le serveur �tait pr�t � attendre. Le client PEUT r�p�ter la demande sans modifications � tout moment ult�rieur.\" [ 8 ]");
		put("409","La requ�te ne peut �tre trait�e en l��tat actuel.");
		put("410","La ressource n'est plus disponible et aucune adresse de redirection n�est connue.");
		put("411","La longueur de la requ�te n�a pas �t� pr�cis�e.");
		put("412","Pr�conditions envoy�es par la requ�te non v�rifi�es.");
		put("413","Traitement abandonn� d� � une requ�te trop importante.");
		put("414","URI trop longue.");
		put("415","Format de requ�te non support� pour une m�thode et une ressource donn�es.");
		put("416","Champs d�en-t�te de requ�te � range � incorrect.");
		put("417","Comportement attendu et d�fini dans l�en-t�te de la requ�te insatisfaisante.");
		put("418","� Je suis une th�i�re �. Ce code est d�fini dans la RFC 2324 [ 9 ] dat�e du premier avril 1998, Hyper Text Coffee Pot Control Protocol .");
		put("421","La requ�te a �t� envoy�e � un serveur qui n'est pas capable de produire une r�ponse (par exemple, car une connexion a �t� r�utilis�e).");
		put("422","WebDAV : L�entit� fournie avec la requ�te est incompr�hensible ou incompl�te.");
		put("423","WebDAV : L�op�ration ne peut avoir lieu car la ressource est verrouill�e.");
		put("424","WebDAV : Une m�thode de la transaction a �chou�.");
		put("425","WebDAV RFC 3648 [ 10 ] . Ce code est d�fini dans le brouillon WebDAV Advanced Collections Protocol , mais est absent de Web Distributed Authoring and Versioning (WebDAV) Ordered Collections Protocol .");
		put("426","RFC 2817 [ 11 ] Le client devrait changer de protocole, par exemple au profit de TLS/1.0 .");
		put("428","RFC 6585 [ 12 ] La requ�te doit �tre conditionnelle.");
		put("429","RFC 6585 [ 13 ] Le client a �mis trop de requ�tes dans un d�lai donn�.");
		put("431","RFC 6585 [ 13 ] Les ent�tes HTTP �mises d�passent la taille maximale admise par le serveur.");
		put("449","Code d�fini par Microsoft . La requ�te devrait �tre renvoy�e apr�s avoir effectu� une action.");
		put("450","Code d�fini par Microsoft. Cette erreur est produite lorsque les outils de contr�le parental de Windows sont activ�s et bloquent l�acc�s � la page.");
		put("451","Ce code d'erreur indique que la ressource demand�e est inaccessible pour des raisons d'ordre l�gal [ 14 ] , [ 15 ] .");
		put("456","WebDAV : Erreur irr�cup�rable.");
		put("444","Indique que le serveur n'a retourn� aucune information vers le client et a ferm� la connexion.");
		put("495","Une extension de l'erreur 400 Bad Request, utilis�e lorsque le client a fourni un certificat invalide.");
		put("496","Une extension de l'erreur 400 Bad Request, utilis�e lorsqu'un certificat client requis n'est pas fourni.");
		put("497","Une extension de l'erreur 400 Bad Request, utilis�e lorsque le client envoie une requ�te HTTP vers le port 443 normalement destin� aux requ�tes HTTPS.");
		put("498","Le jeton a expir� ou est invalide.");
		put("499","Le client a ferm� la connexion avant de recevoir la r�ponse. Cette erreur se produit quand le traitement est trop long c�t� serveur [ 16 ] .");
		put("500","Erreur interne du serveur.");
		put("501","Fonctionnalit� r�clam�e non support�e par le serveur.");
		put("502","En agissant en tant que serveur proxy ou passerelle, le serveur a re�u une r�ponse invalide depuis le serveur distant.");
		put("503","Service temporairement indisponible ou en maintenance.");
		put("504","Temps d�attente d�une r�ponse d�un serveur � un serveur interm�diaire �coul�.");
		put("505","Version HTTP non g�r�e par le serveur.");
		put("506","RFC 2295 [ 17 ] : Erreur de n�gociation. Transparent content negociation .");
		put("507","WebDAV : Espace insuffisant pour modifier les propri�t�s ou construire la collection.");
		put("508","WebDAV : Boucle dans une mise en relation de ressources ( RFC 5842 [ 18 ] ).");
		put("509","Utilis� par de nombreux serveurs pour indiquer un d�passement de quota.");
		put("510","RFC 2774 [ 19 ] : la requ�te ne respecte pas la politique d'acc�s aux ressources HTTP �tendues.");
		put("511","RFC 6585 [ 13 ] : Le client doit s'authentifier pour acc�der au r�seau. Utilis� par les portails captifs pour rediriger les clients vers la page d'authentification.");
		put("520","L'erreur 520 est utilis� en tant que r�ponse g�n�rique lorsque le serveur d'origine retourne un r�sultat impr�vu.");
		put("521","Le serveur a refus� la connexion depuis Cloudflare.");
		put("522","Cloudflare n'a pas pu n�gocier un TCP handshake avec le serveur d'origine.");
		put("523","Cloudflare n'a pas r�ussi � joindre le serveur d'origine. Cela peut se produire en cas d'�chec de r�solution de nom de serveur DNS.");
		put("524","Cloudflare a �tabli une connexion TCP avec le serveur d'origine mais n'a pas re�u de r�ponse HTTP avant l'expiration du d�lai de connexion.");
		put("525","Cloudflare n'a pas pu n�gocier un SSL/TLS handshake avec le serveur d'origine.");
		put("526","Cloudflare n'a pas pu valider le certificat SSL pr�sent� par le serveur d'origine.");
		put("527","L'erreur 527 indique que la requ�te a d�pass� le d�lai de connexion ou a �chou� apr�s que la connexion WAN ait �t� �tablie.");
	}
	
	
	private void put(String code, String name)
	{map.put(code,name);}
	
	
	public Object t(Object obj) throws Exception
	{
		String code = (String) obj;
		if(!map.containsKey(code)) throw new Exception("Unknown code: "+code);
		return map.get(code);
	}
}
