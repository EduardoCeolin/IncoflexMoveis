package incoflexmoveis.cursoandroid.com.incoflexmoveis;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SobreEmpresa extends AppCompatActivity {

    private Button botaovideo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sobre_empresa);

        botaovideo = (Button) findViewById(R.id.BOTAO_VIDEO_EMPRESA);

        botaovideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=upegNmpET_k&t=4s"));
                startActivity(intent);
            }
        });
    }
}
