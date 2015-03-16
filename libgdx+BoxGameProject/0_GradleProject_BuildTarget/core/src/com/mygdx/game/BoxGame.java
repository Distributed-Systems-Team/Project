/*
 * BoxGame - CSC376 Group Project
 * Team Members: David Kim, Baldomero Samaro,
 *				 Matt Wylder, Stipo Perisa
 * 
 * Source File: BoxGame.java
 * 
 * All Files in Project: Boundary.java, BoxCharacter.java, BoxGame.java, CollisionManager.java, ConnectionDisconnectScreen.java,
 * 						ConnectionJoinScreen.java, ConnectionLoseScreen.java, ConnectionWaitScreen.java, ConnectionWinScreen.java,
 * 						GameEntity.java, GameID.java, GameScreen.java, Goal.java, InputEntity.java, InputManager.java, KeyEvent.java,
 * 						MainMenuScreen.java, Obstacle.java, Physics.java
 * 
 * Main Coder: Matt Wylder
 * Contributors: Baldomero Samaro, David Kim
 * 
 */

package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.network.NetworkManager;

public class BoxGame extends Game
{
	public static int WIDTH = 800;
	public static int HEIGHT = 600;
	public static boolean gameON;
	
	float bufferColorR;
	float bufferColorG; 
	float bufferColorB;
	
	//Screens
	MainMenuScreen mainMenu;
	ConnectionWaitScreen 		waitScreen;
	ConnectionJoinScreen 		joinScreen;
	GameScreen 			gameScreen;
	ConnectionWinScreen 		winScreen;
	ConnectionLoseScreen 		loseScreen;
	ConnectionDisconnectScreen 	disconnectedScreen;

	SpriteBatch batch;
	
	@Override
	public void create (){
		batch = new SpriteBatch();
		
		//Create all necessary Scenes/Screens
		mainMenu = new MainMenuScreen( this, batch );
		waitScreen = new ConnectionWaitScreen( this, batch );
		joinScreen = new ConnectionJoinScreen( this, batch);
		gameScreen = new GameScreen( this, batch );
		winScreen = new ConnectionWinScreen( this, batch );
		loseScreen = new ConnectionLoseScreen( this, batch );
		disconnectedScreen = new ConnectionDisconnectScreen( this, batch );
		
		//Set starting screen
		setScreen( mainMenu );
		
		//Setting game to be in ON state
		gameON = false;
	}
	
	@Override
	public void render (){
		CollisionManager.checkCollisions();
		InputManager.updateInputs();
		
		//Buffer flipping
		Gdx.gl.glClearColor( bufferColorR, bufferColorG, bufferColorB, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		
		mainMenu.render(0);
		waitScreen.render(0);
		joinScreen.render(0);
		
		gameScreen.render(0);
		
		winScreen.render(0);
		loseScreen.render(0);
		disconnectedScreen.render(0);
		
		batch.end();
	}
	
	public static void resetAllConnectionFlags(){
		GameScreen.gameLost = false;
		GameScreen.gameWon = false;
		BoxGame.gameON = false;
	}
	
	@Override
	public void resize( int width, int height ){
	}
	
	@Override
	public void dispose(){
		gameON = false;
		
		try{
			NetworkManager.cleanup();
		}catch ( Exception e){System.out.println(e);}
		
		batch.dispose();
		
		mainMenu.dispose();
		waitScreen.dispose();
		joinScreen.dispose();
		
		loseScreen.dispose();
		winScreen.dispose();
		disconnectedScreen.dispose();
	}
	
	public void setBufferColor( float r, float g, float b )	{	 
		bufferColorR = r;
		bufferColorG = g; 
		bufferColorB = b;
		
	}
	
}
