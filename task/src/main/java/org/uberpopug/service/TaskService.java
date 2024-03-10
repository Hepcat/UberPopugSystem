package org.uberpopug.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.uberpopug.entity.Account;
import org.uberpopug.entity.Task;
import org.uberpopug.entity.TaskStatus;
import org.uberpopug.messaging.cud.ActionType;
import org.uberpopug.messaging.cud.task.TaskCUDMessage;
import org.uberpopug.resource.pojo.task.CreateTaskRequest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletionStage;

@ApplicationScoped
public class TaskService {

    @Inject
    @Channel("task")
    Emitter<TaskCUDMessage> TaskActionEmitter;

    @Transactional
    public void createTask(CreateTaskRequest request) {
        Task task = new Task();
        task.assignCost = getRandomCost();
        task.completionPayout = getRandomPayout();
        task.assignedTo = getRandomAccount();
        task.taskName = request.getTaskName();
        task.status = TaskStatus.ASSIGNED;
        task.persist();

        TaskCUDMessage TaskCUDMessage = generateTaskCUDMessage(task, ActionType.CREATE);
        CompletionStage<Void> ack = TaskActionEmitter.send(TaskCUDMessage);
    }

    private Account getRandomAccount() {
        List<Account> accountList = Account.listAll();
        Random r = new Random();
        return accountList.get(r.nextInt(accountList.size()));
    }

    private BigDecimal getRandomPayout() {
        //todo
        return new BigDecimal(33.7);
    }

    private BigDecimal getRandomCost() {
        //todo
        return new BigDecimal(15.65);
    }

    private TaskCUDMessage generateTaskCUDMessage(Task task, ActionType action) {
        TaskCUDMessage taskAction = new TaskCUDMessage();
        taskAction.guid = task.guid;
        taskAction.taskName = task.taskName;
        taskAction.assignCost = task.assignCost;
        taskAction.completionPayout = task.completionPayout;
        taskAction.assignedTo = task.assignedTo.guid;
        taskAction.status = task.status;
        taskAction.actionType = action;
        return taskAction;
    }

}
