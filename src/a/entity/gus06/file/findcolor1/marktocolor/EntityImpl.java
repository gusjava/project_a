package a.entity.gus06.file.findcolor1.marktocolor;

import a.framework.*;
import java.awt.Color;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20220517";}

	public static final String MARK_NORMAL = ">";
	public static final String MARK_TOO_BIG = "!";
	public static final String MARK_EMPTY = "&";
	public static final String MARK_NOTFOUND = "*";

	public static final Color COLOR_NORMAL = Color.BLACK;
	public static final Color COLOR_TOO_BIG = Color.GREEN.darker();
	public static final Color COLOR_EMPTY = Color.RED;
	public static final Color COLOR_NOTFOUND = Color.LIGHT_GRAY;



	public Object t(Object obj) throws Exception
	{
		String mark = (String) obj;
		if(mark.equals(MARK_NORMAL)) return COLOR_NORMAL;
		if(mark.equals(MARK_TOO_BIG)) return COLOR_TOO_BIG;
		if(mark.equals(MARK_EMPTY)) return COLOR_EMPTY;
		if(mark.equals(MARK_NOTFOUND)) return COLOR_NOTFOUND;
		
		throw new Exception("Unknown mark: ["+mark+"]");
	}
}