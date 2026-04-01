package a.entity.gus06.data.string.countchar.diacritics;

public class DIACRITICS {
	
	public static boolean isDiacritic(char c)
	{
		switch(c)
		{
		case '\u00e0':return true;// accent grave
		case '\u00e1':return true;// accent aigu
		case '\u00e2':return true;// accent circonflexe
		case '\u00e3':return true;// tilde
		case '\u00e4':return true;// trma
		case '\u00e5':return true;// rond en chef
		case '\u0101':return true;//a macron (accent plat)
		case '\u0103':return true;//a brve (accent goutte)
		case '\u0105':return true;//a ogonek (petite queue)
		case '\u1ea1':return true;//a point souscrit
		case '\u01ce':return true;//a hatchek (accent hirondelle)
		case '\u0227':return true;//a point suscrit
		
		case '\u00e6':return true;//
		case '\u01fd':return true;// accent aigu
		case '\u01e3':return true;// macron
		
		case '\u1e03':return true;//b point suscrit
		case '\u1e05':return true;//b point souscrit
		case '\u0253':return true;//b crochet
		
		case '\u00e7':return true;// cdille
		case '\u0107':return true;//c accent aigu
		case '\u0109':return true;//c accent circonflexe
		case '\u010b':return true;//c point suscrit
		case '\u010d':return true;//c hatchek
		case '\u0188':return true;//c crochet
		case '\u00f0':return true;// barre
		
		case '\u010f':return true;//d hatchek
		case '\u0257':return true;//d crochet
		case '\u1e0b':return true;//d point suscrit
		case '\u1e0d':return true;//d point souscrit
		case '\u1e11':return true;//d cdille
		
		case '\u00e8':return true;// accent grave
		case '\u00e9':return true;// accent aigu
		case '\u00ea':return true;// accent circonflexe
		case '\u00eb':return true;// trma
		case '\u0113':return true;//e macron
		case '\u0115':return true;//e brve
		case '\u0117':return true;//e point suscrit
		case '\u0119':return true;//e ogonek
		case '\u011b':return true;//e hatchek
		case '\u0229':return true;//e cdille
		case '\u1eb9':return true;//e point souscrit
		case '\u1ebd':return true;//e tilde
		
		case '\u1e1f':return true;//f point suscrit
		
		case '\u011d':return true;//g accent circonflexe
		case '\u011f':return true;//g brve
		case '\u0121':return true;//g point suscrit
		case '\u0123':return true;//g cdille
		case '\u01e5':return true;//g barre
		case '\u01e7':return true;//g hatchek
		case '\u0260':return true;//g crochet
		case '\u01f5':return true;//g accent aigu
		case '\u1e21':return true;//g macron
		
		case '\u0125':return true;//h accent circonflexe
		case '\u0127':return true;//h barre
		case '\u1e23':return true;//h point suscrit
		case '\u1e25':return true;//h point souscrit
		case '\u1e27':return true;//h trma
		case '\u1e29':return true;//h cdille
		case '\u021f':return true;//h hatchek
		
		case '\u00ec':return true;// accent grave
		case '\u00ed':return true;// accent aigu
		case '\u00ee':return true;// accent circonflexe
		case '\u00ef':return true;// trma
		case '\u01d0':return true;//i hatchek
		case '\u0129':return true;//i tilde
		case '\u012b':return true;//i macron
		case '\u012d':return true;//i brve
		case '\u012f':return true;//i ogonek
		case '\u1ecb':return true;//i point souscrit
		
		case '\u0135':return true;//j accent circonflexe
		case '\u01f0':return true;//j hatchek
		
		case '\u1e31':return true;//k accent aigu
		case '\u1e33':return true;//k point souscrit
		case '\u0137':return true;//k cdille
		case '\u0199':return true;//k crochet
		case '\u01e9':return true;//k hatchek
		
		case '\u1e37':return true;//l point souscrit
		case '\u013a':return true;//l accent aigu
		case '\u013c':return true;//l cdille
		case '\u013e':return true;//l hatchek
		case '\u0142':return true;//l barre
		
		case '\u1e3f':return true;//m accent aigu
		case '\u1e41':return true;//m point suscrit
		case '\u1e43':return true;//m point souscrit
		
		case '\u1e45':return true;//n point suscrit
		case '\u1e47':return true;//n point souscrit
		case '\u01f9':return true;//n accent grave
		case '\u00f1':return true;// tilde
		case '\u0144':return true;//n accent aigu
		case '\u0146':return true;//n cdille
		case '\u0148':return true;//n hatchek
		
		case '\u00f2':return true;// accent grave
		case '\u00f3':return true;// accent aigu
		case '\u00f4':return true;// accent circonflexe
		case '\u00f5':return true;// tilde
		case '\u00f6':return true;// trma
		case '\u00f8':return true;// barre
		case '\u014d':return true;//o macron
		case '\u014f':return true;//o brve
		case '\u0151':return true;//o double accent aigu
		case '\u01a1':return true;//o corne
		case '\u01d2':return true;//o hatchek
		case '\u01eb':return true;//o ogonek
		case '\u022f':return true;//o point suscrit
		case '\u1ecd':return true;//o point souscrit
		
		case '\u0153':return true;//
		case '\u1e55':return true;//p accent aigu
		case '\u1e57':return true;//p point suscrit
		case '\u01a5':return true;//p crochet
		
		case '\u1e59':return true;//r point suscrit
		case '\u1e5b':return true;//r point souscrit
		case '\u0155':return true;//r accent aigu
		case '\u0157':return true;//r cdille
		case '\u0159':return true;//r hatchek
		
		case '\u1e61':return true;//s point suscrit
		case '\u1e63':return true;//s point souscrit
		case '\u015b':return true;//s accent aigu
		case '\u015d':return true;//s accent circonflexe
		case '\u015f':return true;//s cdille
		case '\u0161':return true;// hatchek
		case '\u0219':return true;//s virgule
		
		case '\u1e6b':return true;//t point suscrit
		case '\u1e6d':return true;//t point souscrit
		case '\u0163':return true;//t cdille
		case '\u0165':return true;//t hatchek
		case '\u0167':return true;//t barre
		case '\u01ad':return true;//t crochet
		case '\u021b':return true;//t virgule
		case '\u1e97':return true;//t trma
		
		case '\u1ee5':return true;//u point souscrit
		case '\u00f9':return true;// accent grave
		case '\u00fa':return true;// accent aigu
		case '\u00fb':return true;// accent circonflexe
		case '\u00fc':return true;// trma
		case '\u0169':return true;//u tilde
		case '\u016b':return true;//u macron
		case '\u016d':return true;//u brve
		case '\u016f':return true;//u rond en chef
		case '\u0171':return true;//u double accent aigu
		case '\u0173':return true;//u ogonek
		case '\u01b0':return true;//u corne
		case '\u01d4':return true;//u hatchek
		
		case '\u1e7d':return true;//v tilde
		case '\u1e7f':return true;//v point souscrit
		
		case '\u0175':return true;//w accent circonflexe
		case '\u1e81':return true;//w accent grave
		case '\u1e83':return true;//w accent aigu
		case '\u1e85':return true;//w trma
		case '\u1e87':return true;//w point suscrit
		case '\u1e89':return true;//w point souscrit
		case '\u1e98':return true;//w rond en chef
		
		case '\u1e8b':return true;//x point suscrit
		case '\u1e8d':return true;//x trma
		
		case '\u0177':return true;//y accent circonflexe
		case '\u00ff':return true;// trma
		case '\u00fd':return true;// accent aigu
		case '\u1e8f':return true;//y point suscrit
		case '\u01b4':return true;//y crochet
		case '\u0233':return true;//y macron
		case '\u1ef5':return true;//y point souscrit
		case '\u1ef9':return true;//y tilde
		case '\u1ef3':return true;//y accent grave
		case '\u1e99':return true;//y rond en chef
		
		case '\u017a':return true;//z accent aigu
		case '\u017c':return true;//z point suscrit
		case '\u017e':return true;// hatchek
		case '\u01b6':return true;//z barre
		case '\u1e91':return true;//z accent circonflexe
		case '\u1e93':return true;//z point souscrit
		
		case '\u00c0':return true;// accent grave
		case '\u00c1':return true;// accent aigu
		case '\u00c2':return true;// accent circonflexe
		case '\u00c3':return true;// tilde
		case '\u00c4':return true;// trma
		case '\u00c5':return true;// rond en chef
		case '\u0100':return true;//A macron
		case '\u0102':return true;//A brve
		case '\u0104':return true;//A ogonek
		case '\u1ea0':return true;//A point souscrit
		case '\u01cd':return true;//A hatchek
		case '\u0226':return true;//A point suscrit
		
		case '\u00c6':return true;//
		case '\u01fc':return true;// accent aigu
		case '\u01e2':return true;// macron
		
		case '\u0243':return true;//B barre
		case '\u1e02':return true;//B point suscrit
		case '\u1e04':return true;//B point souscrit
		case '\u0181':return true;//B crochet
		
		case '\u00c7':return true;// cdille
		case '\u0106':return true;//C accent aigu
		case '\u0108':return true;//C accent circonflexe
		case '\u010a':return true;//C point suscrit
		case '\u010c':return true;//C hatchek
		case '\u0187':return true;//C crochet
		
		case '\u00d0':return true;//D barre
		case '\u010e':return true;//D hatchek
		case '\u018a':return true;//D crochet
		case '\u1e0a':return true;//D point suscrit
		case '\u1e0c':return true;//D point souscrit
		case '\u1e10':return true;//D cdille
		
		case '\u00c8':return true;// accent grave
		case '\u00c9':return true;// accent aigu
		case '\u00ca':return true;// accent circonflexe
		case '\u00cb':return true;// trma
		case '\u0112':return true;//E macron
		case '\u0114':return true;//E brve
		case '\u0116':return true;//E point suscrit
		case '\u0118':return true;//E ogonek
		case '\u011a':return true;//E hatchek
		case '\u0228':return true;//E cdille
		case '\u1eb8':return true;//E point souscrit
		case '\u1ebc':return true;//E tilde
		
		case '\u1e1e':return true;//F point suscrit
		
		case '\u011c':return true;//G accent circonflexe
		case '\u011e':return true;//G brve
		case '\u0120':return true;//G point suscrit
		case '\u0122':return true;//G cdille
		case '\u01e4':return true;//G barre
		case '\u01e6':return true;//G hatchek
		case '\u0193':return true;//G crochet
		case '\u01f4':return true;//G accent aigu
		case '\u1e20':return true;//G macron
		
		case '\u0124':return true;//H accent circonflexe
		case '\u0126':return true;//H barre
		case '\u1e22':return true;//H point suscrit
		case '\u1e24':return true;//H point souscrit
		case '\u1e26':return true;//H trma
		case '\u1e28':return true;//H cdille
		case '\u021e':return true;//H hatchek
		
		case '\u00cc':return true;// accent grave
		case '\u00cd':return true;// accent aigu
		case '\u00ce':return true;// accent circonflexe
		case '\u00cf':return true;// trma
		case '\u01cf':return true;//I hatchek
		case '\u0128':return true;//I tilde
		case '\u012a':return true;//I macron
		case '\u012c':return true;//I brve
		case '\u012e':return true;//I ogonek
		case '\u0130':return true;//I point suscrit
		case '\u1eca':return true;//I point souscrit
		
		case '\u0134':return true;//J accent circonflexe
		
		case '\u1e30':return true;//K accent aigu
		case '\u1e32':return true;//K point souscrit
		case '\u0136':return true;//K cdille
		case '\u0198':return true;//K crochet
		case '\u01e8':return true;//K hatchek
		
		case '\u1e36':return true;//L point souscrit
		case '\u0139':return true;//L accent aigu
		case '\u013b':return true;//L cdille
		case '\u013d':return true;//L hatchek
		case '\u0141':return true;//L barre
		
		case '\u1e3e':return true;//M accent aigu
		case '\u1e40':return true;//M point suscrit
		case '\u1e42':return true;//M point souscrit
		
		case '\u1e44':return true;//N point suscrit
		case '\u1e46':return true;//N point souscrit
		case '\u01f8':return true;//N accent grave
		case '\u00d1':return true;//N tilde
		case '\u0143':return true;//N accent aigu
		case '\u0145':return true;//N cdille
		case '\u0147':return true;//N hatchek
		
		case '\u00d2':return true;// accent grave
		case '\u00d3':return true;// accent aigu
		case '\u00d4':return true;// accent circonflexe
		case '\u00d5':return true;// tilde
		case '\u00d6':return true;// trma
		case '\u00d8':return true;// barre
		case '\u014c':return true;//O macron
		case '\u014e':return true;//O brve
		case '\u0150':return true;//O double accent aigu
		case '\u01a0':return true;//O corne
		case '\u01d1':return true;//O hatchek
		case '\u01ea':return true;//O ogonek
		case '\u022e':return true;//O point suscrit
		case '\u1ecc':return true;//O point souscrit
		
		case '\u0152':return true;//
		
		case '\u1e54':return true;//P accent aigu
		case '\u1e56':return true;//P point suscrit
		case '\u01a4':return true;//P crochet
		
		case '\u1e58':return true;//R point suscrit
		case '\u1e5a':return true;//R point souscrit
		case '\u0154':return true;//R accent aigu
		case '\u0156':return true;//R cdille
		case '\u0158':return true;//R hatchek
		
		case '\u1e60':return true;//S point suscrit
		case '\u1e62':return true;//S point souscrit
		case '\u015a':return true;//S accent aigu
		case '\u015c':return true;//S accent circonflexe
		case '\u015e':return true;//S cdille
		case '\u0160':return true;//S hatchek
		case '\u0218':return true;//S virgule
		
		case '\u1e6a':return true;//T point suscrit
		case '\u1e6c':return true;//T point souscrit
		case '\u0162':return true;//T cdille
		case '\u0164':return true;//T hatchek
		case '\u0166':return true;//T barre
		case '\u01ac':return true;//T crochet
		case '\u021a':return true;//T virgule
		
		case '\u1ee4':return true;//U point souscrit
		case '\u00d9':return true;// accent grave
		case '\u00da':return true;// accent aigu
		case '\u00db':return true;// accent circonflexe
		case '\u00dc':return true;// trma
		case '\u0168':return true;//U tilde
		case '\u016a':return true;//U macron
		case '\u016c':return true;//U brve
		case '\u016e':return true;//U rond en chef
		case '\u0170':return true;//U double accent aigu
		case '\u0172':return true;//U ogonek
		case '\u01af':return true;//U corne
		case '\u01d3':return true;//U hatchek
		
		case '\u1e7c':return true;//V tilde
		case '\u1e7e':return true;//V point souscrit
		
		case '\u0174':return true;//W accent circonflexe
		case '\u1e80':return true;//W accent grave
		case '\u1e82':return true;//W accent aigu
		case '\u1e84':return true;//W trma
		case '\u1e86':return true;//W point suscrit
		case '\u1e88':return true;//W point souscrit
		
		case '\u1e8a':return true;//X point suscrit
		case '\u1e8c':return true;//X trma
		
		case '\u0176':return true;//Y accent circonflexe
		case '\u0178':return true;// trma
		case '\u00dd':return true;// accent aigu
		case '\u1e8e':return true;//Y point suscrit
		case '\u01b3':return true;//Y crochet
		case '\u0232':return true;//Y macron
		case '\u1ef4':return true;//Y point souscrit
		case '\u1ef8':return true;//Y tilde
		case '\u1ef2':return true;//Y accent grave
		
		case '\u0179':return true;//Z accent aigu
		case '\u017b':return true;//Z point suscrit
		case '\u017d':return true;//Z hatchek
		case '\u01b5':return true;//Z barre
		case '\u1e90':return true;//Z accent circonflexe
		case '\u1e92':return true;//Z point souscrit
		
		default: return false;
		}
	}
}
