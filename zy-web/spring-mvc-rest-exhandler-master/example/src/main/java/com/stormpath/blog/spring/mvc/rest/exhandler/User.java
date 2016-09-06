package com.stormpath.blog.spring.mvc.rest.exhandler;

import com.stormpath.blog.spring.mvc.rest.exhandler.validator.Phone;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
public class User {

    @Size(min=2, max=30)
    private String name;

    @NotNull
    @Min(18)
    @Max(100)
    private Integer age;

    @Phone
    private String phone;

}
