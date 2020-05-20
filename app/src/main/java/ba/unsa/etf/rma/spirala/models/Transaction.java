package ba.unsa.etf.rma.spirala.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.Date;

public class Transaction implements Serializable {


/*    protected Transaction(Parcel in) {
        amount = in.readDouble();
        title = in.readString();
        type = Type.valueOf(in.readString());
        date = new Date(in.readLong());
        itemDescription = in.readString();
        transactionInterval = in.readInt();
        endDate = new Date(in.readLong());
    }

    public static final Creator<Transaction> CREATOR = new Creator<Transaction>() {
        @Override
        public Transaction createFromParcel(Parcel in) {
            return new Transaction(in);
        }

        @Override
        public Transaction[] newArray(int size) {
            return new Transaction[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(amount);
        dest.writeString(title);
        dest.writeString(type.toString());
        dest.writeLong(date.getTime());
        if(endDate != null) dest.writeLong(endDate.getTime());
        dest.writeString(itemDescription);
        dest.writeInt(transactionInterval);
    }*/

    public enum Type {
        REGULARPAYMENT("Regular payment"),
        REGULARINCOME("Regular income"),
        PURCHASE("Purchase"),
        INDIVIDUALINCOME("Individual income"),
        INDIVIDUALPAYMENT("Individual payment");

        private final String opis;

        private Type(String opis) {
            this.opis = opis;
        }

        @NonNull
        @Override
        public String toString() {
            return opis;
        }
    }

    private int idTransaction;
    private Date date;
    private double amount;
    private String title;
    private Type type;
    private String itemDescription;
    private Integer transactionInterval;
    private Date endDate = null;

    public Transaction() {
    }

    public Transaction(Date date, double amount, String title, int type, String itemDescription, Integer transactionInterval, Date endDate) {
        this.date = date;
        this.amount = amount;
        setTitle(title);
        switch (type) {
            case 1:
                this.type = Type.REGULARPAYMENT;
                break;
            case 2:
                this.type = Type.REGULARINCOME;
                break;
            case 3:
                this.type = Type.PURCHASE;
                break;
            case 4:
                this.type = Type.INDIVIDUALINCOME;
                break;
            case 5:
                this.type = Type.INDIVIDUALPAYMENT;
                break;
        }
        setItemDescription(itemDescription);
        setTransactionInterval(transactionInterval);
        setEndDate(endDate);
    }

    public Transaction(int idTransaction, String date, double amount, String title, int type, String itemDescription, String transactionInterval, String endDate) {
        this.idTransaction = idTransaction;
        Date date1 = pretvoriUDatum(date);
        Date endDate1 = pretvoriUDatum(endDate);
        this.date = date1;

        this.amount = amount;
        setTitle(title);
        switch (type) {
            case 1:
                this.type = Type.REGULARPAYMENT;
                break;
            case 2:
                this.type = Type.REGULARINCOME;
                break;
            case 3:
                this.type = Type.PURCHASE;
                break;
            case 4:
                this.type = Type.INDIVIDUALINCOME;
                break;
            case 5:
                this.type = Type.INDIVIDUALPAYMENT;
                break;
        }
        setItemDescription(itemDescription);
        setTransactionInterval(transactionInterval);
        setEndDate(endDate1);
    }

    private Date pretvoriUDatum(String date) {
        Date date1 = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        try {
            date1 = dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date1;
    }

    public Transaction(Date date, double amount, String title, Type type, String itemDescription, Integer transactionInterval, Date endDate) {
        this.date = date;
        this.amount = amount;
        setTitle(title);
        this.type = type;
        setItemDescription(itemDescription);
        setTransactionInterval(transactionInterval);
        setEndDate(endDate);
    }

    public Date getDate() {
        return date;
    }

    public String getDate1(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd. MMMM, yyyy");
        String d = dateFormat.format(date);
        return d;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        //if (title.length() <= 3 || title.length() >= 15)
        //    System.out.println("G R E S K A");//throw new IllegalArgumentException();
        this.title = title;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }


    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        if (type == Type.INDIVIDUALINCOME || type == Type.REGULARINCOME)
            this.itemDescription = null;
        else
            this.itemDescription = itemDescription;
    }

    public Integer getTransactionInterval() {
        return transactionInterval;
    }

    public void setTransactionInterval(Integer transactionInterval) {
        this.transactionInterval = null;
        if (type == Type.REGULARINCOME || type == Type.REGULARPAYMENT)
            this.transactionInterval = transactionInterval;
    }

    private void setTransactionInterval(String transactionInterval) {
        //if (type == Type.REGULARINCOME || type == Type.REGULARPAYMENT)
        if (transactionInterval != null)
            this.transactionInterval = Integer.getInteger(transactionInterval);
            //this.transactionInterval = Integer.parseInt(transactionInterval);
        else this.transactionInterval = null;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        if (type == Type.REGULARPAYMENT || type == Type.REGULARINCOME)
            this.endDate = endDate;
        else this.endDate = null;
    }
}
