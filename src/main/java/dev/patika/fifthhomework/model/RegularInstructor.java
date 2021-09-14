package dev.patika.fifthhomework.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor()
@NoArgsConstructor
public class RegularInstructor extends Instructor {
    private double constantSalary;

    public RegularInstructor(String name, String address, long phoneNumber, double constantSalary) {
        super(name, address, phoneNumber);
        this.constantSalary = constantSalary;
    }
}
