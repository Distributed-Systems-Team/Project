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
	
	boolean onLand;
		float landedPlatformLeft;
		float landedPlatformRight;
	boolean isJumping;
	
	Vector2 spawnPos;
	
	Vector2 prevPos;
	
	boolean playerControlled;
	
	public BoxCharacter( Vector2 startPos , boolean playerOn)
	{
		id = GameID.ID_box;
		
		//Setup hitbox and rectangle
		sprite = new ShapeRenderer();
		position = new Vector2( startPos );
		spawnPos = new Vector2( startPos );
		prevPos = new Vector2( startPos );
		
		if( startPos.y > GameScreen.groundPos )
			onLand = false;
		
		characterHeight = 20.0f;
		characterWidth = 20.0f;
		
		hitbox = new Rectangle( position.x, position.y, characterHeight, characterWidth );
		landedPlatformLeft = 0;
		landedPlatformRight = 0;
		
		//Setup movement
		characterImpulse = new Vector2( 0, 0 );
		characterSpeed = 200;
		
		//RegisterCollision
		registerCollision();
		
		playerControlled = playerOn;
		
		
		//Input Setup
		
		if( playerControlled )
		{
			registerInput( Input.Keys.LEFT );
			registerInput( Input.Keys.RIGHT );
			registerInput( Input.Keys.SPACE );
			
			sprite.setColor(1, 1, 1,1);
		}
		else
		{
			sprite.setColor(0,0,0,1);
		}
		
		
		
		
		isJumping = false;
	}
	

	public void render()
	{
		//Gravity
		if( !onLand )
		{
			Physics.runGravity( this );
		}
		else
		{
			if( position.x > landedPlatformRight	||
				position.x + characterWidth < landedPlatformLeft )
			{
				Physics.runGravity( this );
			}
		}
		
		
		prevPos.set(position);
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
		isJumping = true;
		onLand = false;
		characterImpulse.y =  850 * Gdx.graphics.getDeltaTime();
	}
	private void moveStopX()
	{
		characterImpulse.x = 0;
	}
	private void moveStopY()
	{
		characterImpulse.y = 0;
	}
	
	private void land()
	{
		onLand = true;
		moveStopY();
	}
	
	//Collision stuff
	public void collided( GameEntity other )
	{	
		int collideID = other.id;
		
		if( collideID == GameID.ID_ground ||
			collideID == GameID.ID_wall)
		{
			//if right of other
			if( hitbox.x  < other.hitbox.x + other.hitbox.width   &&
				hitbox.x  > other.hitbox.x + other.hitbox.width - 5)
			{
				position.x = other.hitbox.x + other.hitbox.width;
			}
			//if left of other
			else if( hitbox.x + characterWidth > other.hitbox.x &&
					 hitbox.x + characterWidth < other.hitbox.x + 5)
			{
				position.x = other.hitbox.x - characterWidth;
			}
			//if above other
			else if( hitbox.y >= other.hitbox.y + other.hitbox.height/2 )
			{
				land();
				
				position.y = other.hitbox.y + other.hitbox.height;
				
				landedPlatformLeft = other.hitbox.x;
				landedPlatformRight = other.hitbox.x + other.hitbox.width;
			}
			//if below other
			else if( hitbox.y + characterHeight < other.hitbox.y + other.hitbox.height/2 )
			{
				position.y = other.hitbox.y - characterHeight;
				moveStopY();
			}
		
		}
		else if( collideID == GameID.ID_obstacle )
		{
			//if right of other
			if( hitbox.x  < other.hitbox.x + other.hitbox.width   &&
				hitbox.x  > other.hitbox.x + other.hitbox.width - 5)
			{
				position.x = other.hitbox.x + other.hitbox.width;
			}
			//if left of other
			else if( hitbox.x + characterWidth > other.hitbox.x &&
					 hitbox.x + characterWidth < other.hitbox.x + 5)
			{
				position.x = other.hitbox.x - characterWidth;
			}
			//if above other
			else if( hitbox.y >= other.hitbox.y + other.hitbox.height/2 )
			{
				land();
				
				position.y = other.hitbox.y + other.hitbox.height;
				
				landedPlatformLeft = other.hitbox.x;
				landedPlatformRight = other.hitbox.x + other.hitbox.width;
			}
			//if below other
			else if( hitbox.y + characterHeight < other.hitbox.y + other.hitbox.height/2 )
			{
				position.y = other.hitbox.y - characterHeight;
				moveStopY();
			}

		}
		else if( collideID == GameID.ID_box )
		{
			//if right of other
			if( hitbox.x  < other.hitbox.x + characterWidth  &&
				hitbox.x  > other.hitbox.x + characterWidth/2 )
			{
				position.x = other.hitbox.x + characterWidth;
			}
			//if left of other
			else if( hitbox.x + characterWidth > other.hitbox.x &&
					 hitbox.x + characterWidth < other.hitbox.x + characterWidth/2 )
			{
				position.x = other.hitbox.x - characterWidth;
			}
			//if above other
			else if( hitbox.y >= other.hitbox.y + other.hitbox.height/2 )
			{
				land();
				
				position.y = other.hitbox.y + other.hitbox.height;
				
				landedPlatformLeft = other.hitbox.x;
				landedPlatformRight = other.hitbox.x + other.hitbox.width;
			}
			//if below other
			else if( hitbox.y + characterHeight < other.hitbox.y + other.hitbox.height/2 )
			{
				position.y = other.hitbox.y - characterHeight;
				moveStopY();
			}
			
		}
	}
	
	//Input Stuff
	void keyPressed( int key )
	{
		if( key == Input.Keys.LEFT )
		{
			if(playerControlled)
				TESTNetworkMan.pushToNetwork( NetworkProtocols.KEY_L_ARROW_pressed);
		}
		
		if( key == Input.Keys.RIGHT )
		{
			if(playerControlled)
				TESTNetworkMan.pushToNetwork( NetworkProtocols.KEY_R_ARROW_pressed);
		}
		
		if( key == Input.Keys.SPACE )
		{
			if(playerControlled)
				TESTNetworkMan.pushToNetwork( NetworkProtocols.KEY_SPACE_pressed);
		}
	}
	
	void keyReleased( int key )
	{
		if( key == Input.Keys.LEFT || key == Input.Keys.RIGHT)
		{
			if(playerControlled)
				TESTNetworkMan.pushToNetwork( NetworkProtocols.KEY_L_ARROW_release);
			
			moveStopX();
		}
		
		if( key == Input.Keys.SPACE )
		{
			if(playerControlled)
				TESTNetworkMan.pushToNetwork( NetworkProtocols.KEY_SPACE_release);
		}	
	}
	
	void keyDown( int key )
	{
		if( key == Input.Keys.LEFT )
		{
			if(playerControlled)
				TESTNetworkMan.pushToNetwork( NetworkProtocols.KEY_L_ARROW_down);
			
			moveLeft();
		}
		
		if( key == Input.Keys.RIGHT )
		{
			if(playerControlled)
				TESTNetworkMan.pushToNetwork( NetworkProtocols.KEY_R_ARROW_down);
			
			moveRight();
		}
		
		if( key == Input.Keys.SPACE )
		{
			if(playerControlled)
				TESTNetworkMan.pushToNetwork( NetworkProtocols.KEY_SPACE_down);
			
			if(onLand)
				jump();
		}
	}
}
