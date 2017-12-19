package incoflexmoveis.cursoandroid.com.incoflexmoveis;

import android.content.*;
import android.content.SharedPreferences;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.database.DatabaseReference;

public class Administracao extends AppCompatActivity {

    private EditText usuario, senha;
    private CheckBox checkBox;
    private ImageView login;
    private TextView esqueceu;

    private FirebaseAuth autenticacao;
    private static final String USUARIO_PREFERENCIA = "UsuarioPreferencia";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administracao);

        usuario = (EditText) findViewById(R.id.user);
        senha = (EditText) findViewById(R.id.password);
        checkBox = (CheckBox) findViewById(R.id.checkBox);
        login = (ImageView) findViewById(R.id.login);
        esqueceu = (TextView) findViewById(R.id.esqueceu);

        SharedPreferences sharedPreferences = getSharedPreferences(USUARIO_PREFERENCIA, 0);
        final SharedPreferences.Editor editor = sharedPreferences.edit();

        esqueceu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Administracao.this, "Usuario: admin@incoflex.com Senha: 123456", Toast.LENGTH_LONG).show();
            }
        });

        usuario.setText(sharedPreferences.getString("user", ""));
        senha.setText(sharedPreferences.getString("senha",""));

        if(usuario.getText().toString().equals("admin@incoflex.com") && senha.getText().toString().equals("123456")){

            Intent intent = new Intent(Administracao.this, Administracao2.class);
            startActivity(intent);

        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkBox.isChecked()) {

                    if (usuario.getText().toString().equals("") || senha.getText().toString().equals("")) {
                        Toast.makeText(Administracao.this, "Usuario ou Senha Nao digitados !", Toast.LENGTH_LONG).show();
                    } else {
                        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();

                        autenticacao.signInWithEmailAndPassword(
                                usuario.getText().toString(),
                                senha.getText().toString()
                        ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()) {

                                    editor.putString("user", usuario.getText().toString());
                                    editor.putString("senha", senha.getText().toString());
                                    editor.commit();

                                    Intent intent = new Intent(Administracao.this, Administracao2.class);
                                    startActivity(intent);
                                } else {
                                    String erroExcecao = "";

                                    try {
                                        throw task.getException();
                                    } catch (FirebaseAuthInvalidUserException e) {
                                        erroExcecao = "Email não existe ou está desativado !";
                                    } catch (FirebaseAuthInvalidCredentialsException e) {
                                        erroExcecao = "Senha inválida !";
                                    } catch (Exception e) {
                                        erroExcecao = "Erro ao efetuar login !";
                                    }

                                    Toast.makeText(Administracao.this, "Erro: " + erroExcecao, Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    }
                }

                else{
                    Toast.makeText(Administracao.this, "CheckBox não selecionada !", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
