package com.imooc.framework;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.imooc.framework.base.controller.BaseController;

@Controller
@RequestMapping("common/*")
public class CommonController extends BaseController{
   
}
