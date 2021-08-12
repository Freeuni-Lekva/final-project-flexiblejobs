package states;

import accounts.Account;

public class State {
    private Account loggedUser;

    public State(Account loggedUser) {
        this.loggedUser = loggedUser;
    }

    public Account getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(Account loggedUser) {
        this.loggedUser = loggedUser;
    }
}
