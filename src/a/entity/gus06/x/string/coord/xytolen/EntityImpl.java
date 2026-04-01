package a.entity.gus06.x.string.coord.xytolen;

import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20251121";}

	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if (o.length != 2) throw new Exception("Wrong data number: " + o.length);

		String data = (String) o[0];
		int[] xy = (int[]) o[1];

		if (xy.length != 2) throw new Exception("Wrong xy number: " + xy.length);
		int x = xy[0];
		int y = xy[1];

		String[] lines = data.split("\n");
		if (x >= lines.length) return null;
		if (y > lines[x].length()) return null;

		int len = 0;
		for (int i = 0; i < x; i++) len += lines[i].length() + 1;
		return Integer.valueOf(len + y);
	}
}