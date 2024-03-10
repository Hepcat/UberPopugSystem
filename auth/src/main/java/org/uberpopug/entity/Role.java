package org.uberpopug.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "ROLE")
public class Role {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ROLE_SEQ")
    @SequenceGenerator(name = "ROLE_SEQ", sequenceName = "ROLE_SEQ", allocationSize = 100)
    public Long id;

    @Column(name = "SYSNAME")
    public String sysName;

    @Column(name = "NAME")
    public String name;



}
