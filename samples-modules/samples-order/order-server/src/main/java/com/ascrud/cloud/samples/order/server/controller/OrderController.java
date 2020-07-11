package com.ascrud.cloud.samples.order.server.controller;

import com.ascrud.cloud.samples.product.common.entity.ProductOutput;
import com.ascrud.cloud.samples.core.common.ValidList;
import com.ascrud.cloud.samples.core.enums.ResultEnum;
import com.ascrud.cloud.samples.core.utils.ResultVOUtils;
import com.ascrud.cloud.samples.core.vo.ResultVO;
import com.ascrud.cloud.samples.order.common.entity.OrderDetailInput;
import com.ascrud.cloud.samples.order.common.entity.OrderDetailOutput;
import com.ascrud.cloud.samples.order.common.entity.OrderMainOutput;
import com.ascrud.cloud.samples.order.server.entity.OrderMain;
import com.ascrud.cloud.samples.order.server.service.OrderService;
import com.ascrud.cloud.samples.order.server.utils.BeanCreators;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.ascrud.cloud.samples.core.constant.Constants.DELETED_STATUS;
import static com.ascrud.cloud.samples.core.enums.ResultEnum.DATA_NOT_EXIST;

/**
 *
 *
 * @author walkman
 */
@RestController
@RequestMapping("/orders")
@Slf4j
public class OrderController {

//    @Value("${eureka.instance.metadata-map.host-mark}")
//    private String grayMark;

    @Autowired
    private OrderService orderService;

    /**
     * 查询所有订单
     * @return
     */
    @GetMapping
    public ResultVO getOrderList() {

        /**
         * notice: 在实际业务中, 需要判断当前登录用户是否是买家或者卖家
         * 卖家: 查询所有
         * 买家: 必须要添加一个查询条件: buyer.
         */
        List<OrderMain> orderMainList = orderService.getAllOrderMain();
        List<OrderMainOutput> orderMainOutputList = orderMainList.stream().map(orderMain -> {
            OrderMainOutput output = BeanCreators.ceateOrderMainOutput();
            BeanUtils.copyProperties(orderMain, output);
            return output;
        }).collect(Collectors.toList());
        return ResultVOUtils.success(orderMainOutputList);
    }

    /**
     * 创建订单
     * @return
     */
    @PostMapping
    public ResultVO createOrder(@Validated @RequestBody ValidList<OrderDetailInput> orderDetailInputList,
                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = bindingResult.getFieldErrors().stream()
                    .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
            return ResultVOUtils.error(ResultEnum.PARAM_ERROR, errorsMap);
        }

        List<String> productIdList = orderDetailInputList.stream()
                .map(OrderDetailInput::getProductId)
                .collect(Collectors.toList());
        ResultVO resultVO = orderService.getProductListByProductIds(productIdList);

        // 1.判断ResultVO::getCode是否是200
        int code = resultVO.getCode();
        if (code != 200) {
            String msg = resultVO.getMsg();
            log.error("order: params=[orderDetailInputList:{}], error=[code:{}, msg:{}]", orderDetailInputList, code, msg);
            return ResultVOUtils.error(code, msg);
        }

        // 2.判断ResultVO::getData是否是空
        Object data = resultVO.getData();
        Map<String, ProductOutput> productOutputMap = (Map<String, ProductOutput>) data;
        if (CollectionUtils.isEmpty(productOutputMap)) {
            String msg = "ProductClient::getProductListByProductIds::getData返回数据为空";
            log.error("order: params=[orderDetailInputList:{}], error=[code:{}, msg:{}]", orderDetailInputList, code, msg);
            return ResultVOUtils.error(code, msg);
        }

        // 3.判断请求的商品Id和返回的商品Id是否完全匹配
        Set<String> keys = productOutputMap.keySet();
        List<String> unExistedProductList = productIdList.stream()
                .filter(ele -> !keys.contains(ele)).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(unExistedProductList)) {
            String msg = String.format("%s%s%s", "商品Id: ", String.join(",", unExistedProductList), ", 商品不存在");
            log.error("order: params=[orderDetailInputList:{}], error=[msg:{}]", orderDetailInputList, msg);
            return ResultVOUtils.error(msg);
        }

        // 4.判断库存是否足够
        List<String> unEnoughProductList = orderDetailInputList.stream()
                .filter(
                        orderDetailInput ->
                                productOutputMap.get(orderDetailInput.getProductId()).getStock() < orderDetailInput.getProductQuantity()
                )
                .map(OrderDetailInput::getProductId)
                .collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(unEnoughProductList)) {
            String msg = String.format("%s%s%s", "商品Id: ", String.join(",", unEnoughProductList), ", 商品库存不足够");
            log.error("order: params=[orderDetailInputList:{}], error=[msg:{}]", orderDetailInputList, msg);
            return ResultVOUtils.error(msg);
        }

        // 5.mmp, 终于可以创建订单了
        OrderMain orderMain = orderService.createOrder(orderDetailInputList, productOutputMap);
        if (orderMain == null) {
            String msg = "订单创建失败";
            log.error("order: params=[orderDetailInputList:{}], error=[msg:{}]", orderDetailInputList, msg);
            return ResultVOUtils.error(msg);
        }
        OrderMainOutput output = BeanCreators.ceateOrderMainOutput();
        BeanUtils.copyProperties(orderMain, output);
        return ResultVOUtils.success(output);
    }

    /**
     * 根据订单Id,查询订单信息
     * @param id
     * @return
     */
    @GetMapping("/{id:\\w{8}(?:-\\w{4}){3}-\\w{12}}")
    public ResultVO getOrderMainById(@PathVariable("id") String id) {
        OrderMain orderMain = orderService.getOrderMainById(id);
        if (orderMain == null) {
            log.error(DATA_NOT_EXIST.getDesc());
            return ResultVOUtils.error(DATA_NOT_EXIST);
        }
        OrderMainOutput output = BeanCreators.ceateOrderMainOutput();
        BeanUtils.copyProperties(orderMain, output);
        return ResultVOUtils.success(output);
    }

    /**
     * 删除指定的订单
     * @param id
     * @return
     */
    @DeleteMapping("/{id:\\w{8}(?:-\\w{4}){3}-\\w{12}}")
    public ResultVO deleteOrder(@PathVariable("id") String id) {
        OrderMain orderMain = orderService.getOrderMainById(id);
        if (orderMain == null) {
            log.error(DATA_NOT_EXIST.getDesc());
            return ResultVOUtils.error(DATA_NOT_EXIST);
        }
        orderMain.setDelFlag(DELETED_STATUS);
        orderService.saveOne(orderMain);
        return ResultVOUtils.success();
    }

    /**
     * 根据订单Id, 查询订单明细
     * @param id
     * @return
     */
    @GetMapping("/{id:\\w{8}(?:-\\w{4}){3}-\\w{12}}/detail")
    public ResultVO getOrderDetailById(@PathVariable("id") String id) {
        OrderMain orderMain = orderService.getOrderMainById(id);
        if (orderMain == null) {
            log.error(DATA_NOT_EXIST.getDesc());
            return ResultVOUtils.error(DATA_NOT_EXIST);
        }
        List<OrderDetailOutput> resultOrderDetailList = orderService.getAllOrderDetailByOrderId(id).stream()
                .map(orderDetail -> {
                    OrderDetailOutput output = BeanCreators.ceateOrderDetailOutput();
                    BeanUtils.copyProperties(orderDetail, output);
                    return output;
                })
                .collect(Collectors.toList());
        return ResultVOUtils.success(resultOrderDetailList);
    }

}
