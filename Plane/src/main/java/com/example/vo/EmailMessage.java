package com.example.vo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailMessage {

        private String email;
        @Length(min = 6,message = "验证码是6位")
        private String math;
    }

