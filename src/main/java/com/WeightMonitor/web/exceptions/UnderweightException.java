package com.WeightMonitor.web.exceptions;

public class UnderweightException extends Exception
{
      //Parameterless Constructor
      public UnderweightException() {}

      //Constructor that accepts a message
      public UnderweightException(String message)
      {
         super(message);
      }
 }
