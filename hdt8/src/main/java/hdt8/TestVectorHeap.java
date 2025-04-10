package hdt8;

public class TestVectorHeap {
    public static void main(String[] args) {
        VectorHeap<Paciente> heap = new VectorHeap<>();


        Paciente p1 = new Paciente("Juan Perez", "fractura de pierna", 'C');
        Paciente p2 = new Paciente("Maria Ramirez", "apendicitis", 'A');
        Paciente p3 = new Paciente("Lorenzo Toledo", "chikunguya", 'E');
        Paciente p4 = new Paciente("Carmen Sarmientos", "dolores de parto", 'B');

        heap.add(p1);
        heap.add(p2);
        heap.add(p3);
        heap.add(p4);

        System.out.println("Pruebas de eliminación en orden de prioridad:");
        while (!heap.isEmpty()) {
            Paciente siguiente = heap.remove();
            System.out.println(siguiente);
        }
    }
}
