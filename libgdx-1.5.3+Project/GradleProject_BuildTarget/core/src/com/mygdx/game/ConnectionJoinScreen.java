package com.mygdx.game;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.network.NetworkManager;

public class ConnectionJoinScreen  implements Screen
{
	public static boolean gameConnected;
	public static boolean gameConnectFailed;
	
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
		gameConnected = false;
		gameConnectFailed = false;
	}
	
	@Override
	public void show() 
	{
		game.setBufferColor(0.0f, .25f, 0.5f);	
		
		timeToDisconnect = 400;
		
		showScreen = true;
		gameConnected = false;
		gameConnectFailed = false;
		
		//Try making room
		NetworkManager.joinRoom();
	}

	@Override
	public void render(float delta) 
	{
		if( showScreen )
		{
			font.draw( batch, "Trying to join a game...", 30, 300);
			font.draw( batch, "Disconnect Timer: " + timeToDisconnect, 30, 250);
			
			
			if( gameConnected )
			{
				game.gameScreen.setGameStatus( NetworkProtocols.joinID);
				game.setScreen(game.gameScreen);
			
			}
			else if( gameConnectFailed )
			{
				try
				{
				NetworkManager.cleanup();
				System.out.println("threadCleanedup");
				}catch( Exception e )
				{
					
				}
				game.setScreen(game.mainMenu);
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
	}

}
