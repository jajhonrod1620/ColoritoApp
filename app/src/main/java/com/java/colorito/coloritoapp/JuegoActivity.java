package com.java.colorito.coloritoapp;

import android.support.v7.app.AppCompatActivity;
import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;

/**
 * Created by christian on 23/05/15.
 */
public class JuegoActivity extends Activity {
    String vector_colores[]={"Azul","Verde","Rojo","Amarillo"};
    TextView tiempo,texto_colores,tiempo_mili;
    ImageView img1,img2,img3,img4;
    CountDownTimer cuenta_atras;
    Button pausa,continuar;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.juego_layout);

        tiempo=(TextView) findViewById(R.id.tiempo);
        tiempo_mili=(TextView) findViewById(R.id.tiempo_mili);
        texto_colores=(TextView) findViewById(R.id.color_texto);

        pausa=(Button) findViewById(R.id.pausar);
        continuar=(Button) findViewById(R.id.continuar);

        continuar.setEnabled(false);

        img1=(ImageView) findViewById(R.id.img_1);
        img2=(ImageView) findViewById(R.id.img_2);
        img3=(ImageView) findViewById(R.id.img_3);
        img4=(ImageView) findViewById(R.id.img_4);

        iniciar();
        cambiarColorTexto(texto_colores);

        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validarBoton01();
            }
        });

        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validarBoton02();
            }
        });

        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validarBoton03();
            }
        });

        img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validarBoton04();
            }
        });

        pausa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cuenta_atras.cancel();
                continuar.setEnabled(true);
                pausa.setEnabled(false);

                img1.setEnabled(false);
                img2.setEnabled(false);
                img3.setEnabled(false);
                img4.setEnabled(false);
            }
        });

        continuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cuenta_atras.start();
                pausa.setEnabled(true);
                continuar.setEnabled(false);

                tiempo.setTextColor(getResources().getColor(R.color.amarillo));
                cambiarTexto(texto_colores);
                cambiarPosicionBotones();
                cambiarColorTexto(texto_colores);

                img1.setEnabled(true);
                img2.setEnabled(true);
                img3.setEnabled(true);
                img4.setEnabled(true);
            }
        });

    }
    int intentos=5;
    private void iniciar() {
        if (intentos > 0) {
            tiempo.setTextColor(getResources().getColor(R.color.amarillo));
            cambiarTexto(texto_colores);
            cambiarPosicionBotones();

            cuenta_atras = new CountDownTimer(5000, 1) {
                public void onTick(long mili) {

                    long v = mili / 1000;
                    tiempo.setText("" + v);
                    tiempo_mili.setText("" + (mili));
                    if (v == 0) {
                        texto_colores.setText("");
                        tiempo_mili.setText("0");
                        tiempo.setTextColor(getResources().getColor(R.color.gris1));
                        cambiarColorTexto(texto_colores);
                    }
                }

                @Override
                public void onFinish() {
                    iniciar();
                }

            }.start();
        }else{
            intentos=5;
            continuar.setEnabled(true);
            pausa.setEnabled(false);
        }
    }

    private void cambiarColorTexto(TextView colortexto){
        int max=4;
        switch (new Random().nextInt(max)){
            case 0: colortexto.setTextColor(getResources().getColor(R.color.azul));
                break;
            case 1: colortexto.setTextColor(getResources().getColor(R.color.verde));
                break;
            case 2: colortexto.setTextColor(getResources().getColor(R.color.rojo));
                break;
            case 3: colortexto.setTextColor(getResources().getColor(R.color.amarillo));
                break;
        }

    }

    private void cambiarTexto(TextView texto){
        texto.setText(vector_colores[new Random().nextInt(4)]);
    }
    int uaz,uam,uro,uve;

    private void cambiarPosicionBotones(){
        uaz=new Random().nextInt(4);
        switch (uaz){
            case 0:img1.setImageDrawable(getResources().getDrawable(R.drawable.azul));
                break;
            case 1:img2.setImageDrawable(getResources().getDrawable(R.drawable.azul));
                break;
            case 2:img3.setImageDrawable(getResources().getDrawable(R.drawable.azul));
                break;
            case 3:img4.setImageDrawable(getResources().getDrawable(R.drawable.azul));
                break;
        }

        do {
            uve = new Random().nextInt(4);
        }while (uve==uaz);
        switch (uve){
            case 0:img1.setImageDrawable(getResources().getDrawable(R.drawable.verde));
                break;
            case 1:img2.setImageDrawable(getResources().getDrawable(R.drawable.verde));
                break;
            case 2:img3.setImageDrawable(getResources().getDrawable(R.drawable.verde));
                break;
            case 3:img4.setImageDrawable(getResources().getDrawable(R.drawable.verde));
                break;
        }

        do {
            uro = new Random().nextInt(4);
        }while (uro==uve || uro==uaz);
        switch (uro){
            case 0:img1.setImageDrawable(getResources().getDrawable(R.drawable.rojo));
                break;
            case 1:img2.setImageDrawable(getResources().getDrawable(R.drawable.rojo));
                break;
            case 2:img3.setImageDrawable(getResources().getDrawable(R.drawable.rojo));
                break;
            case 3:img4.setImageDrawable(getResources().getDrawable(R.drawable.rojo));
                break;
        }




        if(uve!=0 && uaz!=0 && uro!=0){
            img1.setImageDrawable(getResources().getDrawable(R.drawable.amarillo));
        }else if(uve!=1 && uaz!=1 && uro!=1){
            img2.setImageDrawable(getResources().getDrawable(R.drawable.amarillo));
        }else if(uve!=2 && uaz!=2 && uro!=2){
            img3.setImageDrawable(getResources().getDrawable(R.drawable.amarillo));
        }else{
            img4.setImageDrawable(getResources().getDrawable(R.drawable.amarillo));
        }


    }

    private void validarBoton01(){
        if(texto_colores.getText().equals("Rojo") && uro==0) {
            Toast.makeText(getBaseContext(), "Correcto", Toast.LENGTH_SHORT).show();
        }else
        if(texto_colores.getText().equals("Azul") && uaz==0) {
            Toast.makeText(getBaseContext(), "Correcto", Toast.LENGTH_SHORT).show();
        }else
        if(texto_colores.getText().equals("Verde") && uve==0) {
            Toast.makeText(getBaseContext(), "Correcto", Toast.LENGTH_SHORT).show();
        }else
        if(texto_colores.getText().equals("Amarillo") && uam==0) {
            Toast.makeText(getBaseContext(), "Correcto", Toast.LENGTH_SHORT).show();
        }else {
            if(intentos>0)intentos--;
            Toast.makeText(getBaseContext(), "Incorrecto, te quedan "+intentos+" intentos", Toast.LENGTH_SHORT).show();
        }
    }

    private void validarBoton02(){
        if(texto_colores.getText().equals("Rojo") && uro==1) {
            Toast.makeText(getBaseContext(), "Correcto", Toast.LENGTH_SHORT).show();
        }else
        if(texto_colores.getText().equals("Azul") && uaz==1) {
            Toast.makeText(getBaseContext(), "Correcto", Toast.LENGTH_SHORT).show();
        }else
        if(texto_colores.getText().equals("Verde") && uve==1) {
            Toast.makeText(getBaseContext(), "Correcto", Toast.LENGTH_SHORT).show();
        }else
        if(texto_colores.getText().equals("Amarillo") && uam==1) {
            Toast.makeText(getBaseContext(), "Correcto", Toast.LENGTH_SHORT).show();
        }else {
            if(intentos>0)intentos--;
            Toast.makeText(getBaseContext(), "Incorrecto, te quedan "+intentos+" intentos", Toast.LENGTH_SHORT).show();
        }
    }

    private void validarBoton03(){
        if(texto_colores.getText().equals("Rojo") && uro==2) {
            Toast.makeText(getBaseContext(), "Correcto", Toast.LENGTH_SHORT).show();
        }else
        if(texto_colores.getText().equals("Azul") && uaz==2) {
            Toast.makeText(getBaseContext(), "Correcto", Toast.LENGTH_SHORT).show();
        }else
        if(texto_colores.getText().equals("Verde") && uve==2) {
            Toast.makeText(getBaseContext(), "Correcto", Toast.LENGTH_SHORT).show();
        }else
        if(texto_colores.getText().equals("Amarillo") && uam==2) {
            Toast.makeText(getBaseContext(), "Correcto", Toast.LENGTH_SHORT).show();
        }else {
            if(intentos>0)intentos--;
            Toast.makeText(getBaseContext(), "Incorrecto, te quedan "+intentos+" intentos", Toast.LENGTH_SHORT).show();
        }

    }

    private void validarBoton04(){
        if(texto_colores.getText().equals("Rojo") && uro==3) {
            Toast.makeText(getBaseContext(), "Correcto", Toast.LENGTH_SHORT).show();
        }else
        if(texto_colores.getText().equals("Azul") && uaz==3) {
            Toast.makeText(getBaseContext(), "Correcto", Toast.LENGTH_SHORT).show();
        }else
        if(texto_colores.getText().equals("Verde") && uve==3) {
            Toast.makeText(getBaseContext(), "Correcto", Toast.LENGTH_SHORT).show();
        }else
        if(texto_colores.getText().equals("Amarillo") && uam==3) {
            Toast.makeText(getBaseContext(), "Correcto", Toast.LENGTH_SHORT).show();
        }else {
            if(intentos>0)intentos--;
            Toast.makeText(getBaseContext(), "Incorrecto, te quedan "+intentos+" intentos", Toast.LENGTH_SHORT).show();
        }
    }
}