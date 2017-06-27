/**
  * @filename       : JWiringPiCoreInterface.java
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
  * @brief Core functions
  **********************************************************************
  * These functions work directly on the Raspberry Pi and also with external 
  * GPIO modules such as GPIO expanders and so on, although not all modules 
  * support all functions – e.g. the PiFace is pre-configured for its fixed 
  * inputs and outputs, and the Raspberry Pi has no on-board analog hardware.
  */
public interface JWiringPiCoreInterface {

    // Handy defines
    
    // wiringPi modes
    
    public static final int WPI_MODE_PINS            =  0;
    public static final int WPI_MODE_GPIO            =  1;
    public static final int WPI_MODE_GPIO_SYS        =  2;
    public static final int WPI_MODE_PHYS            =  3;
    public static final int WPI_MODE_PIFACE          =  4;
    public static final int WPI_MODE_UNINITIALISED   = -1;
    
    // Pin modes
    
    public static final int INPUT                    =  0;
    public static final int OUTPUT                   =  1;
    public static final int PWM_OUTPUT               =  2;
    public static final int GPIO_CLOCK               =  3;
    public static final int SOFT_PWM_OUTPUT          =  4;
    public static final int SOFT_TONE_OUTPUT         =  5;
    public static final int PWM_TONE_OUTPUT          =  6;
    
    public static final int LOW                      =  0;
    public static final int HIGH                     =  1;
    
    // Pull up/down/none
    
    public static final int PUD_OFF                  =  0;
    public static final int PUD_DOWN                 =  1;
    public static final int PUD_UP                   =  2;
    
    // PWM
    
    public static final int PWM_MODE_MS              =  0;
    public static final int PWM_MODE_BAL             =  1;
    
    // Interrupt levels
    
    public static final int INT_EDGE_SETUP           =  0;
    public static final int INT_EDGE_FALLING         =  1;
    public static final int INT_EDGE_RISING          =  2;
    public static final int INT_EDGE_BOTH            =  3;
    
    // Pi model types and version numbers
    //      Intended for the GPIO program Use at your own risk.
    
    public static final int PI_MODEL_A               =  0;
    public static final int PI_MODEL_B               =  1;
    public static final int PI_MODEL_AP              =  2;
    public static final int PI_MODEL_BP              =  3;
    public static final int PI_MODEL_2               =  4;
    public static final int PI_ALPHA                 =  5;
    public static final int PI_MODEL_CM              =  6;
    public static final int PI_MODEL_07              =  7;
    public static final int PI_MODEL_3               =  8;
    public static final int PI_MODEL_ZERO            =  9;
    public static final int PI_MODEL_CM3             = 10;
    public static final int PI_MODEL_ZERO_W          = 12;
    
    public static final int PI_VERSION_1             =  0;
    public static final int PI_VERSION_1_1           =  1;
    public static final int PI_VERSION_1_2           =  2;
    public static final int PI_VERSION_2             =  3;
    
    public static final int PI_MAKER_SONY            =  0;
    public static final int PI_MAKER_EGOMAN          =  1;
    public static final int PI_MAKER_EMBEST          =  2;
    public static final int PI_MAKER_UNKNOWN         =  3;
    
/**
  * @brief:
  *   This sets the mode of a pin to either INPUT, OUTPUT, PWM_OUTPUT or
  *   GPIO_CLOCK. Note that only wiringPi pin 1 (BCM_GPIO 18) supports PWM
  *   output and only wiringPi pin 7 (BCM_GPIO 4) supports CLOCK output modes.
  * @param: int pin, int mode
  * @retval: none
  */
    public void pinMode (int pin, int mode);

/**
  * @brief:
  *   This sets the pull-up or pull-down resistor mode on the given pin, which
  *   should be set as an input. Unlike the Arduino, the BCM2835 has both 
  *   pull-up an down internal resistors. The parameter pud should be; PUD_OFF,
  *   (no pull up/down), PUD_DOWN (pull to ground) or PUD_UP (pull to 3.3v) 
  *   The internal pull up/down resistors have a value of approximately 50KΩ 
  *   on the Raspberry Pi.
  * @param: int pin, int mode
  * @retval: none
  */
    public void pullUpDnControl (int pin, int pud);

/**
  * @brief:
  *   Writes the value HIGH or LOW (1 or 0) to the given pin which must have 
  *   been previously set as an output.
  * @param: int pin, int value
  * @retval: none
  */
    public void digitalWrite (int pin, int value);

/**
  * @brief:
  *   Writes the value to the PWM register for the given pin. The Raspberry Pi 
  *   has one on-board PWM pin, pin 1 (BMC_GPIO 18, Phys 12) and the range is 
  *   0-1024. Other PWM devices may have other PWM ranges.
  * @param: int pin, int value
  * @retval: none
  */
    public void pwmWrite (int pin, int value);

/**
  * @brief:
  *   This function returns the value read at the given pin. It will be HIGH 
  *   or LOW (1 or 0) depending on the logic level at the pin.
  * @param: int pin
  * @retval: int
  */
    public int digitalRead (int pin);

/**
  * @brief:
  *   This returns the value read on the supplied analog input pin. You will 
  *   need to register additional analog modules to enable this function for 
  *   devices such as the Gertboard, quick2Wire analog board, etc.
  * @param: int pin
  * @retval: int
  */
    public int analogRead (int pin);

/**
  * @brief:
  *   This writes the given value to the supplied analog pin. You will need to 
  *   register additional analog modules to enable this function for devices 
  *   such as the Gertboard.
  * @param: int pin, int value
  * @retval: none
  */
    public void analogWrite (int pin, int value);
}

