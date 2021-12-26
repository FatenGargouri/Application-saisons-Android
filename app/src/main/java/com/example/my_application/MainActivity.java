package com.example.my_application;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ImageView ivSaison;
    private TextView tvSaison;
    private int n=0 , nb_photos=4;
    private float x1 , x2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        choix_saison(n);
    }



    private void init()
    {
        ivSaison=findViewById(R.id.ivSaison);
        tvSaison=findViewById(R.id.tvSaison);
        ajouterEcouteur();

    }


    public void ajouterEcouteur()
    {
        ivSaison.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        x1 = event.getX();
                        break;
                    }

                    case MotionEvent.ACTION_UP: {
                        x2 = event.getX();
                        if (x1 < x2) {
                            n = (n + 1) % nb_photos;
                        } else if (x1 > x2) {
                            n = (n - 1 + nb_photos) % nb_photos;
                        }
                        choix_saison(n);
                        break;


                    }
                }
                return true;
            }
        });
    }


    private void choix_saison(int n)
    {
        switch(n)
        {
            case 0:ivSaison.setImageResource(R.drawable.automne);
                tvSaison.setText(getResources().getString(R.string.automne));break;
            case 1:ivSaison.setImageResource(R.drawable.ete);
                tvSaison.setText(getResources().getString(R.string.ete));break;

            case 2:ivSaison.setImageResource(R.drawable.printemps);
                tvSaison.setText(getResources().getString(R.string.printemps));break;

            case 3:ivSaison.setImageResource(R.drawable.hivers);
                tvSaison.setText(getResources().getString(R.string.hivers));break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.automne:choix_saison(0);break;
            case R.id.ete:choix_saison(1);break;
            case R.id.printemps:choix_saison(2);break;
            case R.id.hivers:choix_saison(3);break;
            case R.id.quitter:

                AlertDialog.Builder alert=new AlertDialog.Builder(this);
                alert.setTitle("Quitter");
                alert.setIcon(R.drawable.automne);
                alert.setMessage("Etes vous sur de vouloir quitter ?");
                alert.setPositiveButton("oui", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {
                        finish();

                    }
                });

                alert.setNegativeButton("non", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {


                    }
                });
                alert.show();
        }

        return super.onOptionsItemSelected(item);
    }


}