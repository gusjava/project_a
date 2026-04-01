package a.entity.gus06.string.transform.normalize.diacritics;

public class DIACRITICS {

	public static String normalize(String s)
	{
		int l = s.length();
		StringBuilder b = new StringBuilder();
		for(int i=0;i<l;i++) handle(b,s.charAt(i));
		return b.toString();
	}
	
	private static void handle(StringBuilder b, char c)
	{
		switch(c)
		{
		case '\u00e0':b.append('a');break;//� accent grave
		case '\u00e1':b.append('a');break;//� accent aigu
		case '\u00e2':b.append('a');break;//� accent circonflexe
		case '\u00e3':b.append('a');break;//� tilde
		case '\u00e4':b.append('a');break;//� tr�ma
		case '\u00e5':b.append('a');break;//� rond en chef
		case '\u0101':b.append('a');break;//a macron (accent plat)
		case '\u0103':b.append('a');break;//a br�ve (accent goutte)
		case '\u0105':b.append('a');break;//a ogonek (petite queue)
		case '\u1ea1':b.append('a');break;//a point souscrit
		case '\u01ce':b.append('a');break;//a hatchek (accent hirondelle)
		case '\u0227':b.append('a');break;//a point suscrit
		
		case '\u00e6':b.append("ae");break;//�
		case '\u01fd':b.append("ae");break;//� accent aigu
		case '\u01e3':b.append("ae");break;//� macron
		
		case '\u1e03':b.append('b');break;//b point suscrit
		case '\u1e05':b.append('b');break;//b point souscrit
		case '\u0253':b.append('b');break;//b crochet
		
		case '\u00e7':b.append('c');break;//� c�dille
		case '\u0107':b.append('c');break;//c accent aigu
		case '\u0109':b.append('c');break;//c accent circonflexe
		case '\u010b':b.append('c');break;//c point suscrit
		case '\u010d':b.append('c');break;//c hatchek
		case '\u0188':b.append('c');break;//c crochet
		case '\u00f0':b.append('c');break;//� barre
		
		case '\u010f':b.append('d');break;//d hatchek
		case '\u0257':b.append('d');break;//d crochet
		case '\u1e0b':b.append('d');break;//d point suscrit
		case '\u1e0d':b.append('d');break;//d point souscrit
		case '\u1e11':b.append('d');break;//d c�dille
		
		case '\u00e8':b.append('e');break;//� accent grave
		case '\u00e9':b.append('e');break;//� accent aigu
		case '\u00ea':b.append('e');break;//� accent circonflexe
		case '\u00eb':b.append('e');break;//� tr�ma
		case '\u0113':b.append('e');break;//e macron
		case '\u0115':b.append('e');break;//e br�ve
		case '\u0117':b.append('e');break;//e point suscrit
		case '\u0119':b.append('e');break;//e ogonek
		case '\u011b':b.append('e');break;//e hatchek
		case '\u0229':b.append('e');break;//e c�dille
		case '\u1eb9':b.append('e');break;//e point souscrit
		case '\u1ebd':b.append('e');break;//e tilde
		
		case '\u1e1f':b.append('f');break;//f point suscrit
		
		case '\u011d':b.append('g');break;//g accent circonflexe
		case '\u011f':b.append('g');break;//g br�ve
		case '\u0121':b.append('g');break;//g point suscrit
		case '\u0123':b.append('g');break;//g c�dille
		case '\u01e5':b.append('g');break;//g barre
		case '\u01e7':b.append('g');break;//g hatchek
		case '\u0260':b.append('g');break;//g crochet
		case '\u01f5':b.append('g');break;//g accent aigu
		case '\u1e21':b.append('g');break;//g macron
		
		case '\u0125':b.append('h');break;//h accent circonflexe
		case '\u0127':b.append('h');break;//h barre
		case '\u1e23':b.append('h');break;//h point suscrit
		case '\u1e25':b.append('h');break;//h point souscrit
		case '\u1e27':b.append('h');break;//h tr�ma
		case '\u1e29':b.append('h');break;//h c�dille
		case '\u021f':b.append('h');break;//h hatchek
		
		case '\u00ec':b.append('i');break;//� accent grave
		case '\u00ed':b.append('i');break;//� accent aigu
		case '\u00ee':b.append('i');break;//� accent circonflexe
		case '\u00ef':b.append('i');break;//� tr�ma
		case '\u01d0':b.append('i');break;//i hatchek
		case '\u0129':b.append('i');break;//i tilde
		case '\u012b':b.append('i');break;//i macron
		case '\u012d':b.append('i');break;//i br�ve
		case '\u012f':b.append('i');break;//i ogonek
		case '\u1ecb':b.append('i');break;//i point souscrit
		
		case '\u0135':b.append('j');break;//j accent circonflexe
		case '\u01f0':b.append('j');break;//j hatchek
		
		case '\u1e31':b.append('k');break;//k accent aigu
		case '\u1e33':b.append('k');break;//k point souscrit
		case '\u0137':b.append('k');break;//k c�dille
		case '\u0199':b.append('k');break;//k crochet
		case '\u01e9':b.append('k');break;//k hatchek
		
		case '\u1e37':b.append('l');break;//l point souscrit
		case '\u013a':b.append('l');break;//l accent aigu
		case '\u013c':b.append('l');break;//l c�dille
		case '\u013e':b.append('l');break;//l hatchek
		case '\u0142':b.append('l');break;//l barre
		
		case '\u1e3f':b.append('m');break;//m accent aigu
		case '\u1e41':b.append('m');break;//m point suscrit
		case '\u1e43':b.append('m');break;//m point souscrit
		
		case '\u1e45':b.append('n');break;//n point suscrit
		case '\u1e47':b.append('n');break;//n point souscrit
		case '\u01f9':b.append('n');break;//n accent grave
		case '\u00f1':b.append('n');break;//� tilde
		case '\u0144':b.append('n');break;//n accent aigu
		case '\u0146':b.append('n');break;//n c�dille
		case '\u0148':b.append('n');break;//n hatchek
		
		case '\u00f2':b.append('o');break;//� accent grave
		case '\u00f3':b.append('o');break;//� accent aigu
		case '\u00f4':b.append('o');break;//� accent circonflexe
		case '\u00f5':b.append('o');break;//� tilde
		case '\u00f6':b.append('o');break;//� tr�ma
		case '\u00f8':b.append('o');break;//� barre
		case '\u014d':b.append('o');break;//o macron
		case '\u014f':b.append('o');break;//o br�ve
		case '\u0151':b.append('o');break;//o double accent aigu
		case '\u01a1':b.append('o');break;//o corne
		case '\u01d2':b.append('o');break;//o hatchek
		case '\u01eb':b.append('o');break;//o ogonek
		case '\u022f':b.append('o');break;//o point suscrit
		case '\u1ecd':b.append('o');break;//o point souscrit
		
		case '\u0153':b.append("oe");break;//�

		case '\u1e55':b.append('p');break;//p accent aigu
		case '\u1e57':b.append('p');break;//p point suscrit
		case '\u01a5':b.append('p');break;//p crochet
		
		case '\u1e59':b.append('r');break;//r point suscrit
		case '\u1e5b':b.append('r');break;//r point souscrit
		case '\u0155':b.append('r');break;//r accent aigu
		case '\u0157':b.append('r');break;//r c�dille
		case '\u0159':b.append('r');break;//r hatchek
		
		case '\u1e61':b.append('s');break;//s point suscrit
		case '\u1e63':b.append('s');break;//s point souscrit
		case '\u015b':b.append('s');break;//s accent aigu
		case '\u015d':b.append('s');break;//s accent circonflexe
		case '\u015f':b.append('s');break;//s c�dille
		case '\u0161':b.append('s');break;//� hatchek
		case '\u0219':b.append('s');break;//s virgule
		
		case '\u1e6b':b.append('t');break;//t point suscrit
		case '\u1e6d':b.append('t');break;//t point souscrit
		case '\u0163':b.append('t');break;//t c�dille
		case '\u0165':b.append('t');break;//t hatchek
		case '\u0167':b.append('t');break;//t barre
		case '\u01ad':b.append('t');break;//t crochet
		case '\u021b':b.append('t');break;//t virgule
		case '\u1e97':b.append('t');break;//t tr�ma
		
		case '\u1ee5':b.append('u');break;//u point souscrit
		case '\u00f9':b.append('u');break;//� accent grave
		case '\u00fa':b.append('u');break;//� accent aigu
		case '\u00fb':b.append('u');break;//� accent circonflexe
		case '\u00fc':b.append('u');break;//� tr�ma
		case '\u0169':b.append('u');break;//u tilde
		case '\u016b':b.append('u');break;//u macron
		case '\u016d':b.append('u');break;//u br�ve
		case '\u016f':b.append('u');break;//u rond en chef
		case '\u0171':b.append('u');break;//u double accent aigu
		case '\u0173':b.append('u');break;//u ogonek
		case '\u01b0':b.append('u');break;//u corne
		case '\u01d4':b.append('u');break;//u hatchek
		
		case '\u1e7d':b.append('v');break;//v tilde
		case '\u1e7f':b.append('v');break;//v point souscrit
		
		case '\u0175':b.append('w');break;//w accent circonflexe
		case '\u1e81':b.append('w');break;//w accent grave
		case '\u1e83':b.append('w');break;//w accent aigu
		case '\u1e85':b.append('w');break;//w tr�ma
		case '\u1e87':b.append('w');break;//w point suscrit
		case '\u1e89':b.append('w');break;//w point souscrit
		case '\u1e98':b.append('w');break;//w rond en chef
		
		case '\u1e8b':b.append('x');break;//x point suscrit
		case '\u1e8d':b.append('x');break;//x tr�ma
		
		case '\u0177':b.append('y');break;//y accent circonflexe
		case '\u00ff':b.append('y');break;//� tr�ma
		case '\u00fd':b.append('y');break;//� accent aigu
		case '\u1e8f':b.append('y');break;//y point suscrit
		case '\u01b4':b.append('y');break;//y crochet
		case '\u0233':b.append('y');break;//y macron
		case '\u1ef5':b.append('y');break;//y point souscrit
		case '\u1ef9':b.append('y');break;//y tilde
		case '\u1ef3':b.append('y');break;//y accent grave
		case '\u1e99':b.append('y');break;//y rond en chef
		
		case '\u017a':b.append('z');break;//z accent aigu
		case '\u017c':b.append('z');break;//z point suscrit
		case '\u017e':b.append('z');break;//� hatchek
		case '\u01b6':b.append('z');break;//z barre
		case '\u1e91':b.append('z');break;//z accent circonflexe
		case '\u1e93':b.append('z');break;//z point souscrit
		
		case '\u00c0':b.append('A');break;//� accent grave
		case '\u00c1':b.append('A');break;//� accent aigu
		case '\u00c2':b.append('A');break;//� accent circonflexe
		case '\u00c3':b.append('A');break;//� tilde
		case '\u00c4':b.append('A');break;//� tr�ma
		case '\u00c5':b.append('A');break;//� rond en chef
		case '\u0100':b.append('A');break;//A macron
		case '\u0102':b.append('A');break;//A br�ve
		case '\u0104':b.append('A');break;//A ogonek
		case '\u1ea0':b.append('A');break;//A point souscrit
		case '\u01cd':b.append('A');break;//A hatchek
		case '\u0226':b.append('A');break;//A point suscrit
		
		case '\u00c6':b.append("AE");break;//�
		case '\u01fc':b.append("AE");break;//� accent aigu
		case '\u01e2':b.append("AE");break;//� macron
		
		case '\u0243':b.append('B');break;//B barre
		case '\u1e02':b.append('B');break;//B point suscrit
		case '\u1e04':b.append('B');break;//B point souscrit
		case '\u0181':b.append('B');break;//B crochet
		
		case '\u00c7':b.append('C');break;//� c�dille
		case '\u0106':b.append('C');break;//C accent aigu
		case '\u0108':b.append('C');break;//C accent circonflexe
		case '\u010a':b.append('C');break;//C point suscrit
		case '\u010c':b.append('C');break;//C hatchek
		case '\u0187':b.append('C');break;//C crochet
		
		case '\u00d0':b.append('D');break;//D barre
		case '\u010e':b.append('D');break;//D hatchek
		case '\u018a':b.append('D');break;//D crochet
		case '\u1e0a':b.append('D');break;//D point suscrit
		case '\u1e0c':b.append('D');break;//D point souscrit
		case '\u1e10':b.append('D');break;//D c�dille
		
		case '\u00c8':b.append('E');break;//� accent grave
		case '\u00c9':b.append('E');break;//� accent aigu
		case '\u00ca':b.append('E');break;//� accent circonflexe
		case '\u00cb':b.append('E');break;//� tr�ma
		case '\u0112':b.append('E');break;//E macron
		case '\u0114':b.append('E');break;//E br�ve
		case '\u0116':b.append('E');break;//E point suscrit
		case '\u0118':b.append('E');break;//E ogonek
		case '\u011a':b.append('E');break;//E hatchek
		case '\u0228':b.append('E');break;//E c�dille
		case '\u1eb8':b.append('E');break;//E point souscrit
		case '\u1ebc':b.append('E');break;//E tilde
		
		case '\u1e1e':b.append('F');break;//F point suscrit
		
		case '\u011c':b.append('G');break;//G accent circonflexe
		case '\u011e':b.append('G');break;//G br�ve
		case '\u0120':b.append('G');break;//G point suscrit
		case '\u0122':b.append('G');break;//G c�dille
		case '\u01e4':b.append('G');break;//G barre
		case '\u01e6':b.append('G');break;//G hatchek
		case '\u0193':b.append('G');break;//G crochet
		case '\u01f4':b.append('G');break;//G accent aigu
		case '\u1e20':b.append('G');break;//G macron
		
		case '\u0124':b.append('H');break;//H accent circonflexe
		case '\u0126':b.append('H');break;//H barre
		case '\u1e22':b.append('H');break;//H point suscrit
		case '\u1e24':b.append('H');break;//H point souscrit
		case '\u1e26':b.append('H');break;//H tr�ma
		case '\u1e28':b.append('H');break;//H c�dille
		case '\u021e':b.append('H');break;//H hatchek
		
		case '\u00cc':b.append('I');break;//� accent grave
		case '\u00cd':b.append('I');break;//� accent aigu
		case '\u00ce':b.append('I');break;//� accent circonflexe
		case '\u00cf':b.append('I');break;//� tr�ma
		case '\u01cf':b.append('I');break;//I hatchek
		case '\u0128':b.append('I');break;//I tilde
		case '\u012a':b.append('I');break;//I macron
		case '\u012c':b.append('I');break;//I br�ve
		case '\u012e':b.append('I');break;//I ogonek
		case '\u0130':b.append('I');break;//I point suscrit
		case '\u1eca':b.append('I');break;//I point souscrit
		
		case '\u0134':b.append('J');break;//J accent circonflexe
		
		case '\u1e30':b.append('K');break;//K accent aigu
		case '\u1e32':b.append('K');break;//K point souscrit
		case '\u0136':b.append('K');break;//K c�dille
		case '\u0198':b.append('K');break;//K crochet
		case '\u01e8':b.append('K');break;//K hatchek
		
		case '\u1e36':b.append('L');break;//L point souscrit
		case '\u0139':b.append('L');break;//L accent aigu
		case '\u013b':b.append('L');break;//L c�dille
		case '\u013d':b.append('L');break;//L hatchek
		case '\u0141':b.append('L');break;//L barre
		
		case '\u1e3e':b.append('M');break;//M accent aigu
		case '\u1e40':b.append('M');break;//M point suscrit
		case '\u1e42':b.append('M');break;//M point souscrit
		
		case '\u1e44':b.append('N');break;//N point suscrit
		case '\u1e46':b.append('N');break;//N point souscrit
		case '\u01f8':b.append('N');break;//N accent grave
		case '\u00d1':b.append('N');break;//N tilde
		case '\u0143':b.append('N');break;//N accent aigu
		case '\u0145':b.append('N');break;//N c�dille
		case '\u0147':b.append('N');break;//N hatchek
		
		case '\u00d2':b.append('O');break;//� accent grave
		case '\u00d3':b.append('O');break;//� accent aigu
		case '\u00d4':b.append('O');break;//� accent circonflexe
		case '\u00d5':b.append('O');break;//� tilde
		case '\u00d6':b.append('O');break;//� tr�ma
		case '\u00d8':b.append('O');break;//� barre
		case '\u014c':b.append('O');break;//O macron
		case '\u014e':b.append('O');break;//O br�ve
		case '\u0150':b.append('O');break;//O double accent aigu
		case '\u01a0':b.append('O');break;//O corne
		case '\u01d1':b.append('O');break;//O hatchek
		case '\u01ea':b.append('O');break;//O ogonek
		case '\u022e':b.append('O');break;//O point suscrit
		case '\u1ecc':b.append('O');break;//O point souscrit
		
		case '\u0152':b.append("OE");break;//�
		
		case '\u1e54':b.append('P');break;//P accent aigu
		case '\u1e56':b.append('P');break;//P point suscrit
		case '\u01a4':b.append('P');break;//P crochet
		
		case '\u1e58':b.append('R');break;//R point suscrit
		case '\u1e5a':b.append('R');break;//R point souscrit
		case '\u0154':b.append('R');break;//R accent aigu
		case '\u0156':b.append('R');break;//R c�dille
		case '\u0158':b.append('R');break;//R hatchek
		
		case '\u1e60':b.append('S');break;//S point suscrit
		case '\u1e62':b.append('S');break;//S point souscrit
		case '\u015a':b.append('S');break;//S accent aigu
		case '\u015c':b.append('S');break;//S accent circonflexe
		case '\u015e':b.append('S');break;//S c�dille
		case '\u0160':b.append('S');break;//S hatchek
		case '\u0218':b.append('S');break;//S virgule
		
		case '\u1e6a':b.append('T');break;//T point suscrit
		case '\u1e6c':b.append('T');break;//T point souscrit
		case '\u0162':b.append('T');break;//T c�dille
		case '\u0164':b.append('T');break;//T hatchek
		case '\u0166':b.append('T');break;//T barre
		case '\u01ac':b.append('T');break;//T crochet
		case '\u021a':b.append('T');break;//T virgule
		
		case '\u1ee4':b.append('U');break;//U point souscrit
		case '\u00d9':b.append('U');break;//� accent grave
		case '\u00da':b.append('U');break;//� accent aigu
		case '\u00db':b.append('U');break;//� accent circonflexe
		case '\u00dc':b.append('U');break;//� tr�ma
		case '\u0168':b.append('U');break;//U tilde
		case '\u016a':b.append('U');break;//U macron
		case '\u016c':b.append('U');break;//U br�ve
		case '\u016e':b.append('U');break;//U rond en chef
		case '\u0170':b.append('U');break;//U double accent aigu
		case '\u0172':b.append('U');break;//U ogonek
		case '\u01af':b.append('U');break;//U corne
		case '\u01d3':b.append('U');break;//U hatchek
		
		case '\u1e7c':b.append('V');break;//V tilde
		case '\u1e7e':b.append('V');break;//V point souscrit
		
		case '\u0174':b.append('W');break;//W accent circonflexe
		case '\u1e80':b.append('W');break;//W accent grave
		case '\u1e82':b.append('W');break;//W accent aigu
		case '\u1e84':b.append('W');break;//W tr�ma
		case '\u1e86':b.append('W');break;//W point suscrit
		case '\u1e88':b.append('W');break;//W point souscrit
		
		case '\u1e8a':b.append('X');break;//X point suscrit
		case '\u1e8c':b.append('X');break;//X tr�ma
		
		case '\u0176':b.append('Y');break;//Y accent circonflexe
		case '\u0178':b.append('Y');break;//� tr�ma
		case '\u00dd':b.append('Y');break;//� accent aigu
		case '\u1e8e':b.append('Y');break;//Y point suscrit
		case '\u01b3':b.append('Y');break;//Y crochet
		case '\u0232':b.append('Y');break;//Y macron
		case '\u1ef4':b.append('Y');break;//Y point souscrit
		case '\u1ef8':b.append('Y');break;//Y tilde
		case '\u1ef2':b.append('Y');break;//Y accent grave
		
		case '\u0179':b.append('Z');break;//Z accent aigu
		case '\u017b':b.append('Z');break;//Z point suscrit
		case '\u017d':b.append('Z');break;//Z hatchek
		case '\u01b5':b.append('Z');break;//Z barre
		case '\u1e90':b.append('Z');break;//Z accent circonflexe
		case '\u1e92':b.append('Z');break;//Z point souscrit
		
		default: b.append(c);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static boolean isDiacritic(char c)
	{
		switch(c)
		{
		case '\u00e0':return true;//� accent grave
		case '\u00e1':return true;//� accent aigu
		case '\u00e2':return true;//� accent circonflexe
		case '\u00e3':return true;//� tilde
		case '\u00e4':return true;//� tr�ma
		case '\u00e5':return true;//� rond en chef
		case '\u0101':return true;//a macron (accent plat)
		case '\u0103':return true;//a br�ve (accent goutte)
		case '\u0105':return true;//a ogonek (petite queue)
		case '\u1ea1':return true;//a point souscrit
		case '\u01ce':return true;//a hatchek (accent hirondelle)
		case '\u0227':return true;//a point suscrit
		
		case '\u00e6':return true;//�
		case '\u01fd':return true;//� accent aigu
		case '\u01e3':return true;//� macron
		
		case '\u1e03':return true;//b point suscrit
		case '\u1e05':return true;//b point souscrit
		case '\u0253':return true;//b crochet
		
		case '\u00e7':return true;//� c�dille
		case '\u0107':return true;//c accent aigu
		case '\u0109':return true;//c accent circonflexe
		case '\u010b':return true;//c point suscrit
		case '\u010d':return true;//c hatchek
		case '\u0188':return true;//c crochet
		case '\u00f0':return true;//� barre
		
		case '\u010f':return true;//d hatchek
		case '\u0257':return true;//d crochet
		case '\u1e0b':return true;//d point suscrit
		case '\u1e0d':return true;//d point souscrit
		case '\u1e11':return true;//d c�dille
		
		case '\u00e8':return true;//� accent grave
		case '\u00e9':return true;//� accent aigu
		case '\u00ea':return true;//� accent circonflexe
		case '\u00eb':return true;//� tr�ma
		case '\u0113':return true;//e macron
		case '\u0115':return true;//e br�ve
		case '\u0117':return true;//e point suscrit
		case '\u0119':return true;//e ogonek
		case '\u011b':return true;//e hatchek
		case '\u0229':return true;//e c�dille
		case '\u1eb9':return true;//e point souscrit
		case '\u1ebd':return true;//e tilde
		
		case '\u1e1f':return true;//f point suscrit
		
		case '\u011d':return true;//g accent circonflexe
		case '\u011f':return true;//g br�ve
		case '\u0121':return true;//g point suscrit
		case '\u0123':return true;//g c�dille
		case '\u01e5':return true;//g barre
		case '\u01e7':return true;//g hatchek
		case '\u0260':return true;//g crochet
		case '\u01f5':return true;//g accent aigu
		case '\u1e21':return true;//g macron
		
		case '\u0125':return true;//h accent circonflexe
		case '\u0127':return true;//h barre
		case '\u1e23':return true;//h point suscrit
		case '\u1e25':return true;//h point souscrit
		case '\u1e27':return true;//h tr�ma
		case '\u1e29':return true;//h c�dille
		case '\u021f':return true;//h hatchek
		
		case '\u00ec':return true;//� accent grave
		case '\u00ed':return true;//� accent aigu
		case '\u00ee':return true;//� accent circonflexe
		case '\u00ef':return true;//� tr�ma
		case '\u01d0':return true;//i hatchek
		case '\u0129':return true;//i tilde
		case '\u012b':return true;//i macron
		case '\u012d':return true;//i br�ve
		case '\u012f':return true;//i ogonek
		case '\u1ecb':return true;//i point souscrit
		
		case '\u0135':return true;//j accent circonflexe
		case '\u01f0':return true;//j hatchek
		
		case '\u1e31':return true;//k accent aigu
		case '\u1e33':return true;//k point souscrit
		case '\u0137':return true;//k c�dille
		case '\u0199':return true;//k crochet
		case '\u01e9':return true;//k hatchek
		
		case '\u1e37':return true;//l point souscrit
		case '\u013a':return true;//l accent aigu
		case '\u013c':return true;//l c�dille
		case '\u013e':return true;//l hatchek
		case '\u0142':return true;//l barre
		
		case '\u1e3f':return true;//m accent aigu
		case '\u1e41':return true;//m point suscrit
		case '\u1e43':return true;//m point souscrit
		
		case '\u1e45':return true;//n point suscrit
		case '\u1e47':return true;//n point souscrit
		case '\u01f9':return true;//n accent grave
		case '\u00f1':return true;//� tilde
		case '\u0144':return true;//n accent aigu
		case '\u0146':return true;//n c�dille
		case '\u0148':return true;//n hatchek
		
		case '\u00f2':return true;//� accent grave
		case '\u00f3':return true;//� accent aigu
		case '\u00f4':return true;//� accent circonflexe
		case '\u00f5':return true;//� tilde
		case '\u00f6':return true;//� tr�ma
		case '\u00f8':return true;//� barre
		case '\u014d':return true;//o macron
		case '\u014f':return true;//o br�ve
		case '\u0151':return true;//o double accent aigu
		case '\u01a1':return true;//o corne
		case '\u01d2':return true;//o hatchek
		case '\u01eb':return true;//o ogonek
		case '\u022f':return true;//o point suscrit
		case '\u1ecd':return true;//o point souscrit
		
		case '\u0153':return true;//�

		case '\u1e55':return true;//p accent aigu
		case '\u1e57':return true;//p point suscrit
		case '\u01a5':return true;//p crochet
		
		case '\u1e59':return true;//r point suscrit
		case '\u1e5b':return true;//r point souscrit
		case '\u0155':return true;//r accent aigu
		case '\u0157':return true;//r c�dille
		case '\u0159':return true;//r hatchek
		
		case '\u1e61':return true;//s point suscrit
		case '\u1e63':return true;//s point souscrit
		case '\u015b':return true;//s accent aigu
		case '\u015d':return true;//s accent circonflexe
		case '\u015f':return true;//s c�dille
		case '\u0161':return true;//� hatchek
		case '\u0219':return true;//s virgule
		
		case '\u1e6b':return true;//t point suscrit
		case '\u1e6d':return true;//t point souscrit
		case '\u0163':return true;//t c�dille
		case '\u0165':return true;//t hatchek
		case '\u0167':return true;//t barre
		case '\u01ad':return true;//t crochet
		case '\u021b':return true;//t virgule
		case '\u1e97':return true;//t tr�ma
		
		case '\u1ee5':return true;//u point souscrit
		case '\u00f9':return true;//� accent grave
		case '\u00fa':return true;//� accent aigu
		case '\u00fb':return true;//� accent circonflexe
		case '\u00fc':return true;//� tr�ma
		case '\u0169':return true;//u tilde
		case '\u016b':return true;//u macron
		case '\u016d':return true;//u br�ve
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
		case '\u1e85':return true;//w tr�ma
		case '\u1e87':return true;//w point suscrit
		case '\u1e89':return true;//w point souscrit
		case '\u1e98':return true;//w rond en chef
		
		case '\u1e8b':return true;//x point suscrit
		case '\u1e8d':return true;//x tr�ma
		
		case '\u0177':return true;//y accent circonflexe
		case '\u00ff':return true;//� tr�ma
		case '\u00fd':return true;//� accent aigu
		case '\u1e8f':return true;//y point suscrit
		case '\u01b4':return true;//y crochet
		case '\u0233':return true;//y macron
		case '\u1ef5':return true;//y point souscrit
		case '\u1ef9':return true;//y tilde
		case '\u1ef3':return true;//y accent grave
		case '\u1e99':return true;//y rond en chef
		
		case '\u017a':return true;//z accent aigu
		case '\u017c':return true;//z point suscrit
		case '\u017e':return true;//� hatchek
		case '\u01b6':return true;//z barre
		case '\u1e91':return true;//z accent circonflexe
		case '\u1e93':return true;//z point souscrit
		
		case '\u00c0':return true;//� accent grave
		case '\u00c1':return true;//� accent aigu
		case '\u00c2':return true;//� accent circonflexe
		case '\u00c3':return true;//� tilde
		case '\u00c4':return true;//� tr�ma
		case '\u00c5':return true;//� rond en chef
		case '\u0100':return true;//A macron
		case '\u0102':return true;//A br�ve
		case '\u0104':return true;//A ogonek
		case '\u1ea0':return true;//A point souscrit
		case '\u01cd':return true;//A hatchek
		case '\u0226':return true;//A point suscrit
		
		case '\u00c6':return true;//�
		case '\u01fc':return true;//� accent aigu
		case '\u01e2':return true;//� macron
		
		case '\u0243':return true;//B barre
		case '\u1e02':return true;//B point suscrit
		case '\u1e04':return true;//B point souscrit
		case '\u0181':return true;//B crochet
		
		case '\u00c7':return true;//� c�dille
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
		case '\u1e10':return true;//D c�dille
		
		case '\u00c8':return true;//� accent grave
		case '\u00c9':return true;//� accent aigu
		case '\u00ca':return true;//� accent circonflexe
		case '\u00cb':return true;//� tr�ma
		case '\u0112':return true;//E macron
		case '\u0114':return true;//E br�ve
		case '\u0116':return true;//E point suscrit
		case '\u0118':return true;//E ogonek
		case '\u011a':return true;//E hatchek
		case '\u0228':return true;//E c�dille
		case '\u1eb8':return true;//E point souscrit
		case '\u1ebc':return true;//E tilde
		
		case '\u1e1e':return true;//F point suscrit
		
		case '\u011c':return true;//G accent circonflexe
		case '\u011e':return true;//G br�ve
		case '\u0120':return true;//G point suscrit
		case '\u0122':return true;//G c�dille
		case '\u01e4':return true;//G barre
		case '\u01e6':return true;//G hatchek
		case '\u0193':return true;//G crochet
		case '\u01f4':return true;//G accent aigu
		case '\u1e20':return true;//G macron
		
		case '\u0124':return true;//H accent circonflexe
		case '\u0126':return true;//H barre
		case '\u1e22':return true;//H point suscrit
		case '\u1e24':return true;//H point souscrit
		case '\u1e26':return true;//H tr�ma
		case '\u1e28':return true;//H c�dille
		case '\u021e':return true;//H hatchek
		
		case '\u00cc':return true;//� accent grave
		case '\u00cd':return true;//� accent aigu
		case '\u00ce':return true;//� accent circonflexe
		case '\u00cf':return true;//� tr�ma
		case '\u01cf':return true;//I hatchek
		case '\u0128':return true;//I tilde
		case '\u012a':return true;//I macron
		case '\u012c':return true;//I br�ve
		case '\u012e':return true;//I ogonek
		case '\u0130':return true;//I point suscrit
		case '\u1eca':return true;//I point souscrit
		
		case '\u0134':return true;//J accent circonflexe
		
		case '\u1e30':return true;//K accent aigu
		case '\u1e32':return true;//K point souscrit
		case '\u0136':return true;//K c�dille
		case '\u0198':return true;//K crochet
		case '\u01e8':return true;//K hatchek
		
		case '\u1e36':return true;//L point souscrit
		case '\u0139':return true;//L accent aigu
		case '\u013b':return true;//L c�dille
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
		case '\u0145':return true;//N c�dille
		case '\u0147':return true;//N hatchek
		
		case '\u00d2':return true;//� accent grave
		case '\u00d3':return true;//� accent aigu
		case '\u00d4':return true;//� accent circonflexe
		case '\u00d5':return true;//� tilde
		case '\u00d6':return true;//� tr�ma
		case '\u00d8':return true;//� barre
		case '\u014c':return true;//O macron
		case '\u014e':return true;//O br�ve
		case '\u0150':return true;//O double accent aigu
		case '\u01a0':return true;//O corne
		case '\u01d1':return true;//O hatchek
		case '\u01ea':return true;//O ogonek
		case '\u022e':return true;//O point suscrit
		case '\u1ecc':return true;//O point souscrit
		
		case '\u0152':return true;//�
		
		case '\u1e54':return true;//P accent aigu
		case '\u1e56':return true;//P point suscrit
		case '\u01a4':return true;//P crochet
		
		case '\u1e58':return true;//R point suscrit
		case '\u1e5a':return true;//R point souscrit
		case '\u0154':return true;//R accent aigu
		case '\u0156':return true;//R c�dille
		case '\u0158':return true;//R hatchek
		
		case '\u1e60':return true;//S point suscrit
		case '\u1e62':return true;//S point souscrit
		case '\u015a':return true;//S accent aigu
		case '\u015c':return true;//S accent circonflexe
		case '\u015e':return true;//S c�dille
		case '\u0160':return true;//S hatchek
		case '\u0218':return true;//S virgule
		
		case '\u1e6a':return true;//T point suscrit
		case '\u1e6c':return true;//T point souscrit
		case '\u0162':return true;//T c�dille
		case '\u0164':return true;//T hatchek
		case '\u0166':return true;//T barre
		case '\u01ac':return true;//T crochet
		case '\u021a':return true;//T virgule
		
		case '\u1ee4':return true;//U point souscrit
		case '\u00d9':return true;//� accent grave
		case '\u00da':return true;//� accent aigu
		case '\u00db':return true;//� accent circonflexe
		case '\u00dc':return true;//� tr�ma
		case '\u0168':return true;//U tilde
		case '\u016a':return true;//U macron
		case '\u016c':return true;//U br�ve
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
		case '\u1e84':return true;//W tr�ma
		case '\u1e86':return true;//W point suscrit
		case '\u1e88':return true;//W point souscrit
		
		case '\u1e8a':return true;//X point suscrit
		case '\u1e8c':return true;//X tr�ma
		
		case '\u0176':return true;//Y accent circonflexe
		case '\u0178':return true;//� tr�ma
		case '\u00dd':return true;//� accent aigu
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
