package hdt8;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AtencionEmergencias {
    public static void main(String[] args) {
        VectorHeap<Paciente> heap = new VectorHeap<>();

    
        try (BufferedReader br = new BufferedReader(new FileReader("pacientes.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 3) {
                    String nombre = partes[0].trim();
                    String sintomas = partes[1].trim();
                    char codigo = partes[2].trim().charAt(0);
                    Paciente paciente = new Paciente(nombre, sintomas, codigo);
                    heap.add(paciente);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }

        System.out.println("Orden de atención de los pacientes:");
        while (!heap.isEmpty()) {
            Paciente pacienteAtendido = heap.remove();
            System.out.println(pacienteAtendido);
        }
    }
}