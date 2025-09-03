package ControlAsistenciaEstudiantes;

import java.util.Scanner;

public class ControlAsistenciaEstudiantes {


final static int DIAS_SEMANA = 5;
final static int NUM_ESTUDIANTES = 4;

public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    
    System.out.println("Sistema de Control de Asistencia");
    System.out.println("Coordinador Academico\n");
    
    char[][] asistencia = new char[NUM_ESTUDIANTES][DIAS_SEMANA];
    
    String[] estudiantes = {"Estudiante 1", "Estudiante 2", "Estudiante 3", "Estudiante 4"};
    String[] dias = {"Lunes", "Martes", "Miercoles", "Jueves", "Viernes"};
    
    int opcion = 0;
    
    while (opcion != 4) {
        System.out.println("===== MENU =====");
        System.out.println("1. Ver asistencia individual");
        System.out.println("2. Ver resumen general");
        System.out.println("3. Volver a registrar");
        System.out.println("4. Salir");
        System.out.print("Opcion: ");
        opcion = sc.nextInt();
        sc.nextLine();
        
        if (opcion == 1) {
            System.out.println("\nEstudiantes:");
            for (int i = 0; i < NUM_ESTUDIANTES; i++) {
                System.out.println((i + 1) + ". " + estudiantes[i]);
            }
            System.out.print("Seleccione estudiante: ");
            int num = sc.nextInt();
            sc.nextLine();
            
            if (num >= 1 && num <= NUM_ESTUDIANTES) {
                int est = num - 1;
                System.out.println("\n" + estudiantes[est] + ":");
                
                int presentes = 0;
                int ausentes = 0;
                
                for (int d = 0; d < DIAS_SEMANA; d++) {
                    char estado = asistencia[est][d];
                    if (estado == 'P') {
                        System.out.println(dias[d] + ": Presente");
                        presentes = presentes + 1;
                    } else if (estado == 'A') {
                        System.out.println(dias[d] + ": Ausente");
                        ausentes = ausentes + 1;
                    } else {
                        System.out.println(dias[d] + ": Sin registrar");
                    }
                }
                
                System.out.println("Total presencias: " + presentes);
                System.out.println("Total ausencias: " + ausentes);
            }
            
        } else if (opcion == 2) {
            System.out.println("\nRESUMEN GENERAL");
            
            System.out.println("\nAsistencias por estudiante:");
            for (int est = 0; est < NUM_ESTUDIANTES; est++) {
                int presentes = 0;
                int ausentes = 0;
                
                for (int d = 0; d < DIAS_SEMANA; d++) {
                    if (asistencia[est][d] == 'P') {
                        presentes = presentes + 1;
                    } else if (asistencia[est][d] == 'A') {
                        ausentes = ausentes + 1;
                    }
                }
                System.out.println(estudiantes[est] + ": " + presentes + " presencias, " + ausentes + " ausencias");
            }
            System.out.println("\nEstudiantes que asistieron todos los dias:");
            boolean hay = false;
            for (int est = 0; est < NUM_ESTUDIANTES; est++) {
                boolean todos = true;
                for (int d = 0; d < DIAS_SEMANA; d++) {
                    if (asistencia[est][d] != 'P') {
                        todos = false;
                    }
                }
                if (todos == true) {
                    System.out.println("- " + estudiantes[est]);
                    hay = true;
                }
            }
            if (hay == false) {
                System.out.println("Ningun estudiante asistio todos los dias");
            }
            
            System.out.println("\nDias con mayor numero de ausencias:");
            int[] ausenciasPorDia = new int[DIAS_SEMANA];
            
            for (int d = 0; d < DIAS_SEMANA; d++) {
                int ausencias = 0;
                for (int est = 0; est < NUM_ESTUDIANTES; est++) {
                    if (asistencia[est][d] == 'A') {
                        ausencias = ausencias + 1;
                    }
                }
                ausenciasPorDia[d] = ausencias;
            }
            
            int maxAusencias = 0;
            for (int i = 0; i < DIAS_SEMANA; i++) {
                if (ausenciasPorDia[i] > maxAusencias) {
                    maxAusencias = ausenciasPorDia[i];
                }
            }
            
            if (maxAusencias > 0) {
                for (int d = 0; d < DIAS_SEMANA; d++) {
                    if (ausenciasPorDia[d] == maxAusencias) {
                        System.out.println(dias[d] + ": " + maxAusencias + " ausencias");
                    }
                }
            } else {
                System.out.println("No hay ausencias");
            }
            
        } else if (opcion == 3) {
            System.out.println("\nREGISTRAR ASISTENCIA");
            System.out.println("Escriba P para Presente o A para Ausente\n");
            
            for (int est = 0; est < NUM_ESTUDIANTES; est++) {
                System.out.println(estudiantes[est] + ":");
                
                for (int d = 0; d < DIAS_SEMANA; d++) {
                    String entrada = "";                    
                    while (entrada.equals("P") == false && entrada.equals("A") == false) {
                        System.out.print(dias[d] + " (P/A): ");
                        entrada = sc.nextLine();
                        entrada = entrada.toUpperCase();
                        
                        if (entrada.equals("P") == false && entrada.equals("A") == false) {
                            System.out.println("Error: Solo P o A");
                        }
                    }             
                    asistencia[est][d] = entrada.charAt(0);
                }
                System.out.println();
            }           
            System.out.println("Asistencia guardada!");
            
        } else if (opcion == 4) {
            System.out.println("Gracias!");
            
        } else {
            System.out.println("Opcion incorrecta");
        }        
        if (opcion != 4) {
            System.out.println("\nPresione Enter...");
            sc.nextLine();
        }
    }
    sc.close();
}

}