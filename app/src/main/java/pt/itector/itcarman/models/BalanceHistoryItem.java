package pt.itector.itcarman.models;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by me on 31/07/2019
 */

@Entity(tableName = "balance_history")
public class BalanceHistoryItem {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name="month")
    private int month;

    @ColumnInfo(name="year")
    private int year;

    @ColumnInfo(name="balance")
    private double balance;

    public BalanceHistoryItem(int id, int month, int year, double balance) {
        this.id = id;
        this.month = month;
        this.year = year;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
