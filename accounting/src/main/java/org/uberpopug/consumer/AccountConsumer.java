package org.uberpopug.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.uberpopug.SchemaRegistry;
import org.uberpopug.entity.Account;
import org.uberpopug.event.Event;
import org.uberpopug.exception.ValidationException;
import org.uberpopug.messaging.cud.account.AccountCUDMessage;

@ApplicationScoped
public class AccountConsumer {

    @Incoming("account")
    public void consume(Event event) throws ValidationException, JsonProcessingException {
        SchemaRegistry.validate(event);
        ObjectMapper mapper = new ObjectMapper();
        AccountCUDMessage accountCUDMessage = mapper.readValue(event.getData().toString(), AccountCUDMessage.class);

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
