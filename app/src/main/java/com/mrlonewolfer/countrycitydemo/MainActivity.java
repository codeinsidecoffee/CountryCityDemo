package com.mrlonewolfer.countrycitydemo;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Spinner spinnerCountry,spinnerRegion,spinnercity;
    TextView txtLongitude,txtLatitude;
   List<CountryRoot> listCountry;
   List<Region> listRegion;
    List<City> listCity;
    String countrycode,regioncode;
   String regionUrl,cityUrl;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinnerCountry=findViewById(R.id.spinnerCountry);
        spinnerRegion=findViewById(R.id.spinnerRegion);
        spinnercity=findViewById(R.id.spinnerCity);
        txtLongitude=findViewById(R.id.txtLongitude);
        txtLatitude=findViewById(R.id.txtLatitude);
        context=this;

        fillCounryData();

        spinnerCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                countrycode=listCountry.get(position).getCode();

                MyRetrofitServices myRetrofitServices=MyRetrofitClient.getClient();
                regionUrl="https://battuta.medunes.net/api/region/"+countrycode+"/all/?key=00000000000000000000000000000000";
                //Log.e("HEKK", "onItemSelected: "+regionUrl );
                Call<List<Region>> call=myRetrofitServices.selectRegion(regionUrl);
              call.enqueue(new Callback<List<Region>>() {
                  @Override
                  public void onResponse(Call<List<Region>> call, Response<List<Region>> response) {
                      listRegion =response.body();

                      ArrayAdapter<Region> arrayAdapter=new ArrayAdapter<>(context,android.R.layout.simple_list_item_1,listRegion);
                      spinnerRegion.setAdapter(arrayAdapter);

                  }

                  @Override
                  public void onFailure(Call<List<Region>> call, Throwable t) {

                  }
              });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerRegion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                MyRetrofitServices myRetrofitServices=MyRetrofitClient.getClient();
                regioncode=listRegion.get(position).getRegion();
                countrycode=listRegion.get(position).getCountry();

                cityUrl="https://battuta.medunes.net/api/city/"+countrycode+"/search/?region="+regioncode+"&key=00000000000000000000000000000000";
                Log.e("HEKK", "onItemSelected: "+cityUrl );
                Call<List<City>> call=myRetrofitServices.selectCity(cityUrl);
                call.enqueue(new Callback<List<City>>() {
                    @Override
                    public void onResponse(Call<List<City>> call, Response<List<City>> response) {
                        listCity =response.body();

                        ArrayAdapter<City> arrayAdapter=new ArrayAdapter<>(context,android.R.layout.simple_list_item_1,listCity);
                        spinnercity.setAdapter(arrayAdapter);
                    }

                    @Override
                    public void onFailure(Call<List<City>> call, Throwable t) {

                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnercity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    txtLongitude.setText(listCity.get(position).longitude);
                    txtLatitude.setText(listCity.get(position).latitude);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void fillCounryData() {
        MyRetrofitServices myRetrofitServices=MyRetrofitClient.getClient();
        Call<List<CountryRoot>> call=myRetrofitServices.selectCountry(Const.CountryURL);
        call.enqueue(new Callback<List<CountryRoot>>() {
            @Override
            public void onResponse(Call<List<CountryRoot>> call, Response<List<CountryRoot>> response) {

               listCountry =response.body();

            ArrayAdapter<CountryRoot> arrayAdapter=new ArrayAdapter<>(context,android.R.layout.simple_list_item_1,listCountry);
                spinnerCountry.setAdapter(arrayAdapter);


            }

            @Override
            public void onFailure(Call<List<CountryRoot>> call, Throwable t) {

            }
        });

    }
}
