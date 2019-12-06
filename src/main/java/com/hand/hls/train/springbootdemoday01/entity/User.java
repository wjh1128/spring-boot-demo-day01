package com.hand.hls.train.springbootdemoday01.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 用户实体类
 */
@Data
@ApiModel(value = "用户", description = "用户实体类")
public class User {
    @ApiModelProperty("用户id")
    private Integer id;

    @ApiModelProperty("用户名")
    @NotNull(message = "用户名不为空")
    private String username;

    @ApiModelProperty("用户年龄")
    @Max(value = 200, message = "年龄最大为200岁")
    @Min(value = 1, message = "年龄最小为1岁")
    private Integer age;//年龄

    @ApiModelProperty("创建时间")
    private Date createTime;
}
