package accounts;

import javax.sql.DataSource;

public class ReviewDao {
    private static DataSource dataSource;

    public ReviewDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
