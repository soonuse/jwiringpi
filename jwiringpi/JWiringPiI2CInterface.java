/**
  * @filename       : JWiringPiI2CInterface.java
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
  * @brief I2C Library
  **********************************************************************
  * WiringPi includes a library which can make it easier to use the Raspberry 
  * Pi’s on-board I2C interface.  
  * Before you can use the I2C interface, you may need to use the gpio utility    * to load the I2C drivers into the kernel: 
  *     gpio load i2c
  * If you need a baud rate other than the default 100Kbps, then you can supply
  * this on the command-line: 
  *     gpio load i2c 1000
  * will set the baud rate to 1000Kbps – ie. 1,000,000 bps.
  * (K here is times 1000)
  */
public interface JWiringPiI2CInterface {

/**
  * @brief:
  *   This initialises the I2C system with your given device identifier. The
  *   ID is the I2C number of the device and you can use the i2cdetect program 
  *   to find this out. wiringPiI2CSetup() will work out which revision 
  *   Raspberry Pi you have and open the appropriate device in /dev.
  *   The return value is the standard Linux filehandle, or -1 if any error – 
  *   in which case, you can consult errno as usual.
  * @param: int devId
  * @retval: int
  */
    public int wiringPiI2CSetup (int devId);

/**
  * @brief:
  *   Simple device read. Some devices present data when you read them without 
  *   having to do any register transactions.
  * @param: int fd
  * @retval: int
  */
    public int wiringPiI2CRead (int fd);

/**
  * @brief:
  *   Simple device write. Some devices accept data this way without needing to 
  *   access any internal registers.
  * @param: int fd, int data
  * @retval: int
  */
    public int wiringPiI2CWrite (int fd, int data);

/**
  * @brief:
  *   Continuously device write. The byte[0] is often the internal register of 
  *   the device. This function is rewrite from the write() function of system
  *   return the number of bytes read or -1 if any error.
  * @param: int fd, byte[] data, int length
  * @retval: int
  */
    public int wiringPiI2CWrite (int fd, byte[] data, int length);

/**
  * @brief:
  *   These write an 8 or 16-bit data value into the device register indicated.
  * @param: int fd, byte[] data, int length
  * @retval: int
  */
    public int wiringPiI2CWriteReg8 (int fd, int reg, int data);
    public int wiringPiI2CWriteReg16 (int fd, int reg, int data);

/**
  * @brief:
  *   These read an 8 or 16-bit value from the device register indicated.
  * @param: int fd, byte[] data, int length
  * @retval: int
  */
    public int wiringPiI2CReadReg8 (int fd, int reg);
    public int wiringPiI2CReadReg16 (int fd, int reg);
}

