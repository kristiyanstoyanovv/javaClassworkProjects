package uni.fmi.inf.java.homework;

import java.util.Random;

/**
 * Главен клас определящ характиристиките на една машина, той се онаследяваа от другите.
 * Машина -> Двигател -> Двигател за топлина -> Двигател за опаковане.
 */

public class Machine {
    protected int machinePower;
    protected int machineHeight;
    protected String machineColor;
    protected String [] Colors = new String [] {"Red", "White", "Black", "Silver"};
    protected Random randomGenerator = new Random();

    public Machine() {
        machinePower = randomGenerator.nextInt(2000);
        machineHeight = randomGenerator.nextInt(300);
        machineColor = Colors[randomGenerator.nextInt(4)];
    }

    public int getMachinePower() { return machinePower; }
    public int getMachineHeight() { return machineHeight; }
    public String getMachineColor() { return machineColor; }

    public void setMachinePower(int newPower) { machinePower = newPower; }
    public void setMachineHeight(int newHeight) { machineHeight = newHeight; }
    public void setMachineColor (String newColor) { machineColor = newColor; }
}
