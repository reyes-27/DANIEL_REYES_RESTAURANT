package com.example.daniel_reyes_restaurant.Restaurant_Logic

class ItemMesa(val itemMenu: ItemMenu, var cantidad: Int) {
    fun calcularSubtotal(): Double {
        return cantidad * itemMenu.precio
    }
}