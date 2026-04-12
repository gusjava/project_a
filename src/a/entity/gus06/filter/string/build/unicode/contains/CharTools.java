package a.entity.gus06.filter.string.build.unicode.contains;

public class CharTools {

	
	public static final String TYPE_UPPERCASE = "UPPERCASE";
	public static final String TYPE_LOWERCASE = "LOWERCASE";
	public static final String TYPE_NUMBER = "NUMBER";
	public static final String TYPE_KANA = "KANA";
	public static final String TYPE_KATAKANA = "KATAKANA";
	public static final String TYPE_HIRAGANA = "HIRAGANA";
	public static final String TYPE_JAPANESE = "JAPANESE";
	public static final String TYPE_KANJI = "KANJI";
	public static final String TYPE_ROMAJI = "ROMAJI";
	
	
	
	public static CharType buildCharType(String value)
	{
		value = value.toUpperCase();
		if(value.equals(TYPE_UPPERCASE)) return new CharTools.CharType_uppercase();
		if(value.equals(TYPE_LOWERCASE)) return new CharTools.CharType_lowercase();
		if(value.equals(TYPE_NUMBER)) return new CharTools.CharType_digit();
		
		String[] n = formatValue(value).split(";");
		Character.UnicodeBlock[] b = new Character.UnicodeBlock[n.length];
		for(int i=0;i<n.length;i++) b[i] = Character.UnicodeBlock.forName(n[i]);
		return new CharTools.CharType_blocks(b);
	}
	
	
	private static String formatValue(String value)
	{
		if(value.equals(TYPE_KANA)) return "HIRAGANA;KATAKANA";
		if(value.equals(TYPE_JAPANESE)) return "HIRAGANA;KATAKANA;CJK_UNIFIED_IDEOGRAPHS";
		if(value.equals(TYPE_KANJI)) return "CJK_UNIFIED_IDEOGRAPHS";
		if(value.equals(TYPE_ROMAJI)) return "BASIC_LATIN";
		return value;
	}
	
	
	
	
	public interface CharType
	{public boolean isTypeOf(char c);}
	
	
	
	
	
	public static class CharType_blocks implements CharType
	{
		private Character.UnicodeBlock[] blocks;
		public CharType_blocks(Character.UnicodeBlock[] blocks) {this.blocks = blocks;}
		
		public boolean isTypeOf(char c) {
			Character.UnicodeBlock b = Character.UnicodeBlock.of(c);
			for(int i=0;i<blocks.length;i++) if(blocks[i]==b) return true;
			return false;
		}
	}
	
	public static class CharType_uppercase implements CharType
	{
		public boolean isTypeOf(char c) {
			return Character.isUpperCase(c);
		}
	}
	
	public static class CharType_lowercase implements CharType
	{
		public boolean isTypeOf(char c) {
			return Character.isLowerCase(c);
		}
	}
	
	public static class CharType_digit implements CharType
	{
		public boolean isTypeOf(char c) {
			return Character.isDigit(c);
		}
	}
}
