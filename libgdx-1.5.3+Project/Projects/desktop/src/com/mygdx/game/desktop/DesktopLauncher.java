package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.BoxGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		
		//Setting Window Size based on BoxGame Constants
		config.width = BoxGame.WIDTH;
		config.height = BoxGame.HEIGHT;
		
		new LwjglApplication(new BoxGame(), config);
	}
}
