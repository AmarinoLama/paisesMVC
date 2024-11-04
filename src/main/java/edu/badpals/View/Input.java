package edu.badpals.View;

import edu.badpals.Model.Pais;

import java.util.Objects;
import java.util.Scanner;

public class Input {

    Scanner sc = new Scanner(System.in);
    Pais pais;
    String nombrePais;

    public String displayMenu(){
        Output.printMenu();
        return sc.nextLine();
    }

    public boolean preguntarSalida() {
        Output.printSalir();
        return Objects.equals(sc.nextLine(), "s");
    }


    public String pedirNombrePais() {
        Output.pedirNombrePais();
        return sc.nextLine();
    }

    public Pais insertarPais() {
        return pedirUnPais();
    }

    public String eliminarPais() {
        Output.eliminarPais();
        return pedirNombrePais();
    }


    public Pais pedirUnPais() {
        Output.damePais();
        String nombrePais = pedirNombrePais();
        int numeroHabitantes;
        while (true) {
            Output.pedirHabitantes();
            String scRes = sc.nextLine();
            if (scRes.matches("\\d+")){
                numeroHabitantes = Integer.parseInt(scRes);
                break;
            }
            Output.errorHabitantes();
        }
        Output.pedircapital();
        String nombreCapital = sc.nextLine();
        Output.pedirMoneda();
        String nombreMoneda = sc.nextLine();
        return new Pais(nombrePais,numeroHabitantes,nombreCapital,nombreMoneda);
    }
}
