package org.example.controller.dto;

import java.util.List;
import java.util.Objects;

public class DisciplineIncomingDto {
    private String discipline;
    private int status;
    private List<TermIncomingDto> terms;

    public DisciplineIncomingDto() {
    }

    public DisciplineIncomingDto(String discipline, int status, List<TermIncomingDto> terms) {
        this.discipline = discipline;
        this.status = status;
        this.terms = terms;
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

    public List<TermIncomingDto> getTerms() {
        return terms;
    }

    public void setTerms(List<TermIncomingDto> terms) {
        this.terms = terms;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DisciplineIncomingDto that = (DisciplineIncomingDto) o;

        if (status != that.status) return false;
        if (!Objects.equals(discipline, that.discipline)) return false;
        return Objects.equals(terms, that.terms);
    }

    @Override
    public int hashCode() {
        int result = discipline != null ? discipline.hashCode() : 0;
        result = 31 * result + status;
        result = 31 * result + (terms != null ? terms.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DisciplineIncomingDto{" +
                "discipline='" + discipline + '\'' +
                ", status=" + status +
                ", terms=" + terms +
                '}';
    }
}
