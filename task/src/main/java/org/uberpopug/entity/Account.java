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
    public String guid;

    public String login;

    @Column(name = "PARROTNAME")
    public String parrotName;


}
