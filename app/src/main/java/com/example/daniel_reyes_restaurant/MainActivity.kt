package com.example.daniel_reyes_restaurant

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.widget.*
import androidx.core.widget.addTextChangedListener
import com.example.daniel_reyes_restaurant.Restaurant_Logic.CuentaMesa
import com.example.daniel_reyes_restaurant.Restaurant_Logic.ItemMenu

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val miswitch = findViewById<Switch>(R.id.miswitch)
        val cantidad_cazuela = findViewById<EditText>(R.id.cantidad_cazuela)
        val cantidad_choclo = findViewById<EditText>(R.id.cantidad_choclo)
        val pastel_de_choclo = ItemMenu(nombre = "Pastel de Choclo", precio = 12000.0)
        val propina_text=findViewById<TextView>(R.id.propina)
        val comida_text = findViewById<TextView>(R.id.comida_text)
        var totalText = findViewById<TextView>(R.id.total_text)
        val cazuela = ItemMenu(nombre = "Cazuela", precio = 10000.0)
        val cuenta = CuentaMesa(mesa = 1)
        fun actualizarTotal() {
            cuenta.agregarItem(pastel_de_choclo, cantidad_choclo.text.toString().toIntOrNull() ?: 0)
            cuenta.agregarItem(cazuela, cantidad_cazuela.text.toString().toIntOrNull() ?: 0)

            val total = if (miswitch.isChecked) {
                cuenta.calcularTotalConPropina()
            } else {
                cuenta.calcularTotalSinPropina()
            }
            propina_text.text = "$${cuenta.calcularTotalSinPropina() * 0.1}"
            totalText.text = "$${total.toInt()}"
            comida_text.text = "$${cuenta.calcularTotalSinPropina()}"
        }

        // Listeners
        cantidad_choclo.addTextChangedListener {

            actualizarTotal()
            Log.d("DEBUG", "Cantidad choclo cambiado ${cuenta.calcularTotalConPropina()}")
        }
        cantidad_cazuela.addTextChangedListener { actualizarTotal() }
        miswitch.setOnCheckedChangeListener { _, _ -> actualizarTotal() }
    }

}
