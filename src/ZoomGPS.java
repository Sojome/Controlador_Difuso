/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Aaron Jaramillo
 */
public class ZoomGPS {

    public static void main(String[] arg) {
        System.out.println("Logica difusa: caso del zoom de un GPS");

        //Creacion del sistema
        ControladorDifuso controlador = new ControladorDifuso("Gestion del zoom de un GPS");
        System.out.println("Agregar las variables de entrada");
        // Variable linguistica de entrada:
        // distancia (en m, de 0 a 500 000)
        VariableLinguistica distancia = new VariableLinguistica("Distancia", 0, 500000);
        distancia.AgregarValorLinguistico(new ValorLinguistico("Pequeña", new ConjuntoDifusoTrapecioIzquierda(0, 500000, 30, 50)));
        distancia.AgregarValorLinguistico(new ValorLinguistico("Media", new ConjuntoDifusoTrapecio(0, 500000, 40, 50, 100, 150)));
        distancia.AgregarValorLinguistico(new ValorLinguistico("Grande", new ConjuntoDifusoTrapecioDerecha(0, 500000, 100, 150)));
        controlador.AgregarVariableEntrada(distancia);
        // Variable linguistica de entrada:
        // velocidad (en km/h, de 0 a 200)
        VariableLinguistica velocidad = new VariableLinguistica("Velocidad", 0, 200);
        velocidad.AgregarValorLinguistico(new ValorLinguistico("Lenta", new ConjuntoDifusoTrapecioIzquierda(0, 200, 20, 30)));
        velocidad.AgregarValorLinguistico(new ValorLinguistico("Media", new ConjuntoDifusoTrapecio(0, 200, 20, 30, 70, 80)));
        velocidad.AgregarValorLinguistico(new ValorLinguistico("Rapida", new ConjuntoDifusoTrapecio(0, 200, 70, 80, 90, 110)));
        velocidad.AgregarValorLinguistico(new ValorLinguistico("MuyRapida", new ConjuntoDifusoTrapecioDerecha(0, 200, 90, 110)));
        controlador.AgregarVariableEntrada(velocidad);
        System.out.println("Agregar las variables de salida");
        // Variable linguistica de salida: nivel de zoom (de 1 a 5)
        VariableLinguistica zoom = new VariableLinguistica("Zoom", 0, 5);
        zoom.AgregarValorLinguistico(new ValorLinguistico("Bajo", new ConjuntoDifusoTrapecioIzquierda(0, 5, 1, 2)));
        zoom.AgregarValorLinguistico(new ValorLinguistico("Normal", new ConjuntoDifusoTrapecio(0, 5, 1, 2, 3, 4)));
        zoom.AgregarValorLinguistico(new ValorLinguistico("Grande", new ConjuntoDifusoTrapecioIzquierda(0, 5, 3, 4)));
        controlador.AgregarVariableSalida(zoom);
        System.out.println("Agregar las reglas");
        // Se agregan las distintas reglas (9 para cubrir los 12 casos)
        controlador.AgregarRegla("IF Distancia IS Grande THEN Zoom IS Bajo");
        controlador.AgregarRegla("IF Distancia IS Pequeña AND Velocidad IS Lenta THEN Zoom IS Normal");
        controlador.AgregarRegla("IF Distancia IS Pequeña AND Velocidad IS Media THEN Zoom IS Normal");
        controlador.AgregarRegla("IF Distancia IS Pequeña AND Velocidad IS Rapida THEN Zoom IS Grande");
        controlador.AgregarRegla("IF Distancia IS Pequeña AND Velocidad IS MuyRapida THEN Zoom IS Grande");
        controlador.AgregarRegla("IF Distancia IS Media AND Velocidad IS Lenta THEN Zoom IS Bajo");
        controlador.AgregarRegla("IF Distancia IS Media AND Velocidad IS Media THEN Zoom IS Normal");
        controlador.AgregarRegla("IF Distancia IS Media AND Velocidad IS Rapida THEN Zoom IS Normal");
        controlador.AgregarRegla("IF Distancia IS Media AND Velocidad IS MuyRapida THEN Zoom IS Grande");
        System.out.println("Resolucion de casos practicos");
        // Caso practico 1: velocidad 35 km/h,
        // y proximo cambio de direccion a 70 m
        System.out.println("Caso 1:");
        controlador.AgregarValorNumerico(velocidad, 35.0);
        controlador.AgregarValorNumerico(distancia, 70.0);
        System.out.println("Resultado: " + controlador.Resolver() + "\n");

        // Caso practico 2: velocidad 25 km/h,
        // y proximo cambio de direccion a 70 m
        controlador.BorrarValorNumericos();
        System.out.println("Caso 2:");
        controlador.AgregarValorNumerico(velocidad, 25);
        controlador.AgregarValorNumerico(distancia, 70);
        System.out.println("Resultado: " + controlador.Resolver() + "\n");

        // Caso practico 3: velocidad 72.5 km/h,
        // y proximo cambio de direccion a 40 m
        controlador.BorrarValorNumericos();
        System.out.println("Caso 3:");
        controlador.AgregarValorNumerico(velocidad, 72.5);
        controlador.AgregarValorNumerico(distancia, 40);
        System.out.println("Resultado: " + controlador.Resolver() + "\n");

        // Caso practico 4: velocidad 100 km/h,
        // y proximo cambio de direccion a 110 m
        controlador.BorrarValorNumericos();
        System.out.println("Caso 4:");
        controlador.AgregarValorNumerico(velocidad, 100);
        controlador.AgregarValorNumerico(distancia, 110);
        System.out.println("Resultado: " + controlador.Resolver() + "\n");

        // Caso practico 5: velocidad 45 km/h,
        // y proximo cambio de direccion a 160 m
        controlador.BorrarValorNumericos();
        System.out.println("Caso 5:");
        controlador.AgregarValorNumerico(velocidad, 45);
        controlador.AgregarValorNumerico(distancia, 160);
        System.out.println("Resultado: " + controlador.Resolver() + "\n");
    }
}
