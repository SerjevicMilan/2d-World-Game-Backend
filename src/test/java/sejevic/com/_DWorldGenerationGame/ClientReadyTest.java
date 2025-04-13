package sejevic.com._DWorldGenerationGame;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = {
        "game.width=15",
        "game.height=10",
        "game.seed=1",
        "game.density=1.0"
})
public class ClientReadyTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void gameShouldNotAdvanceBeforeClientReady() throws Exception {
        String sessionId = "wait-test";

        // Just init world
        mockMvc.perform(get("/api/init").param("sessionId", sessionId))
                .andExpect(status().isOk());

        // Wait a bit to simulate game tick
        Thread.sleep(6000);//under this config it needs less then 6 sec for enemy to find player

        // Game should still be RUNNING (not LOST/WON)
        mockMvc.perform(get("/api/state").param("sessionId", sessionId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("RUNNING"));
    }

    @Test
    void gameShouldAdvanceAfterClientReady() throws Exception {
        String sessionId = "start-test";

        mockMvc.perform(post("/api/ready").param("sessionId", sessionId))
                .andExpect(status().isOk());

        Thread.sleep(500);

        mockMvc.perform(get("/api/state").param("sessionId", sessionId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").exists());
    }
}
