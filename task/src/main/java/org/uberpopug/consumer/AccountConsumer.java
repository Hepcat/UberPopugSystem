package org.uberpopug.consumer;

import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.uberpopug.entity.Account;
import org.uberpopug.messaging.cud.account.AccountCUDMessage;

@ApplicationScoped
public class AccountConsumer {

    @Incoming("account")
    public void consume(AccountCUDMessage accountCUDMessage) {
        switch (accountCUDMessage.actionType) {
            case CREATE:
                Account account = new Account();
                account.login = accountCUDMessage.login;
                account.guid = accountCUDMessage.guid;
                account.parrotName = accountCUDMessage.parrotName;
                account.persist();
                break;
            case UPDATE:
                Account accountToUpdate = Account.find("guid", accountCUDMessage.guid).firstResult();
                accountToUpdate.login = accountCUDMessage.login;
                accountToUpdate.parrotName = accountCUDMessage.parrotName;
                accountToUpdate.persist();
                break;
            case DELETE:
                Account accountToDelete = Account.find("guid", accountCUDMessage.guid).firstResult();
                accountToDelete.delete();
                break;
        }
    }

}
