/*
 * BoxGame - CSC376 Group Project
 * Team Members: David Kim, Baldomero Samaro,
 *				 Matt Wylder, Stipo Perisa
 * 
 * Source File: NetworkProtocols.java
 * 
 * All Files in Project: Boundary.java, BoxCharacter.java, BoxGame.java, CollisionManager.java, ConnectionDisconnectScreen.java,
 * 						ConnectionJoinScreen.java, ConnectionLoseScreen.java, ConnectionWaitScreen.java, ConnectionWinScreen.java,
 * 						GameEntity.java, GameID.java, GameScreen.java, Goal.java, InputEntity.java, InputManager.java, KeyEvent.java,
 * 						MainMenuScreen.java, Obstacle.java, Physics.java
 * 
 * Main Coder: David Kim
 * Contributors: 
 * 
 */

package com.mygdx.network;


public final class NetworkProtocols
{
	public static int IDLE = -999;
	
	public static int KEY_SPACE_pressed = 0;
	public static int KEY_SPACE_down = 1;
	public static int KEY_SPACE_release = 2;

	public static int KEY_L_ARROW_pressed = 10;
	public static int KEY_L_ARROW_down = 11;
	public static int KEY_L_ARROW_release = 12;

	public static int KEY_R_ARROW_pressed = 20;
	public static int KEY_R_ARROW_down = 21;
	public static int KEY_R_ARROW_release = 22;

	public static int KEY_UP_ARROW_pressed = 30;
	public static int KEY_UP_ARROW_down = 31;
	public static int KEY_UP_ARROW_release = 32;
	
	
	
	public static int gameEnded = -9;
	public static int youLose	= -19;
	
	public static int waitID = 100;
	public static int joinID = 101;
	
	public static final int portNum = 3333;
	public static String ipAddress = "NO IP SET";
	public static boolean joinIPSet = false;
}