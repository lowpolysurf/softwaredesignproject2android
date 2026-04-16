//CM415026

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import java.util.List;

@Dao
public interface UserDAO {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    void insert(USER user);

    @Query("SELECT * FROM users")
    List<USER> getAllUsers();

    @Query("DELETE FROM users")
    void deleteAll();
}