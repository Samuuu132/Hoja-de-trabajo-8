package hdt8;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * Pruebas unitarias para la implementación de la cola de prioridad con VectorHeap
 * y la clase Paciente.
 */
public class VectorHeapTest {

    /**
     * Test para verificar la comparación entre objetos de la clase Paciente.
     */
    @Test
    public void testPacienteComparison() {
        Paciente p1 = new Paciente("Paciente 1", "Síntoma 1", 'A');
        Paciente p2 = new Paciente("Paciente 2", "Síntoma 2", 'B');
        Paciente p3 = new Paciente("Paciente 3", "Síntoma 3", 'A');

        // p1 y p3 tienen el mismo código (A), por lo que compareTo debe retornar 0.
        assertEquals(0, p1.compareTo(p3), "Dos pacientes con prioridad A deben ser iguales en prioridad");

        // Con 'A' menor que 'B', p1 (con A) debe tener mayor prioridad que p2 (con B).
        assertTrue(p1.compareTo(p2) < 0, "El paciente con prioridad A debe venir antes que el de prioridad B");
        assertTrue(p2.compareTo(p1) > 0, "El paciente con prioridad B debe venir después que el de prioridad A");
    }

    /**
     * Test para verificar que al insertar pacientes en el VectorHeap, el método peek retorne
     * siempre al paciente con mayor prioridad.
     */
    @Test
    public void testVectorHeapInsertionAndPeek() {
        VectorHeap<Paciente> heap = new VectorHeap<>();

        Paciente p1 = new Paciente("Paciente 1", "Síntoma 1", 'C');
        Paciente p2 = new Paciente("Paciente 2", "Síntoma 2", 'A'); // Prioridad más alta (A)
        Paciente p3 = new Paciente("Paciente 3", "Síntoma 3", 'B');

        // Insertar pacientes en orden aleatorio
        heap.add(p1);
        heap.add(p2);
        heap.add(p3);

        // Se espera que peek retorne al paciente con código A.
        Paciente peeked = heap.peek();
        assertEquals('A', peeked.getCodigo(), "Peek debe retornar el paciente con prioridad A");
    }

    /**
     * Test para verificar que la remoción de pacientes en el VectorHeap se realice en el orden
     * correcto según su código de emergencia.
     */
    @Test
    public void testVectorHeapRemovalOrder() {
        VectorHeap<Paciente> heap = new VectorHeap<>();

        // Insertar pacientes con distintos códigos de emergencia
        Paciente p1 = new Paciente("Juan Perez", "fractura de pierna", 'C');
        Paciente p2 = new Paciente("Maria Ramirez", "apendicitis", 'A');
        Paciente p3 = new Paciente("Lorenzo Toledo", "chikunguya", 'E');
        Paciente p4 = new Paciente("Carmen Sarmientos", "dolores de parto", 'B');

        heap.add(p1);
        heap.add(p2);
        heap.add(p3);
        heap.add(p4);

        // Se espera el siguiente orden al remover:
        // 1. Maria Ramirez (A)
        // 2. Carmen Sarmientos (B)
        // 3. Juan Perez (C)
        // 4. Lorenzo Toledo (E)

        Paciente removed1 = heap.remove();
        assertEquals('A', removed1.getCodigo(), "El primer paciente removido debe tener prioridad A");

        Paciente removed2 = heap.remove();
        assertEquals('B', removed2.getCodigo(), "El segundo paciente removido debe tener prioridad B");

        Paciente removed3 = heap.remove();
        assertEquals('C', removed3.getCodigo(), "El tercer paciente removido debe tener prioridad C");

        Paciente removed4 = heap.remove();
        assertEquals('E', removed4.getCodigo(), "El cuarto paciente removido debe tener prioridad E");

        // Verificar que la cola esté vacía después de remover todos los elementos.
        assertTrue(heap.isEmpty(), "La cola debe estar vacía luego de remover todos los elementos");
    }
}
