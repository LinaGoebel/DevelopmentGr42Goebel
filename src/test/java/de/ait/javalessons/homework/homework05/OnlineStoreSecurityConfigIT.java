package de.ait.javalessons.homework.homework05;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/*@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestPropertySource(properties = {
        "spring.main.allow-bean-definition-overriding=true",
        "app.security.main-config.enabled=false" // Предполагая, что мы можем отключить основную конфигурацию
})*/
public class OnlineStoreSecurityConfigIT {

  /*  @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Проверяем, что публичный эндпоинт доступен всем и возвращает правильный контент")
    void testGetPublicInfo() throws Exception {
        mockMvc.perform(get("/products/public/list"))
                .andExpect(status().isOk())
                .andExpect(content().string("Product List, public information"));
    }

    @Nested
    @DisplayName("Тесты для эндпоинта /products/customer/cart")
    class CartTests {

        @Test
        @DisplayName("Проверяем, что эндпоинт доступен только пользователям с ролью CUSTOMER")
        @WithMockUser(username = "customer", roles = {"CUSTOMER"})
        void testGetCart() throws Exception {
            mockMvc.perform(get("/products/customer/cart"))
                    .andExpect(status().isOk())
                    .andExpect(content().string("Cart of products, customer information"));
        }

        @Test
        @DisplayName("Проверяем, когда пользователь не авторизован, эндпоинт недоступен")
        void testGetCartUnauthorized() throws Exception {
            mockMvc.perform(get("/products/customer/cart"))
                    .andExpect(status().is3xxRedirection());
        }
    }

    @Nested
    @DisplayName("Тесты для эндпоинта /products/manager/add")
    class AddProductTests {

        @Test
        @DisplayName("Проверяем, что эндпоинт доступен только пользователям с ролью MANAGER")
        @WithMockUser(username = "manager", roles = {"MANAGER"})
        void testAddProduct() throws Exception {
            mockMvc.perform(get("/products/manager/add"))
                    .andExpect(status().isOk())
                    .andExpect(content().string("Add product, manager information"));
        }

        @Test
        @DisplayName("Проверяем, когда пользователь не авторизован, эндпоинт недоступен")
        void testAddProductUnauthorized() throws Exception {
            mockMvc.perform(get("/products/manager/add"))
                    .andExpect(status().is3xxRedirection());
        }

        @Test
        @DisplayName("Проверяем, когда пользователь не имеет роли MANAGER, возвращается ошибка 403")
        @WithMockUser(username = "testUser", roles = {"CUSTOMER"})
        void testAddProductForbidden() throws Exception {
            mockMvc.perform(get("/products/manager/add"))
                    .andExpect(status().isForbidden());
        }
    }
*/}
