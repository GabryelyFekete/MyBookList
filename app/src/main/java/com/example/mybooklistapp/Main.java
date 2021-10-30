package com.example.mybooklistapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.drawerlayout.widget.DrawerLayout;

public class Main extends Activity {
    //Iniciando variavél
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        //Atribuir variavél
        drawerLayout = findViewById(R.id.drawer_layout);
    }

    public void ClickMenu(View view){
        //Abrir drawer
        Tela_de_Navegacao.openDrawer(drawerLayout);
    }

    public void Clicklogo(View view){
        //Fechar drawer
        Tela_de_Navegacao.closeDrawer(drawerLayout);
    }

    public void ClickInicio(View view){
        //Redirecionar activity para inicio
        Tela_de_Navegacao.redirectActivity(this,Tela_de_Navegacao.class);
    }

    public void ClickCadastro(View view){
        //Recriar activity
        recreate();
    }

    public void ClickLogout(View view){
        //Redirecionar activity para logout
        Tela_de_Navegacao.redirectActivity(this, TelaLogout.class);
    }

    public void ClickSobre(View view){
        //Fechar app
        Tela_de_Navegacao.redirectActivity(this,Sobre.class);
    }

    public void onClickAdicionar(View view) {
        startActivityForResult(new Intent(this, LivroActivity.class), 1);
    }
}
