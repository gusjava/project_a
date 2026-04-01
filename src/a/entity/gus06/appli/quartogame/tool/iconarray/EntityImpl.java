package a.entity.gus06.appli.quartogame.tool.iconarray;

import a.framework.*;
import javax.swing.Icon;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20191118";}
	
	
	private Service iconProvider;
	
	private Icon[] icons;

	public EntityImpl() throws Exception
	{
		iconProvider = Outside.service(this,"gus06.icon.provider");
		
		icons = new Icon[16];
		icons[0] = icon("QUARTO_black_small_rect_empty");
		icons[1] = icon("QUARTO_black_small_rect_filled");
		icons[2] = icon("QUARTO_black_small_round_empty");
		icons[3] = icon("QUARTO_black_small_round_filled");
		icons[4] = icon("QUARTO_black_tall_rect_empty");
		icons[5] = icon("QUARTO_black_tall_rect_filled");
		icons[6] = icon("QUARTO_black_tall_round_empty");
		icons[7] = icon("QUARTO_black_tall_round_filled");
		icons[8] = icon("QUARTO_white_small_rect_empty");
		icons[9] = icon("QUARTO_white_small_rect_filled");
		icons[10] = icon("QUARTO_white_small_round_empty");
		icons[11] = icon("QUARTO_white_small_round_filled");
		icons[12] = icon("QUARTO_white_tall_rect_empty");
		icons[13] = icon("QUARTO_white_tall_rect_filled");
		icons[14] = icon("QUARTO_white_tall_round_empty");
		icons[15] = icon("QUARTO_white_tall_round_filled");
	}
	
	
	public Object g() throws Exception
	{return icons;}
	
	
	private Icon icon(String key) throws Exception
	{return (Icon) iconProvider.t(key);}
}
