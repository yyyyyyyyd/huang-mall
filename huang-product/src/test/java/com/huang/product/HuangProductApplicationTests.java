package com.huang.product;

import com.huang.product.entity.CategoryEntity;
import com.huang.product.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@Slf4j
@SpringBootTest
class HuangProductApplicationTests {

    @Test
    void contextLoads() {
    }

    @Resource
    private CategoryService categoryService;

    @Test
    void name() {
        List<CategoryEntity> list = categoryService.list();
        log.info("数量为{}",list.size());
    }


    public static void main(String[] args) {
        int count = 0;
        while (true){
            count ++;
            if (count < 20){
                System.out.println("hello ！");
            }
        }
    }
}
