package entities.airport;

import entities.planes.ExperimentalPlane;
import models.MilitaryType;
import entities.planes.MilitaryPlane;
import entities.planes.PassengerPlane;
import entities.planes.Plane;

import java.util.*;
import java.util.stream.Collectors;

public class Airport {
    private final List<? extends Plane> planes;

    public Airport(List<? extends Plane> planes) {
        this.planes = planes;
    }

    public <T extends Plane> List<T> getPlanesByType(Class<T> planeType) {
        return (List<T>) planes.stream().filter(planeType::isInstance).collect(Collectors.toList());
    }

    public List<PassengerPlane> getPassengerPlanes() {
        return getPlanesByType(PassengerPlane.class);
    }

    public List<MilitaryPlane> getMilitaryPlanes() {
        return getPlanesByType(MilitaryPlane.class);
    }

    public List<ExperimentalPlane> getExperimentalPlanes() {
        return getPlanesByType(ExperimentalPlane.class);
    }

    public PassengerPlane getPassengerPlaneWithMaxPassengersCapacity() {
        List<PassengerPlane> passengerPlanes = getPassengerPlanes();
        PassengerPlane planeWithMaxCapacity = passengerPlanes.get(0);

        for (PassengerPlane passengerPlane : passengerPlanes) {
            if (passengerPlane.getPassengersCapacity() > planeWithMaxCapacity.getPassengersCapacity()) {
                planeWithMaxCapacity = passengerPlane;
            }
        }

        return planeWithMaxCapacity;
    }

    public List<MilitaryPlane> getMilitaryPlanesByType(MilitaryType militaryType) {
        List<MilitaryPlane> transportMilitaryPlanes = new ArrayList<>();

        for (MilitaryPlane plane : getMilitaryPlanes()) {
            if (plane.getType() == militaryType) {
                transportMilitaryPlanes.add(plane);
            }
        }

        return transportMilitaryPlanes;
    }

    public List<MilitaryPlane> getTransportMilitaryPlanes() {
        return getMilitaryPlanesByType(MilitaryType.TRANSPORT);
    }

    public List<MilitaryPlane> getBomberMilitaryPlanes() {
        return getMilitaryPlanesByType(MilitaryType.BOMBER);
    }

    public Airport sortByMaxDistance() {
        planes.sort(Comparator.comparingInt(Plane::Get_Max_Flight_Distance));

        return this;
    }

    public Airport sortByMaxSpeed() {
        planes.sort(Comparator.comparingInt(Plane::getMS));

        return this;
    }

    public Airport sortByMaxLoadCapacity() {
        planes.sort(Comparator.comparingInt(Plane::getMinLoadCapacity));

        return this;
    }

    public List<? extends Plane> getPlanes() {
        return planes;
    }

    @Override
    public String toString() {
        return "entities.airport.Airport{" +
                "Planes=" + planes.toString() +
                '}';
    }
}
