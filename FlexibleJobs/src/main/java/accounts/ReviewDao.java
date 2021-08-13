package accounts;

import servlets.FlexibleJobsConstants;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReviewDao {
    private static DataSource dataSource;

    public ReviewDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addReview(Review review) {
        PreparedStatement stm;
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            stm = connection.prepareStatement(
                    "INSERT INTO reviews (fromuser, touser, points,jobid)" +
                            "VALUES (?, ?, ?, ?);");
            stm.setString(1, review.getFrom());
            stm.setString(2, review.getTo());
            stm.setInt(3, review.getPoints());
            stm.setInt(4, review.getJobid());
            stm.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }

    public List<Review> selectByEmployee(String username) {
        Connection connection=null;
        List<Review> result = new ArrayList<>();
        try {
            connection=dataSource.getConnection();
            PreparedStatement stm = connection.prepareStatement(
                    "SELECT jobid FROM reviews WHERE fromuser=?;");
            stm.setString(1,username);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                String from = rs.getString(1);
                String to = rs.getString(2);
                int points = rs.getInt(3);
                int jobid = rs.getInt(4);
                Review rev = new Review(from, to, points, jobid);
                result.add(rev);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if(connection!=null) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        return result;
    }

    public double averageReview(String username) {
        List<Review> reviews = selectByEmployee(username);
        double sum = 0;
        for (int i = 0; i < reviews.size(); i++) {
            sum += reviews.get(i).getPoints();
        }
        return sum/reviews.size();
    }
}
