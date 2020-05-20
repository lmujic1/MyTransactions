package ba.unsa.etf.rma.spirala.presenters;

import java.util.ArrayList;
import java.util.Date;

import ba.unsa.etf.rma.spirala.models.Transaction;

public interface ITransactionListPresenter {
    public void getTransactions(String query);
    public void getTransactionOnDate(Date date);
    public void getSortTransaction(String string);

    void getFilteredTransacion(String selectedItemText);
    /*void refreshTransaction();
    void refreshTransactionOnDate(Date date);
    void refreshTransactionSort(String string);
    void refreshTransactionFilter(Date date, String string);

    //void refreshTransactionDelete(Date date, ArrayList<Transaction> transactions);
    void refreshTransactionDelete(Transaction transaction);

    void refreshTransactionAdd(Transaction trans);*/
}
