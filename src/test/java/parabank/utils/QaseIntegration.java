package parabank.utils;

import okhttp3.*;

import java.io.IOException;

public class QaseIntegration {
    private static final String BASE_URL = "https://api.qase.io/v1/result/";
    private static final String API_KEY = "666e47d0bb56368994728f43ede1426772b0ad04390753cc476f8c3bfa80c3a9"; // Zastąp swoim kluczem API Qase

    private final OkHttpClient client;

    public QaseIntegration() {
        this.client = new OkHttpClient();
    }

    public void updateTestCaseStatus(String testCaseId, String status, String project, String runId) throws Exception {
        // Budowanie URL na podstawie przekazanych parametrów

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\"status\":\""+status+"\",\"case_id\":"+testCaseId+"}");
        Request request = new Request.Builder()
                .url(BASE_URL +project+"/"+runId+"")
                .post(body)
                .addHeader("accept", "application/json")
                .addHeader("content-type", "application/json")
                .addHeader("Token", API_KEY)
                .build();

        Response response = client.newCall(request).execute();


        if (response.isSuccessful()) {

            String responseBody = response.body().string();
        } else {

            throw new IOException("Błąd żądania: " + response.code() + " - " + response.message());
        }
    }
}