package com.gokhand.roomwebapp.models;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name="EMPLOYEE")
public class Staff {
    @Id
    @Column(name="EMPLOYEE_ID")
    private String id;
    @Column(name="FIRST_NAME")
    private String firstName;
    @Column(name="LAST_NAME")
    private String lastName;
    @Enumerated(EnumType.STRING)
    @Column(name="POSITION")
    private Position position;

    public Staff(String id,
                 String firstName,
                 String lastName,
                 Position position) {

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
    }

    public Staff() {
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
