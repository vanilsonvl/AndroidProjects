package com.leite.vanilson.startapp.entity;

public enum Profession {
    ARCHTECT("Arquiteto"), ENGINEER("Engenheiro"), MILITAR("Militar"), DEVELOPER("Desenvolvedor de sistemas"),
    DOCTOR("Doutor"), LAWER("Advogado"), TEACHER("Professor");

    private Profession (String description) {
        this.description = description;
    }

    private String description;

    public String getDescription() {
        return this.description;
    }
}
