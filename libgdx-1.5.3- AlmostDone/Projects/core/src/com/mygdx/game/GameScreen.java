package com.mygdx.game;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

public class GameScreen implements Screen
{
	boolean showScreen;
	
	BoxGame game;
	SpriteBatch batch;
	
	BitmapFont font;
	
	
	//Game Elements
	BoxCharacter myBox;
	BoxCharacter otherBox;
	
	Obstacle anObstacle;
	Obstacle floatingObstacle;
	
	Boundary ground;
	Boundary leftWall;
	Boundary rightWall;
	
	int gameID;
	
	final Vector2 PLAYERONE_spawnLoc = new Vector2( 80, 150 );
	final Vector2 PLAYERTWO_spawnLoc = new Vector2( BoxGame.WIDTH - 100, 150 );
	
	static final float groundPos = 100;
	
	
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
		
		
		anObstacle = new Obstacle(true, Color.CYAN, new Vector2(40,100), new Vector2(BoxGame.WIDTH/2 - 50, 100) );
		floatingObstacle = new Obstacle( true, Color.CYAN, new Vector2(30, 100), new Vector2(300, 200));
		
		ground = new Boundary(true, Color.TEAL, groundPos, false, false);
		leftWall = new Boundary( true, Color.TEAL, BoxGame.HEIGHT, true, true );
		rightWall = new Boundary( true, Color.TEAL, BoxGame.HEIGHT, true, false );
		
		//NETWORK
		TESTNetworkMan.register(otherBox);
		renderCount = 0;
		
	}
	
	@Override
	public void show() 
	{
		game.setBufferColor(0.5f, 0.25f, 0.8f);	
		showScreen = true;
	}

	@Override
	public void render(float delta) 
	{
		if( showScreen )
		{
			//NETWORK
		//	TESTNetworkMan.send();
		//  TESTNetworkMan.receive();
			
			TESTNetworkMan.pushToNPC();
			
			otherBox.render();
			
			if( renderCount >= renderTime )
			{
				InputManager.updateInputs();
				myBox.render();
				
				anObstacle.render();
				floatingObstacle.render();
				
				ground.render();
				leftWall.render();
				rightWall.render();
				
				renderCount = 0;
			}
			
			renderCount ++;
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
		
		anObstacle.destroy();
		floatingObstacle.destroy();
	}

}
