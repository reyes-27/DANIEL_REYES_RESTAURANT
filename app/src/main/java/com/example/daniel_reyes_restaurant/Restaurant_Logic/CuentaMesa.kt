package com.example.daniel_reyes_restaurant.Restaurant_Logic


class CuentaMesa(val mesa: Int, val aceptaPropina: Boolean = true) {
    private val items: MutableList<ItemMesa> = mutableListOf()

        fun agregarItem(itemMenu: ItemMenu, cantidad: Int) {

            val itemExistente = items.find { it.itemMenu == itemMenu }
            if (itemExistente != null) {

                itemExistente.cantidad = cantidad
            } else {

                items.add(ItemMesa(itemMenu, cantidad))
            }
        }

        fun agregarItem(itemMesa: ItemMesa) {
            items.add(itemMesa)
        }

        fun calcularTotalSinPropina(): Double {
            return items.sumOf { it.calcularSubtotal() }
        }

        fun calcularPropina(): Double {
            return if (aceptaPropina) calcularTotalSinPropina() * 0.1 else 0.0
        }

        fun calcularTotalConPropina(): Double {
            return calcularTotalSinPropina() + calcularPropina()
        }
    }
