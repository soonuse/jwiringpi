/**
  * @filename       : JWiringPiTimingInterface.java
  * @project        : JWiringPi
  * @date           : June 26 2017
  * @description:
  *      Java wrapper of Arduino like Wiring library for the Raspberry Pi.
  *      The implements are based on WiringPi library.
  *      WiringPi Library Copyright (c) 2012-2017 Gordon Henderson
  *      JWiringPi project Copyright (c) 2017 soonuse from GitHub
  ***********************************************************************
  * This file is part of JWiringPi interface.
  *
  * The purpose of JWiringPi project is to create a convenient IO control
  * interface (containing the implements of class) for Raspberry Pi Java
  * programming.
  *
  * JWiringPi is free software: you can redistribute it and/or modify
  * it under the terms of the General Public License (GPL).
  *
  * JWiringPi is distributed in the hope that it will be useful,
  * but WITHOUT ANY WARRANTY; without even the implied warranty of
  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  * GNU Lesser General Public License for more details.
  *
  * You should have received a copy of the GNU Lesser General Public License
  * along with wiringPi.  If not, see <http://www.gnu.org/licenses/>.
  ***********************************************************************
 */

package jwiringpi;

/**
  * @brief Timing functions
  **********************************************************************
  * While Linux provides a multitude of system calls and functions to
  * providing various timing and sleeping functions, sometimes it can be
  * quite confusing, especially if you are new to Linux, so the ones
  * presented here mimic those available on the Arduino platform, making
  * porting code and writing new code somewhat easier.  
  * Note: Even if you are not using any of the input/output functions you
  * still need to call one of the wiringPi setup functions – just use 
  * wiringPiSetupSys() if you don’t need root access in your program.
  */
public interface JWiringPiTimingInterface {

/**
  * @brief:
  *   This returns a number representing the number of milliseconds since 
  *   your program called one of the wiringPiSetup functions. It returns an 
  *   unsigned 32-bit number which wraps after 49 days.
  * @param: none
  * @retval: int
  */
    public int millis ();

/**
  * @brief:
  *   This returns a number representing the number of microseconds since 
  *   your program called one of the wiringPiSetup functions. It returns an 
  *   unsigned 32-bit number which wraps after approximately 71 minutes.
  * @param: none
  * @retval: int
  */
    public int micros ();

/**
  * @brief:
  *   This causes program execution to pause for at least howLong milliseconds.
  *   Due to the multi-tasking nature of Linux it could be longer. 
  * @param: int howLong
  * @retval: none
  */
    public void delay (int howLong);

/**
  * @brief:
  *   This causes program execution to pause for at least howLong microseconds.
  *   Due to the multi-tasking nature of Linux it could be longer. 
  * @param: int howLong
  * @retval: none
  */
    public void delayMicroseconds (int howLong);
}

