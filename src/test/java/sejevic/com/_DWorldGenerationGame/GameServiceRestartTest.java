package sejevic.com._DWorldGenerationGame;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static com.google.common.truth.Truth.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class GameServiceRestartTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void restartShouldCreateNewGameForSession() throws Exception {
        String sessionId = "restart-test";

        // Get initial state
        MvcResult result1 = mockMvc.perform(get("/api/state")
                        .param("sessionId", sessionId))
                .andReturn();

        String stateBefore = result1.getResponse().getContentAsString();

        // Restart
        mockMvc.perform(post("/api/restart").param("sessionId", sessionId))
                .andExpect(status().isOk());

        Thread.sleep(200); // allow state to regenerate

        MvcResult result2 = mockMvc.perform(get("/api/state")
                        .param("sessionId", sessionId))
                .andReturn();

        String stateAfter = result2.getResponse().getContentAsString();

        assertThat(stateBefore).isNotEqualTo(stateAfter);
    }
}
