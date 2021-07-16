package accounts;

import jobs.Job;

import java.sql.Connection;
import java.util.List;

public class AccountDao {

    private Connection connection;

    public AccountDao (Connection connection){
        this.connection=connection;
    }

    public void addAccount(Account acc){}

    public List<Account> selectAll(){
        return null;
    }

    public void delete(String username){}

    public List<Account> SelectBySkills(Job job){
        return null;
    }

}
