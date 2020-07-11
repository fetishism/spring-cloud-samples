package com.ascrud.cloud.samples.product.server;

import com.ascrud.cloud.samples.product.server.controller.ProductControllerTest;
import com.ascrud.cloud.samples.product.server.repository.ProductRepositoryTest;
import com.ascrud.cloud.samples.product.server.service.impl.ProductServiceImplTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 *
 * @author walkman
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ProductControllerTest.class, ProductServiceImplTest.class, ProductRepositoryTest.class})
public class ProductServerApplicationSuite {
}
