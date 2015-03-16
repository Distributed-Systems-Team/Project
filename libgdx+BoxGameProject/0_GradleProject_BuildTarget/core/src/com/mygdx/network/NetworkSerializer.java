/*
 * 
 * This class is NOT from any of the BoxGame Group members
 * 
 * NetworkSerializer is a class written as an answer to a question on StackOverFlow
 * by Henrik Gustafsson
 *  
 *  Source Site: http://stackoverflow.com/questions/5837698/converting-any-object-to-a-byte-array-in-java
 *
 */

package com.mygdx.network;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class NetworkSerializer
{
	
	public static byte[] serialize(Object obj) throws IOException{
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		ObjectOutputStream o = new ObjectOutputStream(b);
		o.writeObject(obj);
		return b.toByteArray();
	}
	public static Object deserialize(byte[] bytes) throws IOException, ClassNotFoundException{
		ByteArrayInputStream b = new ByteArrayInputStream(bytes);
		ObjectInputStream o = new ObjectInputStream(b);
		return o.readObject();
	}
}