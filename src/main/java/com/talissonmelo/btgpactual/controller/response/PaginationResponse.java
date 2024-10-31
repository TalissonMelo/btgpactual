package com.talissonmelo.btgpactual.controller.response;

import org.springframework.data.domain.Page;

public record PaginationResponse(Integer page,
                                 Integer size,
                                 long totalElements,
                                 Integer totalPage) {

    public static PaginationResponse fromPage(Page<?> page) {
        return new PaginationResponse(
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages()
        );
    }
}
