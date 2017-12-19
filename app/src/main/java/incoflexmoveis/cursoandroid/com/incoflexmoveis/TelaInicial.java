package incoflexmoveis.cursoandroid.com.incoflexmoveis;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class TelaInicial extends AppCompatActivity {

    private ImageButton empresa, contato, catalogo, encontre, admin, desenvol;
    private TextView textEmpresa, textContato, textCatalogo, textAdmin, textDesenvol, textEncontre;


    @Override
    protected void onPostResume() {
        super.onPostResume();

        empresa = (ImageButton) findViewById(R.id.empresaID);
        catalogo = (ImageButton) findViewById(R.id.catalogoID);
        contato = (ImageButton) findViewById(R.id.contatoID);
        encontre = (ImageButton) findViewById(R.id.encontreID);
        admin = (ImageButton) findViewById(R.id.adminID);
        desenvol = (ImageButton) findViewById(R.id.desenvolvID);
        textEmpresa = (TextView) findViewById(R.id.empresa);
        textContato = (TextView) findViewById(R.id.contato);
        textDesenvol = (TextView) findViewById(R.id.desenvol);
        textEncontre = (TextView) findViewById(R.id.encontre);
        textAdmin = (TextView) findViewById(R.id.admin);
        textCatalogo = (TextView) findViewById(R.id.catalogo);

        if(textEmpresa.getTranslationX() < 0 ){

            textEmpresa.animate().translationXBy(800f);
            empresa.animate().translationXBy(800f);

        }

        if(textContato.getTranslationX() < 0 ){

            textContato.animate().translationXBy(800f);
            contato.animate().translationXBy(800f);

        }

        if(textAdmin.getTranslationX() < 0 ){

            textAdmin.animate().translationXBy(800f);
            admin.animate().translationXBy(800f);

        }

        if(textCatalogo.getTranslationX() > 0 ){

            textCatalogo.animate().translationXBy(-800f);
            catalogo.animate().translationXBy(-800f);

        }

        if(textEncontre.getTranslationX() > 0 ){

            textEncontre.animate().translationXBy(-800f);
            encontre.animate().translationXBy(-800f);

        }

        if(textDesenvol.getTranslationX() > 0 ){

            textDesenvol.animate().translationXBy(-800f);
            desenvol.animate().translationXBy(-800f);

        }

        empresa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                textEmpresa.animate().translationXBy(-800f).setDuration(800);
                empresa.animate().translationXBy(-800f).setDuration(800);

                Handler handle = new Handler();
                handle.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(TelaInicial.this, SobreEmpresa.class));
                    }
                }, 800);
            }
        });

        catalogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                textCatalogo.animate().translationXBy(800f).setDuration(800);
                catalogo.animate().translationXBy(800f).setDuration(800);

                Handler handle = new Handler();
                handle.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        AlertDialog.Builder dialog;

                        dialog = new AlertDialog.Builder(TelaInicial.this);

                        dialog.setTitle("Alerta !");

                        dialog.setMessage("É necessário o uso da internet para acessar o catálogo.");
                        dialog.setCancelable(false);
                        dialog.setIcon(android.R.drawable.ic_menu_preferences);

                        dialog.setNegativeButton("NÃO ACESSAR", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(TelaInicial.this, "LIGUE O WI-FI OU DADOS MOVEIS !", Toast.LENGTH_SHORT).show();
                                textCatalogo.animate().translationXBy(-800f);
                                catalogo.animate().translationXBy(-800f);
                            }
                        });

                        dialog.setPositiveButton("ACESSAR", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                startActivity(new Intent(TelaInicial.this, Catalogo.class));
                            }
                        });

                        dialog.create();
                        dialog.show();
                    }
                }, 800);


            }
        });

        contato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                textContato.animate().translationXBy(-800f).setDuration(800);
                contato.animate().translationXBy(-800f).setDuration(800);

                Handler handle = new Handler();
                handle.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(TelaInicial.this, Contato.class));
                    }
                }, 800);
            }
        });

        encontre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                textEncontre.animate().translationXBy(800f).setDuration(800);
                encontre.animate().translationXBy(800f).setDuration(800);

                Handler handle = new Handler();
                handle.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        AlertDialog.Builder dialog;

                        dialog = new AlertDialog.Builder(TelaInicial.this);

                        dialog.setTitle("Alerta !");

                        dialog.setMessage("É necessário o uso da internet para acessar o mapa.");
                        dialog.setCancelable(false);
                        dialog.setIcon(android.R.drawable.ic_menu_preferences);

                        dialog.setNegativeButton("NÃO ACESSAR", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(TelaInicial.this, "LIGUE O WI-FI OU DADOS MOVEIS !", Toast.LENGTH_SHORT).show();
                                textEncontre.animate().translationXBy(-800f);
                                encontre.animate().translationXBy(-800f);
                            }
                        });

                        dialog.setPositiveButton("ACESSAR", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                startActivity(new Intent(TelaInicial.this, MapsActivity.class));
                            }
                        });

                        dialog.create();
                        dialog.show();
                    }
                }, 800);
            }
        });

        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                textAdmin.animate().translationXBy(-800f).setDuration(800);
                admin.animate().translationXBy(-800f).setDuration(800);

                Handler handle = new Handler();
                handle.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(TelaInicial.this, Administracao.class));
                    }
                }, 800);
            }
        });

        desenvol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                textDesenvol.animate().translationXBy(800f).setDuration(800);
                desenvol.animate().translationXBy(800f).setDuration(800);

                Handler handle = new Handler();
                handle.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(TelaInicial.this, Desenvolvedor.class));
                    }
                }, 800);
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);

    }
}
