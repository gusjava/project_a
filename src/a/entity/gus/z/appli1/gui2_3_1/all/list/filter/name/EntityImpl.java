package a.entity.gus.z.appli1.gui2_3_1.all.list.filter.name;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20240112";}

	private Service getDownMap;
	private Service getUpMap;

	public EntityImpl() throws Exception
	{
		getDownMap = Outside.service(this, "gus.y.entitydb1.entity_link.count1byname");
		getUpMap = Outside.service(this, "gus.y.entitydb1.entity_link.count2byname");
	}

	public Object t(Object obj) throws Exception {
		Object[] o = (Object[]) obj;
		if (o.length != 3) throw new Exception("Wrong data number: " + o.length);

		R engine = (R) o[0];
		List list = (List) o[1];
		String search = (String) o[2];

		if (list == null) return null;

		String devId = devId(engine);
		Set lockSet = lockSet(engine);
		Set errSet = errSet(engine);
		Map downMap = needsDownMap(search) ? (Map) getDownMap.t(cx(engine)) : null;
		Map upMap = needsUpMap(search) ? (Map) getUpMap.t(cx(engine)) : null;

		Filter filter = new Filter(search, devId, downMap, upMap);

		List list1 = new ArrayList();
		List list2 = new ArrayList();
		List list3 = new ArrayList();

		for (int i = 0; i < list.size(); i++)
		{
			String[] data = (String[]) list.get(i);
			String name = data[0];

			if (errSet != null && errSet.contains(name))
				list1.add(data);
			else if (lockSet != null && lockSet.contains(name))
				list2.add(data);
			else if (filter.f(data))
				list3.add(data);
		}

		List results = new ArrayList();
		results.addAll(list1);
		results.addAll(list2);
		results.addAll(list3);
		return results;
	}

	private boolean needsDownMap(String search)
	{return search != null && search.contains("#");}

	private boolean needsUpMap(String search)
	{return search != null && search.contains("$");}

	private Connection cx(R engine) throws Exception
	{return (Connection) engine.r("cx");}

	private Map compileErrMap(R engine) throws Exception
	{return (Map) engine.r("compileErrMap");}

	private Set errSet(R engine) throws Exception
	{
		Map m = compileErrMap(engine);
		return m != null ? m.keySet() : null;
	}

	private String devId(R engine) throws Exception
	{return (String) engine.r("devId");}

	private Set lockSet(R engine) throws Exception
	{return (Set) engine.r("lockSet");}

	private class Filter implements F
	{
		private String devId;
		private Map downMap;
		private Map upMap;

		private boolean all = false;
		private boolean onlyMine = false;
		private boolean onlyOther = false;
		private boolean ffStrict = false;

		private List ff = new ArrayList();
		private List st = new ArrayList();
		private List en = new ArrayList();
		private List co = new ArrayList();
		private List in = new ArrayList();
		private List downCriteria = new ArrayList();
		private List upCriteria = new ArrayList();

		public Filter(String search, String devId, Map downMap, Map upMap)
		{
			this.devId = devId;
			this.downMap = downMap;
			this.upMap = upMap;

			if (search == null) {all = true; return;}
			search = search.trim();
			if (search.equals("") || search.equals("*")) {all = true; return;}

			if (search.contains("&&")) {onlyOther = true; search = search.replace("&&", " ");}
			if (search.contains("&")) {onlyMine = true; search = search.replace("&", " ");}
			if (search.contains("!")) {ffStrict = true; search = search.replace("!", " ");}

			String[] nn = search.split(" +");
			for (String n : nn) {
				String n0 = n.toLowerCase();

				if (n.startsWith("#")) {
					downCriteria.add(parseCriterion(n.substring(1)));
				} else if (n.startsWith("$")) {
					upCriteria.add(parseCriterion(n.substring(1)));
				} else if (n.matches("[BEFGHIPRSTV]+")) {
					for (int i = 0; i < n0.length(); i++)
						ff.add("" + n0.charAt(i));
				} else {
					if (n0.startsWith("*") && n0.endsWith("*"))
						in.add(n0.substring(1, n0.length() - 1));
					else if (n0.startsWith("*"))
						en.add(n0.substring(1));
					else if (n0.endsWith("*"))
						st.add(n0.substring(0, n0.length() - 1));
					else
						co.add(n0);
				}
			}
		}

		// returns {op, value} — op: 0='=', 1='>', 2='<'
		private int[] parseCriterion(String s) {
			if (s.startsWith(">")) return new int[]{1, Integer.parseInt(s.substring(1))};
			if (s.startsWith("<")) return new int[]{2, Integer.parseInt(s.substring(1))};
			return new int[]{0, Integer.parseInt(s)};
		}

		private boolean matchesCriterion(int[] c, int nb) {
			if (c[0] == 1) return nb > c[1];
			if (c[0] == 2) return nb < c[1];
			return nb == c[1];
		}

		private int getCount(Map map, String name) {
			if (map == null) return 0;
			Object v = map.get(name);
			return v != null ? (int) v : 0;
		}

		public boolean f(Object obj) throws Exception {
			if (all) return true;

			String[] data = (String[]) obj;
			String name = data[0];
			String features = data[1];

			if (onlyMine && !name.startsWith(devId + ".")) return false;
			if (onlyOther && name.startsWith(devId + ".")) return false;

			for (int i = 0; i < ff.size(); i++) {
				String f = (String) ff.get(i);
				if (!features.contains(f)) return false;
			}

			if (ffStrict) {
				for (int i = 0; i < features.length(); i++) {
					String feature = "" + features.charAt(i);
					if (!ff.contains(feature)) return false;
				}
			}

			for (int i = 0; i < downCriteria.size(); i++) {
				int[] c = (int[]) downCriteria.get(i);
				if (!matchesCriterion(c, getCount(downMap, name))) return false;
			}

			for (int i = 0; i < upCriteria.size(); i++) {
				int[] c = (int[]) upCriteria.get(i);
				if (!matchesCriterion(c, getCount(upMap, name))) return false;
			}

			int nbIn = in.size();
			int nbCo = co.size();
			int nbSt = st.size();
			int nbEn = en.size();

			if (nbIn + nbCo + nbSt + nbEn == 0) return true;

			for (int i = 0; i < nbIn; i++) {
				String s = (String) in.get(i);
				if (name.matches(".+" + Pattern.quote(s) + ".+")) return true;
			}
			for (int i = 0; i < nbCo; i++) {
				String s = (String) co.get(i);
				if (name.contains(s)) return true;
			}
			for (int i = 0; i < nbSt; i++) {
				String s = (String) st.get(i);
				if (name.startsWith(s)) return true;
			}
			for (int i = 0; i < nbEn; i++) {
				String s = (String) en.get(i);
				if (name.endsWith(s)) return true;
			}
			return false;
		}
	}
}
