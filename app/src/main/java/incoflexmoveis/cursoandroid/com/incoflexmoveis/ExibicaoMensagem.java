package incoflexmoveis.cursoandroid.com.incoflexmoveis;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class ExibicaoMensagem extends AppCompatActivity {

    private String mensagem, contato;
    private TextView mensagemExibir, contatoExibir;
    private Button responder, deletar;
    private DatabaseReference firebase;
    private ValueEventListener valueEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exibicao_mensagem);

        mensagemExibir = (TextView) findViewById(R.id.mensagemContato_id);
        contatoExibir = (TextView) findViewById(R.id.emailContado_id);
        responder = (Button) findViewById(R.id.responder_id);
        deletar = (Button) findViewById(R.id.deletar_id);

        Bundle extras = getIntent().getExtras();

        final String emailContato = extras.getString("emailcontato");

        final String nomeEmail = Base64Custom.decodificarBase64(emailContato);

        if(extras!=null){

            firebase = ConfiguracaoFirebase.getFirebase().child("mensagens").child(emailContato);

            //pegando contato
            String emailContatoExibir = firebase.getKey();
            emailContatoExibir = Base64Custom.decodificarBase64(emailContatoExibir);

            final String finalEmailContatoExibir = emailContatoExibir;

            firebase.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            //pegando mensagem
                            String mensagemContatoExibir = (String) dataSnapshot.getValue();

                            exibicao(finalEmailContatoExibir, mensagemContatoExibir);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                        }
                    });
        }

        deletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Cria o gerador do AlertDialog
                AlertDialog.Builder pgtDeleta  = new AlertDialog.Builder(ExibicaoMensagem.this);

                //define o titulo
                pgtDeleta.setTitle("Exclusão Mensagem");

                //define a mensagem
                pgtDeleta.setMessage("Tem certeza que deseja excluir a mensagem ?");

                //define um botão como positivo
                pgtDeleta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        firebase = ConfiguracaoFirebase.getFirebase().child("mensagens").child(emailContato);

                        firebase.removeValue();

                        Intent intent = new Intent(ExibicaoMensagem.this, Administracao2.class);

                        startActivity(intent);

                        finish();
                        Toast.makeText(ExibicaoMensagem.this,"Mensagem Deletada !", Toast.LENGTH_LONG).show();

                    }
                });

                //define um botão como negativo.
                pgtDeleta.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(ExibicaoMensagem.this, "Produto Não Foi Deletado !", Toast.LENGTH_LONG).show();
                    }
                });

                //cria o AlertDialog
                pgtDeleta.create();
                //Exibe
                pgtDeleta.show();
            }
        });

        responder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("mailto:" + nomeEmail));
                startActivity(intent);

            }
        });
    }

    public void exibicao(String contatoAExibir,String mensagemAExibir){
        mensagemExibir.setText(mensagemAExibir);
        contatoExibir.setText(contatoAExibir);
    }
}
