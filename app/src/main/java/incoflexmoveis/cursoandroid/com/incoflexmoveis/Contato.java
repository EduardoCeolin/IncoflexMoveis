package incoflexmoveis.cursoandroid.com.incoflexmoveis;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;


public class Contato extends AppCompatActivity {

    private Button enviar;
    private ImageView face;
    private EditText mensagem, email;
    private Task<Void> firebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contato);

        face = (ImageView) findViewById(R.id.botaoFace);
        enviar = (Button) findViewById(R.id.botaoEnviar);
        mensagem = (EditText) findViewById(R.id.mensagem);
        email = (EditText) findViewById(R.id.emailID);

        face.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.facebook.com/moveisescritoriosincoflex"));
                startActivity(intent);
            }
        });

        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailMsg = email.getText().toString();
                String msg = mensagem.getText().toString();

                if(emailMsg.equals("")){
                        Toast.makeText(Contato.this,"Email não digitado!", Toast.LENGTH_LONG).show();
                }
                else if(msg.equals("")){
                    Toast.makeText(Contato.this,"Mensagem sem conteúdo !", Toast.LENGTH_LONG).show();
                }
                else{
                    emailMsg = Base64Custom.codificarBase64(emailMsg);
                    firebase = ConfiguracaoFirebase.getFirebase().child("mensagens").child(emailMsg).setValue(msg);


                    mensagem.setText("");
                    email.setText("");

                    Toast.makeText(Contato.this,"Mensagem enviada !", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
