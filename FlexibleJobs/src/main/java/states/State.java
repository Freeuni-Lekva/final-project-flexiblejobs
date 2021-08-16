package states;

import accounts.Account;
import accounts.Message;

import java.util.List;

public class State {
    private Account loggedUser;
    private Account userRegistering;
    private List<String> error;
    private List<Account> contacts;
    private Account conversationWith;
    private List<Message> conversation;
    private boolean chatStarted;
    private boolean chatOpened;
    private boolean reloadForChatPartner;
    private boolean searchedByFilter;
    private String searchKeyword;

    public State(Account loggedUser, Account userRegistering, List<String> error, List<Account> contacts, Account conversationWith, List<Message> conversation, boolean chatStarted, boolean chatOpened, boolean reloadForChatPartner, boolean searchedByFilter, String searchKeyword) {
        this.loggedUser = loggedUser;
        this.userRegistering = userRegistering;
        this.error = error;
        this.contacts = contacts;
        this.conversationWith = conversationWith;
        this.conversation = conversation;
        this.chatStarted = chatStarted;
        this.chatOpened = chatOpened;
        this.reloadForChatPartner = reloadForChatPartner;
        this.searchedByFilter = searchedByFilter;
        this.searchKeyword = searchKeyword;
    }

    public boolean isSearchedByFilter() {
        return searchedByFilter;
    }

    public void setSearchedByFilter(boolean searchedByFilter) {
        this.searchedByFilter = searchedByFilter;
    }

    public String getSearchKeyword() {
        return searchKeyword;
    }

    public void setSearchKeyword(String searchKeyword) {
        this.searchKeyword = searchKeyword;
    }

    public boolean isReloadForChatPartner() {
        return reloadForChatPartner;
    }

    public void setReloadForChatPartner(boolean reloadForChatPartner) {
        this.reloadForChatPartner = reloadForChatPartner;
    }

    public List<Message> getConversation() {
        return conversation;
    }

    public void setConversation(List<Message> conversation) {
        this.conversation = conversation;
    }

    public Account getConversationWith() {
        return conversationWith;
    }

    public void setConversationWith(Account conversationWith) {
        this.conversationWith = conversationWith;
    }

    public boolean isChatStarted() {
        return chatStarted;
    }

    public void setChatStarted(boolean chatStarted) {
        this.chatStarted = chatStarted;
    }

    public boolean isChatOpened() {
        return chatOpened;
    }

    public void setChatOpened(boolean chatOpened) {
        this.chatOpened = chatOpened;
    }

    public List<Account> getContacts() {
        return contacts;
    }

    public void setContacts(List<Account> contacts) {
        this.contacts = contacts;
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

    public List<String> getError() {
        return error;
    }

    public void setError(List<String> error) {
        this.error = error;
    }
}
