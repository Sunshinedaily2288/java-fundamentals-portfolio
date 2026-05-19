package com.matharsa.ecommerce.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetCatalogReturnsSuccessAndData() throws Exception {
        // Checks index 0 of the array returned by the controller
        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Enterprise Java Book"));
    }

    @Test
    public void testPostProductSavesCorrectly() throws Exception {
        String mockJson = "{\"name\":\"Scrum Framework Guide\",\"price\":19.99}";

        mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mockJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Scrum Framework Guide"));
    }
}
