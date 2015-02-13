package com.mygdx.game;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;



public class BoxCharacter extends GameEntity
{	
	Vector2 characterImpulse;
	float   characterSpeed;
	
	float characterHeight;
	float characterWidth;
	
	public BoxCharacter()
	{
		//Input Setup
		registerInput( Input.Keys.LEFT );
		registerInput( Input.Keys.RIGHT );
		registerInput( Input.Keys.SPACE );
		
		//Setup ID: 0 is box
		id = 0;
		
		//Setup hitbox and rectangle
		sprite = new ShapeRenderer();
			sprite.setColor(1,1,1,1);
		position = new Vector2(100, 100);
		
		characterHeight = 20.0f;
		characterWidth = 20.0f;
		
		hitbox = new Rectangle( position.x, position.y, characterHeight, characterWidth );
		
		//Setup movement
		characterImpulse = new Vector2( 0, 0 );
		characterSpeed = 100;
		
		//RegisterCollision
		registerCollision();
	}
	

	public void render()
	{
		//updating position
		position.x += characterImpulse.x;
		position.y += characterImpulse.y;
		hitbox.setPosition(position);
		
		//Render box Shape
		sprite.begin( ShapeType.Filled );
		
		sprite.rect(hitbox.getX(), hitbox.getY(), hitbox.getWidth(), hitbox.getHeight());
		
		sprite.end();
	}
	
	//CharacterMovement
	private void moveLeft()
	{
		characterImpulse.x = -characterSpeed * Gdx.graphics.getDeltaTime();
	}
	private void moveRight()
	{
		characterImpulse.x = characterSpeed * Gdx.graphics.getDeltaTime();
	}
	private void jump()
	{
	}
	private void moveStop()
	{
		characterImpulse.setZero();
	}
	
	
	//Collision stuff
	public void collided( GameEntity other )
	{
		System.out.println("BoxCharacter colliding");
	}
	
	//Input Stuff
	void keyPressed( int key )
	{
		if( key == Input.Keys.LEFT )
		{
		}
		
		if( key == Input.Keys.RIGHT )
		{
		}
		
		if( key == Input.Keys.SPACE )
		{
			jump();
		}
	}
	
	void keyReleased( int key )
	{
		if( key == Input.Keys.LEFT || key == Input.Keys.RIGHT)
		{
			moveStop();
		}
		
		if( key == Input.Keys.SPACE )
		{
			
		}	
	}
	
	void keyDown( int key )
	{
		if( key == Input.Keys.LEFT )
		{
			moveLeft();
		}
		
		if( key == Input.Keys.RIGHT )
		{
			moveRight();
		}
		
		if( key == Input.Keys.SPACE )
		{
			
		}
	}
}
