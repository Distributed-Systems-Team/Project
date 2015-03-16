/*
 * BoxGame - CSC376 Group Project
 * Team Members: David Kim, Baldomero Samaro,
 *				 Matt Wylder, Stipo Perisa
 * 
 * Source File: GameScreen.java
 * 
 * All Files in Project: Boundary.java, BoxCharacter.java, BoxGame.java, CollisionManager.java, ConnectionDisconnectScreen.java,
 * 						ConnectionJoinScreen.java, ConnectionLoseScreen.java, ConnectionWaitScreen.java, ConnectionWinScreen.java,
 * 						GameEntity.java, GameID.java, GameScreen.java, Goal.java, InputEntity.java, InputManager.java, KeyEvent.java,
 * 						MainMenuScreen.java, Obstacle.java, Physics.java
 * 
 * Main Coder: David Kim
 * Contributors: Matt Wylder, Stipo Perisa
 * 
 */

package com.mygdx.game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.network.NetworkManager;
import com.mygdx.network.NetworkProtocols;

public class GameScreen implements Screen
{
	boolean showScreen;
	
	BoxGame game;
	SpriteBatch batch;
	
	BitmapFont font;
	
	
	//Game Elements
	BoxCharacter myBox;
	BoxCharacter otherBox;
	
	List< Obstacle > obstacleList;
	int numObstacles;
	
	Boundary ground;
	Boundary leftWall;
	Boundary rightWall;
	
	int gameID;
	
	final Vector2 PLAYERONE_spawnLoc = new Vector2( 80, 150 );
	final Vector2 PLAYERTWO_spawnLoc = new Vector2( BoxGame.WIDTH - 100, 150 );
	
	static final float groundPos = 100;
	
	public static boolean gameWon;
	public static boolean gameLost;
	
	//Render
	final int renderTime = 1;
	int renderCount;
	
	public GameScreen( BoxGame game, SpriteBatch inBatch )
	{
		this.game = game;
		this.batch = inBatch;
		
		font = new BitmapFont();
		
		showScreen = false;
	}
	
	public void setGameStatus( int joinID )
	{
		gameID = joinID;
		
		if( gameID == NetworkProtocols.waitID )
		{
			myBox = new BoxCharacter( PLAYERONE_spawnLoc, true );
			otherBox = new BoxCharacter( PLAYERTWO_spawnLoc, false );
		}
		else if( gameID == NetworkProtocols.joinID )
		{
			myBox = new BoxCharacter( PLAYERTWO_spawnLoc, true );
			otherBox = new BoxCharacter( PLAYERONE_spawnLoc, false );
		}
		
		obstacleList = new ArrayList<Obstacle>();
		obstacleList.add( new Obstacle(true, Color.CYAN, new Vector2(60, 40), new Vector2(BoxGame.WIDTH/2 - 20, 100) ) );
		obstacleList.add( new Goal(true, Color.RED, new Vector2(40, 40), new Vector2(BoxGame.WIDTH/2 - 20, BoxGame.HEIGHT - 80) ) );
		obstacleList.add( new Obstacle(true, Color.CYAN, new Vector2(40, 40), new Vector2(260, 180) ) );
		obstacleList.add( new Obstacle(true, Color.CYAN, new Vector2(40, 40), new Vector2(500, 180) ) );
		obstacleList.add( new Obstacle(true, Color.CYAN, new Vector2(35, 35), new Vector2(125, 200) ) );
		obstacleList.add( new Obstacle(true, Color.CYAN, new Vector2(35, 35), new Vector2(640, 200) ) );
		obstacleList.add( new Obstacle(true, Color.CYAN, new Vector2(35, 35), new Vector2(70, 290) ) );
		obstacleList.add( new Obstacle(true, Color.CYAN, new Vector2(35, 35), new Vector2(695, 290) ) );
		obstacleList.add( new Obstacle(true, Color.CYAN, new Vector2(35, 120), new Vector2(BoxGame.WIDTH/2 - 200, 340) ) );
		obstacleList.add( new Obstacle(true, Color.CYAN, new Vector2(35, 120), new Vector2(BoxGame.WIDTH/2 + 80, 340) ) );
		obstacleList.add( new Obstacle(true, Color.CYAN, new Vector2(60, 20), new Vector2(BoxGame.WIDTH/2 - 80 - 20, 395) ) );
		obstacleList.add( new Obstacle(true, Color.CYAN, new Vector2(60, 20), new Vector2(BoxGame.WIDTH/2 + 80, 395) ) );
		numObstacles = obstacleList.size();
		
		ground = new Boundary(true, Color.TEAL, groundPos, false, false);
		leftWall = new Boundary( true, Color.TEAL, BoxGame.HEIGHT, true, true );
		rightWall = new Boundary( true, Color.TEAL, BoxGame.HEIGHT, true, false );
		
		//NETWORK
		renderCount = 0;
		
	}
	
	@Override
	public void show() 
	{
		game.setBufferColor(0.5f, 0.25f, 0.8f);	
		showScreen = true;
		
		gameWon = false;
		gameLost = false;
	}

	@Override
	public void render(float delta) 
	{
		if( showScreen )
		{
			otherBox.render();
			myBox.render();
				

			for( int i = 0; i < numObstacles; i++ )
			{
				obstacleList.get( i ).render();
			}
			
			ground.render();
			leftWall.render();
			rightWall.render();
			
			
			if( gameWon )
			{
				BoxGame.gameON = false;
				try {
					NetworkManager.cleanup();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				

				game.setScreen(game.winScreen);
			}
			else if( gameLost )
			{
				BoxGame.gameON = false;
				
				try {
					NetworkManager.cleanup();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				game.setScreen(game.loseScreen);
			}
			else if( !BoxGame.gameON )
			{
				try {
					NetworkManager.cleanup();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				game.setScreen(game.disconnectedScreen);
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
		font.dispose();
		
		ground.destroy();
		leftWall.destroy();
		rightWall.destroy();
		
		myBox.destroy();
		otherBox.destroy();
		

		for( int i = 0; i < numObstacles; i++ )
		{
			obstacleList.get( i ).destroy();
		}
	}

}
