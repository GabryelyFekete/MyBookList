package com.example.mybooklistapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Tela_de_Navegacao extends AppCompatActivity {
    //iniciando variavél
    DrawerLayout drawerLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_de_navegacao);

        //Atribuir variavél
        drawerLayout = findViewById(R.id.drawer_layout);
    }

    public void ClickMenu(View view){
        //Abrir drawer
        openDrawer(drawerLayout);
    }

    public static void openDrawer(DrawerLayout drawerLayout) {
        // Abrir drawer layout
        drawerLayout.openDrawer(GravityCompat.START);
    }

    public void Clicklogo(View view){
        //Fechar drawer
        closeDrawer(drawerLayout);
    }

    public static void closeDrawer(DrawerLayout drawerLayout) {
        //Fechar drawer layout
        //Checar condição
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            //Quando drawer está aberto
            //Fechar drawer
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    public void ClickInicio(View view){
        //Recriar activity
        recreate();
    }

    public void ClickCadastro(View view){
        //Redirecionar activity para tela inical
        redirectActivity(this,Main.class);
    }

    public void ClickLogout(View view){
        //Redirecionar activity para logout
        redirectActivity(this,TelaLogout.class);
    }

    public void ClickSobre(View view){
        //Redirecionar activity para sobre
        redirectActivity(this,Sobre.class);
    }

    public static void redirectActivity(Activity activity,Class aClass) {
        //Inicializando intent
        Intent intent = new Intent(activity,aClass);
        //Enviar flag
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //Iniciar activity
        activity.startActivity(intent);

    }

    @Override
    protected void onPause() {
        super.onPause();
        //Fechar drawer
        closeDrawer(drawerLayout);
    }
}