package studio.blackmonkey.chat.server;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import studio.blackmonkey.chat.server.controller.PortalController;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = PortalController.class)
public class MainApplicationTests {

    @Autowired
	private MockMvc mMockMvc;

	@Test
	public void testPortal() throws Exception {
		mMockMvc.perform(MockMvcRequestBuilders.get("/"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.view().name("portal"))
				.andDo(MockMvcResultHandlers.print())
                .andReturn();
	}
}
