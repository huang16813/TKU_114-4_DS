abstract class Transport {
    private String routeName;

    Transport(String routeName) {
        this.routeName = routeName;
    }

    String getRouteName() {
        return routeName;
    }

    abstract int calculateFare(int distance);
}

class Bus extends Transport {
    Bus(String routeName) {
        super(routeName);
    }

    @Override
    int calculateFare(int distance) {
        return Math.max(15, distance * 2);
    }
}

class Taxi extends Transport {
    Taxi(String routeName) {
        super(routeName);
    }

    @Override
    int calculateFare(int distance) {
        return 85 + distance * 20;
    }
}

public class TransportFareSystem {
    public static void main(String[] args) {
        Transport[] transports = {
            new Bus("Route 5"),
            new Taxi("Taxi A")
        };
        int[] distances = {12, 8};

        for (int i = 0; i < transports.length; i++) {
            Transport transport = transports[i];
            System.out.println(transport.getRouteName() + " fare="
                    + transport.calculateFare(distances[i]));
        }
    }
}
