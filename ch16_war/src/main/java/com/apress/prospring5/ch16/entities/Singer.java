package com.apress.prospring5.ch16.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "Singer")
public class Singer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Version
    @Column(name = "VERSION")
    private int version;

    @NotEmpty(message = "{validation.firstName.NotEmpty.message}")
    @Size(min = 3, max = 60, message = "{validation.firstName.Size.message}")
    @Column(name = "FIRST_NAME")
    private String firstName;

    @NotEmpty(message = "{validation.lastName.NotEmpty.message}")
    @Size(min = 1, max = 40, message = "{validation.lastName.Size.message}")
    @Column(name = "LAST_NAME")
    private String lastName;

    @Temporal(TemporalType.DATE)
    @Column(name = "BIRTH_DATE")
    private Date birthDate;

    @Column(name = "DESCRIPTION")
    private String description;

    @Basic(fetch = FetchType.LAZY)
    @Lob
    @Column(name = "PHOTO")
    private byte photo;

    @Transient
    public String getBirthDateString() {
        String birthDateString = "";
        if (birthDate != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            birthDateString = dateFormat.format(birthDate);
        }
        return birthDateString;
    }

    @Override
    public String toString() {
        return "Singer{" +
                "id=" + id +
                ", version=" + version +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", description='" + description + '\'' +
                '}';
    }
}
