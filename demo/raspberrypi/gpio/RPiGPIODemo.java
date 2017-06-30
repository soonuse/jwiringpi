/**
  * @filename       : RPiGPIODemo.java
  * @date           : June 28 2017
  * @author         : soonuse from Github
  * @description:
  *   This demo is written in Java and tested on Raspberry Pi 3 Model B.
  *
  *   Expected result:
  *   The LED state of pin 25 will be toggled continuously.
  ***********************************************************************
  * This file is a demo of GPIO control on Raspberry Pi.
  *
  * This program is distributed in the hope that it will be useful,
  * but WITHOUT ANY WARRANTY; without even the implied warranty of
  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  * GNU Lesser General Public License for more details.
  *
  * You should have received a copy of the GNU Lesser General Public License
  * along with wiringPi.  If not, see <http://www.gnu.org/licenses/>.
  ***********************************************************************
 */

import jwiringpi.*;

public class RPiGPIODemo {
    public static final int HIGH = 1;
    public static final int LOW = 0;
    public static final int OUTPUT = 1;

    public static void main(String[] args) {
        JWiringPiController gpio = new JWiringPiController();
        if (gpio.wiringPiSetup() < 0) {
            System.out.println("WiringPi setup error");
            return;
        }
        gpio.pinMode(25, OUTPUT);
        while(true) {
            gpio.digitalWrite(25, HIGH);
            gpio.delay(1000);
            gpio.digitalWrite(25, LOW);
            gpio.delay(1000);
        }
    }
}
