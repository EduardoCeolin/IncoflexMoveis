package incoflexmoveis.cursoandroid.com.incoflexmoveis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Administracao2 extends AppCompatActivity {

    private ListView listaContatos;
    private DatabaseReference firebase;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> contatos;
    private ValueEventListener valueEventListener;

    @Override
    protected void onStart() {
        super.onStart();
        firebase.addValueEventListener(valueEventListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        firebase.removeEventListener(valueEventListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administracao2);

        listaContatos = (ListView) findViewById(R.id.listaContatos);

        contatos = new ArrayList<String>();

        adapter = new ArrayAdapter<String>(
                getApplicationContext(),
                R.layout.lista_layout,
                R.id.contato_id,
                contatos
        );

        listaContatos.setAdapter(adapter);

        firebase = ConfiguracaoFirebase.getFirebase().child("mensagens");

        //listener p/ adicionar o contato a lista
        valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //limpa a lista
                contatos.clear();

                for (DataSnapshot dados: dataSnapshot.getChildren()){

                    String contato = dados.getKey();
                    contato = Base64Custom.decodificarBase64(contato);
                            contatos.add(contato);
            }

            adapter.notifyDataSetChanged();
        }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };

        firebase.addValueEventListener(valueEventListener);

        listaContatos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(Administracao2.this, ExibicaoMensagem.class);

                //recupera dados a serem passados

                String contato = contatos.get(i);
                contato = Base64Custom.codificarBase64(contato);

                //envia dados p/ activity seguinte
                intent.putExtra("emailcontato", contato);

                startActivity(intent);
                finish();

            }
        });
    }
}
