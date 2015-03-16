/*
 * BoxGame - CSC376 Group Project
 * Team Members: David Kim, Baldomero Samaro,
 *				 Matt Wylder, Stipo Perisa
 * 
 * Source File: CollisionManager.java
 * 
 * All Files in Project: Boundary.java, BoxCharacter.java, BoxGame.java, CollisionManager.java, ConnectionDisconnectScreen.java,
 * 						ConnectionJoinScreen.java, ConnectionLoseScreen.java, ConnectionWaitScreen.java, ConnectionWinScreen.java,
 * 						GameEntity.java, GameID.java, GameScreen.java, Goal.java, InputEntity.java, InputManager.java, KeyEvent.java,
 * 						MainMenuScreen.java, Obstacle.java, Physics.java
 * 
 */

package com.mygdx.game;


import java.util.Iterator;
import java.util.LinkedList;

public class CollisionManager 
{
	//Singleton Setup START//
		private static CollisionManager colManInstance;
		
		private CollisionManager()
		{
			collistionList = new LinkedList<GameEntity>();
		}
		private static CollisionManager instance()
		{
			if( colManInstance == null )
			{
				colManInstance = new CollisionManager();
			}
			
			return colManInstance;
		}
	//Singleton Setup   END//
		
		
	LinkedList<GameEntity> collistionList;
	

//Collision Checking START//
	
	public static void checkCollisions()
	{
		instance().processCollisions();
	}
	
		//Singleton internal process collision call
		private void processCollisions()
		{
			Iterator< GameEntity > it_check;
			Iterator< GameEntity > it_other;
			
			GameEntity check;
			GameEntity other;
			

			it_check = collistionList.iterator();
			
			while( it_check.hasNext() )
			{
				check = (GameEntity) it_check.next();

				it_other = collistionList.iterator();
				while( it_other.hasNext() )
				{
					other = (GameEntity) it_other.next();

					if( check != other)
					{
						if( testCollision( check, other ) )
						{
							check.collided(other);
							other.collided(check);
						}
					}
				}
			}
		}
		
		private boolean testCollision( GameEntity one, GameEntity two )
		{
			return one.hitbox.overlaps( two.hitbox );
		}

//Collision Checking   END//
	

//Collision Registration START//
	public static void registerCollision( GameEntity toReg )
	{
		instance().internalRegistration( toReg );
	}
		
		//Singleton internal register call method
		private void internalRegistration( GameEntity toReg )
		{
			Iterator< GameEntity > it = collistionList.iterator();
			GameEntity check;
				
			while( it.hasNext() )
			{
				check = (GameEntity) it.next();
				
				//if already registered
				if( check == toReg )
				{
					return;
				}
			}

			//registering gameentity
			collistionList.add( toReg );
		}
//Collision Registration   END//

		
//Collision Deregistration START//
	public static void deregisterCollision( GameEntity toDereg )
	{
		instance().internalDeregistration( toDereg );
	}
		
		//Singleton internal register call method
		private void internalDeregistration( GameEntity toDereg )
		{
			Iterator< GameEntity > it = collistionList.iterator();
			GameEntity check;
				
			while( it.hasNext() )
			{
				check = (GameEntity) it.next();
				
				//found entity to deregister
				if( check == toDereg )
				{
					it.remove();
					return;
				}
			}
		}
		
//Collision Deregistration   END//
		
	

}
