package a.entity.gus06.sys.langdetect1.t1.prepare.text;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NORMALIZE_VI {
	
	public static final String DMARK_CLASS = "\u0300\u0301\u0303\u0309\u0323";
	public static final String TO_NORMALIZE_VI_CHARS = "AEIOUYaeiouy\u00c2\u00ca\u00d4\u00e2\u00ea\u00f4\u0102\u0103\u01a0\u01a1\u01af\u01b0";
    public static final Pattern ALPHABET_WITH_DMARK = Pattern.compile("([" + TO_NORMALIZE_VI_CHARS + "])([" + DMARK_CLASS + "])");
	
    public static final String[] NORMALIZED_VI_CHARS = {
            "\u00C0\u00C8\u00CC\u00D2\u00D9\u1EF2\u00E0\u00E8\u00EC\u00F2\u00F9\u1EF3\u1EA6\u1EC0\u1ED2\u1EA7\u1EC1\u1ED3\u1EB0\u1EB1\u1EDC\u1EDD\u1EEA\u1EEB",
            "\u00C1\u00C9\u00CD\u00D3\u00DA\u00DD\u00E1\u00E9\u00ED\u00F3\u00FA\u00FD\u1EA4\u1EBE\u1ED0\u1EA5\u1EBF\u1ED1\u1EAE\u1EAF\u1EDA\u1EDB\u1EE8\u1EE9",
            "\u00C3\u1EBC\u0128\u00D5\u0168\u1EF8\u00E3\u1EBD\u0129\u00F5\u0169\u1EF9\u1EAA\u1EC4\u1ED6\u1EAB\u1EC5\u1ED7\u1EB4\u1EB5\u1EE0\u1EE1\u1EEE\u1EEF",
            "\u1EA2\u1EBA\u1EC8\u1ECE\u1EE6\u1EF6\u1EA3\u1EBB\u1EC9\u1ECF\u1EE7\u1EF7\u1EA8\u1EC2\u1ED4\u1EA9\u1EC3\u1ED5\u1EB2\u1EB3\u1EDE\u1EDF\u1EEC\u1EED",
            "\u1EA0\u1EB8\u1ECA\u1ECC\u1EE4\u1EF4\u1EA1\u1EB9\u1ECB\u1ECD\u1EE5\u1EF5\u1EAC\u1EC6\u1ED8\u1EAD\u1EC7\u1ED9\u1EB6\u1EB7\u1EE2\u1EE3\u1EF0\u1EF1"
    };

	public static String normalize_vi(String text) {
        Matcher m = ALPHABET_WITH_DMARK.matcher(text);
        StringBuffer buf = new StringBuffer();
        
        while(m.find())
        {
            int alphabet = TO_NORMALIZE_VI_CHARS.indexOf(m.group(1));
            int dmark = DMARK_CLASS.indexOf(m.group(2)); // Diacritical Mark
            m.appendReplacement(buf, NORMALIZED_VI_CHARS[dmark].substring(alphabet, alphabet + 1));
        }
        
        if(buf.length() == 0) return text;
        m.appendTail(buf);
        return buf.toString();
    }
}
