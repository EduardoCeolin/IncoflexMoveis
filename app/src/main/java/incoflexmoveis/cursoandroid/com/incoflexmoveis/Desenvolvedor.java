package incoflexmoveis.cursoandroid.com.incoflexmoveis;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

public class Desenvolvedor extends AppCompatActivity {

        private ImageView insta,face,linkedin;
        private Button video;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desenvolvedor);

        insta = (ImageView) findViewById(R.id.insta);
        face = (ImageView) findViewById(R.id.face);
        linkedin = (ImageView) findViewById(R.id.linkedin);
        video = (Button) findViewById(R.id.botaovideo);

        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=ic1roAxHxxk&list=UU7sQe-x9FRNI9TQ5zPh9Hcw"));
                startActivity(intent);
                finish();
            }
        });

        insta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.instagram.com/duuhceolin/"));
                startActivity(intent);
            }
        });

        face.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.facebook.com/eduardo.ceolin.39"));
                startActivity(intent);
            }
        });

        linkedin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.linkedin.com/in/luis-eduardo-ceolin-teixeira-13417b8b/"));
                startActivity(intent);
            }
        });
    }
}
