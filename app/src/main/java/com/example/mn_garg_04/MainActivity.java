package com.example.mn_garg_04;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.mn_garg_04.util.Basicas;

public class MainActivity extends AppCompatActivity {

    // Variables para controlar los cuadros de texto y donde se muestra el resultado
    private EditText etNum1;
    private EditText etNum2;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Enlazamos las variables con los elementos visuales de la pantalla usando sus IDs
        etNum1 = findViewById(R.id.etNum1);
        etNum2 = findViewById(R.id.etNum2);
        tvResult = findViewById(R.id.tvResult);

        // Enlazamos los botones de la pantalla
        Button btnAdd = findViewById(R.id.btnAdd);
        Button btnSub = findViewById(R.id.btnSub);
        Button btnMul = findViewById(R.id.btnMul);
        Button btnDiv = findViewById(R.id.btnDiv);

        // Le decimos a cada botón qué hacer cuando lo tocamos 
        // se llama al metodo performOperation enviando el símbolo de su operacion
        btnAdd.setOnClickListener(v -> performOperation('+'));
        btnSub.setOnClickListener(v -> performOperation('-'));
        btnMul.setOnClickListener(v -> performOperation('*'));
        btnDiv.setOnClickListener(v -> performOperation('/'));
    }

    // Este metodo se encarga de leer los números, hacer la operación y mostrar el resultado o un error
    private void performOperation(char op) {
        // obtenemos el texto que el usuario escribió en los cuadros
        String strNum1 = etNum1.getText().toString();
        String strNum2 = etNum2.getText().toString();

        // verificamos si alguno de los cuadros está vacío
        if (strNum1.isEmpty() || strNum2.isEmpty()) {
            // si están vacíos mostramos un pequeño mensaje (Toast) y detenemos la operación con "return"
            Toast.makeText(this, "Debe ingresar números", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            // convertimos el texto ingresado a números con decimales (Double) para poder operar
            double num1 = Double.parseDouble(strNum1);
            double num2 = Double.parseDouble(strNum2);
            double result;

            // Dependiendo del símbolo que recibimos, llamamos a la clase Basicas para calcular
            switch (op) {
                case '+':
                    result = Basicas.sumar(num1, num2);
                    break;
                case '-':
                    result = Basicas.restar(num1, num2);
                    break;
                case '*':
                    result = Basicas.multiplicar(num1, num2);
                    break;
                case '/':
                    // Antes de dividir, revisamos si el segundo número es un cero
                    if (num2 == 0) {
                        // Si es cero, avisamos con un Toast y nos salimos para no causar errores en la app
                        Toast.makeText(this, "Error: División por cero", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    result = Basicas.dividir(num1, num2);
                    break;
                default:
                    return;
            }

            // Revisamos si el resultado es un número entero (ejemplo: 4.0 es realmente igual a 4)
            if (result == (long) result) {
                // Si es entero, le quitamos los decimales para que se vea más limpio (ej. "4" en vez de "4.0")
                tvResult.setText(String.valueOf((long) result));
            } else {
                // Si tiene decimales reales (ej. "4.5"), lo mostramos tal cual
                tvResult.setText(String.valueOf(result));
            }

        } catch (NumberFormatException e) {
            // Si el usuario escribe algo raro que no se pueda convertir a número (como letras), mostramos este error
            Toast.makeText(this, "Formato de número inválido", Toast.LENGTH_SHORT).show();
        }
    }
}
