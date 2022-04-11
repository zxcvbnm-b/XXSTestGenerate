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
    public Map<String, Object> list(@RequestParam Map<String, Object> params){
        System.out.println(params);
        return params;
    }

    /**
     * 列表-new
     */
    @PostMapping("/list_sys")
    //("front:customermaster:list")
    public MockControllerMethodRequestEntity sysQueryPage(@RequestBody MockControllerMethodRequestEntity params){
       return  params;
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //("front:customermaster:info")
    public Long info(@PathVariable("id") Long id){
    return id;
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    //("front:customermaster:save")
    public MockControllerMethodRequestEntity save(@RequestBody MockControllerMethodRequestEntity customerMaster){
    return customerMaster;
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    //("front:customermaster:update")
    public MockControllerMethodRequestEntity update(@RequestBody MockControllerMethodRequestEntity customerMaster){
      return customerMaster;

    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //("front:customermaster:delete")
    public Long[] delete(@RequestBody Long[] ids){
        return ids;
    }

}
