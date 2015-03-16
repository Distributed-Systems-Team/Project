package com.mygdx.game;


import java.util.LinkedList;
import java.util.Iterator;

public class InputManager 
{
//Singleton Setup START//
	private static InputManager inputManInstance;
	
	private InputManager()
	{
		KeyEventsList = new LinkedList<KeyEvent>();
	}
	private static InputManager instance()
	{
		if( inputManInstance == null )
		{
			inputManInstance = new InputManager();
		}
		
		return inputManInstance;
	}
//Singleton Setup   END//
	
	
	//Member Variables
	LinkedList< KeyEvent > KeyEventsList;
	
	
	public static void updateInputs()
	{
		instance().internalUpdate();
	}
		private void internalUpdate()
		{
			Iterator< KeyEvent > it = KeyEventsList.iterator();
			while( it.hasNext() )
			{
				( (KeyEvent) it.next() ).keyUpdate();
			}
		}
	
	
//InputEntity Registration START//
	public static void registerInputEntity( InputEntity toReg, int key )
	{
		instance().internalRegistration( toReg, key );
	}
	
		//Singleton internal register call method
		private void internalRegistration( InputEntity toReg, int key  )
		{
			Iterator< KeyEvent > it = KeyEventsList.iterator();
			KeyEvent check;
			
			while( it.hasNext() )
			{
				check = (KeyEvent) it.next();
				
				if( check.getKey() == key )
				{
					check.registerKey(toReg);
					return;
				}
			}


			//InputEntity has not been registered yet
			KeyEvent event = new KeyEvent( key );
			
			event.registerKey(toReg);
			
			KeyEventsList.add( event );
			
		}
//InputEntity Registration   END//
		
		
		
//InputEntity Deregistration START//		
	public static void deregisterInputEntity( InputEntity toReg, int key  )
	{
		instance().internalDeregistration( toReg, key );
	}
	
		//Singleton internal deregister call method
		private void internalDeregistration( InputEntity toReg, int key  )
		{
			Iterator< KeyEvent > it = KeyEventsList.iterator();
			KeyEvent check;
			
			while( it.hasNext() )
			{
				check = (KeyEvent) it.next();
				
				if( check.getKey() == key )
				{
					check.deregisterKey(toReg);
					return;
				}
			}
		}
//InputEntity Deregistration   END//		
		
}
