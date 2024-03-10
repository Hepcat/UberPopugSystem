package org.uberpopug.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.uberpopug.entity.Account;
import org.uberpopug.resource.pojo.account.CreateAccountRequest;
import org.uberpopug.resource.pojo.account.DeleteAccountRequest;
import org.uberpopug.resource.pojo.account.UpdateAccountRequest;
import org.uberpopug.messaging.cud.account.AccountCUDMessage;
import org.uberpopug.messaging.cud.ActionType;

import java.util.concurrent.CompletionStage;

@ApplicationScoped
public class AccountService {

    @Inject
    @Channel("account")
    Emitter<AccountCUDMessage> accountActionEmitter;

    @Transactional
    public void createAccount(CreateAccountRequest request) {
        Account account = new Account();
        account.login = request.getLogin();
        account.password = request.getPassword();
        account.parrotName = request.getParrotName();
        account.persist();

        AccountCUDMessage accountCUDMessage = generateAccountCUDMessage(account, ActionType.CREATE);
        CompletionStage<Void> ack = accountActionEmitter.send(accountCUDMessage);
    }

    @Transactional
    public void updateAccount(UpdateAccountRequest request) {
        Account account = Account.findById(request.getId());
        account.login = request.getLogin();
        account.password = request.getPassword();
        account.parrotName = request.getParrotName();
        account.persist();

        AccountCUDMessage accountCUDMessage = generateAccountCUDMessage(account, ActionType.UPDATE);
        CompletionStage<Void> ack = accountActionEmitter.send(accountCUDMessage);
    }

    @Transactional
    public void deleteAccount(DeleteAccountRequest request) {
        Account account = Account.findById(request.getId());
        account.delete();

        AccountCUDMessage accountCUDMessage = generateAccountCUDMessage(account, ActionType.UPDATE);
        CompletionStage<Void> ack = accountActionEmitter.send(accountCUDMessage);
    }


    private AccountCUDMessage generateAccountCUDMessage(Account account, ActionType action) {
        AccountCUDMessage accountAction = new AccountCUDMessage();
        accountAction.guid = account.guid;
        accountAction.login = account.login;
        accountAction.parrotName = account.parrotName;
        accountAction.actionType = action;
        return accountAction;
    }

}
