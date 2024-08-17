package com.club.circle.api.req;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>
 * 圈子信息
 * </p>
 *
 * @author ChickenWing
 * @since 2024/05/16
 */
@Getter
@Setter
public class RemoveShareCircleReq implements Serializable {

    private Long id;

}
