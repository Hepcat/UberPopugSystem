package org.uberpopug.service;

import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;
import org.uberpopug.entity.Account;
import org.uberpopug.resource.pojo.authorization.TokenRequest;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@ApplicationScoped
public class AuthorizationService {

    public String getToken(TokenRequest tokenRequest) {
        Optional<Account> account = Account.find("login", tokenRequest.getLogin()).firstResultOptional();
        if (account.isPresent()) {
            if (tokenRequest.getPassword().equals(account.get().password)) {
                String token = Jwt.issuer("https://uberpopug.org/issuer")
                        .upn(account.get().login)
                        .groups(getGroups(account.get()))
                        .sign();
                return token;
            }
        }
        return null;
    }

    private Set<String> getGroups(Account account) {
        return account.roleList.stream().map(role -> role.sysName).collect(Collectors.toSet());
    }
}
