//CM415026

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;

@Database(entities = {USER.class}, version = 1)
public abstract class TravelDatabase extends RoomDatabase{

    public abstract UserDAO userDAO();

    private static TravelDatabase INSTANCE;

    public static TravelDatabase getInstance(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(
                    context.getApplicationContext(),
                    TravelDatabase.class,
                    "travel_database"
            ).allowMainThreadQueries().build();
        }
        return INSTANCE;
    }
}
