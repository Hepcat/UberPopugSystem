package org.uberpopug.messaging.cud.task;

import org.uberpopug.entity.TaskStatus;
import org.uberpopug.messaging.cud.ActionType;

import java.math.BigDecimal;

public class TaskCUDMessage {

    public String guid;

    public String taskName;

    public String assignedTo;

    public BigDecimal assignCost;

    public BigDecimal completionPayout;

    public TaskStatus status;

    public ActionType actionType;

}
