/*
 * BoxGame - CSC376 Group Project
 * Team Members: David Kim, Baldomero Samaro,
 *				 Matt Wylder, Stipo Perisa
 * 
 * Source File: MainMenuScreen.java
 * 
 * All Files in Project: Boundary.java, BoxCharacter.java, BoxGame.java, CollisionManager.java, ConnectionDisconnectScreen.java,
 * 						ConnectionJoinScreen.java, ConnectionLoseScreen.java, ConnectionWaitScreen.java, ConnectionWinScreen.java,
 * 						GameEntity.java, GameID.java, GameScreen.java, Goal.java, InputEntity.java, InputManager.java, KeyEvent.java,
 * 						MainMenuScreen.java, Obstacle.java, Physics.java
 * 
 * Main Coder: Stipo Perisa
 * Contributors: Baldomero Samaro
 * 
 */

package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.Input;

public class MainMenuScreen implements Screen
{
	boolean showScreen;
	
	BoxGame game;
	
	BitmapFont titleFont;
	float titleLocY;
	float titleLocX;
	
	
	BitmapFont directionsFont;
	
	
	SpriteBatch batch;
	
	
	public MainMenuScreen( BoxGame game, SpriteBatch inBatch )
	{
		this.game = game;
		
		showScreen = false;
		
		batch = inBatch;
		
		
		titleFont = new BitmapFont(  );
		titleFont.setScale( 4);
		titleLocY = BoxGame.HEIGHT/3 * 2;
		titleLocX = 20;
		
		directionsFont = new BitmapFont();
		directionsFont.setScale(2);
	}
	
	
	@Override
	public void show() 
	{
		showScreen = true;
		game.setBufferColor(0.0f, 0.0f, .65f);
		
		BoxGame.resetAllConnectionFlags();
	}

	@Override
	public void render(float delta) 
	{
		if( showScreen )
		{
			titleFont.draw( batch, "BOX Game" , titleLocX, titleLocY);
			
			directionsFont.draw( batch, "Press Spacebar to create a game", 50, 250);
			directionsFont.draw( batch, "Press Enter to enter a game", 50, 200);
			
			
			
			if( Gdx.input.isKeyPressed( Input.Keys.SPACE ) )
			{
				game.setScreen( game.waitScreen );
			}
			else if( Gdx.input.isKeyPressed( Input.Keys.ENTER ) )
			{
				game.setScreen( game.joinScreen );
			}
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
		directionsFont.dispose();
		titleFont.dispose();
	}

}
