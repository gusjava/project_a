package a.entity.gus06.string.transform.format.html.decode;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170112";}

	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		
		s = r(s,"&lsquo;","\u2018");
		s = r(s,"&rsquo;","\u2019");
		
		s = r(s,"&ldquo;","\u201c");
		s = r(s,"&rdquo;","\u201d");
		
		s = r(s,		"&#33;","!");
		s = r(s,"&quot;",	"&#34;","\"");
		s = r(s,		"&#35;","#");
		s = r(s,"&amp;",	"&#38;","&");
		s = r(s,"&apos;",	"&#39;","'");
		s = r(s,		"&#40;","(");
		s = r(s,		"&#41;",")");
		s = r(s,		"&#42;","*");
		s = r(s,"&sbquo;",	"&#44;",",");
		s = r(s,		"&#45;","-");
		s = r(s,		"&#46;",".");
		s = r(s,		"&#47;","/");
		s = r(s,		"&#58;",":");
		s = r(s,		"&#59;",";");
		s = r(s,		"&#61;","=");
		s = r(s,		"&#63;","?");
		s = r(s,		"&#91;","[");
		s = r(s,		"&#92;","\\");
		s = r(s,		"&#93;","]");
		
		s = r(s,		"&#033;","!");
		s = r(s,"&quot;",	"&#034;","\"");
		s = r(s,		"&#035;","#");
		s = r(s,"&amp;",	"&#038;","&");
		s = r(s,"&apos;",	"&#039;","'");
		s = r(s,		"&#040;","(");
		s = r(s,		"&#041;",")");
		s = r(s,		"&#042;","*");
		s = r(s,"&sbquo;",	"&#044;",",");
		s = r(s,		"&#045;","-");
		s = r(s,		"&#046;",".");
		s = r(s,		"&#047;","/");
		s = r(s,		"&#058;",":");
		s = r(s,		"&#059;",";");
		s = r(s,		"&#061;","=");
		s = r(s,		"&#063;","?");
		s = r(s,		"&#091;","[");
		s = r(s,		"&#092;","\\");
		s = r(s,		"&#093;","]");
		
		s = r(s,"&Aacute;",	"&#193;","\u00c1");
		s = r(s,"&Acirc;",	"&#194;","\u00c2");
		s = r(s,"&Aelig;",	"&#198;","\u00c6");
		s = r(s,"&Agrave;",	"&#192;","\u00c0");
		s = r(s,"&Aring;",	"&#197;","\u00c5");
		s = r(s,"&Atilde;",	"&#195;","\u00c3");
		s = r(s,"&Auml;",	"&#196;","\u00c4");
		s = r(s,"&Ccedil;",	"&#199;","\u00c7");
		s = r(s,"&Yuml;",	"&#159;","\u0178");
		s = r(s,"&acute;",	"&#180;","\u00b4");
		s = r(s,"&brvbar;",	"&#166;","\u00a6");
		s = r(s,"&cedil;",	"&#184;","\u00b8");
		s = r(s,"&cent;",	"&#162;","\u00a2");
		s = r(s,"&copy;",	"&#169;","\u00a9");
		s = r(s,"&curren;",	"&#164;","\u00a4");
		s = r(s,"&deg;",	"&#176;","\u00b0");
		s = r(s,"&euro;",	"&#128;","\u20ac");
		s = r(s,"&frac12;",	"&#189;","\u00bd");
		s = r(s,"&frac14;",	"&#188;","\u00bc");
		s = r(s,"&frac34;",	"&#190;","\u00be");
		s = r(s,"&gt;",		"&#155;",">");
		s = r(s,"&iexcl;",	"&#161;","\u00a1");
		s = r(s,"&iquest;",	"&#191;","\u00bf");
		s = r(s,"&laquo;",	"&#171;","\u00ab");
		s = r(s,"&lt;",		"&#139;","<");
		s = r(s,"&masr;",	"&#175;","\u00af");
		s = r(s,"&micro;",	"&#181;","\u00b5");
		s = r(s,"&middot;",	"&#183;","\u00b7");
		s = r(s,"&nbsp;",	"&#160;"," ");
		s = r(s,"&not;",	"&#172;","\u00ac");
		s = r(s,"&para;",	"&#182;","\u00b6");
		s = r(s,"&plusmn;",	"&#177;","\u00b1");
		s = r(s,"&pound;",	"&#163;","\u00a3");
		s = r(s,"&raquo;",	"&#187;","\u00bb");
		s = r(s,"&reg;",	"&#174;","\u00ae");
		s = r(s,"&sect;",	"&#167;","\u00a7");
		s = r(s,"&shy;",	"&#173;","\u00ad");
		s = r(s,"&sup1;",	"&#185;","\u00b9");
		s = r(s,"&sup2;",	"&#178;","\u00b2");
		s = r(s,"&sup3;",	"&#179;","\u00b3");
		s = r(s,"&yen;",	"&#165;","\u00a5");
		s = r(s,"&uml;",	"&#168;","\u00a8");
		s = r(s,"&oelig;",	"&#156;","\u0153");
		s = r(s,"&ordf;",	"&#170;","\u00aa");
		s = r(s,"&ordm;",	"&#186;","\u00ba");
		
		s = r(s,"&Eacute;",	"&#201;","\u00c9");
		s = r(s,"&Ecirc;",	"&#202;","\u00ca");
		s = r(s,"&Egrave;",	"&#200;","\u00c8");
		s = r(s,"&Euml;",	"&#203;","\u00cb");
		s = r(s,"&Iacute;",	"&#205;","\u00cd");
		s = r(s,"&Icirc;",	"&#206;","\u00ce");
		s = r(s,"&Igrave;",	"&#204;","\u00cc");
		s = r(s,"&Iuml;",	"&#207;","\u00cf");
		s = r(s,"&Ntilde;",	"&#209;","\u00d1");
		s = r(s,"&Oacute;",	"&#211;","\u00d3");
		s = r(s,"&Ocirc;",	"&#212;","\u00d4");
		s = r(s,"&Ograve;",	"&#210;","\u00d2");
		s = r(s,"&Oslash;",	"&#216;","\u00d8");
		s = r(s,"&Otilde;",	"&#213;","\u00d5");
		s = r(s,"&Ouml;",	"&#214;","\u00d6");
		s = r(s,"&Uacute;",	"&#218;","\u00da");
		s = r(s,"&Ucirc;",	"&#219;","\u00db");
		s = r(s,"&Ugrave;",	"&#217;","\u00d9");
		s = r(s,"&Uuml;",	"&#220;","\u00dc");
		s = r(s,"&Yacute;",	"&#221;","\u00dd");
		s = r(s,"&aacute;",	"&#225;","\u00e1");
		s = r(s,"&acirc;",	"&#226;","\u00e2");
		s = r(s,"&aelig;",	"&#230;","\u00e6");
		s = r(s,"&agrave;",	"&#224;","\u00e0");
		s = r(s,"&aring;",	"&#229;","\u00e5");
		s = r(s,"&atilde;",	"&#227;","\u00e3");
		s = r(s,"&auml;",	"&#228;","\u00e4");
		s = r(s,"&ccedil;",	"&#231;","\u00e7");
		s = r(s,"&divide;",	"&#247;","\u00f7");
		s = r(s,"&eacute;",	"&#233;","\u00e9");
		s = r(s,"&ecirc;",	"&#234;","\u00ea");
		s = r(s,"&egrave;",	"&#232;","\u00e8");
		s = r(s,"&eth;",	"&#208;","\u00d0");
		s = r(s,"&eth;",	"&#240;","\u00f0");
		s = r(s,"&euml;",	"&#235;","\u00eb");
		s = r(s,"&iacute;",	"&#237;","\u00ed");
		s = r(s,"&icirc;",	"&#238;","\u00ee");
		s = r(s,"&igrave;",	"&#236;","\u00ec");
		s = r(s,"&iuml;",	"&#239;","\u00ef");
		s = r(s,"&ntilde;",	"&#241;","\u00f1");
		s = r(s,"&oacute;",	"&#243;","\u00f3");
		s = r(s,"&ocirc;",	"&#244;","\u00f4");
		s = r(s,"&ograve;",	"&#242;","\u00f2");
		s = r(s,"&oslash;",	"&#248;","\u00f8");
		s = r(s,"&otilde;",	"&#245;","\u00f5");
		s = r(s,"&ouml;",	"&#246;","\u00f6");
		s = r(s,"&szlig;",	"&#223;","\u00df");
		s = r(s,"&thorn;",	"&#222;","\u00de");
		s = r(s,"&thorn;",	"&#254;","\u00fe");
		s = r(s,"&times;",	"&#215;","\u00d7");
		s = r(s,"&uacute;",	"&#250;","\u00fa");
		s = r(s,"&ucirc;",	"&#251;","\u00fb");
		s = r(s,"&ugrave;",	"&#249;","\u00f9");
		s = r(s,"&uuml;",	"&#252;","\u00fc");
		s = r(s,"&yacute;",	"&#253;","\u00fd");
		s = r(s,"&yuml;",	"&#255;","\u00ff");
		
		return s.replaceAll("(?s)\\s+"," ").trim();
	}
	
	
	private String r(String s, String in1, String rep)
	{return s.replace(in1,rep);}
	
	private String r(String s, String in1, String in2, String rep)
	{return s.replace(in1,rep).replace(in2,rep);}
}