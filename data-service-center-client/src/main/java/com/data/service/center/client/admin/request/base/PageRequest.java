package com.data.service.center.client.admin.request.base;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 分页查询入参
 *
 * @author wenbo.zhuang
 * @date 2023/03/02:23:33
 */
@Getter
@Setter
@ToString(callSuper = true)
public class PageRequest<T> extends BaseRequest<T> {

    private static final long serialVersionUID = -7094467535851807299L;

    /**
     * 当前页，从1开始
     */
    @Min(value = 1, message = "页码不能小于1")
    @NotNull(message = "页码不能为空")
    private Integer pageNum;

    /**
     * 每页大小
     */
    @NotNull(message = "分页大小不能为空")
    @Min(value = 1, message = "最小分页大小不能小于1")
    @Max(value = 100, message = "最大分页大小限制不超过100")
    private Integer pageSize;
}
