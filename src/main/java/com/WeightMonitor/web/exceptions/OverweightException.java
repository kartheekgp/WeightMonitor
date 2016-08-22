package com.WeightMonitor.web.exceptions;

public class OverweightException extends Exception
{
      //Parameterless Constructor
      public OverweightException() {}

      //Constructor that accepts a message
      public OverweightException(String message)
      {
         super(message);
      }
 }
