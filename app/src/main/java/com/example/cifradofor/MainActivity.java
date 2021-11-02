package com.example.cifradofor;


import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;


import android.content.Context;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener {

    private final String letters = " qwertyuiopasdfghjklñzxcvbnm";
    private boolean mayus = false;
    private boolean longClick = false;
    Spinner seleccion;
    Context context;
    TextView text;
    ImageButton mayuscula;
    TextView salidaTexto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final int xPos = 0;
        final int yPos = 350;

        int columns = 0;
        int rows = 0;

        final ConstraintLayout layout = findViewById(R.id.root);
        final ConstraintSet set = new ConstraintSet();
        set.clone(layout);

        // A-Z
        for (int i = 1; i < 28; i++) {
            Button button = new Button(this);
            button.setText(letters.split("")[i]);
            button.setId(i);
            button.setAllCaps(false);
            button.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
            button.setBackgroundResource(R.color.primary_dark);
            layout.addView(button);
            button.setOnClickListener(this);

            set.setTranslationX(button.getId(), xPos + (columns * 48));
            set.setTranslationY(button.getId(), yPos + (rows * 60));
            set.constrainHeight(button.getId(), 60);
            set.constrainWidth(button.getId(), 50);
            set.applyTo(layout);

            columns++;
            if (rows == 2) {
                set.setTranslationX(button.getId(), xPos + (columns * 48 + 25));
                set.setTranslationY(button.getId(), yPos + (rows * 60));
                set.applyTo(layout);
            }
            if (i % 10 == 0) {
                rows++;
                columns = 0;
            }
        }
/* movil grande
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final int xPos = 0;
        final int yPos = 700;

        int columns = 0;
        int rows = 0;

        final ConstraintLayout layout = findViewById(R.id.root);
        final ConstraintSet set = new ConstraintSet();
        set.clone(layout);

        // A-Z
        for (int i = 1; i < 28; i++) {
            final Button button = new Button(this);
            button.setText(letters.split("")[i]);
            button.setId(i);
            button.setAllCaps(false);
            button.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
            button.setBackgroundResource(R.color.primary_dark);
            layout.addView(button);
            button.setOnClickListener(this);

            set.setTranslationX(button.getId(), xPos + (columns * 110));
            set.setTranslationY(button.getId(), yPos + (rows * 120));
            set.constrainHeight(button.getId(), 140);
            set.constrainWidth(button.getId(), 110);
            set.applyTo(layout);

            columns++;
            if (rows == 2) {
                set.setTranslationX(button.getId(), xPos + (columns * 110 + 25));
                set.setTranslationY(button.getId(), yPos + (rows * 120));
                set.applyTo(layout);
            }
            if (i % 10 == 0) {
                rows++;
                columns = 0;
            }
        }
 */

        // Mayus
        final int mayusId = 30;
        mayuscula = (ImageButton) findViewById(R.id.mayus);
        mayuscula.setId(mayusId);
        mayuscula.setOnClickListener(this);
        mayuscula.setOnLongClickListener(this);
        mayuscula.setBackgroundResource(R.drawable.normal);



        Button cifrar, descifrar,espacio;
        ImageButton borrar;
        cifrar = (Button) findViewById(R.id.cifrar);
        descifrar = (Button) findViewById(R.id.descifrar);
        espacio = (Button) findViewById(R.id.espacio);

        borrar = (ImageButton) findViewById(R.id.borrar);
        borrar.setBackgroundResource(R.drawable.borrar);

        cifrar.setOnClickListener(this::cifrar);
        descifrar.setOnClickListener(this::descifrar);
        espacio.setOnClickListener(this::espacio);
        borrar.setOnClickListener(this::borrar);


        String[] datos = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        seleccion = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adaptador = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, datos);
        seleccion.setAdapter(adaptador);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case 30:
                if (this.longClick) this.updateLetterButton();
                this.mayus = !this.mayus;
                this.updateLetterButton();

                if(this.mayus){
                    v.setBackgroundResource(R.drawable.una);
                }else{
                    v.setBackgroundResource(R.drawable.normal);
                }
                break;

            default:
                final TextView text = findViewById(R.id.noCifrado);
                String letter = letters.split("")[v.getId()];

                if (this.mayus) letter = letter.toUpperCase();

                //solo dejamos 20 letras
                if (text.getText().length() < 20) {
                    text.setText(text.getText() + letter);
                } else {
                    context = getApplicationContext();
                    CharSequence error = "No puedes escribir más";
                    int duracion = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, error, duracion);
                    toast.show();
                }
                if (!this.longClick) {
                    this.mayus = false;
                    this.updateLetterButton();

                }
                break;

        }
    }

    @Override
    public boolean onLongClick(View v) {

        this.mayus = true;
        this.longClick = true;
        v.setBackgroundResource(R.drawable.todo);
        this.updateLetterButton();
        return true;

    }

    private void updateLetterButton() {
        for (int i = 1; i < 28; i++) {
            Button button = findViewById(i);
            button.setAllCaps(this.mayus);
        }

    }

    public void cifrar(View v) {
        text = (TextView) findViewById(R.id.noCifrado);
        salidaTexto = (TextView) findViewById(R.id.salidaTexto);
        String selectStr = seleccion.getSelectedItem().toString();
        int selectInt = 0;
        switch (selectStr) {
            case "0":
                selectInt = 0;
                break;
            case "1":
                selectInt = 1;
                break;
            case "2":
                selectInt = 2;
                break;
            case "3":
                selectInt = 3;
                break;
            case "4":
                selectInt = 4;
                break;
            case "5":
                selectInt = 5;
                break;
            case "6":
                selectInt = 6;
                break;
            case "7":
                selectInt = 7;
                break;
            case "8":
                selectInt = 8;
                break;
            case "9":
                selectInt = 9;
                break;
        }

        salidaTexto.setText("");
        Cifrado cesar = new Cifrado();

        String[]partes;
            partes = text.getText().toString().split(" ");
            for (String parte : partes) {
                salidaTexto.setText((salidaTexto.getText().toString())+cesar.cifrar(parte, selectInt) + " ");

        }

    }

    public void descifrar(View v) {
        text = (TextView) findViewById(R.id.noCifrado);
        salidaTexto = (TextView) findViewById(R.id.salidaTexto);
        String selectStr = seleccion.getSelectedItem().toString();
        int selectInt = 0;
        switch (selectStr) {
            case "0":
                selectInt = 0;
                break;
            case "1":
                selectInt = 1;
                break;
            case "2":
                selectInt = 2;
                break;
            case "3":
                selectInt = 3;
                break;
            case "4":
                selectInt = 4;
                break;
            case "5":
                selectInt = 5;
                break;
            case "6":
                selectInt = 6;
                break;
            case "7":
                selectInt = 7;
                break;
            case "8":
                selectInt = 8;
                break;
            case "9":
                selectInt = 9;
                break;
        }
        String guardado=text.getText().toString();
        salidaTexto.setText("");
        Cifrado cesar = new Cifrado();
        String[]partesdescifrado;
        partesdescifrado = guardado.split(" ");
        for (String parte : partesdescifrado) {
            salidaTexto.setText((salidaTexto.getText().toString())+cesar.descifrar(parte, selectInt) + " ");

        }

    }

    public void borrar(View v) {
        text = findViewById(R.id.noCifrado);
        String borrarPos = text.getText().toString();
        if (borrarPos.length() > 0) {
            borrarPos = borrarPos.substring(0, borrarPos.length() - 1);
            text.setText(borrarPos);
            salidaTexto.setText("");
        } else {
            context = getApplicationContext();
            CharSequence error = "No puedes borrar más";
            int duracion = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, error, duracion);
            toast.show();
        }
    }

    public void espacio(View v){
        text = findViewById(R.id.noCifrado);
        text.setText(text.getText().toString()+" ");
    }

}
