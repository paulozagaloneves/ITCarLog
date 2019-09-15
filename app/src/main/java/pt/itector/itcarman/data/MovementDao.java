package pt.itector.itcarman.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import pt.itector.itcarman.models.MovementItem;
import pt.itector.itcarman.utils.Constants;

/**
 * Created by me on 31/07/2019c
 */

@Dao
public interface MovementDao {

    @Query("SELECT * from movement ORDER BY date " + Constants.MOVEMENTS_ORDER +" LIMIT " + Constants.MOVEMENTS_LIMIT)
    LiveData<List<MovementItem>> getMovements();

    @Insert
    void insertMovement(MovementItem movement);

    @Update
    void updateMovement(MovementItem movement);

    @Delete
    void deleteMovement(MovementItem movement);
}
