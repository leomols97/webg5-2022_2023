package be.he2b.scrum.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class WebControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void iniProjects() throws Exception {

    }

    @Test
    public void addStoryWhenProjectOpen() throws Exception {

    }


    @Test
    public void addStoryWhenProjectClosed() throws Exception {

    }
}
