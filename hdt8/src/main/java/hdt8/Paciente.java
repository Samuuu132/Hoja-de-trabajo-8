package hdt8;

import java.util.Objects;

public class Paciente implements Comparable<Paciente> {
    private String nombre;
    private String sintomas;
    private char codigo; 

    public Paciente(String nombre, String sintomas, char codigo) {
        this.nombre = nombre;
        this.sintomas = sintomas;
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getSintomas() {
        return sintomas;
    }

    public char getCodigo() {
        return codigo;
    }

    @Override
    public int compareTo(Paciente otro) {
        return Character.compare(this.codigo, otro.codigo);
    }

    @Override
    public String toString() {
        return nombre + ", " + sintomas + ", " + codigo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Paciente)) return false;
        Paciente paciente = (Paciente) o;
        return codigo == paciente.codigo &&
               Objects.equals(nombre, paciente.nombre) &&
               Objects.equals(sintomas, paciente.sintomas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, sintomas, codigo);
    }
}

