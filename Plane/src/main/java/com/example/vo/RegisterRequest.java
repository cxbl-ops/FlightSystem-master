package com.example.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * @author chyj0
 * @date 2024/6/7 下午3:35 15 35
 * @package_name com.example.vo
 * @Project_name FlightSystem-master
 */
@Setter
@Getter
public class RegisterRequest {
    private User user;
    private EmailMessage emailMessage;

    // 构造函数、getter 和 setter 方法省略

}
