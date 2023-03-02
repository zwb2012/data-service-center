package com.data.service.center.client.admin.request.base;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.Valid;
import java.io.Serializable;

/**
 * @author wenbo.zhuang
 * @date 2023/03/02:23:35
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@EqualsAndHashCode
public class BaseRequest<T> implements Serializable {

    private static final long serialVersionUID = -8963898836438438708L;

    @Valid
    private T data;
}
