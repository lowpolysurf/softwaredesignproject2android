//CM0415026

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ForeignKey;

@Entity(tableName = "Trips",
        primaryKeys = {"username","tripName"},
        foreignKeys = {
            @ForeignKey(entity = USER.class,
                    parentColumns = "username",
                    childColumns = "username")
            }
        )
public class TRIPS{

    //primary keys
    public String username;
    public String tripName;

    public int startDate;
    public int endDate;
}