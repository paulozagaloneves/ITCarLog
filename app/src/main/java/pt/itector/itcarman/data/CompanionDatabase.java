package pt.itector.itcarman.data;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


import pt.itector.itcarman.models.BalanceHistoryItem;
import pt.itector.itcarman.models.MovementItem;
import pt.itector.itcarman.utils.Constants;

/**
 * Created by me on 31/07/2019
 */

@Database(entities = {MovementItem.class, BalanceHistoryItem.class}, version = 1, exportSchema = false)
public abstract class CompanionDatabase extends RoomDatabase {
    private static CompanionDatabase sDBInstance;

    public abstract BalanceHistoryDao getBalanceHistoryDao();

    public abstract MovementDao getMovementDao();

    public static CompanionDatabase getCompanionDatabase(Context context){
        if(sDBInstance == null)
            initializeDB(context);

        return sDBInstance;
    }

    private static void initializeDB(Context context) {
        sDBInstance = Room.databaseBuilder(context.getApplicationContext(), CompanionDatabase.class, Constants.DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build();
    }

    public static void destroyInstance(){
        sDBInstance = null;
    }
}
