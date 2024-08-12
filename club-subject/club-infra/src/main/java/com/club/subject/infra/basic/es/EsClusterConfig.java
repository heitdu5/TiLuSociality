package com.club.subject.infra.basic.es;

import lombok.Data;

import java.io.Serializable;

/**
 * @author es集群类
 * @date 2024−07−31
 */
@Data
public class EsClusterConfig implements Serializable {

    /**
     * 集群名称
     */
    private String name;

    /**
     * 集群节点
     */
    private String nodes;




}
