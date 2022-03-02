package com.zy;

import com.zy.generator.MybatisPlusGenerator;
import org.junit.Test;

/**
 * @author 张雨 - 栀
 * @version 1.0
 * @create 2022/3/2 12:13
 */
public class CodingGenerator {

    @Test
    public void CodingGenerator(){
        MybatisPlusGenerator mybatisPlusGenerator = new MybatisPlusGenerator();
        mybatisPlusGenerator.testGenerator();
    }


}
