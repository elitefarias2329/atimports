package com.atimports.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.atimports.R;
import com.atimports.business.LoteBusinessImpl;
import com.atimports.database.ATImportsDataBase;
import com.atimports.model.Lote;

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

    List<Lote> listaLeiloes;
    ArrayAdapter adapter;

    private CompositeDisposable compositeDisposable;
    private LoteBusinessImpl loteBusiness;


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
        ATImportsDataBase aTImportsDataBase = ATImportsDataBase.getInstance(this);
        loteBusiness = LoteBusinessImpl.getInstance(aTImportsDataBase.loteDAO());

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

        Disposable disposable = loteBusiness.getAllLote()
                                              .observeOn(AndroidSchedulers.mainThread())
                                              .subscribeOn(Schedulers.io())
                                              .subscribe(
                                                      new Consumer<List<Lote>>() {
                                                            @Override
                                                            public void accept(List<Lote> leiloes) throws Exception {

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

