package local.anas.back_java.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.Map;
import org.hibernate.validator.constraints.Length;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJson;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import local.anas.back_java.BackJavaApplication;
import local.anas.back_java.model.User;
import local.anas.back_java.service.JwtService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = BackJavaApplication.class)
@AutoConfigureMockMvc
@AutoConfigureJson
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(Lifecycle.PER_CLASS)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserControllerIT {
        @Autowired
        MockMvc mockMvc;
        @Autowired
        ObjectMapper objectMapper;

        static final Faker faker = Faker.instance();

        final User user;

        UserControllerIT() throws NoSuchFieldException, SecurityException {
                final int passwordLength = User.class.getDeclaredField("password").getAnnotation(Length.class).min();

                user = User.builder().email(faker.internet().emailAddress())
                                .firstname(faker.name().firstName()).username(faker.name().username())
                                .password(faker.internet().password(passwordLength, passwordLength + 4)).build();
        }

        @Autowired
        JwtService jwtService;

        @Test
        @Order(1)
        void testCreateAccount() throws Exception {
                mockMvc.perform(post("/account").contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(user)))

                                .andExpect(status().isCreated());
        }

        @Test
        void testGetToken() throws Exception {

                final MvcResult result = mockMvc
                                .perform(post("/token").contentType(MediaType.APPLICATION_JSON)
                                                .content(objectMapper.writeValueAsString(user)))

                                .andExpect(status().isCreated()).andExpect(jsonPath("$.token").isString())
                                .andReturn();

                final Map<String, String> tokenPayload = objectMapper.readValue(
                                result.getResponse().getContentAsString(),
                                new TypeReference<Map<String, String>>() {
                                });

                final String token = tokenPayload.get("token");

                assertThatCode(() -> jwtService.parse(token)).doesNotThrowAnyException();
                assertThat(jwtService.parse(token).getPayload().get("sub").toString())
                                .isEqualTo(user.getEmail());

        }
}
