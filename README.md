# 🃏 Blackjack Console Game (Java 25)

Un proyecto simple de Blackjack en consola, desarrollado en Java 25, pensado como una implementación didáctica del juego clásico.

El objetivo es ofrecer una experiencia directa desde la terminal, respetando las reglas fundamentales del blackjack tradicional.

## 👥 Autores:
- Cristopher Q (MCris112)
- Marggio R

## 📚 Documentacion:
### Flujo del proyecto figma:

https://www.figma.com/board/phwzhhT5qpcWvmFzlJYT9M/DAW---Programacion---Blackjack?t=aUtwVckl6sPr7KKd-0

🗂️ Estructura del Proyecto
El proyecto está organizado en varios paquetes para mantener claridad y separar responsabilidades. A continuación se describe cada módulo y sus clases principales.

---

## PAQUETE: Contenido/

---

### CardRank.java

Enum que define los rangos de las cartas: AS, TWO, THREE, ..., JACK, QUEEN, KING.

### Figure.java

Enum que define los palos: PICAS, CORAZONES, DIAMANTES, TREBOLES.

### Carta.java

Representa una carta individual. Contiene:

- Palo (Figure)
- Rango (CardRank)
- Valor numérico para blackjack Incluye un método `toString()` que imprime la carta en formato ASCII.

### Mazo.java

Clase que administra la baraja completa. Funciones principales:
- `generate()`: crea las 52 cartas del mazo.
- `getRandCard()`: obtiene una carta aleatoria y la elimina del mazo.
- `giveCardToPlayers()`: reparte cartas a todos los jugadores.
- `giveCardToCrupier()`: reparte carta al crupier.
- `checkBlackjack()`: verifica si dos cartas forman un blackjack natural.
- `checkCardAvailabilityForGive()`: regenera el mazo si no hay suficientes cartas.

### Menu.java

Clase no utilizada.

### Partida.java

Clase vacía, no utilizada.

---

## PAQUETE: Entity/

---

### Crupier.java Representa al crupier. Funciones:
- Recibir cartas
- Calcular puntos totales
- Verificar si su primera carta es un AS
- Verificar si tiene blackjack

### Player.java Representa a un jugador humano o bot. Atributos:
- Nombre
- Dinero
- WalletModel (fichas disponibles)
- Betting (apuesta actual)
- Lista de cartas Funciones:
- Recibir cartas
- Calcular puntos
- Verificar blackjack
- Generar apuestas automáticas si es bot
- Obtener ganancias o perder apuesta

---

## PAQUETE: Wallet/

---

### TypeChips.java
Enum con los tipos de fichas: BLANCO (1), ROJO (5), AZUL (10), VERDE (25), NEGRO (100), MORADO (500), NARANJA (1000).

### Chips.java
Representa un tipo de ficha y cuántas tiene el jugador. Funciones:
- `add()`: añadir fichas
- `remove()`: quitar fichas
- `getTotalValue()`: valor total de ese tipo de ficha
- `valueOfType()`: valor de una ficha según su color
- `parseType()`: convierte texto en enum

### WalletModel.java
Representa el monedero del jugador. Funciones:

- startWallet(): convierte el dinero del jugador en fichas
- minusBet(): resta fichas según la apuesta
- eyeWallet(): muestra las fichas actuales 

### Betting.java
Gestiona la apuesta actual del jugador. Funciones:
- `actionBetMenu()`: menú interactivo para seleccionar fichas
- `agregarChipPorTipo()`: añade fichas a la apuesta
- `getChipPorTipo()`: obtiene o crea un tipo de ficha dentro de la apuesta
- `eyeBet()`: muestra las fichas apostadas
- `calcTotalBet()`: calcula el total apostado
- `clean()`: limpia la apuesta tras la ronda

---

## PAQUETE: Raiz
---

### Game.java
El núcleo del juego. Controla:
- Menú principal
- Registro de jugadores y bots
- Creación del crupier
- Solicitud de apuestas
- Reparto de cartas
- Turnos de jugadores
- Lógica del crupier
- Comparación de manos
- Pagos y pérdidas
- Reinicio de rondas

### Main.java
Punto de entrada del programa. Crea un objeto Game y ejecuta `showMenu()`

## Objetivo del proyecto
- Este proyecto sirve como base para:
- Practicar programación orientada a objetos en Java.
- Comprender la lógica interna del blackjack.
- Trabajar con enums, colecciones, clases y modularidad.
- Implementar un sistema de apuestas con fichas reales.
- Extender el juego con nuevas características.