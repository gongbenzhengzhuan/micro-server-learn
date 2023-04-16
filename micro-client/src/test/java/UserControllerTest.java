import com.template.micro.client.DataSystemClientApplication;
import com.template.micro.client.controller.TUserController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * UserController 单元测试
 * https://www.cnblogs.com/cao-lei/p/16615920.html
 * @author CL
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DataSystemClientApplication.class)
public class UserControllerTest {

    @Autowired
    private TUserController tUserController;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(tUserController).build();
    }

    /**
     * /user/listData 接口测试
     */
    @Test
    public void listDataTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/datasystem-client/tUser/listData"))                                        // 打印结果
                .andDo(System.out::println)
                .andReturn();
    }

}
