package sejevic.com._DWorldGenerationGame;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class GameControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final String sessionId = "test-session-1";

    @Test
    void shouldReturnInitialWorldLayout() throws Exception {
        mockMvc.perform(get("/api/init")
                .param("width", "20")
                        .param("height", "20").param("sessionId", sessionId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.floors").exists())
                .andExpect(jsonPath("$.walls").exists());
    }

    @Test
    void shouldReturnInitialGameState() throws Exception {
        mockMvc.perform(get("/api/state")
                .param("width", "20")
                        .param("height", "20").param("sessionId", sessionId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("RUNNING"));
    }

    @Test
    void shouldAcceptPlayerMove() throws Exception {
        mockMvc.perform(post("/api/move")
                        .param("sessionId", sessionId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"direction\": \"D\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.player").exists());
    }

    @Test
    void shouldRestartGame() throws Exception {
        mockMvc.perform(post("/api/restart")
                        .param("sessionId", sessionId))
                .andExpect(status().isOk());
    }
}
