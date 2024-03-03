package org.uberpopug.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "ACCOUNT")
public class Account extends PanacheEntityBase {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ACCOUNT_SEQ")
    @SequenceGenerator(name = "ACCOUNT_SEQ", sequenceName = "ACCOUNT_SEQ", allocationSize = 100)
    public Long id;

    @Column(name = "GUID")
    @GeneratedValue(strategy = GenerationType.UUID)
    public String guid;

    public String login;

    public String password;

    @Column(name = "PARROTNAME")
    public String parrotName;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "ACCOUNTROLE",
            joinColumns = { @JoinColumn(name = "ACCOUNTID") },
            inverseJoinColumns = { @JoinColumn(name = "ROLEID") }
    )
    public List<Role> roleList;



}
