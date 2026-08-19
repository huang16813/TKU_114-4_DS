abstract class Device {
    private String deviceId;

    Device(String deviceId) {
        this.deviceId = deviceId;
    }

    String getDeviceId() {
        return deviceId;
    }

    abstract void runDiagnostic();
}

class Laptop extends Device {
    Laptop(String deviceId) {
        super(deviceId);
    }

    @Override
    void runDiagnostic() {
        System.out.println(getDeviceId() + " Laptop diagnostic: battery/CPU OK");
    }
}

class Printer extends Device {
    Printer(String deviceId) {
        super(deviceId);
    }

    @Override
    void runDiagnostic() {
        System.out.println(getDeviceId() + " Printer diagnostic: paper/ink OK");
    }

    void cleanPrintHead() {
        System.out.println(getDeviceId() + " cleaning print head...");
    }
}

class Router extends Device {
    Router(String deviceId) {
        super(deviceId);
    }

    @Override
    void runDiagnostic() {
        System.out.println(getDeviceId() + " Router diagnostic: signal OK");
    }
}

public class DeviceInspectionSystem {
    public static void main(String[] args) {
        Device[] devices = {
            new Laptop("L1"),
            new Printer("P1"),
            new Router("R1")
        };

        for (Device device : devices) {
            device.runDiagnostic();
            if (device instanceof Printer printer) {
                printer.cleanPrintHead();
            }
        }
    }
}
