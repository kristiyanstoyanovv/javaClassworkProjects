package uni.fmi.inf.java.homework;
/**
 * Клас реализиращ изпринтирането на
 */
public class HeatManager {
    public HeatManager(Machine heatMachine, Machine wrapperMachine) {
        System.out.printf("Maximum power of this heat machine is %d \n", heatMachine.getMachinePower());
        System.out.printf("Maximum power of this wrapper machine is %d\n", wrapperMachine.getMachinePower());
    }
}
