/**
  * @filename       : JWiringPiInterfaceImpl.java
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
  * @brief this declares the native methods of JWiringPiInterfaces, in fact
  * they are implemented from the external C programs.
  */
public class JWiringPiInterfaceImpl implements 
    JWiringPiSetupInterface,
    JWiringPiCoreInterface,
    JWiringPiTimingInterface,
    JWiringPiSPIInterface,
    JWiringPiI2CInterface
{

    // Implements JWiringPiSetupInterface
    public native int wiringPiSetup() ;
    public native int wiringPiSetupGpio() ;
    public native int wiringPiSetupPhys() ;
    public native int wiringPiSetupSys() ;

    // Implements JWiringPiCoreInterface
    public native void pinMode(int pin, int mode);
    public native void pullUpDnControl(int pin, int pud);
    public native void digitalWrite(int pin, int value);
    public native void pwmWrite(int pin, int value);
    public native int digitalRead(int pin);
    public native int analogRead(int pin);
    public native void analogWrite(int pin, int value); 

    // Implements JWiringPiTimingInterface
    public native int millis();
    public native int micros();
    public native void delay(int howLong);
    public native void delayMicroseconds(int howLong);

    // Implements JWiringPiSPIInterface
    public native int wiringPiSPISetup(int channel, int speed);
    public native int wiringPiSPIDataRW(int channel, byte[] data, int len);

    // Implements JWiringPiI2CInterface
    public native int wiringPiI2CSetup (int devId);
    public native int wiringPiI2CRead (int fd);
    public native int wiringPiI2CWrite (int fd, int data);
    public native int wiringPiI2CWrite (int fd, byte[] data, int length);
    public native int wiringPiI2CWriteReg8 (int fd, int reg, int data);
    public native int wiringPiI2CWriteReg16 (int fd, int reg, int data);
    public native int wiringPiI2CReadReg8 (int fd, int reg);
    public native int wiringPiI2CReadReg16 (int fd, int reg);
}
