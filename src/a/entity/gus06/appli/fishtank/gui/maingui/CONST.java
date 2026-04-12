package a.entity.gus06.appli.fishtank.gui.maingui;

import java.awt.Color;

public interface CONST {

	long RATE = 15;
	int NB_FISH = 1400;
	int RADIUS = 40;
	int LIFETIME = 1500;
	double INC = 0.5;
	
	double PAS = 3;
	double D_MIN = 5;
	double D_MAX = 40;
	double ACC_MUR = 0.3;
	
	Color COLOR_FISH = Color.WHITE;
	Color COLOR_ROCK = Color.DARK_GRAY;
	Color COLOR_OCEAN = Color.BLUE.darker();
	
	double ZONE_RATE = 0.01;
	
	
	double D_MIN2 = D_MIN*D_MIN;
	double D_MAX2 = D_MAX*D_MAX;
}
