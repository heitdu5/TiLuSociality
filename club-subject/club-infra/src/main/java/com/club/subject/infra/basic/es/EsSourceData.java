package com.club.subject.infra.basic.es;

import lombok.Data;

import java.util.Map;
import java.util.Objects;

/**
 * @author Tellsea
 * @date 2024−08−01
 */
@Data
public class EsSourceData {
    private String docId;

    private Map<String, Object> data;


}
