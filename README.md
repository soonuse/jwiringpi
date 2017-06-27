# jwiringpi
Java implements for Raspberry Pi extension GPIO control (GPIO, SPI, I2C, PWM...), based on WiringPi library.

## Description
The jwiringpi project is dedicated to creating an convenient and easy-to-use Java class library for Raspberry Pi extension GPIO control (GPIO, SPI, I2C, PWM...). In fact, this project provides a wrapper for WiringPi library. So in most cases, you can use it just like wiringPi library on Raspberry Pi. Fortunately, WiringPi library is preloaded in the latest Raspbian operating system, and there are no need to do any complex settings.

In addition, the source code (.java and .c) are provided to help users to understand the work of the Java native interface (JNI). You can also easily ontrol the source on Pi by Java implement the corresponding interface or just ontrol the source on Pi by Java.

## How to use
Unlike other Java wrapper for Pi's GPIO control, you can simply use this Java package in Raspbian operating system without complex settings. Just follow these 2 steps:

1.  copy the file libc4jwiringpi.so to the system library.<br />
        `sudo cp libc4jwiringpi.so /usr/lib`
2.  copy the directory jwringpi to your program.
*   Note: if you don't want to copy the library to the /usr/lib, you can specify the library path before running your Java program, like:
         `java -Djava.library.path=. YouProgram`
         (this specify the . directory as the native library path.)

## Demo
1.  copy the file libc4jwiringpi.so to /usr/lib <br />
        `sudo cp libc4jwiringpi.so /usr/lib`
2.  create a java class named RPiGPIODemo.java with:
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
