//CM415026

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import java.util.List;

@Dao
public interface UserDAO {

    //FOR USERS
    @Insert(onConflict = OnConflictStrategy.ABORT)
    void insertUser(USER user);
    @Query("SELECT * FROM Users WHERE username = :loggedInUser")
    String getLoggedInUser(String loggedInUser); //use for local>database>local transfer
    @Query("SELECT * FROM Users")
    List<USER> getAllUsers(); //lists all users admin only
    @Query("DELETE FROM Users WHERE username = :username")
    void deleteUser(String username); //DELETE SINGLE USER ADMIN ONLY
    @Query("DELETE FROM Users")
    void deleteAll(); //DELETE ALL USERS DO NOT USE DEV SIDE ONLY
}