package com.projet.demo.Exceptions;

public class EntityNotFoundException extends RuntimeException{

	public EntityNotFoundException (String message)
	{
		super(message);
	}
}
