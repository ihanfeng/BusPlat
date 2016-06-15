package com.zhiyin.search.es.module;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class C2sSearchParm {
    private String query;

    private Long roleId;
    private int p;
    private int s;
}
