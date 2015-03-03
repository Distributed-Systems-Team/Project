package com.mygdx.game;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ConnectionJoinScreen  implements Screen
{
	boolean showScreen;
	
	BoxGame game;
	SpriteBatch batch;
	
	
	BitmapFont font;
	
	
	int timeToDisconnect;

	public ConnectionJoinScreen( BoxGame game, SpriteBatch inBatch )
	{
		this.game = game;
		this.batch = inBatch;
		
		font = new BitmapFont();
		
		showScreen = false;
	}
	
	@Override
	public void show() 
	{
		game.setBufferColor(0.0f, .25f, 0.5f);	
		
		timeToDisconnect = 50;
		
		showScreen = true;
	}

	@Override
	public void render(float delta) 
	{
		if( showScreen )
		{
			font.draw( batch, "Trying to join a game...", 30, 300);
			font.draw( batch, "Disconnect Timer: " + timeToDisconnect, 30, 250);
			
			
			if( timeToDisconnect <= 0 )
			{
				game.gameScreen.setGameStatus( NetworkProtocols.joinID);
				game.setScreen(game.gameScreen);
			}
			
			timeToDisconnect--;
			System.out.println(timeToDisconnect);
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
