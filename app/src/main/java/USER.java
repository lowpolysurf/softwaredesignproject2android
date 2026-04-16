//CM0415026

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Users")
public class USER{
    @PrimaryKey
    public String username;

    public String password;
    public int isAdmin;
}