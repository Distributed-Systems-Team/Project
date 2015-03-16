/*
 * BoxGame - CSC376 Group Project
 * Team Members: David Kim, Baldomero Samaro,
 *				 Matt Wylder, Stipo Perisa
 * 
 * Source File: ConnectionLoseScreen.java
 * 
 * All Files in Project: Boundary.java, BoxCharacter.java, BoxGame.java, CollisionManager.java, ConnectionDisconnectScreen.java,
 * 						ConnectionJoinScreen.java, ConnectionLoseScreen.java, ConnectionWaitScreen.java, ConnectionWinScreen.java,
 * 						GameEntity.java, GameID.java, GameScreen.java, Goal.java, InputEntity.java, InputManager.java, KeyEvent.java,
 * 						MainMenuScreen.java, Obstacle.java, Physics.java
 * 
 * Main Coder: Baldomero Samaro
 * Contributors: 
 * 
 */

package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ConnectionLoseScreen  implements Screen
{
	boolean showScreen;
	BoxGame game;
	SpriteBatch batch;
	BitmapFont font;
	int timeToEnd;

	public ConnectionLoseScreen( BoxGame game, SpriteBatch inBatch )
	{
		this.game = game;
		this.batch = inBatch;
		
		font = new BitmapFont();
		font.setScale( 4);
		
		showScreen = false;
	}
	
	@Override
	public void show() 
	{
		game.setBufferColor(.3f, .01f, 0f);	
		
		//300 is about 5 seconds
		timeToEnd = 300;
		
		showScreen = true;
	}

	@Override
	public void render(float delta) 
	{
		if( showScreen )
		{
			font.setScale( 4);
			font.draw( batch, "you lose...", 100, 300);
			font.setScale( 5);
			
			if( timeToEnd < 240 )
				font.draw( batch, "*", 200, 200);
			if( timeToEnd < 180 )
				font.draw( batch, "*", 250, 200);
			if( timeToEnd < 120 )
				font.draw( batch, "*", 300, 200);
			if( timeToEnd < 60 )
				font.draw( batch, "*", 350, 200);
			if( timeToEnd < 0 )
				font.draw( batch, "*", 400, 200);
				
			
			if( timeToEnd <  0 )
			{	
				Gdx.app.exit();
			}
			
			
			timeToEnd--;
		}
	}

	@Override
	public void resize(int width, int height) 
	{
		
	}

	@Override
	public void pause() 
	{
		
	}

	@Override
	public void resume() 
	{
		
	}

	@Override
	public void hide() 
	{
		showScreen = false;
	}

	@Override
	public void dispose() 
	{
		font.dispose();
	}

}
