package com.joonfluence.web.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PaginationQueryDto {
    private int page;
    private int pageSize;
}
