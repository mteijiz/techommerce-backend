package com.techommerce.backend.brand;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.techommerce.backend.controller.BrandController;
import com.techommerce.backend.entity.Brand;
import com.techommerce.backend.request.AddBrandRequest;
import com.techommerce.backend.serviceImpl.BrandServiceImpl;

@RunWith(SpringRunner.class)
@WebMvcTest(BrandController.class)
class BrandControllerTest {
	
	@Autowired
    private MockMvc mvc;
	
	@MockBean
	private BrandServiceImpl brandService;
	
	@Test
	public void testAddNewBrandSuccessfully() {
		AddBrandRequest aBrandRequest = new AddBrandRequest();
		aBrandRequest.setBrandCode("testcode");
		aBrandRequest.setBrandName("testname");
		aBrandRequest.setBrandDescription("test description");
		aBrandRequest.setBrandState(true);
		Brand aBrand = new Brand();
		when(brandService.addBrand(aBrand)).thenReturn(aBrand);
	}

}
