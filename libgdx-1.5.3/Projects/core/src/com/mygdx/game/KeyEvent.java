package com.mygdx.game;

import com.badlogic.gdx.Gdx;

import java.util.LinkedList;
import java.util.Iterator;



public class KeyEvent 
{
	int key;
	boolean currentState;
	boolean prevState;
	
	LinkedList< InputEntity > callbackList;
	
	public KeyEvent( int keyReg )
	{
		key = keyReg;

		prevState = false;
		currentState = false;
		
		callbackList = new LinkedList<InputEntity>();
	}
	
	public int getKey()
	{
		return key;
	}	
	
	public void keyUpdate()
	{
		//getting current state of key
		currentState = Gdx.input.isKeyPressed(key);

		//If Change in State
		if( currentState != prevState )
		{
			//IDLE -> PRESSED
			if( prevState == false )
			{
				keyPressed();
			}
			//PRESSED -> IDLE
			else
			{
				keyReleased();
			}
		}
		
		if( currentState )
		{
			keyDown();
		}

		//setting prevState
		prevState = currentState;
	}
	
	
	public void registerKey( InputEntity toReg )
	{
		Iterator< InputEntity > it = callbackList.iterator();
		InputEntity check;

		while( it.hasNext() )
		{
			check = (InputEntity) it.next();
			
			//if already registered, do nothing
			if( check == toReg )
				return;
		}
		
		//InputEntity has not been registered
		//so:
		callbackList.add( toReg );
	}
	
	public void deregisterKey( InputEntity toDereg )
	{
		Iterator< InputEntity > it = callbackList.iterator();
		InputEntity check;
		
		while( it.hasNext() )
		{
			check = (InputEntity) it.next();
			
			//found inputEntity to deregister
			if( check == toDereg )
			{
				it.remove();
			}
		}
	}
	
	

	
	
	private void keyPressed()
	{
		Iterator< InputEntity > it = callbackList.iterator();
		InputEntity toCallback;
		
		while( it.hasNext() )
		{
			toCallback = (InputEntity) it.next();
			
			toCallback.keyPressed( key );
		}
	}
	private void keyReleased()
	{
		Iterator< InputEntity > it = callbackList.iterator();
		InputEntity toCallback;
		
		while( it.hasNext() )
		{
			toCallback = (InputEntity) it.next();
			
			toCallback.keyReleased( key );
		}
	}
	private void keyDown()
	{
		Iterator< InputEntity > it = callbackList.iterator();
		InputEntity toCallback;
		
		while( it.hasNext() )
		{
			toCallback = (InputEntity) it.next();
			
			toCallback.keyDown( key );
		}
	}
}

