package author;

import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.core5.http.HttpResponse;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.ProtocolException;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import org.apache.hc.core5.net.URIBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class DigestAuth {


    /*

    Digest
    - 최초요청시 무조건 401 -> WWW-Authenticate를 던져주는대 그걸가지고 요리해서 재요청 -> 200

     */

    String id = "idjune";
    String pw = "pwjune";
    String middleUri= "/v1/digest";

    //2회초과 요청시 실패임
    int cnt = 0;

    public void request(String url, HashMap<String,String> paramMap, String wwwHeader) throws URISyntaxException, IOException, ProtocolException, NoSuchAlgorithmException, DigestCallException {

        URI uri = makeUri(url,middleUri,paramMap);

        String authUri = uri.getPath() +"?" + uri.getRawQuery();
        System.out.println("authUri => " + authUri);

        HttpClient client = HttpClientBuilder.create().build();
        HttpGet requestGet = new HttpGet(uri);

        System.out.println("responseUri => "+ requestGet.getUri() );

        if(!Objects.equals(wwwHeader,"")){
            String authDg = authSet(wwwHeader,id,pw, authUri);
            requestGet.addHeader("Authorization",authDg);
        }

        HttpResponse response = client.execute(requestGet);
        System.out.println("responseCode => "+response.getCode());

        cnt++;
        if(cnt > 2){
            throw new DigestCallException();
        }

        if(response.getCode() != 200){
            request(url,paramMap,response.getHeader("WWW-Authenticate").getValue());
        }
    }

    private URI makeUri(String url,String middle,HashMap<String,String> paramMap) throws URISyntaxException {
        List<NameValuePair> params = new ArrayList<>();
        for (String s : paramMap.keySet()) {
            params.add(new BasicNameValuePair(s, paramMap.get(s)));
        }
        return new URIBuilder(url + middle).addParameters(params)
                .build();
    }


    //
    public String authSet(String wHeader,String id, String pw, String authUri) throws NoSuchAlgorithmException {
        // Digest realm="coolRealm", nonce="coolNonce", qop="coolQop"

        String[] splitVal = wHeader.replaceAll(",","").split(" ");
        String realm = cutEqualMark(splitVal[1]);
        String nonce = cutEqualMark(splitVal[2]);
        String cNonce = cutEqualMark(splitVal[2]);
        String qop  = cutEqualMark(splitVal[3]);
        String nc = "00000001";
        String opaque = "";


        MessageDigest md = MessageDigest.getInstance("MD5");

        String A1 = Md5UpdateAndGetString(md,beforeMd5String(id,realm,pw));
        String A2 = Md5UpdateAndGetString(md,beforeMd5String("GET",authUri));
        String stuff = beforeMd5String(A1,nonce,nc,cNonce,qop,A2);
        String resp = Md5UpdateAndGetString(md,stuff);
        String authKey = getAuthKey(id, authUri, realm, nonce, cNonce, qop, nc, opaque, resp);

        return authKey;
    }

    private String getAuthKey(String id, String authUri, String realm, String nonce, String cNonce, String qop, String nc, String opaque, String resp) {
        return "Digest username=\"" + id + "\", realm=\"" + realm + "\", nonce=\"" + nonce + "\", uri=\"" + authUri + "\", response=\"" + resp + "\", opaque=\"" + opaque + "\", qop=" + qop + ", nc=" + nc + ", cnonce=\"" + cNonce + "\"";
    }

    public String cutEqualMark(String splitVal){
        return splitVal.split("=")[1].replaceAll("\"","");
    }

    //변환전 string
    public String beforeMd5String(String... strings){
        StringBuilder sb = new StringBuilder();
        for (String string : strings) {
            sb.append(string+":");
        }
        sb.setLength(sb.length()-1);
        return sb.toString();
    }


    public String Md5UpdateAndGetString(MessageDigest md, String bfStr){
        StringBuilder sb = new StringBuilder();
        md.update( bfStr.getBytes());
        byte[] a1Digest = md.digest();
        for (int i = 0; i < a1Digest.length; i++) {
            sb.append(Integer.toString((a1Digest[i] & 0xff) + 0x100, 16).substring(1));
        }
        md.reset();
        return sb.toString();
    }

    public static void main(String[] args) throws URISyntaxException, IOException, ProtocolException, NoSuchAlgorithmException, DigestCallException {

        DigestAuth da = new DigestAuth();

        HashMap<String,String> paramMap = new HashMap<>();
        paramMap.put("name","june");
        paramMap.put("age","28");

        da.request("http://localhost:8080",paramMap,"");




    }
}
