package sejevic.com._DWorldGenerationGame;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static com.google.common.truth.Truth.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = {
        "game.width=20",
        "game.height=15",
        "game.seed=1",
        "game.density=1.0"
})
public class GameServiceTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void differentSessionsShouldHaveIndependentGames() throws Exception {
        String session1 = "player-one";
        String session2 = "player-two";

        // Move player one
        mockMvc.perform(post("/api/move")
                        .param("sessionId", session1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"direction\": \"D\"}"))
                .andExpect(status().isOk());

        // Get both states
        MvcResult result1 = mockMvc.perform(get("/api/state").param("width", "20")
                        .param("height", "20").param("sessionId", session1))
                .andReturn();

        MvcResult result2 = mockMvc.perform(get("/api/state").param("width", "20")
                        .param("height", "20").param("sessionId", session2))
                .andReturn();

        String json1 = result1.getResponse().getContentAsString();
        String json2 = result2.getResponse().getContentAsString();

        // Parse positions
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node1 = mapper.readTree(json1);
        JsonNode node2 = mapper.readTree(json2);

        int x1 = node1.get("player").get("x").asInt();
        int y1 = node1.get("player").get("y").asInt();
        int x2 = node2.get("player").get("x").asInt();
        int y2 = node2.get("player").get("y").asInt();

        assertThat(x1 != x2 || y1 != y2).isTrue();
    }


}
