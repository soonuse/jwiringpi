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
