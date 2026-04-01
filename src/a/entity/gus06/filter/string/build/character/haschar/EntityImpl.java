package a.entity.gus06.filter.string.build.character.haschar;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150526";}


	private Service hasDigit;
	private Service hasLetter;
	private Service hasWhitespace;
	private Service hasLowercase;
	private Service hasUppercase;
	private Service hasDiacritics;
	private Service hasPunctuation;
	private Service hasIdeograph;
	private Service hasLatin;
	private Service hasUnicode;

	private Service hasJapanese;
	private Service hasKanji;
	private Service hasKana;
	private Service hasKatakana;
	private Service hasHiragana;

	private Service findInt;
	private Service toString;
	


	public EntityImpl() throws Exception
	{
		hasDigit = Outside.service(this,"gus06.filter.string.haschar.digit");
		hasLetter = Outside.service(this,"gus06.filter.string.haschar.letter");
		hasWhitespace = Outside.service(this,"gus06.filter.string.haschar.whitespace");
		hasLowercase = Outside.service(this,"gus06.filter.string.haschar.lowercase");
		hasUppercase = Outside.service(this,"gus06.filter.string.haschar.uppercase");
		hasDiacritics = Outside.service(this,"gus06.filter.string.haschar.diacritics");
		hasPunctuation = Outside.service(this,"gus06.filter.string.haschar.punctuation");
		hasIdeograph = Outside.service(this,"gus06.filter.string.haschar.ideograph");
		hasLatin = Outside.service(this,"gus06.filter.string.haschar.latin");
		hasUnicode = Outside.service(this,"gus06.filter.string.haschar.unicode");

		hasJapanese = Outside.service(this,"gus06.filter.string.haschar.japanese");
		hasKanji = Outside.service(this,"gus06.filter.string.haschar.japanese.kanji");
		hasKana = Outside.service(this,"gus06.filter.string.haschar.japanese.kana");
		hasKatakana = Outside.service(this,"gus06.filter.string.haschar.japanese.katakana");
		hasHiragana = Outside.service(this,"gus06.filter.string.haschar.japanese.hiragana");
		
		findInt = Outside.service(this,"gus06.find.integer");
		toString = Outside.service(this,"gus06.tostring.tostring1");
	}



	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;

		if(s.equals("digit")) return hasDigit;
		if(s.equals("letter")) return hasLetter;
		if(s.equals("whitespace")) return hasWhitespace;
		if(s.equals("uppercase")) return hasLowercase;
		if(s.equals("lowercase")) return hasUppercase;
		if(s.equals("diacritic")) return hasDiacritics;
		if(s.equals("punctuation")) return hasPunctuation;
		if(s.equals("ideograph")) return hasIdeograph;
		if(s.equals("unicode")) return hasUnicode;
		if(s.equals("latin")) return hasLatin;

		if(s.equals("japanese")) return hasJapanese;
		if(s.equals("kanji")) return hasKanji;
		if(s.equals("kana")) return hasKana;
		if(s.equals("katakana")) return hasKatakana;
		if(s.equals("hiragana")) return hasHiragana;


		if(s.equals("space"))				return f(' ');//32
		if(s.equals("exclamation_point"))		return f('!');//33
		if(s.equals("double_quotes"))			return f('"');//34
		if(s.equals("sharp"))				return f('#');//35
		if(s.equals("dollar"))				return f('$');//36
		if(s.equals("percent"))				return f('%');//37
		if(s.equals("ampersand"))			return f('&');//38
		if(s.equals("single_quote"))			return f('\'');//39
		if(s.equals("opening_parenthesis"))		return f('(');//40
		if(s.equals("closing_parenthesis"))		return f(')');//41
		if(s.equals("asterisk"))			return f('*');//42
		if(s.equals("plus"))				return f('+');//43
		if(s.equals("comma"))				return f(',');//44
		if(s.equals("minus"))				return f('-');//45
		if(s.equals("period"))				return f('.');//46
		if(s.equals("slash"))				return f('/');//47

		if(s.equals("zero"))				return f('0');//48
		if(s.equals("one"))				return f('1');//49
		if(s.equals("two"))				return f('2');//50
		if(s.equals("three"))				return f('3');//51
		if(s.equals("four"))				return f('4');//52
		if(s.equals("five"))				return f('5');//53
		if(s.equals("six"))				return f('6');//54
		if(s.equals("seven"))				return f('7');//55
		if(s.equals("eight"))				return f('8');//56
		if(s.equals("nine"))				return f('9');//57

		if(s.equals("colon"))				return f(':');//58
		if(s.equals("semicolon"))			return f(';');//59
		if(s.equals("less_than"))			return f('<');//60
		if(s.equals("equal"))				return f('=');//61
		if(s.equals("greater_than"))			return f('>');//62
		if(s.equals("question_mark"))			return f('?');//63
		if(s.equals("at_mark"))				return f('@');//64

		//65-90 : A..Z

		if(s.equals("opening_bracket"))			return f('[');//91
		if(s.equals("backslash"))			return f('\\');//92
		if(s.equals("antislash"))			return f('\\');//92
		if(s.equals("closing_bracket"))			return f(']');//93
		if(s.equals("caret"))				return f('^');//94
		if(s.equals("circumflex"))			return f('^');//94
		if(s.equals("underscore"))			return f('_');//95
		if(s.equals("grave_accent"))			return f('`');//96

		//97-122 : a..z

		if(s.equals("opening_brace"))			return f('{');//123
		if(s.equals("vertical_bar"))			return f('|');//124
		if(s.equals("closing_brace"))			return f('}');//125
		if(s.equals("tilde"))				return f('~');//126

		if(s.equals("pound"))				return f('�');//163
		if(s.equals("currency"))			return f('�');//164
		if(s.equals("yen"))				return f('�');//165
		if(s.equals("broken_vertical_bar"))		return f('�');//166
		if(s.equals("section"))				return f('�');//167
		if(s.equals("umlaut"))				return f('�');//168
		if(s.equals("copyright"))			return f('�');//169

		if(s.equals("left_double_angle_quotes"))	return f('�');//171
		if(s.equals("not"))				return f('�');//172
		if(s.equals("registered_trade_mark"))		return f('�');//174
		if(s.equals("dregree"))				return f('�');//176
		if(s.equals("right_double_angle_quotes"))	return f('�');//187
		if(s.equals("inverted_question_mark"))		return f('�');//191
		if(s.equals("division"))			return f('�');//247
		if(s.equals("euro"))				return f('�');//8364

		
		if(s.toLowerCase().equals("tab"))		return f('\t');
		if(s.toLowerCase().equals("lf"))		return f('\n');
		if(s.toLowerCase().equals("cr"))		return f('\r');

		if(s.equals("\\t"))				return f('\t');
		if(s.equals("\\n"))				return f('\n');
		if(s.equals("\\r"))				return f('\r');

		
		if(s.equals("parenthesis"))			return ff(new char[]{'(',')'});
		if(s.equals("bracket"))				return ff(new char[]{'[',']'});
		if(s.equals("brace"))				return ff(new char[]{'{','}'});

		if(s.startsWith("of:"))				return ff(part1(s).toCharArray());
		
		if(s.startsWith("codepoint:"))			return new FCodePoint(toInt(part1(s)));
		if(s.startsWith("unicodeblock:"))		return new FUnicodeBlock(part1(s));

		if(s.length()==1)				return f(s.charAt(0));

		throw new Exception("Unknown rule: "+s);
	}



	
	
	
	private String part1(String s)
	{return s.split(":",2)[0];}
	
	
	private int toInt(Object obj) throws Exception
	{
		Integer n = (Integer) findInt.t(obj);
		return n.intValue();
	}



	
	private F f(char c)
	{return new FChar(c);}

	private F ff(char[] c)
	{return new FChars(c);}

	
	
	


	private class FChar implements F
	{
		private char c;
		public FChar(char c) {this.c=c;}

		public boolean f(Object obj) throws Exception
		{
			if(obj==null) return false;
			String s = (String) toString.t(obj);
			for(int i=0;i<s.length();i++)
				if(s.charAt(i)==c) return true;
			return false;
		}
	}
	
	
	
	
	private class FCodePoint implements F
	{
		private int code;
		public FCodePoint(int code) {this.code=code;}

		public boolean f(Object obj) throws Exception
		{
			if(obj==null) return false;
			String s = obj.toString();
			for(int i=0;i<s.length();i++)
				if(s.codePointAt(i)==code) return true;
			return false;
		}
	}
	
	
	
	
	
	
	private class FUnicodeBlock implements F
	{
		private String name;
		public FUnicodeBlock(String name) {this.name=name;}

		public boolean f(Object obj) throws Exception
		{
			if(obj==null) return false;
			String s = obj.toString();
			for(int i=0;i<s.length();i++)
				if(Character.UnicodeBlock.of(s.charAt(i)).toString().equals(name)) return true;
			return false;
		}
	}





	private class FChars implements F
	{
		private char[] c;
		public FChars(char[] c) {this.c=c;}

		public boolean f(Object obj) throws Exception
		{
			String s = (String) obj;
			for(int i=0;i<s.length();i++) for(int j=0;j<c.length;j++)
				if(s.charAt(i)==c[j]) return true;
			return false;
		}
	}
}
