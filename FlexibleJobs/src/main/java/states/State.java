package states;

import accounts.Account;

import java.util.ArrayList;

public class State {
    private Account loggedUser;
    private Account userRegistering;
    private ArrayList<String> error;

    public State(Account loggedUser, Account userRegistering, ArrayList<String> error) {
        this.loggedUser = loggedUser;
        this.userRegistering = userRegistering;
        this.error = error;
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

    public ArrayList<String> getError() {
        return error;
    }

    public void setError(ArrayList<String> error) {
        this.error = error;
    }
}
