package com.ascrud.cloud.samples.product.server.controller;

import com.ascrud.cloud.samples.core.common.ValidList;
import com.ascrud.cloud.samples.core.constant.Constants;
import com.ascrud.cloud.samples.core.enums.ResultEnum;
import com.ascrud.cloud.samples.core.utils.ResultVOUtils;
import com.ascrud.cloud.samples.core.vo.ResultVO;
import com.ascrud.cloud.samples.product.common.entity.ProductInput;
import com.ascrud.cloud.samples.product.common.entity.ProductOutput;
import com.ascrud.cloud.samples.product.server.entity.Product;
import com.ascrud.cloud.samples.product.server.service.ProductService;
import com.ascrud.cloud.samples.product.server.utils.BeanCreators;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 商品服务Rest Api
 *
 * @author walkman
 */
@RestController
@RequestMapping("/products")
@Slf4j
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * 查询所有商品信息
     * @return
     */
    @GetMapping
    public ResultVO getAllProduct() {
        List<ProductOutput> productOutputList = productService.getAllProduct().stream().map(product -> {
            ProductOutput productOutput = BeanCreators.createProductOutput();
            BeanUtils.copyProperties(product, productOutput);
            return productOutput;
        }).collect(Collectors.toList());
        return ResultVOUtils.success(productOutputList);
    }

    /**
     * 创建商品
     * @param productInput
     * @return
     */
    @PostMapping
    public ResultVO createProduct(@Validated @RequestBody ProductInput productInput, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = bindingResult.getFieldErrors().stream()
                    .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
            return ResultVOUtils.error(ResultEnum.PARAM_ERROR, errorsMap);
        }

        Product product = BeanCreators.createInitProduct();
        BeanUtils.copyProperties(productInput, product);
        return ResultVOUtils.success(productService.saveOne(product));
    }

    /**
     * 查询指定商品明细信息
     * @param id 商品Id
     * @return
     */
    @GetMapping("/{id:\\w{8}(?:-\\w{4}){3}-\\w{12}}")
    public ResultVO getProductByProductId(@PathVariable("id") String id) {
        Product product = productService.getProductById(id);
        if (product == null) {
            log.error(ResultEnum.PRODUCT_NOT_EXIST.getDesc());
            return ResultVOUtils.error(ResultEnum.PRODUCT_NOT_EXIST);
        }
        ProductOutput productOutput = BeanCreators.createProductOutput();
        BeanUtils.copyProperties(product, productOutput);
        return ResultVOUtils.success(productOutput);
    }

    /**
     * 查询指定商品明细信息
     * @param productIds 商品Id列表
     * @return
     */
    @GetMapping("/list/all")
    public ResultVO getProductListByProductIds(@RequestParam("productIds") List<String> productIds) {
        if (CollectionUtils.isEmpty(productIds)) {
            log.error("ProductController: getProductListByProductIds(productIds={})", productIds);
            return ResultVOUtils.error(ResultEnum.PARAM_ERROR);
        }
        Map<String, ProductOutput> productOutputMap = productService.getProductById(productIds).stream()
                .map(product -> {
                    ProductOutput output = BeanCreators.createProductOutput();
                    BeanUtils.copyProperties(product, output);
                    return output;
                })
                .collect(Collectors.toMap(ProductOutput::getProductId, productOutput -> productOutput));
        return ResultVOUtils.success(productOutputMap);
    }

    /**
     * 更新某个商品全部信息
     * @param id 商品Id
     * @param productInput
     * @return
     */
    @PutMapping("/{id:\\w{8}(?:-\\w{4}){3}-\\w{12}}")
    public ResultVO updateProduct(@PathVariable("id") String id, @Validated @RequestBody ProductInput productInput, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = bindingResult.getFieldErrors().stream()
                            .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
            return ResultVOUtils.error(ResultEnum.PARAM_ERROR, errorsMap);
        }
        Product product = productService.getProductById(id);
        if (product == null) {
            log.error(ResultEnum.PRODUCT_NOT_EXIST.getDesc());
            return ResultVOUtils.error(ResultEnum.PRODUCT_NOT_EXIST);
        }

        BeanUtils.copyProperties(productInput, product);

        Product resultProduct = productService.saveOne(product);
        ProductOutput productOutput = BeanCreators.createProductOutput();
        BeanUtils.copyProperties(resultProduct, productOutput);

        return ResultVOUtils.success(productOutput);
    }

    /**
     * 更新部分商品库存信息
     * @param productInputList
     * @return
     */
    @PutMapping("/save/all")
    public ResultVO updateProductList(@Validated @RequestBody ValidList<ProductInput> productInputList, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = bindingResult.getFieldErrors().stream()
                    .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
            return ResultVOUtils.error(ResultEnum.PARAM_ERROR, errorsMap);
        }

        List<String> productIds = productInputList.stream()
                .map(ProductInput::getProductId).collect(Collectors.toList());

        Map<String, ProductInput> productInputMap = productInputList.stream()
                .collect(Collectors.toMap(ProductInput::getProductId, productInput -> productInput));

        List<Product> productList = productService.getProductById(productIds).stream()
                .map(product -> {
                    product.setStock(productInputMap.get(product.getProductId()).getStock());
                    return product;
                }).collect(Collectors.toList());
        productService.saveAll(productList);
        return ResultVOUtils.success();
    }

    /**
     * 删除指定商品
     * @param id 商品Id
     * @return
     */
    @DeleteMapping("/{id:\\w{8}(?:-\\w{4}){3}-\\w{12}}")
    public ResultVO deleteProduct(@PathVariable("id") String id) {
        Product product = productService.getProductById(id);
        if (product == null) {
            log.error(ResultEnum.PRODUCT_NOT_EXIST.getDesc());
            return ResultVOUtils.error(ResultEnum.PRODUCT_NOT_EXIST);
        }

        product.setDelFlag(Constants.DELETED_STATUS);

        Product resultProduct = productService.saveOne(product);
        ProductOutput productOutput = BeanCreators.createProductOutput();
        BeanUtils.copyProperties(resultProduct, productOutput);

        return ResultVOUtils.success(productOutput);
    }

}
