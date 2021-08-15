package states;

import accounts.Account;

public class State {
    private Account loggedUser;
    private Account userRegistering;

    public State(Account loggedUser, Account userRegistering) {
        this.loggedUser = loggedUser;
        this.userRegistering = userRegistering;
    }

    public Account getUserRegistering() {
        return userRegistering;
    }

    public void setUserRegistering(Account userRegistering) {
        this.userRegistering = userRegistering;
    }

    public Account getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(Account loggedUser) {
        this.loggedUser = loggedUser;
    }
}
