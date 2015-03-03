package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BoxGame extends Game
{
	public static int WIDTH = 800, HEIGHT = 600;

	float bufferColorR, bufferColorG, bufferColorB;
	MainMenuScreen mainMenu;
	ConnectionWaitScreen waitScreen;
	ConnectionJoinScreen joinScreen;
	GameScreen	gameScreen;

	
	
	SpriteBatch batch;
	
	@Override
	public void create () 
	{
		batch = new SpriteBatch();
		
		
		//Create all necessary Scenes/Screens
		mainMenu = new MainMenuScreen( this, batch );
		waitScreen = new ConnectionWaitScreen( this, batch );
		joinScreen = new ConnectionJoinScreen( this, batch);
		gameScreen = new GameScreen( this, batch );
		
		//Set starting screen
		setScreen( mainMenu );
	}
	
	
	@Override
	public void render () 
	{
		CollisionManager.checkCollisions();

		//NETWORK
		TESTNetworkMan.send();
		TESTNetworkMan.receive();
		
		//Buffer flipping
		Gdx.gl.glClearColor( bufferColorR, bufferColorG, bufferColorB, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		
		batch.begin();
		
		mainMenu.render(0);
		waitScreen.render(0);
		joinScreen.render(0);
		
		
		gameScreen.render(0);
		
		batch.end();
	}
	
	
	

	
	@Override
	public void resize( int width, int height )
	{
	}
	
	@Override
	public void dispose()
	{
		batch.dispose();
		
		mainMenu.dispose();
		waitScreen.dispose();
		joinScreen.dispose();
		
	}
	
	public void setBufferColor( float r, float g, float b )
	{	 
		bufferColorR = r;
		bufferColorG = g; 
		bufferColorB = b;
		
	}
	
}
