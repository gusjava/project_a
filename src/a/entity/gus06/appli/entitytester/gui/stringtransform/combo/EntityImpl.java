package a.entity.gus06.appli.entitytester.gui.stringtransform.combo;

import a.framework.*;
import java.util.List;
import javax.swing.JComboBox;

public class EntityImpl implements Entity, I, G {

	public String creationDate() {return "20150920";}

	public static final String ENTITYSTART = "gus.string.transform.";
	public static final String ICONID = "entity";
	
	
	private Service buildCombo;
	private Service entityListing;
	private Service entityunique;
	private Service persister;
	
	private List list;
	private JComboBox combo;



	public EntityImpl() throws Exception
	{
		buildCombo = Outside.service(this,"gus06.swing.combobox.build.fromicon");
		entityListing = Outside.service(this,"gus06.app.jarfile.listing.entities.filter.st");
		entityunique = Outside.service(this,"entityunique");
		persister = Outside.service(this,"gus06.app.persister1");
		
		list = (List) entityListing.t(ENTITYSTART);
		
		combo = (JComboBox) buildCombo.t(ICONID);
		
		for(int i=0;i<list.size();i++)
		combo.addItem(list.get(i));
	}
	
	
	public Object i() throws Exception
	{return combo;}
	
	
	
	public Object g() throws Exception
	{
		String name = (String) combo.getSelectedItem();
		if(name==null) return null;
		return entityunique.t(name);
	}
}
