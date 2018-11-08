package com.atimports.activity;


import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.atimports.R;
import com.atimports.business.LeilaoBusinessImpl;
import com.atimports.dao.LeilaoDAO;
import com.atimports.database.LeilaoDB;
import com.atimports.model.Leilao;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {

    private FloatingActionButton fab;
    private ListView lvLeiloes;

    List<Leilao> listaLeiloes;
    ArrayAdapter adapter;

    private CompositeDisposable compositeDisposable;
    private LeilaoBusinessImpl leilaoBusiness;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //INIT VIEW
        lvLeiloes = findViewById(R.id.lv_leiloes);
        fab       = findViewById(R.id.fab_add);

        compositeDisposable = new CompositeDisposable();
        listaLeiloes = new ArrayList<>();

        //LIST VIEW
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaLeiloes);
        registerForContextMenu(lvLeiloes);
        lvLeiloes.setAdapter(adapter);

        //DATABASE
        LeilaoDB leilaoDB = LeilaoDB.getInstance(this);

        leilaoBusiness = LeilaoBusinessImpl.getInstance(leilaoDB.leilaoDAO());

        popularListViewLeiloes();


        //FLOAT BUTTON
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            startActivity(new Intent(MainActivity.this, AddActivity.class));
            }
        });


    }


    private void popularListViewLeiloes(){

        Disposable disposable = leilaoBusiness.getAllLeilao()
                                              .observeOn(AndroidSchedulers.mainThread())
                                              .subscribeOn(Schedulers.io())

                                              .subscribe(
                                                        new Consumer<List<Leilao>>() {
                                                            @Override
                                                            public void accept(List<Leilao> leiloes) throws Exception {

                                                                //TODO RETIRAR
                                                                for(int i = 0; i<20; i++){
                                                                    Leilao leilao = new Leilao();
                                                                    leilao.setId(Long.valueOf(i));
//                                                                    leilao.setNomeProduto("iphone 6");
//                                                                    leilao.setCondicao("novo");
//                                                                    leilao.setQtd(3);
//                                                                    leilao.setValorCotacaoDolar(3.89);
//                                                                    leilao.setValorFreteUsaDolar(30.0);
//                                                                    leilao.setValorFreteUsaReal(117.0);
                                                                    leiloes.add(leilao);
                                                                }
                                                                //TODO RETIRAR FIM

                                                                listaLeiloes.clear();
                                                                listaLeiloes.addAll(leiloes);
                                                                adapter.notifyDataSetChanged();

                                                            }
                                                        },

                                                        new Consumer<Throwable>() {
                                                            @Override
                                                            public void accept(Throwable throwable) throws Exception {
                                                               Toast.makeText(MainActivity.this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                                                            }
                                                        }
                                              );

        compositeDisposable.add(disposable);

    }



}

