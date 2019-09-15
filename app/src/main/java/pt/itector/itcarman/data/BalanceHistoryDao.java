package pt.itector.itcarman.data;



import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import pt.itector.itcarman.models.BalanceHistoryItem;

/**
 * Created by me on 31/07/2019
 */

@Dao
public interface BalanceHistoryDao {

    @Query("SELECT * from balance_history")
    LiveData<List<BalanceHistoryItem>> getBalanceHistory();

    @Query("SELECT * from balance_history WHERE month = :month AND year = :year")
    BalanceHistoryItem getBalanceHistoryItemByMonthAndYear(int month, int year);

    @Insert
    void insertMovement(BalanceHistoryItem item);

    @Update
    void updateMovement(BalanceHistoryItem item);

    @Delete
    void deleteMovement(BalanceHistoryItem item);
}
