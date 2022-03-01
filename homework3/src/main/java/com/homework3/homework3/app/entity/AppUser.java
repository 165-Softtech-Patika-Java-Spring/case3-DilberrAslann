package com.homework3.homework3.app.entity;

import com.homework3.homework3.app.enums.UserType;
import com.homework3.homework3.gen.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "APP_USER")
@Getter
@Setter
public class AppUser extends BaseEntity {
    @Id
    @SequenceGenerator(name = "User" , sequenceName = "USER_ID_SEQ")
    @GeneratedValue(generator = "User")
    private Long id;

    @Column(name = "NAME", length = 50, nullable = false)
    private String name;

    @Column(name = "SURNAME", length = 100, nullable = false)
    private String surname;

    @Column(name = "PHONENUMBER", nullable = false, unique = true)
    private Long phoneNumber;

    @Column(name = "EMAIL", nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "USERTYPE", length = 30)
    private UserType userType;
}
