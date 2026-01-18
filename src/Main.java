import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while(true)
        {
        System.out.println("\nBienvenido a Hundir la flota, contra quien desea jugar: \n1 - Otro jugador \n2 - La maquina");
        int modo = scanner.nextInt();
        if (modo > 2 || modo < 1) System.out.println("\nEse modo no existe, elija uno que si exista\n");
        switch (modo) {
            case 1:
                JuegoMultijugador();
                break;
            case 2: 
                JuegoMaquina();
                break;
            }
        }
    }

    static void JuegoMultijugador(){
    Boolean juegoTerminado = false;
    int turnos = 200;
    int cantidadCasillasJugador1 = 17;
    int cantidadCasillasJugador2 = 17;

    // [portaaviones, acorazado, submarino, crucero, destructor]
    int[] barcosJugador2 = {5, 4, 3, 3, 2};
    int[] barcosJugador1 = {5, 4, 3, 3, 2};

    char[][] tableroJugador1 = new char[10][10];
    char[][] tableroBarcosJugador1 = new char[10][10];
    char[][] tableroJugador2 = new char[10][10];
    char[][] tableroBarcosJugador2 = new char[10][10];

    crearTableros(tableroJugador1, tableroBarcosJugador1, tableroJugador2, tableroBarcosJugador2);

    System.out.println("\nJugador 1, coloca tus barcos: \n");
    mostrarTablero(tableroBarcosJugador1);
    crearBarcos(tableroBarcosJugador1);

    // Cambiamos esta parte de juegoMaquina para que fuese multijugador 
    System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nJugador 2, coloca tus barcos: \n");
    mostrarTablero(tableroBarcosJugador2); 
    crearBarcos(tableroBarcosJugador2);
    System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");

    if (cantidadCasillasJugador1 == 0 || cantidadCasillasJugador2 == 0) {
        juegoTerminado = true;
    }

    while (!juegoTerminado) {
        for (int i = 0; i < turnos; i++) {
            System.out.println("Turno " + (i + 1) + " de Jugador 1:");
            mostrarTablero(tableroJugador1);
            cantidadCasillasJugador2 = seleccionarCasilla(tableroJugador1, tableroBarcosJugador2, cantidadCasillasJugador2, barcosJugador2);
            if (cantidadCasillasJugador2 == 0) {
                juegoTerminado = true;
                turnos = i + 1;
                break;
            }

            System.out.println("Turno " + (i + 1) + " de Jugador 2:");
            mostrarTablero(tableroJugador2);
            cantidadCasillasJugador1 = seleccionarCasilla(tableroJugador2, tableroBarcosJugador1, cantidadCasillasJugador1, barcosJugador1); // y esta tambien
            if (cantidadCasillasJugador1 == 0) {
                juegoTerminado = true;
                turnos = i + 1;
                break;
            }
        }
    }

    System.out.println("Juego terminado después de " + turnos + " turnos.");
    if (cantidadCasillasJugador2 == 0) {
        System.out.println("Jugador 1 ha ganado!");
    } else {
        System.out.println("Jugador 2 ha ganado!");
    }
    }
    
    static void JuegoMaquina(){

        Boolean juegoTerminado = false;
        int turnos = 200;
        int cantidadCasillasJugador1 = 17; // Total de casillas ocupadas por barcos jugador 1
        int cantidadCasillasJugador2 = 17; // Total de casillas ocupadas por barcos jugador 2 
        
        // [portaaviones, acorazado, submarino, crucero, destructor]
        int[] barcosJugador2 = {5, 4, 3, 3, 2};
        
        char[][] tableroJugador1 = new char[10][10];
        char[][] tableroBarcosJugador1 = new char[10][10];
        char[][] tableroJugador2 = new char[10][10];
        char[][] tableroBarcosJugador2 = new char[10][10];
        
        crearTableros(tableroJugador1, tableroBarcosJugador1, tableroJugador2, tableroBarcosJugador2);
        
        // Mostrar el tablero inicio
        
        System.out.println("\nJugador 1, coloca tus barcos: \n");
        mostrarTablero(tableroBarcosJugador1);
        crearBarcos(tableroBarcosJugador1);

        // Colocar barcos de la maquina
        ponerBarcosMaquina(tableroBarcosJugador2);
        System.out.println("Maquina ha colocado sus barcos: ");
        System.out.println();
        // mostrartableroBarcosJugador2(tableroBarcosJugador2); // Para temas de prueba
        
        if (cantidadCasillasJugador1 == 0 || cantidadCasillasJugador2 == 0) {
            juegoTerminado = true;
        }

        while (juegoTerminado == false) {
            for (int i = 0; i < turnos; i++) {
                System.out.println("Turno " + (i + 1) + " de Jugador 1:");
                mostrarTablero(tableroJugador1);
                // Seleccionar una casilla -> ahora devuelve el nuevo contador
                cantidadCasillasJugador2 = seleccionarCasilla(tableroJugador1, tableroBarcosJugador2, cantidadCasillasJugador2, barcosJugador2);
                if (cantidadCasillasJugador2 == 0) { 
                    juegoTerminado = true; 
                    turnos = i + 1;
                    break; 
                }

                System.out.println("Turno " + (i + 1) + " de Maquina:");
                mostrarTablero(tableroJugador2);
                // Seleccionar una casilla aleatoria para la maquina -> devuelve nuevo contador
                cantidadCasillasJugador1 = seleccionarCasillaMaquina(tableroJugador2, tableroBarcosJugador1, cantidadCasillasJugador1);
                if (cantidadCasillasJugador1 == 0) { 
                    juegoTerminado = true; 
                    turnos = i + 1;
                    break; 
                }   
            }  
            
        }
        
        System.out.println("Juego terminado después de " + turnos + " turnos.");
        if (cantidadCasillasJugador2 == 0) {
            System.out.println("Jugador 1 ha ganado!");
        } else {
            System.out.println("Jugador 2 ha ganado!");
        }
    }
    
    static void crearTableros (char[][] tableroJugador1, char[][] tableroBarcosJugador1, char[][] tableroJugador2, char[][] tableroBarcosJugador2){
                    for (int i = 0; i < tableroJugador1.length; i++) {
            tableroJugador1[i] = new char[10];
            for (int j = 0; j < tableroJugador1[i].length; j++) {
                tableroJugador1[i][j] = '~';
            }
        }

        for (int i = 0; i < tableroBarcosJugador1.length; i++) {
            tableroBarcosJugador1[i] = new char[10];
            for (int j = 0; j < tableroBarcosJugador1[i].length; j++) {
                tableroBarcosJugador1[i][j] = 'A';
            }
        }

        for (int i = 0; i < tableroJugador2.length; i++) {
            tableroJugador2[i] = new char[10];
            for (int j = 0; j < tableroJugador2[i].length; j++) {
                tableroJugador2[i][j] = '~';
            }
        }

        for (int i = 0; i < tableroBarcosJugador2.length; i++) {
            tableroBarcosJugador2[i] = new char[10];
            for (int j = 0; j < tableroBarcosJugador2[i].length; j++) {
                tableroBarcosJugador2[i][j] = 'A';
            }
        }
    }
    
    static void mostrarTablero(char[][] tablero) {
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                System.out.print(tablero[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static void crearBarcos(char[][] tablero) {               
        int barcosRestantes = 5; 
        int portaaviones = 5; 
        int acorazado = 4;
        int submarino = 3;
        int crucero = 3;
        int destructor = 2;
        Boolean portaavionesUsable = true;
        Boolean acorazadoUsable = true;
        Boolean submarinoUsable = true;
        Boolean cruceroUsable = true;
        Boolean destructorUsable = true;

        while (barcosRestantes != 0) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Qué barco deseas colocar (elija una opción): \nOpción    Nombre          Tamaño \n1    -    portaaviones    (5) \n2    -    acorazado       (4) \n3    -    submarino       (3) \n4    -    crucero         (3) \n5    -    destructor      (2) \n");
            int barco = scanner.nextInt();

            if(barco >= 6 || barco <= 0){
                System.out.println("Este barco no existe");
                continue;
            }
            else if (barco == 1 && !portaavionesUsable) {
                System.out.println("Ya has colocado un portaaviones.");
                continue;
            } else if (barco == 2 && !acorazadoUsable) {
                System.out.println("Ya has colocado un acorazado.");
                continue;
            } else if (barco == 3 && !submarinoUsable) {
                System.out.println("Ya has colocado un submarino.");
                continue;
            } else if (barco == 4 && !cruceroUsable) {
                System.out.println("Ya has colocado un crucero.");
                continue;
            } else if (barco == 5 && !destructorUsable) {
                System.out.println("Ya has colocado un destructor.");
                continue;  
            }

            
            int columna;
            int fila;
            int inicio;
            int fin;
            int longitudNormal;
            int longitudReversa;
            int direccion = 0;
            boolean colocable = true; 

            switch (barco){
                case 1: 
                while (direccion != 1 && direccion != 2){
                System.out.println("Hacia que dirección lo desea colocar: \n1 - Vertical \n2 - Horizontal ");
                direccion = scanner.nextInt();
                if (direccion >= 3 || direccion < 1){
                    System.out.println("Dirección no valida");
                    }
                }
                switch (direccion){
                    case 1:
                    System.out.println("Indique la columna: ");
                    columna = scanner.nextInt();
                    System.out.println("Indique desde que fila hasta que fila ");
                    inicio = scanner.nextInt();
                    fin = scanner.nextInt();
                    longitudNormal = fin - inicio + 1;
                    longitudReversa = inicio - fin + 1;
                    if (columna > 9 || columna < 0 || fin > 9 || fin < 0 || inicio < 0 || fin > 9){
                        System.out.println("Coordenadas no validas, ingrese valores correctos.");
                        break;
                    }
                    else if (longitudNormal != portaaviones && longitudReversa != portaaviones) {  
                        System.out.println("Coordenadas no validas, ingrese valores correctos.");
                        break;
                        // revisa si las coordenadas son correctas tanto normal como en reversa
                    }
                    else if (longitudReversa == portaaviones){
                        int cambio = inicio;
                        inicio = fin;
                        fin = cambio;
                        // si las coordenadas se colocaron al reves aqui se voltean
                    }

                    for (int i = inicio; i <= fin; i++) {
                        if(tablero[i][columna] == 'P' || tablero[i][columna] == 'N' || tablero[i][columna] == 'S' || tablero[i][columna] == 'C' || tablero[i][columna] == 'D'){ 
                            System.out.println("\nYa hay un barco aqui, ingrese otras cooredenadas");
                            colocable = false;
                            break;
                            // revisa si hay algun barco en las coordenadas
                        }
                    }
                    if (colocable == true) {
                            for (int j = inicio; j <= fin; j++) {
                                tablero[j][columna] = 'P';  
                                portaavionesUsable = false;
                                // coloca una letra representando al barco
                            }
                        }
                        
                    if (portaavionesUsable == false) barcosRestantes -= 1;
                    
                    break;

                    case 2: // mismo proceso pero en horizontal
                    System.out.println("Indique la fila: ");
                    fila = scanner.nextInt();
                    System.out.println("Indique desde que columna hasta que columna ");
                    inicio = scanner.nextInt();
                    fin = scanner.nextInt();
                    longitudNormal = fin - inicio + 1;
                    longitudReversa = inicio - fin + 1;

                    if (fila > 9 || fila < 0 || fin > 9 || fin < 0 || inicio < 0 || fin > 9){
                        System.out.println("Coordenadas no validas, ingrese valores correctos.");
                        break;
                    }
                    else if (longitudNormal != portaaviones && longitudReversa != portaaviones) {
                        System.out.println("Coordenadas no validas, ingrese valores correctos.");
                        break;
                    }
                    else if (longitudReversa == portaaviones){
                        int cambio = inicio;
                        inicio = fin;
                        fin = cambio;
                    }
                    for (int i = inicio; i <= fin; i++) {
                        if(tablero[fila][i] == 'P' || tablero[fila][i] == 'N' || tablero[fila][i] == 'S' || tablero[fila][i] == 'C' || tablero[fila][i] == 'D'){
                            System.out.println();
                            System.out.println("Ya hay un barco aqui, ingrese otras cooredenadas");
                            colocable = false;
                            break;
                        }
                    }
                    if (colocable == true) {
                            for (int j = inicio; j <= fin; j++) {
                                tablero[fila][j] = 'P';  
                                
                                portaavionesUsable = false;
                            }
                        }
                    if (portaavionesUsable == false) barcosRestantes -= 1;
                    break;

                }
                break;
                
                //se repite con cada barco
                case 2: 
                while (direccion != 1 && direccion != 2){
                System.out.println("1 - Vertical \n2 - Horizontal ");
                direccion = scanner.nextInt();
                if (direccion >= 3 || direccion < 1){
                    System.out.println("Direccion no valida");
                    }
                }
                switch (direccion){
                    case 1:
                    System.out.println("Indique la columna: ");
                    columna = scanner.nextInt();
                    System.out.println("Indique desde que fila hasta que fila ");
                    inicio = scanner.nextInt();
                    fin = scanner.nextInt();
                    longitudNormal = fin - inicio + 1;
                    longitudReversa = inicio - fin + 1;
                    
                    if (columna > 9 || columna < 0 || fin > 9 || fin < 0 || inicio < 0 || fin > 9){
                        System.out.println("Coordenadas no validas, ingrese valores correctos.");
                        break;
                    }
                    else if (longitudNormal != acorazado && longitudReversa != acorazado) {
                        System.out.println("Coordenadas no validas, ingrese valores correctos.");
                        break;
                    }
                    else if (longitudReversa == acorazado){
                        int cambio = inicio;
                        inicio = fin;
                        fin = cambio;
                    }
                    for (int i = inicio; i <= fin; i++) {
                        if(tablero[i][columna] == 'P' || tablero[i][columna] == 'N' || tablero[i][columna] == 'S' || tablero[i][columna] == 'C' || tablero[i][columna] == 'D'){
                            System.out.println();
                            System.out.println("Ya hay un barco aqui, ingrese otras cooredenadas");
                            colocable = false;
                            break;
                        }
                    }
                    if (colocable == true) {
                            for (int j = inicio; j <= fin; j++) {
                                tablero[j][columna] = 'N';  
                                
                                acorazadoUsable = false;
                            }
                        }
                    if (acorazadoUsable == false) barcosRestantes -= 1;
                    break;

                    case 2:
                    System.out.println("Indique la fila: ");
                    fila = scanner.nextInt();
                    System.out.println("Indique desde que columna hasta que columna ");
                    inicio = scanner.nextInt();
                    fin = scanner.nextInt();
                    longitudNormal = fin - inicio + 1;
                    longitudReversa = inicio - fin + 1;
                    
                    if (fila > 9 || fila < 0 || fin > 9 || fin < 0 || inicio < 0 || fin > 9){
                        System.out.println("Coordenadas no validas, ingrese valores correctos.");
                        break;
                    }
                    else if (longitudNormal != acorazado && longitudReversa != acorazado) {
                        System.out.println("Coordenadas no validas, ingrese valores correctos.");
                        break;
                    }
                    else if (longitudReversa == acorazado){
                        int cambio = inicio;
                        inicio = fin;
                        fin = cambio;
                    }
                    for (int i = inicio; i <= fin; i++) {
                        if(tablero[fila][i] == 'P' || tablero[fila][i] == 'N' || tablero[fila][i] == 'S' || tablero[fila][i] == 'C' || tablero[fila][i] == 'D'){
                            System.out.println();
                            System.out.println("Ya hay un barco aqui, ingrese otras cooredenadas");
                            colocable = false;
                            break;
                        }
                    }
                    if (colocable == true) {
                            for (int j = inicio; j <= fin; j++) {
                                tablero[fila][j] = 'N';  
                                
                                acorazadoUsable = false;
                            }
                        }
                    if (acorazadoUsable == false) barcosRestantes -= 1;
                    break;
                }
                break;
                                    
                case 3: 
                while (direccion != 1 && direccion != 2){
                System.out.println("1 - Vertical \n2 - Horizontal ");
                direccion = scanner.nextInt();
                if (direccion >= 3 || direccion < 1){
                    System.out.println("Direccion no valida");
                    }
                }
                switch (direccion){
                    case 1:
                    System.out.println("Indique la columna: ");
                    columna = scanner.nextInt();
                    System.out.println("Indique desde que fila hasta que fila ");
                    inicio = scanner.nextInt();
                    fin = scanner.nextInt();
                    longitudNormal = fin - inicio + 1;
                    longitudReversa = inicio - fin + 1;
                    
                    if (columna > 9 || columna < 0 || fin > 9 || fin < 0 || inicio < 0 || fin > 9){
                        System.out.println("Coordenadas no validas, ingrese valores correctos.");
                        break;
                    }
                    else if (longitudNormal != submarino && longitudReversa != submarino) {
                        System.out.println("Coordenadas no validas, ingrese valores correctos.");
                        break;
                    }
                    else if (longitudReversa == submarino){
                        int cambio = inicio;
                        inicio = fin;
                        fin = cambio;
                    }
                    for (int i = inicio; i <= fin; i++) {
                        if(tablero[i][columna] == 'P' || tablero[i][columna] == 'N' || tablero[i][columna] == 'S' || tablero[i][columna] == 'C' || tablero[i][columna] == 'D'){
                            System.out.println();
                            System.out.println("Ya hay un barco aqui, ingrese otras cooredenadas");
                            colocable = false;
                            break;
                        }
                    }
                    if (colocable == true) {
                            for (int j = inicio; j <= fin; j++) {
                                tablero[j][columna] = 'S';  
                                
                                submarinoUsable = false;
                            }
                        }
                    if (submarinoUsable == false) barcosRestantes -= 1;
                    break;

                    case 2:
                    System.out.println("Indique la fila: ");
                    fila = scanner.nextInt();
                    System.out.println("Indique desde que columna hasta que columna ");
                    inicio = scanner.nextInt();
                    fin = scanner.nextInt();
                    longitudNormal = fin - inicio + 1;
                    longitudReversa = inicio - fin + 1;
                    
                    if (fila > 9 || fila < 0 || fin > 9 || fin < 0 || inicio < 0 || fin > 9){
                        System.out.println("Coordenadas no validas, ingrese valores correctos.");
                        break;
                    }
                    else if (longitudNormal != submarino && longitudReversa != submarino) {
                        System.out.println("Coordenadas no validas, ingrese valores correctos.");
                        break;
                    }
                    else if (longitudReversa == submarino){
                        int cambio = inicio;
                        inicio = fin;
                        fin = cambio;
                    }
                    for (int i = inicio; i <= fin; i++) {
                        if(tablero[fila][i] == 'P' || tablero[fila][i] == 'N' || tablero[fila][i] == 'S' || tablero[fila][i] == 'C' || tablero[fila][i] == 'D'){
                            System.out.println();
                            System.out.println("Ya hay un barco aqui, ingrese otras cooredenadas");
                            colocable = false;
                            break;
                        }
                    }
                    if (colocable == true) {
                            for (int j = inicio; j <= fin; j++) {
                                tablero[fila][j] = 'S';  
                                
                                submarinoUsable = false;
                            }
                        }
                    if (submarinoUsable == false) barcosRestantes -= 1;
                    break;
                }
                break;
                            
                case 4: 
                while (direccion != 1 && direccion != 2){
                System.out.println("1 - Vertical \n2 - Horizontal ");
                direccion = scanner.nextInt();
                if (direccion >= 3 || direccion < 1){
                    System.out.println("Direccion no valida");
                    }
                }
                switch (direccion){
                    case 1:
                    System.out.println("Indique la columna: ");
                    columna = scanner.nextInt();
                    System.out.println("Indique desde que fila hasta que fila ");
                    inicio = scanner.nextInt();
                    fin = scanner.nextInt();
                    longitudNormal = fin - inicio + 1;
                    longitudReversa = inicio - fin + 1;
                    
                    if (columna > 9 || columna < 0 || fin > 9 || fin < 0 || inicio < 0 || fin > 9){
                        System.out.println("Coordenadas no validas, ingrese valores correctos.");
                        break;
                    }
                    else if (longitudNormal != crucero && longitudReversa != crucero) {
                        System.out.println("Coordenadas no validas, ingrese valores correctos.");
                        break;
                    }
                    else if (longitudReversa == crucero){
                        int cambio = inicio;
                        inicio = fin;
                        fin = cambio;
                    }
                    for (int i = inicio; i <= fin; i++) {
                        if(tablero[i][columna] == 'P' || tablero[i][columna] == 'N' || tablero[i][columna] == 'S' || tablero[i][columna] == 'C' || tablero[i][columna] == 'D'){
                            System.out.println();
                            System.out.println("Ya hay un barco aqui, ingrese otras cooredenadas");
                            colocable = false;
                            break;
                        }
                    }
                    if (colocable == true) {
                            for (int j = inicio; j <= fin; j++) {
                                tablero[j][columna] = 'C';  
                                
                                cruceroUsable = false;
                            }
                        }
                    if (cruceroUsable == false) barcosRestantes -= 1;
                    break;

                    case 2:
                    System.out.println("Indique la fila: ");
                    fila = scanner.nextInt();
                    System.out.println("Indique desde que columna hasta que columna ");
                    inicio = scanner.nextInt();
                    fin = scanner.nextInt();
                    longitudNormal = fin - inicio + 1;
                    longitudReversa = inicio - fin + 1;
                    
                    if (fila > 9 || fila < 0 || fin > 9 || fin < 0 || inicio < 0 || fin > 9){
                        System.out.println("Coordenadas no validas, ingrese valores correctos.");
                        break;
                    }
                    else if (longitudNormal != crucero && longitudReversa != crucero) {
                        System.out.println("Coordenadas no validas, ingrese valores correctos.");
                        break;
                    }
                    else if (longitudReversa == crucero){
                        int cambio = inicio;
                        inicio = fin;
                        fin = cambio;
                    }
                    for (int i = inicio; i <= fin; i++) {
                        if(tablero[fila][i] == 'P' || tablero[fila][i] == 'N' || tablero[fila][i] == 'S' || tablero[fila][i] == 'C' || tablero[fila][i] == 'D'){
                            System.out.println();
                            System.out.println("Ya hay un barco aqui, ingrese otras cooredenadas");
                            colocable = false;
                            break;
                        }
                    }
                    if (colocable == true) {
                            for (int j = inicio; j <= fin; j++) {
                                tablero[fila][j] = 'C';  
                                
                                cruceroUsable = false;
                            }
                        }
                    if (cruceroUsable == false) barcosRestantes -= 1;
                    break;
                }
                break;
                            
                case 5: 
                while (direccion != 1 && direccion != 2){
                System.out.println("1 - Vertical \n2 - Horizontal ");
                direccion = scanner.nextInt();
                if (direccion >= 3 || direccion < 1){
                    System.out.println("Direccion no valida");
                    }
                }
                switch (direccion){
                    case 1:
                    System.out.println("Indique la columna: ");
                    columna = scanner.nextInt();
                    System.out.println("Indique desde que fila hasta que fila ");
                    inicio = scanner.nextInt();
                    fin = scanner.nextInt();
                    longitudNormal = fin - inicio + 1;
                    longitudReversa = inicio - fin + 1;
                    
                    if (columna > 9 || columna < 0 || fin > 9 || fin < 0 || inicio < 0 || fin > 9){
                        System.out.println("Coordenadas no validas, ingrese valores correctos.");
                        break;
                    }
                    else if (longitudNormal != destructor && longitudReversa != destructor) {
                        System.out.println("Coordenadas no validas, ingrese valores correctos.");
                        break;
                    }
                    else if (longitudReversa == destructor){
                        int cambio = inicio;
                        inicio = fin;
                        fin = cambio;
                    }
                    for (int i = inicio; i <= fin; i++) {
                        if(tablero[i][columna] == 'P' || tablero[i][columna] == 'N' || tablero[i][columna] == 'S' || tablero[i][columna] == 'C' || tablero[i][columna] == 'D'){
                            System.out.println();
                            System.out.println("Ya hay un barco aqui, ingrese otras cooredenadas");
                            colocable = false;
                            break;
                        }
                    }
                    if (colocable == true) {
                            for (int j = inicio; j <= fin; j++) {
                                tablero[j][columna] = 'D';  
                                
                                destructorUsable = false;
                            }
                        }
                    if (destructorUsable == false) barcosRestantes -= 1;
                    break;

                    case 2:
                    System.out.println("Indique la fila: ");
                    fila = scanner.nextInt();
                    System.out.println("Indique desde que columna hasta que columna ");
                    inicio = scanner.nextInt();
                    fin = scanner.nextInt();
                    longitudNormal = fin - inicio + 1;
                    longitudReversa = inicio - fin + 1;
                    
                    if (fila > 9 || fila < 0 || fin > 9 || fin < 0 || inicio < 0 || fin > 9){
                        System.out.println("Coordenadas no validas, ingrese valores correctos.");
                        break;
                    }
                    else if (longitudNormal != destructor && longitudReversa != destructor) {
                        System.out.println("Coordenadas no validas, ingrese valores correctos.");
                        break;
                    }
                    else if (longitudReversa == destructor){
                        int cambio = inicio;
                        inicio = fin;
                        fin = cambio;
                    }
                    for (int i = inicio; i <= fin; i++) {
                        if(tablero[fila][i] == 'P' || tablero[fila][i] == 'N' || tablero[fila][i] == 'S' || tablero[fila][i] == 'C' || tablero[fila][i] == 'D'){
                            System.out.println();
                            System.out.println("Ya hay un barco aqui, ingrese otras cooredenadas");
                            colocable = false;
                            break;
                        }
                    }
                    if (colocable == true) {
                            for (int j = inicio; j <= fin; j++) {
                                tablero[fila][j] = 'D';  
                                
                                destructorUsable = false;
                            }
                        }
                    if (destructorUsable == false) barcosRestantes -= 1;
                    break;
                }
                break;
            }
            
            // Comprobar
            if (barcosRestantes != 0){
            System.out.println("Barcos Jugador: ");
            mostrarTablero(tablero);}

        }
    }
    
    // Reemplazada: ahora recibe también el tablero de barcos del oponente
    static void revelarBarco(char[][] tableroVis, char[][] tableroBarcosOponente, char tipoBarco) {
        for (int i = 0; i < tableroVis.length; i++) {
            for (int j = 0; j < tableroVis[i].length; j++) {
                if (tableroBarcosOponente[i][j] == tipoBarco) {
                    tableroVis[i][j] = tipoBarco;
                }
            }
        }
    }

    // Cambiar firma para devolver la cantidad actualizada
    static int seleccionarCasilla(char[][] tableroJugador1, char[][] tableroBarcosJugador2, int cantidadCasillasJugador2, int[] barcos) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduce la fila (0-9): ");
        int fila = scanner.nextInt();
        System.out.print("Introduce la columna (0-9): ");
        int columna = scanner.nextInt();

        if (fila >= 0 && fila < 10 && columna >= 0 && columna < 10) {

            while (tableroJugador1[fila][columna] == 'X' || tableroJugador1[fila][columna] == 'A' ||
                   tableroJugador1[fila][columna] == 'P' || tableroJugador1[fila][columna] == 'N' ||
                   tableroJugador1[fila][columna] == 'S' || tableroJugador1[fila][columna] == 'C' ||
                   tableroJugador1[fila][columna] == 'D') {
                System.out.println("Casilla ya seleccionada, elige otra.");
                System.out.print("Introduce la fila (0-9): ");
                fila = scanner.nextInt();
                System.out.print("Introduce la columna (0-9): ");
                columna = scanner.nextInt();
            }

            if (tableroBarcosJugador2[fila][columna] == 'P') {
                System.out.println("Has acertado");
                tableroJugador1[fila][columna] = 'X';
                cantidadCasillasJugador2 -= 1;
                barcos[0] -= 1;
                if (barcos[0] == 0) {
                    // llamada actualizada para usar el tablero de barcos del oponente
                    revelarBarco(tableroJugador1, tableroBarcosJugador2, 'P');
                    System.out.println("Portaaviones hundido");
                }
                mostrarTablero(tableroJugador1);
                cantidadCasillasJugador2 = seleccionarCasilla(tableroJugador1, tableroBarcosJugador2, cantidadCasillasJugador2, barcos);
            } else if (tableroBarcosJugador2[fila][columna] == 'N') {
                System.out.println("Has acertado");
                tableroJugador1[fila][columna] = 'X';
                cantidadCasillasJugador2 -= 1;
                barcos[1] -= 1;
                if (barcos[1] == 0) {
                    revelarBarco(tableroJugador1, tableroBarcosJugador2, 'N');
                    System.out.println("Acorazado hundido");
                }
                mostrarTablero(tableroJugador1);
                cantidadCasillasJugador2 = seleccionarCasilla(tableroJugador1, tableroBarcosJugador2, cantidadCasillasJugador2, barcos);
            } else if (tableroBarcosJugador2[fila][columna] == 'S') {
                System.out.println("Has acertado");
                tableroJugador1[fila][columna] = 'X';
                cantidadCasillasJugador2 -= 1;
                barcos[2] -= 1;
                if (barcos[2] == 0) {
                    revelarBarco(tableroJugador1, tableroBarcosJugador2, 'S');
                    System.out.println("Submarino hundido");
                }
                mostrarTablero(tableroJugador1);
                cantidadCasillasJugador2 = seleccionarCasilla(tableroJugador1, tableroBarcosJugador2, cantidadCasillasJugador2, barcos);
            } else if (tableroBarcosJugador2[fila][columna] == 'C') {
                System.out.println("Has acertado");
                tableroJugador1[fila][columna] = 'X';
                cantidadCasillasJugador2 -= 1;
                barcos[3] -= 1;
                if (barcos[3] == 0) {
                    revelarBarco(tableroJugador1, tableroBarcosJugador2, 'C');
                    System.out.println("Crucero hundido");
                }
                mostrarTablero(tableroJugador1);
                cantidadCasillasJugador2 = seleccionarCasilla(tableroJugador1, tableroBarcosJugador2, cantidadCasillasJugador2, barcos);
            } else if (tableroBarcosJugador2[fila][columna] == 'D') {
                System.out.println("Has acertado");
                tableroJugador1[fila][columna] = 'X';
                cantidadCasillasJugador2 -= 1;
                barcos[4] -= 1;
                if (barcos[4] == 0) {
                    revelarBarco(tableroJugador1, tableroBarcosJugador2, 'D');
                    System.out.println("Destructor hundido");
                }
                mostrarTablero(tableroJugador1);
                cantidadCasillasJugador2 = seleccionarCasilla(tableroJugador1, tableroBarcosJugador2, cantidadCasillasJugador2, barcos);
            } else {
                System.out.println("Has Fallado");
                tableroJugador1[fila][columna] = 'A';
                mostrarTablero(tableroJugador1);
            }
        } else {
            System.out.println("Coordenadas inválidas");
        }
        return cantidadCasillasJugador2;
    }

    static int seleccionarCasillaMaquina(char[][] tableroJugador2, char[][] tableroBarcosJugador1, int cantidadCasillasJugador1) {
        int fila = (int) (Math.random() * 10);
        int columna = (int) (Math.random() * 10);

        if (fila >= 0 && fila < 10 && columna >= 0 && columna < 10) {

            while (tableroJugador2[fila][columna] == 'X' || tableroJugador2[fila][columna] == 'A') {
                fila = (int) (Math.random() * 10);
                columna = (int) (Math.random() * 10);
            }

            if (tableroBarcosJugador1[fila][columna] == 'V') {
                System.out.println("Maquina ha acertado");
                System.out.println();
                tableroJugador2[fila][columna] = 'X';
                cantidadCasillasJugador1 -= 1;
                mostrarTablero(tableroJugador2);
                seleccionarCasillaMaquina(tableroJugador2, tableroBarcosJugador1, cantidadCasillasJugador1);
            } else {
                System.out.println("Maquina ha fallado");
                System.out.println();
                tableroJugador2[fila][columna] = 'A';
            }
            mostrarTablero(tableroJugador2);
        } else {
            System.out.println("Coordenadas inválidas");
        }
        return cantidadCasillasJugador1;
    }

    static void mostrartableroBarcosJugador2(char[][] tableroBarcosJugador2) {
        for (int i = 0; i < tableroBarcosJugador2.length; i++) {
            for (int j = 0; j < tableroBarcosJugador2[i].length; j++) {
                System.out.print(tableroBarcosJugador2[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static void ponerBarcosMaquina(char[][] tableroBarcosJugador2) {
        int portaaviones = 5;
        int acorazado = 4;
        int submarino = 3;
        int crucero = 3;
        int destructor = 2;
        Boolean portaavionesUsable = true;
        Boolean acorazadoUsable = true;
        Boolean submarinoUsable = true;
        Boolean cruceroUsable = true;
        Boolean destructorUsable = true;

        // Colocar cada barco asegurando que no se solapen
        while (portaavionesUsable) {
            if (intentarColocar(tableroBarcosJugador2, portaaviones, true)) portaavionesUsable = false;
        }

        while (acorazadoUsable) {
            if (intentarColocar(tableroBarcosJugador2, acorazado, true)) acorazadoUsable = false;
        }

        while (submarinoUsable) {
            if (intentarColocar(tableroBarcosJugador2, submarino, submarinoUsable)) submarinoUsable = false;
        }

        while (cruceroUsable) {
            if (intentarColocar(tableroBarcosJugador2, crucero, submarinoUsable)) cruceroUsable = false;
        }

        while (destructorUsable) {
            if (intentarColocar(tableroBarcosJugador2, destructor, true)) destructorUsable = false;
        }

        // mostrartableroBarcosJugador2(tableroBarcosJugador2);
    }

    // Intenta colocar un barco en el tableroBarcosJugador2 sin solapar
    static boolean intentarColocar(char[][] tableroBarcosJugador2, int tamaño, Boolean barco) {
        int n = tableroBarcosJugador2.length;
        int maxIntentos = 1000; // evita bucles infinitos en casos improbables
        int portaaviones = 5;
        int acorazado = 4;
        int submarino = 3;
        int crucero = 3;
        int destructor = 2;
        for (int intento = 0; intento < maxIntentos; intento++) {
            int direccion = Math.random() < 0.5 ? 1 : 2; // 1 = vertical, 2 = horizontal
            int inicio = (int) (Math.random() * n);
            if (direccion == 1) { // vertical
                int columna = (int) (Math.random() * n); // elegir columna aleatoria
                int inicioFila, finalFila; // definir filas de inicio y fin
                if (inicio + tamaño <= n) { // cabe hacia abajo?
                    inicioFila = inicio; // fila inicial
                    finalFila = inicio + tamaño - 1; // fila final
                } else { // cabe hacia arriba?
                    finalFila = inicio; // fila final
                    inicioFila = inicio - tamaño + 1; // fila inicial
                    if (inicioFila < 0) continue; // fuera de rango, intentar de nuevo
                }
                if (espacioLibreVertical(tableroBarcosJugador2, columna, inicioFila, finalFila)) { // verificar espacio libre
                    
                    if (tamaño == 5) {
                        for (int i = inicioFila; i <= finalFila; i++) tableroBarcosJugador2[i][columna] = 'P'; // colocar barco
                    } else if (tamaño == 4) {
                        for (int i = inicioFila; i <= finalFila; i++) tableroBarcosJugador2[i][columna] = 'N'; // colocar barco
                    } else if (tamaño == 3) {
                        if (barco == true){
                        for (int i = inicioFila; i <= finalFila; i++) tableroBarcosJugador2[i][columna] = 'S'; // colocar barco
                        barco = false;
                        } else {
                        for (int i = inicioFila; i <= finalFila; i++) tableroBarcosJugador2[i][columna] = 'C'; // colocar barco
                        }
                    } else if (tamaño == 2) {
                        for (int i = inicioFila; i <= finalFila; i++) tableroBarcosJugador2[i][columna] = 'D'; // colocar barco
                    }
                    return true; // barco colocado con éxito
                }
            } else { // horizontal
                int fila = (int) (Math.random() * n); // elegir fila aleatoria
                int inicioColumna, finalColumna; // definir columnas de inicio y fin
                if (inicio + tamaño <= n) { // cabe hacia la derecha?
                    inicioColumna = inicio; // columna inicial
                    finalColumna = inicio + tamaño - 1; // columna final
                } else { // cabe hacia la izquierda?
                    finalColumna = inicio; // columna final
                    inicioColumna = inicio - tamaño + 1; // columna inicial
                    if (inicioColumna < 0) continue; // fuera de rango, intentar de nuevo
                }
                if (espacioLibreHorizontal(tableroBarcosJugador2, fila, inicioColumna, finalColumna)) { // verificar espacio libre
                    
                    if (tamaño == 5) {
                        for (int i = inicioColumna; i <= finalColumna; i++) tableroBarcosJugador2[fila][i] = 'P'; // colocar barco
                    } else if (tamaño == 4) {
                        for (int i = inicioColumna; i <= finalColumna; i++) tableroBarcosJugador2[fila][i] = 'N'; // colocar barco
                    } else if (tamaño == 3) {
                        if (barco == true) {
                        for (int i = inicioColumna; i <= finalColumna; i++) tableroBarcosJugador2[fila][i] = 'S'; // colocar barco
                        barco = false;
                        } else {
                        for (int i = inicioColumna; i <= finalColumna; i++) tableroBarcosJugador2[fila][i] = 'C'; // colocar barco
                        } 
                    } else if (tamaño == 2) {
                        for (int i = inicioColumna; i <= finalColumna; i++) tableroBarcosJugador2[fila][i] = 'D'; // colocar barco
                    }
                    return true; // barco colocado con éxito
                }
            }
        }
        return false;
    }

    static boolean espacioLibreVertical(char[][] tableroBarcosJugador2, int columna, int inicioFila, int finalFila) { // verifica espacio libre vertical
        for (int i = inicioFila; i <= finalFila; i++) { // recorrer filas
            if (tableroBarcosJugador2[i][columna] != 'A') return false; // si no está libre, devolver false
        }
        return true;
    }

    static boolean espacioLibreHorizontal(char[][] tableroBarcosJugador2, int fila, int inicioColumna, int finalColumna) { // verifica espacio libre horizontal
        for (int j = inicioColumna; j <= finalColumna; j++) { // recorrer columnas
            if (tableroBarcosJugador2[fila][j] != 'A') return false; // si no está libre, devolver false
        }
        return true;
    }
}
