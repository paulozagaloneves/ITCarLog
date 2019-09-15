package pt.itector.itcarman.utils;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import java.util.List;

import pt.itector.itcarman.data.CompanionDatabase;
import pt.itector.itcarman.models.MovementItem;

/**
 * Created by me on 01/08/2019
 */
public class MovementsListViewModel extends AndroidViewModel {
    private final LiveData<List<MovementItem>> movementsList;
    private CompanionDatabase db;

    public interface MovementAddedCallback {
        void onMovementAdded(MovementItem item);
    }

    public interface MovementDeletedCallback {
        void onMovementDeleted();
    }


    public MovementsListViewModel(@NonNull Application application) {
        super(application);
        this.db = CompanionDatabase.getCompanionDatabase(this.getApplication());
        this.movementsList = db.getMovementDao().getMovements();
    }

    public LiveData<List<MovementItem>> getMovementsList() {
        return movementsList;
    }

    /**
     * Add a movement to the view model/liveData
     * Async method, so we don't block the UI thread
     * @param context
     * @param item
     * @return
     */
    public MovementItem addMovement(final Context context, final MovementItem item, final MovementAddedCallback callback){
        new Thread(new Runnable() {
            @Override
            public void run() {
                CompanionDatabase db = CompanionDatabase.getCompanionDatabase(context);

                /* Add the movement to the db */
                db.getMovementDao().insertMovement(item);

                /* Update the Balance */
                BalanceUtils.updateBalance(context, item.getValue() * (-1), DateUtils.getCurrentMonth(), DateUtils.getCurrentYear());

                callback.onMovementAdded(item);
            }
        }).start();

        return item;
    }

    /**
     * Delete a movement from the view model/liveData
     * Async method, so we don't block the UI thread
     * @param context
     * @param item
     */
    public void deleteMovement(final Context context, final MovementItem item){
        new Thread(new Runnable() {
            @Override
            public void run() {
                CompanionDatabase db = CompanionDatabase.getCompanionDatabase(context);

                /* Delete the movement from the db */
                db.getMovementDao().deleteMovement(item);

                /* Update the balance */
                BalanceUtils.updateBalance(context, item.getValue(), DateUtils.getCurrentMonth(), DateUtils.getCurrentYear());

            }
        }).start();
    }
}
