package com.example.mybooklistapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.View;

public class Sobre extends AppCompatActivity {
    //iniciando variavél
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sobre);

        //Atribuir variavél
        drawerLayout = findViewById(R.id.drawer_layout);
    }

    public void ClickMenu(View view) {
        //Abrir drawer
        Tela_de_Navegacao.openDrawer(drawerLayout);
    }

    public void Clicklogo(View view) {
        //Fechar drawer
        Tela_de_Navegacao.closeDrawer(drawerLayout);
    }

    public void ClickInicio(View view) {
        //Redirecionar activity para inicio
        Tela_de_Navegacao.redirectActivity(this, Tela_de_Navegacao.class);
    }

    public void ClickCadastro(View view) {
        //Redirecionar activity para cadastro
        Tela_de_Navegacao.redirectActivity(this, Main.class);
    }

    public void ClickLogout(View view) {
        //Redirecionar activity para logout
        Tela_de_Navegacao.redirectActivity(this, TelaLogout.class);
    }

    public void ClickSobre(View view) {
        //Recriar activity
        recreate();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //Fechar drawer
        Tela_de_Navegacao.closeDrawer(drawerLayout);
    }
}