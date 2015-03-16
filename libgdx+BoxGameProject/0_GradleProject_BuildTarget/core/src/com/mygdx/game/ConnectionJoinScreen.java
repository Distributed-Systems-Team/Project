package com.mygdx.game;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.network.NetworkManager;
import com.mygdx.network.NetworkProtocols;

public class ConnectionJoinScreen  implements Screen
{
	public static boolean gameConnected;
	public static boolean gameConnectFailed;
	
	boolean showScreen;
	int timeUntilPrompt;
	
	BoxGame game;
	SpriteBatch batch;
	
	BitmapFont font;

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
		
		showScreen = true;
		gameConnected = false;
		gameConnectFailed = false;
		
		timeUntilPrompt = 10;
		
		//Try making room
		NetworkManager.joinRoom();
	}

	@Override
	public void render(float delta) 
	{
		if( showScreen )
		{
			font.draw( batch, "Trying to join a game...", 30, 300);
			
			if( timeUntilPrompt<= 0 && !NetworkProtocols.joinIPSet )
			{
				// Makes prompt for host ip address
				 JFrame frame = new JFrame();
				 NetworkProtocols.ipAddress = JOptionPane.showInputDialog(frame, "Enter host ip address");
				 NetworkProtocols.joinIPSet = true;	
				 timeUntilPrompt = 10;
			}
			
			
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
				}catch( Exception e )
				{
				}
				
				game.setScreen(game.mainMenu);
			}
			
			timeUntilPrompt--;
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
		NetworkProtocols.joinIPSet = false;
	}

	@Override
	public void dispose() 
	{
		font.dispose();
	}

}
