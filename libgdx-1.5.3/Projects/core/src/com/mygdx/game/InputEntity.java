package com.mygdx.game;



public abstract class InputEntity 
{
	
	abstract void keyPressed( int key );
	abstract void keyReleased( int key );
	abstract void keyDown( int key );
	
	
	public void registerInput( int key )
	{
		InputManager.registerInputEntity(this, key);
	}
	public void deregisterInput( int key )
	{
		InputManager.deregisterInputEntity(this, key);
	}
	
}
