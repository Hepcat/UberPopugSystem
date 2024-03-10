package org.uberpopug.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "TAKS")
public class Task extends PanacheEntityBase {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TASK_SEQ")
    @SequenceGenerator(name = "TASK_SEQ", sequenceName = "TASK_SEQ", allocationSize = 100)
    public Long id;

    @Column(name = "GUID")
    @GeneratedValue(strategy = GenerationType.UUID)
    public String guid;

    @Column(name = "ASSIGNCOST")
    public BigDecimal assignCost;

    @Column(name = "COMPLETIONPAYOUT")
    public BigDecimal completionPayout;

    @Column(name = "TASKNAME")
    public String taskName;

    @OneToOne
    @JoinColumn(name = "ASSIGNEDTO", referencedColumnName = "id")
    public Account assignedTo;



}
