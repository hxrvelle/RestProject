package org.example.controller.dto;

import java.util.List;
import java.util.Objects;

public class DisciplineOutgoingDto {
    private int id;
    private String discipline;
    private int status;
    private List<TermOutgoingDto> terms;

    public DisciplineOutgoingDto() {
    }

    public DisciplineOutgoingDto(int id, String discipline, int status, List<TermOutgoingDto> terms) {
        this.id = id;
        this.discipline = discipline;
        this.status = status;
        this.terms = terms;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<TermOutgoingDto> getTerms() {
        return terms;
    }

    public void setTerms(List<TermOutgoingDto> terms) {
        this.terms = terms;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DisciplineOutgoingDto that = (DisciplineOutgoingDto) o;

        if (id != that.id) return false;
        if (status != that.status) return false;
        if (!Objects.equals(discipline, that.discipline)) return false;
        return Objects.equals(terms, that.terms);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (discipline != null ? discipline.hashCode() : 0);
        result = 31 * result + status;
        result = 31 * result + (terms != null ? terms.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DisciplineOutgoingDto{" +
                "id=" + id +
                ", discipline='" + discipline + '\'' +
                ", status=" + status +
                ", terms=" + terms +
                '}';
    }
}
