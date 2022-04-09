package xxs.gen.gentest;

import org.springframework.boot.ApplicationArguments;
import org.springframework.web.bind.annotation.*;
import xxs.gen.gentest.service.entity.MockControllerMethodRequestEntity;

import java.util.Arrays;
import java.util.Map;

@RestController
@RequestMapping("front/customermaster")
public class TestController {
    /**
     * 列表
     */
    @RequestMapping("/list")
    //("front:customermaster:list")
    public void list(@RequestParam Map<String, Object> params){
    }

    /**
     * 列表-new
     */
    @RequestMapping("/list_sys")
    //("front:customermaster:list")
    public void sysQueryPage( MockControllerMethodRequestEntity params){

    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //("front:customermaster:info")
    public void info(@PathVariable("id") Long id){

    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //("front:customermaster:save")
    public void save(@RequestBody MockControllerMethodRequestEntity customerMaster){

    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //("front:customermaster:update")
    public void update(@RequestBody MockControllerMethodRequestEntity customerMaster){


    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //("front:customermaster:delete")
    public void delete(@RequestBody Long[] ids){
    }

}
