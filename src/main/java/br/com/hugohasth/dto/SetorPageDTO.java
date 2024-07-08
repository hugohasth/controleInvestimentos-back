package br.com.hugohasth.dto;

import java.util.List;

public record SetorPageDTO(List<SetorDTO> setores, long totalElements, int totalPages) {

}
