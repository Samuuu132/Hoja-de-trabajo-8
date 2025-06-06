package hdt8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.File;
import java.io.FileNotFoundException;

public class AtencionEmergencias {
    public static void main(String[] args) {
        // Imprime el directorio actual (aunque en este caso no se usará para encontrar el archivo)
        System.out.println("Directorio actual: " + new File(".").getAbsolutePath());
        
        VectorHeap<Paciente> heap = new VectorHeap<>();
        
        // Intenta cargar el archivo "pacientes.txt" como recurso del classpath.
        try (InputStream is = AtencionEmergencias.class.getResourceAsStream("/pacientes.txt")) {
            if (is == null) {
                throw new FileNotFoundException("No se encontró el archivo pacientes.txt en el classpath.");
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String linea;
            while ((linea = br.readLine()) != null) {
                // Depuración: imprime la línea leída (entre corchetes para ver espacios o caracteres no deseados)
                System.out.println("DEBUG -> [" + linea + "]");
                
                // Elimina el posible BOM (Byte Order Mark) en la primera línea
                linea = linea.replace("\uFEFF", "");
                
                // Salta líneas en blanco
                if (linea.trim().isEmpty()) {
                    continue;
                }
                
                // Se espera que cada línea tenga tres partes separadas por comas.
                String[] partes = linea.split(",");
                if (partes.length == 3) {
                    String nombre = partes[0].trim();
                    String sintomas = partes[1].trim();
                    String codStr = partes[2].trim();
                    
                    // Verifica que el campo de código no esté vacío
                    if (codStr.isEmpty()) {
                        System.out.println("DEBUG -> Código vacío en la línea: " + linea);
                        continue;
                    }
                    
                    char codigo = codStr.charAt(0);
                    Paciente paciente = new Paciente(nombre, sintomas, codigo);
                    heap.add(paciente);
                } else {
                    System.out.println("DEBUG -> Línea con formato incorrecto (se esperaban 3 campos): " + linea);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
            return;
        }
        
        // Mostrar el orden de atención de los pacientes según la cola de prioridad
        System.out.println("Orden de atención de los pacientes:");
        while (!heap.isEmpty()) {
            Paciente pacienteAtendido = heap.remove();
            System.out.println(pacienteAtendido);
        }
    }
}
