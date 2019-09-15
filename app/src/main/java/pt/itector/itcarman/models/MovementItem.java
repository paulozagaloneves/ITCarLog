package pt.itector.itcarman.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import pt.itector.itcarman.utils.DateUtils;

/**
 * Created by me on 31/07/2019
 */
@Entity(tableName = "movement")
public class MovementItem {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name="date")
    private String date;

    @ColumnInfo(name="value")
    private double value;

    @ColumnInfo(name="vehicleKms")
    private double vehicleKms;

    @ColumnInfo(name="balance_after_movement")
    private double balanceAfterMovement;

    public MovementItem(int id, String date, double value, double vehicleKms, double balanceAfterMovement) {
        this.id = id;
        this.date = date;
        this.value = value;
        this.vehicleKms = vehicleKms;
        this.balanceAfterMovement = balanceAfterMovement;
    }

    @Ignore
    public MovementItem(double value, double vehicleKms, double balanceAfterMovement) {
        this.value = value;
        this.vehicleKms = vehicleKms;
        this.balanceAfterMovement = balanceAfterMovement;
        this.date = DateUtils.getCurrentDateInFormat("dd/MM/yyyy");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getVehicleKms() {
        return vehicleKms;
    }

    public void setVehicleKms(double vehicleKms) {
        this.vehicleKms = vehicleKms;
    }

    public double getBalanceAfterMovement() {
        return balanceAfterMovement;
    }

    public void setBalanceAfterMovement(double balanceAfterMovement) {
        this.balanceAfterMovement = balanceAfterMovement;
    }
}
