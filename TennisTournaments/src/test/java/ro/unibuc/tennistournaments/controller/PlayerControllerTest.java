package ro.unibuc.tennistournaments.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import ro.unibuc.tennistournaments.dto.PlayerDto;
import ro.unibuc.tennistournaments.service.PlayerService;
import ro.unibuc.tennistournaments.util.PlayerDtoUtil;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(controllers = PlayerController.class)
public class PlayerControllerTest {

    @MockBean private PlayerService playerService;
    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;

    @Test
    @DisplayName("Testing creating a player")
    void test_createPlayer() throws Exception {
        //Arrange
        PlayerDto dto = PlayerDtoUtil.aPlayerDto("Andrei", "Alexandrescu");
        when(playerService.create(any())).thenReturn(dto);

        //Act
        MvcResult result = mockMvc.perform(post("/player")
                        .content(objectMapper.writeValueAsString(dto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        //Assert
        assertThat(result.getResponse().getContentAsString()).isEqualTo(objectMapper.writeValueAsString(dto));
    }

    @Test
    void test_getOnePlayer() throws Exception {
        String email = "andrei131298@yahoo.com";
        PlayerDto dto = PlayerDtoUtil.aPlayerDto("Andrei", "Alexandrescu");
        when(playerService.getOneByEmail(email)).thenReturn(dto);

        mockMvc.perform(get("/player/email/" + email))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("email", is(dto.getEmail())))
                .andExpect(jsonPath("$.firstName", is(dto.getFirstName())));
    }


}
