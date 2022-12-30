package com.zy.generator.exception;

/**
 *
 */
public class GardeniaException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public GardeniaException(String message){
		super(message);
	}

	public GardeniaException(Throwable cause)
	{
		super(cause);
	}

	public GardeniaException(String message, Throwable cause)
	{
		super(message,cause);
	}
}
