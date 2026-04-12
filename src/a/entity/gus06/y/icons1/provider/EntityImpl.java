package a.entity.gus06.y.icons1.provider;

import a.framework.*;
import javax.swing.Icon;

public class EntityImpl implements Entity, R, T {

	public String creationDate() {return "20251113";}

	private Service iconLoader;
	private Service iconsToIcon;

	public EntityImpl() throws Exception
	{
		iconLoader = Outside.service(this, "gus06.icon.loader");
		iconsToIcon = Outside.service(this, "gus.y.convert1.iconstoicon");
	}

	public Object t(Object obj) throws Exception
	{return r((String) obj);}

	public Object r(String key) throws Exception
	{
		Icon icon = load(key);
		if (icon != null) return icon;

		icon = composeIcon(key);
		if (icon != null) return icon;

		return null;
	}

	private Icon load(String key) throws Exception
	{return (Icon) iconLoader.t(key);}

	private Icon composeIcon(String key) throws Exception
	{
		if (!key.contains("_")) return null;

		String[] n = key.split("_");
		String id0 = n[n.length - 1];
		String id1 = key.substring(0, key.length() - id0.length() - 1);

		Icon icon0 = load("PART_" + id0);
		if (icon0 == null) return null;

		Icon icon1 = load(id1);
		if (icon1 == null) return null;

		return (Icon) iconsToIcon.t(new Icon[] { icon1, icon0 });
	}
}