package com.example.ecommerce.controller;

import com.example.ecommerce.entity.Commodity;
import com.example.ecommerce.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Title: CommodityController
 * @Author 杨金鹏
 * @Package com.example.ecommerce.controller
 * @Date 2023/12/13 0:31
 */

@RestController
@RequestMapping("/commodity")
public class CommodityController {

    @Autowired
    private CommodityService commodityService;


    /**
     * @apiNote 根据商品名和时间段实现分页查询
     * @param name 属性
     */
    @GetMapping("/findByNameAndDate")
    public ResponseEntity<List<Commodity>> findByNameAndDate(@RequestParam(required = false) String name,@RequestParam(required = false) String startTime,@RequestParam(required = false) String endTime){
        List<Commodity> byNameAndDate = commodityService.findByNameAndDate(name, startTime, endTime);//用户可能输入空数据，应采用动态sql查询
        int size = byNameAndDate.size();//计算查找到的所有商品数量
        int pages=size/10;//每页可以显示10条数据，统计总共有多少页
        return ResponseEntity.ok(byNameAndDate);//ResponseEntity能够通知客户端的http响应状态
    }



    /**
     * @apiNote 编辑商品
     * @param commodity 实体
     */
    @PutMapping("/update")
    public ResponseEntity<Commodity>  update(@RequestBody  Commodity commodity){
        this.commodityService.update(commodity);
        return ResponseEntity.ok(commodity);
    }



    /**
     * @apiNote 删除商品
     * @param id 属性
     */
    @DeleteMapping("/delete")
    public  ResponseEntity  deleteById(Integer id){
       this.commodityService.deleteById(id);
       return ResponseEntity.ok(id);
    }


}
