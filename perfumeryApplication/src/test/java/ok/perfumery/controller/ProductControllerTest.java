package ok.perfumery.controller;


import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {
	
	@Autowired
	private MockMvc mockMvc;


	@Test
	void testListAllProducts() throws Exception {
		this.mockMvc.perform(get("/products/"))
			.andExpect(status().isOk())
			.andExpect(content().string(containsString("Colonia")));
	}
	
	@Test
	void showProductOpens() throws Exception {
		this.mockMvc.perform(get("/products/4/"))
			.andExpect(status().isOk())
			.andExpect(content().string(containsString("Pour Un Homme")));
	}
	
	@Test
	void editProductOpens() throws Exception {
		this.mockMvc.perform(get("/products/4/edit/").with(user("peja").password("pass").roles("ADMIN")))
			.andExpect(status().isOk())
			.andExpect(content().string(containsString("Pour Un Homme")));
	}
	


}
