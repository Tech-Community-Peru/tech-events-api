package com.techcommunityperu.techcommunityperu.dto;

public class ComentarioDTO {
    private Integer id;
    private String comentario;

    public ComentarioDTO(Integer id, String comentario) {
        this.id = id;
        this.comentario = comentario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
