package app;

import java.util.BitSet;

import com.pi4j.io.gpio.RaspiPin;
import de.pi3g.pi.rcswitch.RCSwitch;


public class Main {

    private RCSwitch rcSwitch;

    public static void main(String[] args) throws InterruptedException {
        Main piTransformBean = new Main();

        piTransformBean.sendSignal(Integer.parseInt(args[0]), Integer.parseInt(args[1]), args[2]);

    }

    public Main() {
        rcSwitch = new RCSwitch(RaspiPin.GPIO_17);
    }

    //Here we transform the message to the appropriate signal ON/OFF
    //If build failed = ON signal
    //If build cleared = OFF signal
    public void sendSignal(int onCode, int offCode, String unitAddress) throws InterruptedException {


        BitSet address = RCSwitch.getSwitchGroupAddress(unitAddress);
        rcSwitch = new RCSwitch(RaspiPin.GPIO_00);
        rcSwitch.switchOn(address, onCode);
        Thread.sleep(2000); // let it run for 20 seconds
        rcSwitch.switchOff(address, offCode);


    }
}
