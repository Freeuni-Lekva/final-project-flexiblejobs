package states;

import accounts.Account;

public class EmployeeState {
    private Account loggedUser;

    public EmployeeState(Account loggedUser) {
        this.loggedUser = loggedUser;
    }

    public Account getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(Account loggedUser) {
        this.loggedUser = loggedUser;
    }
}
