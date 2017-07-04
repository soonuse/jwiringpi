# SSD1306 OLED (0.96inch OLED) demo
This demo is written in Java and tested with a Waveshare SSD1306 0.96inch OLED module working on Raspberry Pi 3 Model B.
Before running this demo, you have to enable the SPI interface on the Raspberry Pi. See `sudo raspi-config`
## How to use
1.  Hardware connection: 
    OLED   =>    Raspberry Pi
    * VCC    ->    3.3
    * GND    ->    GND
    * DIN    ->    MOSI
    * CLK    ->    SCLK
    * CS     ->    24 (Physical, BCM: CE0, 8)
    * D/C    ->    36 (Physical, BCM: 16)
    * RES    ->    35 (Physical, BCM: 19)
Please be ensure that the OLED is set to SPI mode but not I2C mode, and the SPI interface is enabled on your Pi.
2.  copy the file jwiringpi-native/libc4jwiringpi.so to the system library path e.g. /usr/lib.<br />
        `sudo cp libc4jwiringpi.so /usr/lib`
3.  copy the directory jwiringpi to the directory of this project (i.e. ssd1306spi).<br />
        `cp -rf jwiringpi ssd1306spi`
4.  run with<br />
        `java SSD1306SPIDemo`
*   Note: if you don't want to copy the library to the /usr/lib, you can specify the library path before running your Java program, like:
         `java -Djava.library.path=. RPiGPIODemo`
         this specify the . directory as the native library (libc4jwiringpi.so) path.
## Expected result
The OLED displays strings and images.
## Raspberry Pi pin mapping
<pre>
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
