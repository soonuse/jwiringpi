/**
  * @filename       : JWiringPiInterface.java
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

public interface JWiringPiInterface {
//    /**
//     * Setup
//     */
//    int  wiringPiSetup();
//    int  wiringPiSetupSys();
//    int  wiringPiSetupGpio();
//    int  wiringPiSetupPhys();
//
//    /**
//     * Core Functions
//     */
//    void pinMode (int pin, int mode);
//    void pullUpDnControl (int pin, int pud);
//    void digitalWrite (int pin, int value);
//    void pwmWrite (int pin, int value);
//    int digitalRead (int pin);
//    int analogRead (int pin);
//    void analogWrite (int pin, int value);
//
//    /**
//     * Raspberry Pi Specifics
//     */
//    void digitalWriteByte (int value);
//    void pwmSetMode (int mode);
//    void pwmSetRange (int range);
//    void pwmSetClock (int divisor);
//    int piBoardRev ();  // Deprecated
//    int wpiPinToGpio (int wPiPin);
//    int physPinToGpio (int physPin);
//    void setPadDrive (int group, int value);
//
//    /**
//     * Timing
//     */
//    int millis ();
//    int micros ();
//    void delay (int howLong);
//    void delayMicroseconds (int howLong);
//
//    /**
//     * Serial Library
//     */
//    int serialOpen (String device, int baud);
//    void serialClose (int fd);
//    void  serialPutchar (int fd, byte c);
//    void  serialPuts (int fd, String s);
//    int   serialDataAvail (int fd);
//    int serialGetchar (int fd);
//    void serialFlush (int fd);
//
//    /**
//     * SPI Library
//     */
//    int wiringPiSPISetup (int channel, int speed);
//    int wiringPiSPIDataRW (int channel, unsigned char *data, int len);
//    
//    /**
//     * I2C Library
//     */
//    int wiringPiI2CSetup (int devId);
//    int wiringPiI2CRead (int fd);
//    int wiringPiI2CWrite (int fd, int data);
//    int wiringPiI2CWriteReg8 (int fd, int reg, int data);
//    int wiringPiI2CWriteReg16 (int fd, int reg, int data);
//    int wiringPiI2CReadReg8 (int fd, int reg);
//    int wiringPiI2CReadReg16 (int fd, int reg);
//    
//    /**
//     * Shift Library
//     */
//    byte shiftIn (byte dPin, byte cPin, byte order);
//    void shiftOut (byte dPin, byte cPin, byte order, byte val);
//
//    /**
//     * Software PWM Library
//     */
//    int softPwmCreate (int pin, int initialValue, int pwmRange);
//    void softPwmWrite (int pin, int value);
//
//    /**
//     * Software Tone Library
//     */
//    int softToneCreate (int pin);
//    void softToneWrite (int pin, int freq);
}

