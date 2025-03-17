package net.mahtabalam;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PostRemove;
import jakarta.persistence.PostLoad;

import java.time.LocalDateTime;

public class EmployeeListener {

    @PrePersist
    public void prePersist(Employee employee) {
        // Audit: Log creation event and ensure lastUpdated is set
        employee.setLastUpdated(LocalDateTime.now());
        System.out.println("Listener Audit: PrePersist : Preparing to create student record for '" + employee.getName() + "'");
    }

    @PostPersist
    public void postPersist(Employee employee) {
        // Audit: Confirm successful persistence with ID
        System.out.println("Listener Audit: PostPersist : Employee '" + employee.getName() + "' persisted with ID: " + employee.getId());
    }

    @PreUpdate
    public void preUpdate(Employee employee) {
        // Audit: Update timestamp and log update event
        employee.setLastUpdated(LocalDateTime.now());
        System.out.println("Listener Audit: PreUpdate : Preparing to update employee '" + employee.getName() + "'");
    }

    @PostUpdate
    public void postUpdate(Employee employee) {
        // Audit: Confirm successful update
        System.out.println("Listener Audit: PostUpdate : Employee '" + employee.getName() + "' successfully updated");
    }

    @PreRemove
    public void preRemove(Employee employee) {
        // Audit: Log removal intent
        System.out.println("Listener Audit: PreRemove : Preparing to remove employee '" + employee.getName() + "' with ID: " + employee.getId());
    }

    @PostRemove
    public void postRemove(Employee employee) {
        // Audit: Confirm successful removal
        System.out.println("Listener Audit: PostRemove : Employee '" + employee.getName() + "' successfully removed");
    }

    @PostLoad
    public void postLoad(Employee employee) {
        // Audit: Log when entity is loaded from database
        System.out.println("Listener Audit: PostLoad : Employee '" + employee.getName() + "' loaded from database with last update: " + employee.getLastUpdated());
    }

}
