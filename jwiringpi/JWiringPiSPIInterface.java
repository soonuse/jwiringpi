/**
  * @filename       : JWiringPiSPIInterface.java
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
  * @brief SPI Library
  **********************************************************************
  * WiringPi includes a library which can make it easier to use the Raspberry
  * Pi’s on-board SPI interface.
  * Before you can use SPI interface, you may need to use the gpio utility to
  * load the SPI drivers into the kernel: 
  *     gpio load spi
  * If you need a buffer size of greater than 4KB, then you can specify the 
  * size (in KB) on the command line: 
  *     gpio load spi 100
  * will allocate a 100KB buffer. (You should rarely need this though, the
  * default is more than enough for most applications).
  */
public interface JWiringPiSPIInterface {

/**
  * @brief:
  *   This is the way to initialise a channel (The Pi has 2 channels; 0 and 1).
  *   The speed parameter is an integer in the range 500,000 through 32,000,000
  *   and represents the SPI clock speed in Hz.
  *   The returned value is the Linux file-descriptor for the device, or -1
  *   on error. If an error has happened, you may use the standard errno global
  *   variable to see why.
  * @param: int channel, int speed
  * @retval: int
  */
    public int wiringPiSPISetup (int channel, int speed); 

/**
  * @brief:
  *   This performs a simultaneous write/read transaction over the selected
  *   SPI bus. Data that was in your buffer is overwritten by data returned
  *   from the SPI bus.
  *   That’s all there is in the helper library. It is possible to do simple
  *   read and writes over the SPI bus using the standard read() and write()
  *   system calls though – write() may be better to use for sending data to
  *   chains of shift registers, or those LED strings where you send RGB
  *   triplets of data. Devices such as A/D and D/A converters usually need
  *   to perform a concurrent write/read transaction to work.
  * @param: int channel, byte[] data, int len
  * @retval: int
  */
    public int wiringPiSPIDataRW (int channel, byte[] data, int len);
}

