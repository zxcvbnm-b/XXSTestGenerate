package xxs.gen.gentest;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
//报错：java.lang.IllegalStateException: No ServletContext  改成  SpringBootTest.WebEnvironment.RANDOM_PORT
@AutoConfigureMockMvc// 没有这个 不能注入mvc 会找不到的
public class TestControllerTest {
    @Autowired
    private WebApplicationContext wac;
    @Autowired
    private MockMvc mvc;

    private MockHttpSession session;

    @Before
    public void mockMvcBefore(){

    }
        @Test
        public void update() throws Exception {
        String urlTemplate="/front/customermaster/update";
        String contextPath="";
        HttpMethod httpMethod =HttpMethod.POST;
        HttpHeaders httpHeaders =new HttpHeaders();
                httpHeaders.add("Origin", "http://localhost:8001");
                httpHeaders.add("token", "a71eb3569c9269e38492862c76106d69");
                MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
                String content= "{\"content\":\"\",\"contentType\":{\"charset\":\"UTF-8\",\"concrete\":true,\"parameters\":{\"charset\":\"UTF-8\"},\"qualityValue\":1.0,\"subtype\":\"json\",\"type\":\"application\",\"wildcardSubtype\":false,\"wildcardType\":false},\"contentTypeString\":\"APPLICATION_JSON_UTF8\",\"httpHeaders\":{},\"params\":{}}" ;
        MediaType contentType=MediaType.APPLICATION_JSON_UTF8;
        // 不设置：accept会乱码的吧。。。。
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.request(httpMethod,urlTemplate).
                contextPath(contextPath).headers(httpHeaders).queryParams(params).params(params).content(content).contentType(contentType).accept(contentType);
        /*andExpect添加断言*/
        mvc.perform(mockHttpServletRequestBuilder).andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();
    }
        @Test
        public void delete() throws Exception {
        String urlTemplate="/front/customermaster/delete";
        String contextPath="";
        HttpMethod httpMethod =HttpMethod.POST;
        HttpHeaders httpHeaders =new HttpHeaders();
                httpHeaders.add("Origin", "http://localhost:8001");
                httpHeaders.add("token", "a71eb3569c9269e38492862c76106d69");
                MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
                String content="" ;
        MediaType contentType=MediaType.APPLICATION_JSON_UTF8;
        // 不设置：accept会乱码的吧。。。。
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.request(httpMethod,urlTemplate).
                contextPath(contextPath).headers(httpHeaders).queryParams(params).params(params).content(content).contentType(contentType).accept(contentType);
        /*andExpect添加断言*/
        mvc.perform(mockHttpServletRequestBuilder).andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();
    }
        @Test
        public void list() throws Exception {
        String urlTemplate="/front/customermaster/list";
        String contextPath="";
        HttpMethod httpMethod =HttpMethod.GET;
        HttpHeaders httpHeaders =new HttpHeaders();
                httpHeaders.add("Origin", "http://localhost:8001");
                httpHeaders.add("token", "a71eb3569c9269e38492862c76106d69");
                MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
                 params.add("test", "xxxx");
                 params.add("valuexx", "xxxxdfdsf");
                String content="" ;
        MediaType contentType=MediaType.APPLICATION_JSON_UTF8;
        // 不设置：accept会乱码的吧。。。。
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.request(httpMethod,urlTemplate).
                contextPath(contextPath).headers(httpHeaders).queryParams(params).params(params).content(content).contentType(contentType).accept(contentType);
        /*andExpect添加断言*/
        mvc.perform(mockHttpServletRequestBuilder).andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();
    }
        @Test
        public void save() throws Exception {
        String urlTemplate="/front/customermaster/save";
        String contextPath="";
        HttpMethod httpMethod =HttpMethod.POST;
        HttpHeaders httpHeaders =new HttpHeaders();
                httpHeaders.add("Origin", "http://localhost:8001");
                httpHeaders.add("token", "a71eb3569c9269e38492862c76106d69");
                MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
                String content= "{\"content\":\"\",\"contentType\":{\"charset\":\"UTF-8\",\"concrete\":true,\"parameters\":{\"charset\":\"UTF-8\"},\"qualityValue\":1.0,\"subtype\":\"json\",\"type\":\"application\",\"wildcardSubtype\":false,\"wildcardType\":false},\"contentTypeString\":\"APPLICATION_JSON_UTF8\",\"httpHeaders\":{},\"params\":{}}" ;
        MediaType contentType=MediaType.APPLICATION_JSON_UTF8;
        // 不设置：accept会乱码的吧。。。。
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.request(httpMethod,urlTemplate).
                contextPath(contextPath).headers(httpHeaders).queryParams(params).params(params).content(content).contentType(contentType).accept(contentType);
        /*andExpect添加断言*/
        mvc.perform(mockHttpServletRequestBuilder).andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();
    }
        @Test
        public void info() throws Exception {
        String urlTemplate="/front/customermaster/info/1";
        String contextPath="";
        HttpMethod httpMethod =HttpMethod.GET;
        HttpHeaders httpHeaders =new HttpHeaders();
                httpHeaders.add("Origin", "http://localhost:8001");
                httpHeaders.add("token", "a71eb3569c9269e38492862c76106d69");
                MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
                 params.add("test", "xxxx");
                 params.add("valuexx", "xxxxdfdsf");
                String content="" ;
        MediaType contentType=MediaType.APPLICATION_JSON_UTF8;
        // 不设置：accept会乱码的吧。。。。
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.request(httpMethod,urlTemplate).
                contextPath(contextPath).headers(httpHeaders).queryParams(params).params(params).content(content).contentType(contentType).accept(contentType);
        /*andExpect添加断言*/
        mvc.perform(mockHttpServletRequestBuilder).andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();
    }
        @Test
        public void sysQueryPage() throws Exception {
        String urlTemplate="/front/customermaster/list_sys";
        String contextPath="";
        HttpMethod httpMethod =HttpMethod.POST;
        HttpHeaders httpHeaders =new HttpHeaders();
                httpHeaders.add("Origin", "http://localhost:8001");
                httpHeaders.add("token", "a71eb3569c9269e38492862c76106d69");
                MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
                String content= "{\"content\":\"\",\"contentType\":{\"charset\":\"UTF-8\",\"concrete\":true,\"parameters\":{\"charset\":\"UTF-8\"},\"qualityValue\":1.0,\"subtype\":\"json\",\"type\":\"application\",\"wildcardSubtype\":false,\"wildcardType\":false},\"contentTypeString\":\"APPLICATION_JSON_UTF8\",\"httpHeaders\":{},\"params\":{}}" ;
        MediaType contentType=MediaType.APPLICATION_JSON_UTF8;
        // 不设置：accept会乱码的吧。。。。
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.request(httpMethod,urlTemplate).
                contextPath(contextPath).headers(httpHeaders).queryParams(params).params(params).content(content).contentType(contentType).accept(contentType);
        /*andExpect添加断言*/
        mvc.perform(mockHttpServletRequestBuilder).andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();
    }
    }