package cn.linkedcare.controller;

import cn.linkedcare.config.properties.DataSourceProperties;
import cn.linkedcare.entity.CommonResultMap;
import cn.linkedcare.entity.User;
import cn.linkedcare.enumeration.HttpCode;
import cn.linkedcare.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Benji on 2018/5/7.
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private DataSourceProperties dataSourceProperties;

    @GetMapping(value = "me", produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResultMap getLoginInfo() {
        return CommonResultMap.builder(HttpCode.OK).data(dataSourceProperties).msg("查询成功").total(1).build();
    }

    @ApiOperation(value = "新增用户",notes = "新增一个用户")
    @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    @PostMapping(value = "register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResultMap register(@RequestBody User user) {
        return CommonResultMap.builder(HttpCode.OK).msg("新增成功").data(userService.insert(user)).build();
    }
}
