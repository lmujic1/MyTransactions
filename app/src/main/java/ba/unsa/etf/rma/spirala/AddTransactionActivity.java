package ba.unsa.etf.rma.spirala;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import ba.unsa.etf.rma.spirala.models.Transaction;

public class AddTransactionActivity extends AppCompatActivity {
    private TextView format;
    private EditText titleTransakcije;
    private EditText typeTransakcije;
    private EditText opisTransakcije;
    private EditText dateTransakcije;
    private EditText intervalTransakcije;
    private EditText endTransakcije;
    private EditText iznosTransakcije;
    private ImageView iconTransakcije;
    private Button saveButton;
    private Button deleteButton;

    private Transaction transaction;


    private String title, type, description, date,endDate;
    private int interval;
    private double amount;

    @SuppressLint("SetTextI18n")
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transaction_make_a_change);

        titleTransakcije = (EditText) findViewById(R.id.iTitleTransakcije);
        typeTransakcije = (EditText) findViewById(R.id.iTypeTransakcije);
        opisTransakcije = (EditText) findViewById(R.id.iOpisTransakcije);
        dateTransakcije = (EditText) findViewById(R.id.iDateTransakcije);
        intervalTransakcije = (EditText) findViewById(R.id.iIntervalTransakcije);
        endTransakcije = (EditText) findViewById(R.id.iEndTransakcije);
        iznosTransakcije = (EditText) findViewById(R.id.iIznosTransakcije);
        iconTransakcije = (ImageView) findViewById(R.id.iIconTransakcije);
        saveButton = (Button) findViewById(R.id.iSaveButton);
        deleteButton = (Button) findViewById(R.id.iDeleteButton);
        format = (TextView) findViewById(R.id.format);

        format.setText("Date of transaction (format: dd. MMMM, yyyy)");

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!String.valueOf(titleTransakcije.getText()).equals(title)) {
                    if(!validirajTitle(titleTransakcije.getText())) {
                        titleTransakcije.setBackgroundColor(Color.RED);
                    }
                    else
                        titleTransakcije.setBackgroundColor(Color.GREEN);
                }
                if(!String.valueOf(typeTransakcije.getText()).equals(type)) {
                    if(!validirajType(typeTransakcije.getText()))
                        typeTransakcije.setBackgroundColor(Color.RED);
                    else
                        typeTransakcije.setBackgroundColor(Color.GREEN);
                }
                if(!String.valueOf(opisTransakcije.getText()).equals(description))
                    opisTransakcije.setBackgroundColor(Color.GREEN);
                if(!String.valueOf(dateTransakcije.getText()).equals(date))
                    dateTransakcije.setBackgroundColor(Color.GREEN);
                if(!String.valueOf(intervalTransakcije.getText()).equals(String.valueOf(interval)))
                    intervalTransakcije.setBackgroundColor(Color.GREEN);
                if(!String.valueOf(endTransakcije.getText()).equals(endDate))
                    endTransakcije.setBackgroundColor(Color.GREEN);
                if(!String.valueOf(iznosTransakcije.getText()).equals(String.valueOf(amount)))
                    iznosTransakcije.setBackgroundColor(Color.GREEN);

                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString("title", String.valueOf(titleTransakcije.getText()));
                bundle.putString("type", String.valueOf(typeTransakcije.getText()));
                bundle.putString("itemDescription", String.valueOf(opisTransakcije.getText()));

                bundle.putString("date", String.valueOf(dateTransakcije.getText()));
                int interv = 0;
                if(String.valueOf(intervalTransakcije.getText()).equals("") || String.valueOf(intervalTransakcije.getText())==null)  bundle.putInt("interval", interv);
                else bundle.putInt("interval", Integer.parseInt(String.valueOf(intervalTransakcije.getText())));
                bundle.putString("endDate", String.valueOf(endTransakcije.getText()));
                bundle.putDouble("amount", Double.parseDouble(String.valueOf(iznosTransakcije.getText())));

                intent.putExtras(bundle);
                setResult(20,intent);
                finish();
            }
        });
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(AddTransactionActivity.this);
                builder.setTitle("Select your answer.");
                builder.setMessage("Want to delete transaction?");
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch(which){
                            case DialogInterface.BUTTON_POSITIVE:
                                Intent intent = new Intent();
                                setResult(10,intent);
                                finish();
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

    private boolean validirajType(Editable text) {
        String s = String.valueOf(text);
        return s.equals("Individual payment") || s.equals("Regular payment") || s.equals("Purchase") || s.equals("Individual income") || s.equals("Regular income");
    }

    private boolean validirajTitle(Editable text) {
        String s = String.valueOf(text);
        return s.length() > 3 && s.length() < 15;
    }
}
