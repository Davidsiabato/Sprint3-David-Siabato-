package com.example.Sprint3;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.example.Sprint3.Adaptadores.ProductAdapter;
import com.example.Sprint3.BD.DBFirebase;
import com.example.Sprint3.BD.DBHelper;
import com.example.Sprint3.Entidades.Product;
import com.example.Sprint3.Servicios.ProductService;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private DBHelper dbHelper;
    private DBFirebase dbFirebase;

    private ProductService productService;
    private ListView listViewProducts;
    private ProductAdapter productAdapter;
    private ArrayList<Product> arrayProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        arrayProducts = new ArrayList<>();
        productService = new ProductService();

        try {
            dbHelper = new DBHelper(this);
            dbFirebase = new DBFirebase();
        }catch (Exception e){
            Log.e("Error DB", e.toString());
        }


        // **** Productos para llenar

        /*
        Producto producto1 = new Producto("Producto1", "Descripcion1", 1000, R.drawable.naranja);
        Producto producto2 = new Producto("Producto2", "Descripcion2", 2000, R.drawable.papaya);
        Producto producto3 = new Producto("Producto3", "Descripcion3", 3000, R.drawable.pina);
        Producto producto4 = new Producto("Producto4", "Descripcion4", 4000, R.drawable.ciruela);
        Producto producto5 = new Producto("Producto5", "Descripcion5", 5000, R.drawable.limon);
        Producto producto6 = new Producto("Producto6", "Descripcion6", 6000, R.drawable.mango);
        Producto producto7 = new Producto("Producto7", "Descripcion7", 7000, R.drawable.pera);
        Producto producto8 = new Producto("Producto8", "Descripcion8", 8000, R.drawable.manzana);
        Producto producto9 = new Producto("Producto9", "Descripcion9", 9000, R.drawable.banana);

        dbHelper.insertData(product1);
        dbHelper.insertData(product2);
        dbHelper.insertData(product3);
        dbHelper.insertData(product4);
        dbHelper.insertData(product5);
        dbHelper.insertData(product6);
        dbHelper.insertData(product7);
        dbHelper.insertData(product8);
        dbHelper.insertData(product9);


        arrayProducts.add(product1);
        arrayProducts.add(product2);
        arrayProducts.add(product3);
        arrayProducts.add(product4);
        arrayProducts.add(product5);
        arrayProducts.add(product6);
        arrayProducts.add(product7);
        arrayProducts.add(product8);
        arrayProducts.add(product9);
        */


        // *************
        //arrayProducts = productService.cursorToArrayList(dbHelper.getData());
        listViewProducts = (ListView) findViewById(R.id.listViewProducts);
        productAdapter = new ProductAdapter(this, arrayProducts);
        listViewProducts.setAdapter(productAdapter);

        dbFirebase.getData(productAdapter, arrayProducts);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.itemAdd:
                Intent intent = new Intent(getApplicationContext(), ProductForm.class);
                startActivity(intent);
                return true;
            case R.id.itemFavorite:
                Toast.makeText(getApplicationContext(),"Favoritos",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.itemShare:
                Toast.makeText(getApplicationContext(),"Compartir",Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}