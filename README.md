# jwiringpi
Java implements for Raspberry Pi extension GPIO control (GPIO, SPI, I2C, PWM...), based on WiringPi library.

## Description
The jwiringpi project is dedicated to creating an convenient and easy-to-use Java class library for Raspberry Pi extension GPIO control (GPIO, SPI, I2C, PWM...). In fact, this project provides a wrapper for WiringPi library. So in most cases, you can use it just like wiringPi library on Raspberry Pi. Fortunately, WiringPi library is preloaded in the latest Raspbian operating system, and there are no need to do any complex settings.

In addition, the source code (.java and .c) are provided to help users to understand the work of the Java native interface (JNI). You can also easily ontrol the source on Pi by Java implement the corresponding interface or just ontrol the source on Pi by Java.

## How to use
Unlike other Java wrapper for Pi's GPIO control, you can simply use this Java package in Raspbian operating system without complex settings. Just follow these 2 steps:

1.  copy the file libc4jwiringpi.so to the system library path e.g. /usr/lib.<br />
        `sudo cp libc4jwiringpi.so /usr/lib`
2.  copy the directory jwringpi to your project.
*   Note: if you don't want to copy the library to the /usr/lib, you can specify the library path before running your Java program, like:
         `java -Djava.library.path=. YourProgram`
         (this specify the . directory as the native library path.)
## Methods jwiringpi provides
You can invode these methods by instantiating JWiringPiController
### Implements JWiringPiSetupInterface
    public native int wiringPiSetup() ;
    public native int wiringPiSetupGpio() ;
    public native int wiringPiSetupPhys() ;
    public native int wiringPiSetupSys() ;

### Implements JWiringPiCoreInterface
    public native void pinMode(int pin, int mode);
    public native void pullUpDnControl(int pin, int pud);
    public native void digitalWrite(int pin, int value);
    public native void pwmWrite(int pin, int value);
    public native int digitalRead(int pin);
    public native int analogRead(int pin);
    public native void analogWrite(int pin, int value); 

### Implements JWiringPiTimingInterface
    public native int millis();
    public native int micros();
    public native void delay(int howLong);
    public native void delayMicroseconds(int howLong);

### Implements JWiringPiSPIInterface
    public native int wiringPiSPISetup(int channel, int speed);
    public native int wiringPiSPIDataRW(int channel, byte[] data, int len);

### Implements JWiringPiI2CInterface
    public native int wiringPiI2CSetup (int devId);
    public native int wiringPiI2CRead (int fd);
    public native int wiringPiI2CWrite (int fd, int data);
    public native int wiringPiI2CWrite (int fd, byte[] data, int length);
    public native int wiringPiI2CWriteReg8 (int fd, int reg, int data);
    public native int wiringPiI2CWriteReg16 (int fd, int reg, int data);
    public native int wiringPiI2CReadReg8 (int fd, int reg);
    public native int wiringPiI2CReadReg16 (int fd, int reg);
## Getting started
A demo for getting started.
1.  copy the file libc4jwiringpi.so to /usr/lib <br />
        `sudo cp libc4jwiringpi.so /usr/lib`
2.  save the following as RPiGPIODemo.java:
<pre>
import jwiringpi.*;

public class RPiGPIODemo {
    public static void main(String[] args) {
        JWiringPiController c = new JWiringPiController();
        if (c.wiringPiSetup() < 0) {
            System.out.println("WiringPi setup error");
            return;
        }
        c.pinMode(25, c.OUTPUT);
        while(true) {
            c.digitalWrite(25, c.HIGH);
            c.delay(1000);
            c.digitalWrite(25, c.LOW);
            c.delay(1000);
        }
    }
}
</pre>
3.  copy the directory jwringpi to the directory of RPiGPIODemo.java
4.  compile the program with
        `javac RPiGPIODemo.java`
5.  run the program
        `java RPiGPIODemo`
6.  expected result: the voltage level of pin 25 will be toggled continuously.
*   Note: the pins are named by WiringPi pin mapping. The pin25 in WiringPi is the pin37 in physical.
<pre>
## Pin mapping of Raspberry Pi 3 Model B
 +-----+-----+---------+------+---+---Pi 3---+---+------+---------+-----+-----+
 | BCM | wPi |   Name  | Mode | V | Physical | V | Mode | Name    | wPi | BCM |
 +-----+-----+---------+------+---+----++----+---+------+---------+-----+-----+
 |     |     |    3.3v |      |   |  1 || 2  |   |      | 5v      |     |     |
 |   2 |   8 |   SDA.1 | ALT0 | 1 |  3 || 4  |   |      | 5v      |     |     |
 |   3 |   9 |   SCL.1 | ALT0 | 1 |  5 || 6  |   |      | 0v      |     |     |
 |   4 |   7 | GPIO. 7 |   IN | 1 |  7 || 8  | 1 | ALT5 | TxD     | 15  | 14  |
 |     |     |      0v |      |   |  9 || 10 | 1 | ALT5 | RxD     | 16  | 15  |
 |  17 |   0 | GPIO. 0 |   IN | 0 | 11 || 12 | 0 | IN   | GPIO. 1 | 1   | 18  |
 |  27 |   2 | GPIO. 2 |   IN | 0 | 13 || 14 |   |      | 0v      |     |     |
 |  22 |   3 | GPIO. 3 |   IN | 0 | 15 || 16 | 0 | IN   | GPIO. 4 | 4   | 23  |
 |     |     |    3.3v |      |   | 17 || 18 | 0 | IN   | GPIO. 5 | 5   | 24  |
 |  10 |  12 |    MOSI | ALT0 | 0 | 19 || 20 |   |      | 0v      |     |     |
 |   9 |  13 |    MISO | ALT0 | 0 | 21 || 22 | 0 | IN   | GPIO. 6 | 6   | 25  |
 |  11 |  14 |    SCLK | ALT0 | 0 | 23 || 24 | 1 | OUT  | CE0     | 10  | 8   |
 |     |     |      0v |      |   | 25 || 26 | 1 | OUT  | CE1     | 11  | 7   |
 |   0 |  30 |   SDA.0 |   IN | 1 | 27 || 28 | 1 | IN   | SCL.0   | 31  | 1   |
 |   5 |  21 | GPIO.21 |   IN | 1 | 29 || 30 |   |      | 0v      |     |     |
 |   6 |  22 | GPIO.22 |   IN | 1 | 31 || 32 | 0 | IN   | GPIO.26 | 26  | 12  |
 |  13 |  23 | GPIO.23 |   IN | 0 | 33 || 34 |   |      | 0v      |     |     |
 |  19 |  24 | GPIO.24 |  OUT | 1 | 35 || 36 | 1 | OUT  | GPIO.27 | 27  | 16  |
 |  26 |  25 | GPIO.25 |   IN | 0 | 37 || 38 | 0 | IN   | GPIO.28 | 28  | 20  |
 |     |     |      0v |      |   | 39 || 40 | 0 | IN   | GPIO.29 | 29  | 21  |
 +-----+-----+---------+------+---+----++----+---+------+---------+-----+-----+
 | BCM | wPi |   Name  | Mode | V | Physical | V | Mode | Name    | wPi | BCM |
 +-----+-----+---------+------+---+---Pi 3---+---+------+---------+-----+-----+
</pre>
