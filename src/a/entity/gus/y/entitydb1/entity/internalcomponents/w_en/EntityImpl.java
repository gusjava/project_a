package a.entity.gus.y.entitydb1.entity.internalcomponents.w_en;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260825";}

	private static final int CAP = 10;

	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if (o.length != 2) throw new Exception("Wrong data number: " + o.length);

		Connection cx = (Connection) o[0];
		String suffix = (String) o[1];
		String pattern = "%" + suffix;

		Map parent = new HashMap();

		PreparedStatement stNodes = cx.prepareStatement("SELECT entity_name FROM entity WHERE entity_name LIKE ?");
		stNodes.setString(1, pattern);
		ResultSet rsNodes = stNodes.executeQuery();
		int nodeCount = 0;
		while (rsNodes.next())
		{
			String name = rsNodes.getString("entity_name");
			parent.put(name, name);
			nodeCount++;
		}
		stNodes.close();

		PreparedStatement stEdges = cx.prepareStatement("SELECT entity_name, link FROM entity_link WHERE entity_name LIKE ? AND link LIKE ?");
		stEdges.setString(1, pattern);
		stEdges.setString(2, pattern);
		ResultSet rsEdges = stEdges.executeQuery();
		while (rsEdges.next())
		{
			String a = rsEdges.getString("entity_name");
			String b = rsEdges.getString("link");
			if (parent.containsKey(a) && parent.containsKey(b)) union(parent, a, b);
		}
		stEdges.close();

		Map sizes = new HashMap();
		Iterator it = parent.keySet().iterator();
		while (it.hasNext())
		{
			String name = (String) it.next();
			String root = find(parent, name);
			Integer c = (Integer) sizes.get(root);
			sizes.put(root, c == null ? 1 : c + 1);
		}

		List sizeList = new ArrayList();
		Iterator sit = sizes.values().iterator();
		while (sit.hasNext())
		{
			int v = ((Integer) sit.next()).intValue();
			int idx = 0;
			while (idx < sizeList.size() && ((Integer) sizeList.get(idx)).intValue() >= v) idx++;
			sizeList.add(idx, Integer.valueOf(v));
		}

		List top = new ArrayList();
		for (int i = 0; i < sizeList.size() && i < CAP; i++) top.add(sizeList.get(i));

		Map data = new HashMap();
		data.put("nodeCount", nodeCount);
		data.put("componentCount", sizeList.size());
		data.put("sizes", top);

		return data;
	}

	private String find(Map parent, String name)
	{
		String root = name;
		while (!root.equals(parent.get(root))) root = (String) parent.get(root);
		String cur = name;
		while (!cur.equals(root))
		{
			String next = (String) parent.get(cur);
			parent.put(cur, root);
			cur = next;
		}
		return root;
	}

	private void union(Map parent, String a, String b)
	{
		String ra = find(parent, a);
		String rb = find(parent, b);
		if (!ra.equals(rb)) parent.put(ra, rb);
	}
}
