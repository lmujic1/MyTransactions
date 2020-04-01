package ba.unsa.etf.rma.spirala;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import ba.unsa.etf.rma.spirala.models.Transaction;
import ba.unsa.etf.rma.spirala.presenters.TransactionListPresenter;

public class TransactionActivitiy extends AppCompatActivity {
    private TextView titleTransakcije;
    private TextView typeTransakcije;
    private TextView opisTransakcije;
    private TextView dateTransakcije;
    private TextView intervalTransakcije;
    private TextView endTransakcije;
    private TextView iznosTransakcije;
    private ImageView iconTransakcije;
    private Button saveButton;
    private Button deleteButton;
    private Transaction transaction;


    private TransactionListPresenter transactionListPresenter=new TransactionListPresenter();
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transaction_view);

        titleTransakcije = (TextView) findViewById(R.id.titleTransakcije);
        typeTransakcije = (TextView) findViewById(R.id.typeTransakcije);
        opisTransakcije = (TextView) findViewById(R.id.opisTransakcije);
        dateTransakcije = (TextView) findViewById(R.id.dateTransakcije);
        intervalTransakcije = (TextView) findViewById(R.id.intervalTransakcije);
        endTransakcije = (TextView) findViewById(R.id.endTransakcije);
        iznosTransakcije = (TextView) findViewById(R.id.iznosTransakcije);
        iconTransakcije = (ImageView) findViewById(R.id.iconTransakcije);
        saveButton = (Button) findViewById(R.id.saveButton);
        deleteButton = (Button) findViewById(R.id.deleteButton);

        String title = getIntent().getStringExtra("title");
        transaction = (Transaction) getIntent().getSerializableExtra("Transaction");

        titleTransakcije.setText(transaction.getTitle());
        typeTransakcije.setText(transaction.getType().toString());
        if(transaction.getItemDescription() == null) opisTransakcije.setText("-");
        else opisTransakcije.setText(transaction.getItemDescription());
        dateTransakcije.setText(transaction.getDate1(transaction.getDate()));
        intervalTransakcije.setText(String.format("%d",transaction.getTransactionInterval()));
        if (transaction.getEndDate() == null)
            endTransakcije.setText(transaction.getDate1(transaction.getDate()));
        else endTransakcije.setText(transaction.getDate1(transaction.getEndDate()));
        iznosTransakcije.setText(String.format("%.2f", transaction.getAmount()));

        String tipTransakcije = transaction.getType().toString();
        switch (tipTransakcije) {
            case "Individual payment":
                iconTransakcije.setImageResource(R.drawable.individual_payment);
                break;
            case "Regular payment":
                iconTransakcije.setImageResource(R.drawable.regular_payment);
                break;
            case "Purchase":
                iconTransakcije.setImageResource(R.drawable.purchase);
                break;
            case "Individual income":
                iconTransakcije.setImageResource(R.drawable.individual_income);
                break;
            case "Regular income":
                iconTransakcije.setImageResource(R.drawable.regular_income);
                break;
            default:
                iconTransakcije.setImageResource(R.drawable.transaction);
                break;
        }
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(TransactionActivitiy.this);
                builder.setTitle("Select your answer.");
                builder.setMessage("Want to delete transaction?");
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch(which){
                            case DialogInterface.BUTTON_POSITIVE:
                                transactionListPresenter.deleteTransaction(transaction);
                                Intent intent = new Intent(TransactionActivitiy.this, MainActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                                break;
                            case DialogInterface.BUTTON_NEGATIVE:
                                break;
                        }
                    }
                };
                builder.setPositiveButton("Yes", dialogClickListener);
                builder.setNegativeButton("No",dialogClickListener);
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

    }


}
