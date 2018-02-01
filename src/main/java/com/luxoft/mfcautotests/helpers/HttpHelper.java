package com.luxoft.mfcautotests.helpers;

import com.luxoft.mfcautotests.config.forhelpers.HttpResponseData;
import com.luxoft.mfcautotests.config.forhelpers.LoginParameters;
import org.apache.http.NameValuePair;
import org.apache.http.cookie.Cookie;
import org.apache.http.message.BasicNameValuePair;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class HttpHelper extends BaseHelper {

    public LoginParameters getOooLoginParameters(String url) {
        Map<String, String> urlParameters = new HashMap<>();
        urlParameters.put("window_id", "7");
        String service = "mmcreg";

        return getLoginParameters(url, urlParameters, service);
    }

    private LoginParameters getLoginParameters(String url,Map<String, String> urlParameters, String service) {
        HttpResponseData responseData = sendGetHttpRequest(url, urlParameters);
        LoginParameters loginParameters = new LoginParameters();
        Document document = responseData.getResponseData();
        List<Cookie> cookies = responseData.getCookies();

        loginParameters.set_eventId(document.select("[name=_eventId]").val())
                .setExecution(document.select("[name=execution]").val())
                .setLt(document.select("[name=lt]").val())
                .setService(env.baseUrl + service + "/shiro-cas")
                .setCookies(cookies);

        return loginParameters;
    }

    public HttpResponseData login(LoginParameters loginParameters) {
        String url = env.loginHost + "/cas/login";

        Cookie[] cookies = loginParameters.getCookies().toArray(new Cookie[loginParameters.getCookies().size()]);

        List<NameValuePair> postParameters = new ArrayList<>();
        postParameters.add(new BasicNameValuePair("service", loginParameters.getService()));
        postParameters.add(new BasicNameValuePair("username", loginParameters.getUserName()));
        postParameters.add(new BasicNameValuePair("password", loginParameters.getPassword()));
        postParameters.add(new BasicNameValuePair("lt", loginParameters.getLt()));
        postParameters.add(new BasicNameValuePair("execution", loginParameters.getExecution()));
        postParameters.add(new BasicNameValuePair("_eventId", loginParameters.get_eventId()));
        postParameters.add(new BasicNameValuePair("submit", "Войти"));

        return sendPostHttpRequest(url, postParameters, cookies);

    }

//    public void sendPostHttpRequest(LoginParameters loginParameters) {
//        String url = env.loginHost + "/cas/login";
//        BasicCookieStore cookieStore = new BasicCookieStore();
//        Cookie[] cookies = loginParameters.getCookies().toArray(new Cookie[loginParameters.getCookies().size()]);
//        cookieStore.addCookies(cookies);
//        CloseableHttpClient client = HttpClientBuilder.create().setRedirectStrategy(new LaxRedirectStrategy()).setDefaultCookieStore(cookieStore).build();
//        HttpPost post = new HttpPost(url);
//        post.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36 ql66u54b");

//        List<NameValuePair> urlParameters = new ArrayList<>();
//        urlParameters.add(new BasicNameValuePair("service", loginParameters.getService()));
//        urlParameters.add(new BasicNameValuePair("username", loginParameters.getUserName()));
//        urlParameters.add(new BasicNameValuePair("password", loginParameters.getPassword()));
//        urlParameters.add(new BasicNameValuePair("lt", loginParameters.getLt()));
//        urlParameters.add(new BasicNameValuePair("execution", loginParameters.getExecution()));
//        urlParameters.add(new BasicNameValuePair("_eventId", loginParameters.get_eventId()));
//        urlParameters.add(new BasicNameValuePair("submit", "Войти"));

//        try {
//            post.setEntity(new UrlEncodedFormEntity(urlParameters));
//            CloseableHttpResponse response = client.execute(post);
//            HttpEntity entity = response.getEntity();
//            String data = IOUtils.toString(entity.getContent(), "UTF-8");
//            System.out.println(data);
//            response.close();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }


//    public Response goToSearch() {
//        Map<String, String> parametersOfGet = new HashMap<>();
//        parametersOfGet.put("window_id", env.windowId);
//        return sendHttpGetToUrl(env.baseUrl + "mmcreg/search.htm", parametersOfGet);
//    }
//
//    public Document login(User user) {
//        try {
//
//            Map<String, String> parametersOfGet = new HashMap<>();
//            parametersOfGet.put("window_id", env.windowId);
//            Response response = sendHttpGetToUrl(env.baseUrl + "mmcreg/search.htm", parametersOfGet);
//
//            Document document = response.parse();
//            LoginParameters loginParameters = new LoginParameters()
//                    .setUserName(user.getLogin())
//                    .setPassword(user.getPassword())
//                    .set_eventId(document.select("[name=_eventId]").val())
//                    .setExecution(document.select("[name=execution]").val())
//                    .setLt(document.select("[name=lt]").val())
//                    .setService(env.baseUrl + "mmcreg/shiro-cas");
//
//            Map<String, String> postParameters = new HashMap<>();
//            postParameters.put("username", loginParameters.getUserName());
//            postParameters.put("password", loginParameters.getPassword());
//            postParameters.put("lt", loginParameters.getLt());
//            postParameters.put("execution", loginParameters.getExecution());
//            postParameters.put("_eventId", loginParameters.get_eventId());
//            postParameters.put("submit", "Войти");
//            postParameters.put("service", loginParameters.getService());
//
//            return sendHttpPostToUrl(env.loginHost + "cas/login", postParameters, response.cookies()).parse();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public Response logout() {
//        return sendHttpGetToUrl(env.baseUrl + "mmcreg/logout.htm");
//    }

}
