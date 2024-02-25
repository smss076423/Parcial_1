package com.ugb.parcial_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TabHost tbh;
    Spinner spn;
    TextView tempVal;
    Button btn;
    conversores miObj = new conversores();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tbh= findViewById(R.id.tbhParcial);
        tbh.setup();

        tbh.addTab(tbh.newTabSpec("CALC").setContent(R.id.tabcalc).setIndicator("Calc",null));
        tbh.addTab(tbh.newTabSpec("MASA").setContent(R.id.tab_Area).setIndicator("Area",null));
        btn = findViewById(R.id.btnConvertirArea);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spn=findViewById(R.id.spnArea);
                int de = spn.getSelectedItemPosition();
                spn=findViewById(R.id.spnDArea);
                int a =spn.getSelectedItemPosition();
                tempVal=findViewById(R.id.txtCantidadArea);
                double cantidad = Double.parseDouble(tempVal.getText().toString());
                double respuesta = miObj.convertir(0,de,a,cantidad);
                Toast.makeText(getApplicationContext(), "Respuesta "+ respuesta, Toast.LENGTH_SHORT).show();
            }
        });

        btn = findViewById(R.id.btncalc);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tempVal= findViewById(R.id.txtAgua);
                double agua = Double.parseDouble(tempVal.getText().toString());

                double resp=0;
                if (agua<=18){
                    resp =6;
                } else if (agua <=28) {
                    resp = ((agua -18)*0.45)+6;
                }
                else if (agua >=29){
                    resp = ((agua -18)*0.65)+6;
                }

                tempVal = findViewById(R.id.lblresp);
                tempVal.setText("Pago: $"+ resp);


            }
        });

    }


    class conversores{
        double[][] valores ={
                {1,10.763,1.43,1.19599,0.0022883295194508,0.000143082804880,0.0001}
        };
        public double convertir(int opcion,int de, int a, double cantidad){
            return valores[opcion][a]/valores[opcion][de]*cantidad;
        }
    };

}