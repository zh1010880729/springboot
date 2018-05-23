package cn.linkedcare.controller;

import cn.linkedcare.entity.CommonResultMap;
import cn.linkedcare.entity.User;
import cn.linkedcare.enumeration.HttpCode;
import cn.linkedcare.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private PasswordEncoder passwordEncoder;

    @GetMapping(value = "me", produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResultMap getLoginInfo() {
        return CommonResultMap.builder(HttpCode.OK).msg("查询成功").build();
    }

    @ApiOperation(value = "新增用户", notes = "新增一个用户")
    @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResultMap register(@RequestBody User user) {
        if (StringUtils.isNotBlank(user.getPassword())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        return CommonResultMap.builder(HttpCode.OK).msg("新增成功").data(userService.insert(user)).build();
    }


}
