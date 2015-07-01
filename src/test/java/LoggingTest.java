/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jonathanodgis
 */
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingTest 
{
  public static void main(String[] args) 
  {
    Logger logger = LoggerFactory.getLogger(LoggingTest.class);
    logger.info("Log test in a main class");
    logger.debug("HERE");
  }
}