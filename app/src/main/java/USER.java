//CM0415026

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ForeignKey;

@Entity(tableName = "Users")
public class USER{
    @PrimaryKey
    public String username;

    public String password;
    public int isAdmin;
}