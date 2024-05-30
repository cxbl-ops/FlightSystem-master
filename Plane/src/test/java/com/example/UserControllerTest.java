//package com.example;
//
//import com.example.Controller.UserController;
//import com.example.Service.UserService;
//import com.example.vo.ResponseResult;
//import com.example.vo.User;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.when;
//
///**
// * @author vir
// * @date 2024/5/13 下午3:52 15 52
// * @package_name com.example
// */
//public class UserControllerTest {
//    @Mock
//    private UserService userService;
//
//    @InjectMocks
//    private UserController userController;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    public void testRegister_Success() {
//        // 准备测试数据
//        User user = new User();
//        user.setUsername("test@example.com");
//        user.setPasswd("password123");
//
//        // 模拟userService的register方法返回false，表示注册成功
//        when(userService.register(user)).thenReturn(false);
//
//        // 调用注册方法
//        ResponseResult result = userController.register(user);
//
//        // 验证注册结果
//        assertEquals(200, result.getCode());
//        assertEquals("注册成功", result.getMsg());
//    }
//
//    @Test
//    public void testRegister_Failure_InvalidEmail() {
//        // 准备测试数据
//        User user = new User();
//        user.setUsername("invalid-email");
//        user.setAccount("0xc4712a806bffb1e211f5754c1a46f3d4535b6422");
//        user.setPasswd("password123");
//
//        // 调用注册方法
//        ResponseResult result = userController.register(user);
//
//        // 验证注册结果
//        assertEquals(400, result.getCode());
//        assertEquals("用户名必须为电子邮件格式", result.getMsg());
//    }
//
//    @Test
//    public void testRegister_Failure_ShortPassword() {
//        // 准备测试数据
//        User user = new User();
//        user.setUsername("test@example.com");
//        user.setPasswd("short");
//
//        // 调用注册方法
//        ResponseResult result = userController.register(user);
//
//        // 验证注册结果
//        assertEquals(400, result.getCode());
//        assertEquals("密码长度不能少于6位", result.getMsg());
//    }
//}
