package a.entity.gus06.sys.expression1.apply.op._tray_add;

import a.framework.*;
import java.awt.TrayIcon;
import java.awt.SystemTray;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161003";}

	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof TrayIcon) return new E1((TrayIcon) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private class E1 implements E
	{
		private TrayIcon o;
		public E1(TrayIcon o){this.o = o;}
		
		public void e() throws Exception
		{addTrayIcon(o);}
	}
	
	private void addTrayIcon(TrayIcon trayIcon) throws Exception
	{
		if(!SystemTray.isSupported())
			throw new Exception("SystemTray is not available");
		SystemTray.getSystemTray().add(trayIcon);
	}
}
