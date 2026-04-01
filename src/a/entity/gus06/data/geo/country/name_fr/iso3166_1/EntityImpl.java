package a.entity.gus06.data.geo.country.name_fr.iso3166_1;

import java.util.HashMap;
import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity, T, G {

	public String creationDate() {return "20160414";}

	
	private Service normalize;
	private Map map;



	public EntityImpl() throws Exception
	{
		normalize = Outside.service(this,"gus06.string.transform.normalize.diacritics.upper");
		map = new HashMap();
		
		put("AFGHANISTAN","AF");
		put("AFRIQUE DU SUD","ZA");
		put("ILES ALAND","AX");
		put("ALBANIE","AL");
		put("ALGERIE","DZ");
		put("ALLEMAGNE","DE");
		put("ANDORRE","AD");
		put("ANGOLA","AO");
		put("ANGUILLA","AI");
		put("ANTARCTIQUE","AQ");
		put("ANTIGUA ET BARBUDA","AG");
		put("ARABIE SAOUDITE","SA");
		put("ARGENTINE","AR");
		put("ARMENIE","AM");
		put("ARUBA","AW");
		put("AUSTRALIE","AU");
		put("AUTRICHE","AT");
		put("AZERBAIDJAN","AZ");
		
		put("BAHAMAS","BS");
		put("BAHREIN","BH");
		put("BANGLADESH","BD");
		put("BARBADE","BB");
		put("BELARUS","BY");
		put("BIELORUSSIE","BY");
		put("BELGIQUE","BE");
		put("BELIZE","BZ");
		put("BENIN","BJ");
		put("BERMUDES","BM");
		put("BHOUTAN","BT");
		put("BOLIVIE","BO");
		put("ETAT PLURINATIONAL DE BOLIVIE","BO");
		put("BONAIRE","BQ");
		put("BONAIRE, SAINT EUSTACHE ET SABA","BQ");
		put("PAYS BAS CARIBEENS","BQ");
		put("BOSNIE HERZEGOVINE","BA");
		put("BOTSWANA","BW");
		put("ILE BOUVET","BV");
		put("BRESIL","BR");
		put("BRUNEI DARUSSALAM","BN");
		put("BRUNEI","BN");
		put("BULGARIE","BG");
		put("BURKINA FASO","BF");
		put("BURUNDI","BI");

		put("ILES CAIMANES","KY");
		put("ILES CAIMANS","KY");
		put("CAMBODGE","KH");
		put("CAMEROUN","CM");
		put("CANADA","CA");
		put("CAP VERT","CV");
		put("CABO VERDE","CV");
		put("REPUBLIQUE CENTRAFRICAINE","CF");
		put("CENTRAFRIQUE","CF");
		put("CHILI","CL");
		put("CHINE","CN");
		put("ILE CHRISTMAS","CX");
		put("CHYPRE","CY");
		put("ILES COCOS","CC");
		put("COLOMBIE","CO");
		put("COMORES","KM");
		put("CONGO","CG");
		put("REPUBLIQUE DU CONGO","CG");
		put("REPUBLIQUE DEMOCRATIQUE DU CONGO","CD");
		put("ILES COOK","CK");
		put("COREE","KR");
		put("COREE DU SUD","KR");
		put("REPUBLIQUE DE COREE","KR");
		put("COREE DU NORD","KP");
		put("REPUBLIQUE POPULAIRE DEMOCRATIQUE DE COREE","KP");
		put("COSTA RICA","CR");
		put("COTE D'IVOIRE","CI");
		put("CROATIE","HR");
		put("CUBA","CU");
		put("CURACAO","CW");

		put("DANEMARK","DK");
		put("DJIBOUTI","DJ");
		put("REPUBLIQUE DOMINICAINE","DO");
		put("DOMINIQUE","DM");

		put("EGYPTE","EG");
		put("SALVADOR","SV");
		put("EL SALVADOR","SV");
		put("EMIRATS ARABES UNIS","AE");
		put("EQUATEUR","EC");
		put("ERYTHREE","ER");
		put("ESPAGNE","ES");
		put("ESTONIE","EE");
		put("ETATS UNIS","US");
		put("ETHIOPIE","ET");

		put("MALOUINES","FK");
		put("ILES MALOUINES","FK");
		put("ILES FALKLAND","FK");
		put("ILES FEROE","FO");
		put("FIDJI","FJ");
		put("FINLANDE","FI");
		put("FRANCE","FR");

		put("GABON","GA");
		put("GAMBIE","GM");
		put("GEORGIE","GE");
		put("GEORGIE DU SUD ET LES ILES SANDWICH DU SUD","GS");
		put("GHANA","GH");
		put("GIBRALTAR","GI");
		put("GRECE","GR");
		put("GRENADE","GD");
		put("GROENLAND","GL");
		put("GUADELOUPE","GP");
		put("GUAM","GU");
		put("GUATEMALA","GT");
		put("GUERNESEY","GG");
		put("GUINEE","GN");
		put("GUINEE BISSAU","GW");
		put("GUINEE EQUATORIALE","GQ");
		put("GUYANA","GY");
		put("GUYANE FRANCAISE","GF");
		put("GUYANE","GF");

		put("HAITI","HT");
		put("ILES HEARD ET MCDONALD","HM");
		put("ILES MCDONALD","HM");
		put("ILE HEARD","HM");
		put("HONDURAS","HN");
		put("HONG KONG","HK");
		put("HONGRIE","HU");

		put("ILE DE MAN","IM");
		put("ILES MINEURES ELOIGNEES DES ETATS UNIS","UM");
		put("ILES VIERGES BRITANNIQUES","VG");
		put("ILES VIERGES DES ETATS UNIS","VI");
		put("INDE","IN");
		put("INDONESIE","ID");
		put("IRAN","IR");
		put("REPUBLIQUE ISLAMIQUE D'IRAN","IR");
		put("IRAK","IQ");
		put("IRAQ","IQ");
		put("IRLANDE","IE");
		put("ISLANDE","IS");
		put("ISRAEL","IL");
		put("ITALIE","IT");

		put("JAMAIQUE","JM");
		put("JAPON","JP");
		put("JERSEY","JE");
		put("JORDANIE","JO");

		put("KAZAKHSTAN","KZ");
		put("KENYA","KE");
		put("KIRGHIZISTAN","KG");
		put("KIRGHIZSTAN","KG");
		put("KIRIBATI","KI");
		put("KOWEIT","KW");

		put("LAO","LA");
		put("LAOS","LA");
		put("REPUBLIQUE DEMOCRATIQUE POPULAIRE LAO","LA");
		put("REPUBLIQUE DEMOCRATIQUE POPULAIRE DU LAOS","LA");
		put("LESOTHO","LS");
		put("LETTONIE","LV");
		put("LIBAN","LB");
		put("LIBERIA","LR");
		put("JAMAHIRIYA ARABE LIBYENNE","LY");
		put("REPUBLIQUE ARABE LIBYENNE","LY");
		put("LIBYE","LY");
		put("LIECHTENSTEIN","LI");
		put("LITUANIE","LT");
		put("LUXEMBOURG","LU");

		put("MACAO","MO");
		put("MACEDOINE","MK");
		put("EX REPUBLIQUE YOUGOSLAVE DE MACEDOINE","MK");
		put("MADAGASCAR","MG");
		put("MALAISIE","MY");
		put("MALAWI","MW");
		put("MALDIVES","MV");
		put("MALI","ML");
		put("MALTE","MT");
		put("ILES MARIANNES DU NORD","MP");
		put("ILES MARIANNES","MP");
		put("MAROC","MA");
		put("ILES MARSHALL","MH");
		put("MARSHALL","MH");
		put("MARTINIQUE","MQ");
		put("MAURICE","MU");
		put("MAURITANIE","MR");
		put("MAYOTTE","YT");
		put("MEXIQUE","MX");
		put("ETATS FEDERES DE MICRONESIE","FM");
		put("MICRONESIE","FM");
		put("MOLDOVA","MD");
		put("REPUBLIQUE DE MOLDOVA","MD");
		put("MOLDAVIE","MD");
		put("REPUBLIQUE DE MOLDAVIE","MD");
		put("MONACO","MC");
		put("MONGOLIE","MN");
		put("MONTENEGRO","ME");
		put("MONTSERRAT","MS");
		put("MOZAMBIQUE","MZ");
		put("BIRMANIE","MM");
		put("MYANMAR","MM");

		put("NAMIBIE","NA");
		put("NAURU","NR");
		put("NEPAL","NP");
		put("NICARAGUA","NI");
		put("NIGER","NE");
		put("NIGERIA","NG");
		put("NIUE","NU");
		put("ILE NORFOLK","NF");
		put("NORFOLK","NF");
		put("NORVEGE","NO");
		put("NOUVELLE CALEDONIE","NC");
		put("NOUVELLE ZELANDE","NZ");

		put("TERRITOIRE BRITANNIQUE DE L'OCEAN INDIEN","IO");
		put("OMAN","OM");
		put("OUGANDA","UG");
		put("OUZBEKISTAN","UZ");

		put("PAKISTAN","PK");
		put("PALAOS","PW");
		put("TERRITOIRE PALESTINIEN OCCUPE","PS");
		put("ETAT DE PALESTINE","PS");
		put("PALESTINE","PS");
		put("PANAMA","PA");
		put("PAPOUASIE NOUVELLE GUINEE","PG");
		put("PARAGUAY","PY");
		put("PAYS BAS","NL");
		put("PEROU","PE");
		put("PHILIPPINES","PH");
		put("ILES PITCAIRN","PN");
		put("POLOGNE","PL");
		put("POLYNESIE FRANCAISE","PF");
		put("POLYNESIE","PF");
		put("PORTO RICO","PR");
		put("PUERTO RICO","PR");
		put("PORTUGAL","PT");

		put("QATAR","QA");

		put("REUNION","RE");
		put("LA REUNION","RE");
		put("ILE DE LA REUNION","RE");
		put("ROUMANIE","RO");
		put("ROYAUME UNI","GB");
		put("RUSSIE","RU");
		put("FEDERATION DE RUSSIE","RU");
		put("RWANDA","RW");

		put("SAHARA OCCIDENTAL","EH");
		put("REPUBLIQUE ARABE SAHRAOUIE DEMOCRATIQUE","EH");
		put("SAINT BARTHELEMY","BL");
		put("SAINT HELENE","SH");
		put("SAINTE HELENE","SH");
		put("SAINTE HELENE, ASCENSION ET TRISTAN DA CUNHA","SH");
		put("SAINT HELENE, ASCENSION ET TRISTAN DA CUNHA","SH");
		put("SAINT LUCIE","LC");
		put("SAINTE LUCIE","LC");
		put("SAINT KITTS ET NEVIS","KN");
		put("SAINT CHRISTOPHE ET NIEVES","KN");
		put("SAINT MARIN","SM");
		put("SAINT MARTIN","MF");
		put("SAINT MARTIN FRANCAISE","MF");
		put("SAINT MARTIN (PARTIE FRANCAISE)","MF");
		put("SINT MAARTEN","SX");
		put("SAINT MARTIN NEERLANDAISE","SX");
		put("SAINT MARTIN (PARTIE NEERLANDAISE)","SX");
		put("SAINT PIERRE ET MIQUELON","PM");
		put("SAINT SIEGE","VA");
		put("ETAT DE LA CITE DU VATICAN","VA");
		put("VATICAN","VA");
		put("SAINT VINCENT ET LES GRENADINES","VC");
		put("ILES SALOMON","SB");
		put("SALOMON","SB");
		put("SAMOA","WS");
		put("SAMOA AMERICAINES","AS");
		put("SAO TOME ET PRINCIPE","ST");
		put("SENEGAL","SN");
		put("SERBIE","RS");
		put("SEYCHELLES","SC");
		put("SIERRA LEONE","SL");
		put("SINGAPOUR","SG");
		put("SLOVAQUIE","SK");
		put("SLOVENIE","SI");
		put("SOMALIE","SO");
		put("SOUDAN","SD");
		put("SOUDAN DU SUD","SS");
		put("SRI LANKA","LK");
		put("SUEDE","SE");
		put("SUISSE","CH");
		put("SURINAME","SR");
		put("SVALBARD ET JAN MAYEN","SJ");
		put("SVALBARD ET ILE JAN MAYEN","SJ");
		put("SWAZILAND","SZ");
		put("REPUBLIQUE ARABE SYRIENNE","SY");
		put("SYRIE","SY");

		put("TADJIKISTAN","TJ");
		put("TAIWAN","TW");
		put("TAIWAN, PROVINCE DE CHINE","TW");
		put("TANZANIE","TZ");
		put("REPUBLIQUE UNIE DE TANZANIE","TZ");
		put("TCHAD","TD");
		put("REPUBLIQUE TCHEQUE","CZ");
		put("TERRES AUSTRALES FRANCAISES","TF");
		put("TERRES AUSTRALES ET ANTARCTIQUES FRANCAISES","TF");
		put("THAILANDE","TH");
		put("TIMOR LESTE","TL");
		put("TIMOR ORIENTAL","TL");
		put("TOGO","TG");
		put("TOKELAU","TK");
		put("TONGA","TO");
		put("TRINITE ET TOBAGO","TT");
		put("TUNISIE","TN");
		put("TURKMENISTAN","TM");
		put("ILES TURKS ET CAIQUES","TC");
		put("TURQUIE","TR");
		put("TUVALU","TV");

		put("UKRAINE","UA");
		put("URUGUAY","UY");

		put("VANUATU","VU");
		put("VENEZUELA","VE");
		put("REPUBLIQUE BOLIVARIENNE DU VENEZUELA","VE");
		put("VIET NAM","VN");
		put("VIETNAM","VN");

		put("WALLIS ET FUTUNA","WF");

		put("YEMEN","YE");

		put("ZAMBIE","ZM");
		put("ZIMBABWE","ZW");
	}
	
	
	private void put(String name, String code)
	{map.put(name,code);}


	
	
	

	public Object t(Object obj) throws Exception
	{
		String name = normalize((String) obj);
		name = name.replace("-"," ").trim();
		
		if(!map.containsKey(name))
			throw new Exception("Unknown country name: ["+name+"]");
		return map.get(name);
	}
	
	
	
	public Object g() throws Exception
	{return map;}


	
	private String normalize(String s) throws Exception
	{return (String) normalize.t(s);}
}
