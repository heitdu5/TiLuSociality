package com.club.subject.infra.basic.es;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Tellsea
 * @date 2024−08−01
 */
@Data
public class EsIndexInfo implements Serializable {
    /**
     * 集群名称
     */
    private String clusterName;

    /**
     * 索引名称
     */
    private String indexName;
}
