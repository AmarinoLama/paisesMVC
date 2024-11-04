package edu.badpals.Controller;

import edu.badpals.Model.Conexion;
import edu.badpals.View.Input;
import edu.badpals.View.Output;

public class Controller {
    Conexion conexion = new Conexion();
    Input inputs = new Input();

    public void comenzar() {
        boolean salir = false;
        do {
            String respuesta = inputs.displayMenu();

            switch (respuesta){
                case "1":
                    verPaises();
                    break;
                case "2":
                    insertarPais();
                    break;
                case "3":
                    editarPais();
                    break;
                case "4":
                    eliminarPais();
                    break;
                case "5":
                    salir = inputs.preguntarSalida();
                    break;
                default:
                    Output.errorSwitch();
                    break;
            }


        }while (!salir);
    }

    private void eliminarPais(){
        conexion.borrarPais(inputs.eliminarPais());
    }

    private void editarPais(){
        conexion.modificarPais(inputs.pedirNombrePais(),inputs.pedirHabitantes(), inputs.pedirCapital(), inputs.pedirMoneda());
    }

    private void insertarPais() {
        conexion.insertPais(inputs.insertarPais());
    }

    private void verPaises() {
        Output.verPaises();
        conexion.getPaises();
    }



}