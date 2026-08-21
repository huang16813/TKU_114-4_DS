import java.util.ArrayDeque;
import java.util.Deque;

class Patient {
    private final String id;
    private final String name;
    private final boolean emergency;

    Patient(String id, String name, boolean emergency) {
        this.id = id;
        this.name = name;
        this.emergency = emergency;
    }

    boolean isEmergency() {
        return emergency;
    }

    @Override
    public String toString() {
        return id + " " + name + (emergency ? " [emergency]" : "");
    }
}

public class ClinicQueueSystem {
    private final Deque<Patient> emergencyQueue = new ArrayDeque<>();
    private final Deque<Patient> generalQueue = new ArrayDeque<>();

    void registerGeneral(Patient patient) {
        generalQueue.offerLast(patient);
    }

    void registerEmergency(Patient patient) {
        emergencyQueue.offerLast(patient);
    }

    Patient peekNext() {
        return emergencyQueue.isEmpty() ? generalQueue.peekFirst() : emergencyQueue.peekFirst();
    }

    Patient serveNext() {
        if (!emergencyQueue.isEmpty()) {
            return emergencyQueue.pollFirst();
        }
        return generalQueue.pollFirst();
    }

    int waitingCount() {
        return emergencyQueue.size() + generalQueue.size();
    }

    public static void main(String[] args) {
        ClinicQueueSystem clinic = new ClinicQueueSystem();

        clinic.registerGeneral(new Patient("P001", "Amy", false));
        clinic.registerGeneral(new Patient("P002", "Ben", false));
        clinic.registerEmergency(new Patient("P003", "Cara", true));
        clinic.registerGeneral(new Patient("P004", "Dan", false));
        clinic.registerEmergency(new Patient("P005", "Eva", true));

        System.out.println("等待人數：" + clinic.waitingCount());
        System.out.println("下一位：" + clinic.peekNext());

        System.out.println("看診：" + clinic.serveNext());
        System.out.println("看診：" + clinic.serveNext());
        System.out.println("等待人數：" + clinic.waitingCount());

        System.out.println("看診：" + clinic.serveNext());
        System.out.println("看診：" + clinic.serveNext());
        System.out.println("看診：" + clinic.serveNext());
        System.out.println("等待人數：" + clinic.waitingCount());
    }
}
