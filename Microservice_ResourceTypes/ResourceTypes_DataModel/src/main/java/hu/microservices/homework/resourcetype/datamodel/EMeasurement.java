package hu.microservices.homework.resourcetype.datamodel;

public enum EMeasurement {
    G,
    L,
    M,
    DB,
    M2,
    M3;

    static EMeasurement parseString(String measurementString) {
        switch (measurementString.toUpperCase()) {
            case "G":
                return EMeasurement.G;
            case "L":
                return EMeasurement.L;
            case "M":
                return EMeasurement.M;
            case "DB":
                return EMeasurement.DB;
            case "M2":
                return EMeasurement.M2;
            case "M3":
                return EMeasurement.M3;
        }
        return null;
    }
}
