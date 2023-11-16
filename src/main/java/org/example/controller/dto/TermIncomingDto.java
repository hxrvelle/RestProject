package org.example.controller.dto;

import org.example.model.Discipline;

import java.util.List;
import java.util.Objects;

public class TermIncomingDto {
    private String term;
    private String duration;
    private int status;
    private List<DisciplineIncomingDto> disciplines;

    public TermIncomingDto() {
    }

    public TermIncomingDto(String term, String duration, int status, List<DisciplineIncomingDto> disciplines) {
        this.term = term;
        this.duration = duration;
        this.status = status;
        this.disciplines = disciplines;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<DisciplineIncomingDto> getDisciplines() {
        return disciplines;
    }

    public void setDisciplines(List<DisciplineIncomingDto> disciplines) {
        this.disciplines = disciplines;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TermIncomingDto that = (TermIncomingDto) o;

        if (status != that.status) return false;
        if (!Objects.equals(term, that.term)) return false;
        if (!Objects.equals(duration, that.duration)) return false;
        return Objects.equals(disciplines, that.disciplines);
    }

    @Override
    public int hashCode() {
        int result = term != null ? term.hashCode() : 0;
        result = 31 * result + (duration != null ? duration.hashCode() : 0);
        result = 31 * result + status;
        result = 31 * result + (disciplines != null ? disciplines.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TermIncomingDto{" +
                "term='" + term + '\'' +
                ", duration='" + duration + '\'' +
                ", status=" + status +
                ", disciplines=" + disciplines +
                '}';
    }
}
