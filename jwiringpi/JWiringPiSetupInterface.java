/**
  * @filename       : JWiringPiSetupInterface.java
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
  * @brief Setup functions
  *****************************************************************
  * One of the setup functions must be called at the start of your
  * program or your program will fail to work correctly. You may
  * experience symptoms from it simply not working to segfaults and
  * timing issues.
  */
public interface JWiringPiSetupInterface {

/**
  * @brief: 
  *   This initialises wiringPi and assumes that the calling program 
  *   is going to be using the wiringPi pin numbering scheme. This is
  *   a simplified numbering scheme which provides a mapping from 
  *   virtual pin numbers 0 through 16 to the real underlying Broadcom
  *   GPIO pin numbers.
  * @param: None
  * @retval: int
  */
    public int wiringPiSetup();

/**
  * @brief: 
  *   This initialises wiringPi but uses the /sys/class/gpio interface 
  *   rather than accessing the hardware directly. This can be called as
  *   a non-root user provided the GPIO pins have been exported before-hand
  *   using the gpio program. Pin numbering in this mode is the native
  *   Broadcom GPIO numbers – the same as wiringPiSetupGpio() above, so be
  *   aware of the differences between Rev 1 and Rev 2 boards.
  * @param: None
  * @retval: int
  */
    public int wiringPiSetupSys();

/**
  * @brief: 
  *   This is identical to above, however it allows the calling programs
  *   to use the Broadcom GPIO pin numbers directly with no re-mapping.
  * @param: None
  * @retval: int
  */
    public int wiringPiSetupGpio();

/**
  * @brief: 
  *   Identical to above, however it allows the calling programs to use
  *   the physical pin numbers on the P1 connector only.
  * @param: None
  * @retval: int
  */
    public int wiringPiSetupPhys();
}

